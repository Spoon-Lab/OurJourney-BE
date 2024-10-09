package pudding.toy.ourJourney.content.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.attendee.AttendeeRepository;
import pudding.toy.ourJourney.attendee.entity.Attendee;
import pudding.toy.ourJourney.category.entity.Category;
import pudding.toy.ourJourney.category.repository.CategoryRepository;
import pudding.toy.ourJourney.comment.CommentRepository;
import pudding.toy.ourJourney.content.dto.CreateContentRequest;
import pudding.toy.ourJourney.content.dto.DetailContentResponse;
import pudding.toy.ourJourney.content.dto.ListContentDto;
import pudding.toy.ourJourney.content.dto.UpdateContentRequest;
import pudding.toy.ourJourney.content.entity.ContentLike;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.content.repository.ContentLikeRepository;
import pudding.toy.ourJourney.content.repository.ContentRepository;
import pudding.toy.ourJourney.content.repository.ContentsQueryRepository;
import pudding.toy.ourJourney.profile.entity.Profile;
import pudding.toy.ourJourney.profile.repository.ProfileRepository;
import pudding.toy.ourJourney.tags.entity.ContentTag;
import pudding.toy.ourJourney.tags.entity.Tag;
import pudding.toy.ourJourney.tags.repository.ContentTagRepository;
import pudding.toy.ourJourney.tags.repository.TagRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService {
    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
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
        Boolean isLiked = false;
        if (!profile.isEmpty()) {
            isLiked = contents.getContentLikes()
                    .stream()
                    .anyMatch(contentLikes -> contentLikes.getProfile().equals(profile.get()));
        }

        List<Tag> tags = contents.getContentTags().stream()
                .map(ContentTag::getTag)
                .toList();

        return DetailContentResponse.from(contents, likeCount, tags, totalCount, isLiked, isEditable, isRemovable);
    }

    public void updateContent(Long contentId, UpdateContentRequest editRequestDto, Profile profile) {
        Contents contents = contentRepository.findById(contentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if (!contents.getProfile().equals(profile)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "수정 권한이 없습니다.");
        }
        Optional.ofNullable(editRequestDto.getTitle())
                .ifPresent(contents::setTitle);

        Optional.ofNullable(editRequestDto.getImgUrl())
                .ifPresent(contents::setImgUrl);

        updateContentTags(editRequestDto, contents);

        contentRepository.save(contents);
    }

    private void updateContentTags(UpdateContentRequest editRequestDto, Contents contents) {
        Optional.ofNullable(editRequestDto.getTags())
                .ifPresent(newTags -> {
                    //기존에 있던 태그
                    Optional<List<ContentTag>> existingContentTags = contentTagRepository.findAllByContents(contents);
                    if (existingContentTags.isPresent()) {
                        createNewTagsAndDeleteOldTags(contents, newTags, existingContentTags);
                        return;
                    }
                    createNewUpdateContentTags(contents, newTags, existingContentTags);

                });
    }

    private void createNewTagsAndDeleteOldTags(Contents contents, List<Long> newTags, Optional<List<ContentTag>> existingContentTags) {
        List<Tag> newTagEntities = tagRepository.findAllById(newTags).stream()
                .filter(tag -> !existingContentTags.equals(tag))
                .toList();
        List<ContentTag> tagsToDelete = existingContentTags.get().stream()
                .filter(contentTag -> !newTagEntities.equals(contentTag))
                .toList();
        contentTagRepository.deleteAll(tagsToDelete);
        List<ContentTag> contentTags = newTagEntities.stream()
                .map(tag -> new ContentTag(contents, tag))
                .toList();
        contentTagRepository.saveAll(contentTags);
    }

    private void createNewUpdateContentTags(Contents contents, List<Long> newTags, Optional<List<ContentTag>> existingContentTags) {
        List<Tag> newTagEntities = tagRepository.findAllById(newTags).stream()
                .filter(tag -> !existingContentTags.equals(tag))
                .toList();
        List<ContentTag> contentTags = newTagEntities.stream()
                .map(tag -> new ContentTag(contents, tag))
                .toList();
        contentTagRepository.saveAll(contentTags);
    }

    public void deleteContent(Long contentId, Profile profile) {
        Contents contents = contentRepository.findById(contentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if (!contents.getProfile().equals(profile)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "삭제 권한이 없습니다.");
        }
        contents.remove(LocalDateTime.now());
        contents.getContentLikes()
                .forEach(cl -> cl.remove(LocalDateTime.now()));
        contentRepository.save(contents);
    }

    public Long addLikesToContent(Long contentId, Profile profile) {
        Contents content = contentRepository.findById(contentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if (contentLikeRepository.existsByContentsAndProfile(content, profile)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT); //이미 좋아요 처리
        }
        ContentLike contentLike = new ContentLike(content, profile);
        contentLikeRepository.save(contentLike);
        return contentLike.getId();
    }

    public void deleteLike(Long contentId, Profile profile) {
        Contents content = contentRepository.findById(contentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        ContentLike contentLike = contentLikeRepository.findByContentsAndProfile(content, profile).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        contentLike.remove(LocalDateTime.now());
    }

    public void deleteContentTag(Long contentId, ContentTag contentTag) {
        Contents content = contentRepository.findById(contentId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        ContentTag tag = contentTagRepository.findByContents(content).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        contentTagRepository.delete(tag);
    }

}
