package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.content.CreateContentRequest;
import pudding.toy.ourJourney.dto.content.DetailContentResponse;
import pudding.toy.ourJourney.dto.content.ListContentDto;
import pudding.toy.ourJourney.dto.content.UpdateContentRequest;
import pudding.toy.ourJourney.entity.*;
import pudding.toy.ourJourney.mapper.UpdateContentsMapper;
import pudding.toy.ourJourney.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService {
    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final UpdateContentsMapper contentsMapper;
    private final AttendeeRepository attendeeRepository;
    private final TagRepository tagRepository;
    private final ContentTagRepository contentTagRepository;
    private final ContentsQueryRepository contentsQueryRepository;
    private final ContentLikeRepository contentLikeRepository;
    private final ProfileRepository profileRepository;
    private final CommentRepository commentRepository;

    public PageImpl<ListContentDto> getAllContents(
            Pageable pageable,
            Optional<Long> categoryId,
            Optional<String> title,
            Optional<List<Long>> tagIds) {
        return contentsQueryRepository.findAll(pageable, categoryId, title, tagIds);
    }

    public Long createContent(CreateContentRequest createContentRequest, Profile profile) {
        Category category = categoryRepository.findById(createContentRequest.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Contents content = Contents.builder()
                .title(createContentRequest.getTitle())
                .profile(profile)
                .category(category)
                .build();
        contentRepository.save(content);

        createContentRequest.getImgUrl().ifPresent(content::setImgUrl);
        createContentRequest.getAttendeeIds()
                .filter(profileIds -> !profileIds.isEmpty())
                .ifPresent(profileIds -> addAttendee(profileIds, content));

        createContentRequest.getTagIds()
                .filter(tagIds -> !tagIds.isEmpty())
                .ifPresent(tagIds -> addContentTag(tagIds, content));

        contentRepository.save(content);

        return content.getId();
    }


    private void addAttendee(List<Long> profileId, Contents content) {
        List<Profile> profiles = profileRepository.findAllById(profileId);
        if (profiles.size() != profileId.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 유저가 없습니다.");
        }
        List<Attendee> attendees = profiles.stream()
                .map(profile -> new Attendee(profile, content))
                .toList();
        attendeeRepository.saveAll(attendees);
    }

    private void addContentTag(List<Long> tagIds, Contents content) {
        List<Tag> tags = tagRepository.findAllById(tagIds);
        if (tagIds.size() != tags.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 태그가 없습니다.");
        }
        List<ContentTag> contentTags = tags.stream()
                .map(tag -> new ContentTag(content, tag))
                .toList();
        contentTagRepository.saveAll(contentTags);
    }

    public DetailContentResponse getDetailContent(Long contentId, Optional<Profile> profile) {
        Contents contents = contentRepository.findById(contentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 글이 없습니다.")
        );
        Long likeCount = contentLikeRepository.countByContentsId(contentId);
        Long totalCount = commentRepository.countByContentsIdAndDeletedAtIsNull(contentId);

        Boolean isEditable = profile.filter(value -> contents.getProfile().getId().equals(value.getId())).isPresent();
        Boolean isRemovable = profile.filter(value -> contents.getProfile().getId().equals(value.getId())).isPresent();

        return DetailContentResponse.from(contents, likeCount, totalCount, isEditable, isRemovable);
    }

    public void updateContent(Long contentId, UpdateContentRequest editRequestDto) {
        Contents contents = contentRepository.findById(contentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        contentsMapper.updateEntityFromDto(editRequestDto, contents);
        contentRepository.save(contents);
    }

    public void deleteContent(Long contentId) {
        Contents contents = contentRepository.findById(contentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        contents.remove(LocalDateTime.now());
        contentRepository.save(contents);
    }

    public Long addLikesToContent(Long contentId, Profile profile) {
        Contents content = contentRepository.findById(contentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        ContentLike contentLike = new ContentLike(content, profile);
        contentLikeRepository.save(contentLike);
        if (contentLikeRepository.existsByContentsAndProfile(content, profile)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT); //이미 좋아요 처리
        }
        return contentLike.getId();
    }

    public void deleteLike(Long contentId, Profile profile) {
        Contents content = contentRepository.findById(contentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        ContentLike contentLike = contentLikeRepository.findByContentsAndProfile(content, profile).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        contentLikeRepository.delete(contentLike);
    }
}
