package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.auth.ProfileAuthRequest;
import pudding.toy.ourJourney.dto.profile.*;
import pudding.toy.ourJourney.entity.Comment;
import pudding.toy.ourJourney.entity.ContentLike;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.CommentRepository;
import pudding.toy.ourJourney.repository.ContentLikeRepository;
import pudding.toy.ourJourney.repository.ContentRepository;
import pudding.toy.ourJourney.repository.ProfileRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;
    private final ContentLikeRepository contentLikeRepository;


    public NewProfileResponse createProfile(ProfileAuthRequest profileAuthRequest) {
        profileRepository.findByUserId(profileAuthRequest.getId()).ifPresent(profile -> {
            throw new IllegalStateException("프로필이 존재합니다.");
        });
        Profile profile = Profile.builder().userId(profileAuthRequest.getId()).build();
        String defaultNickName = defaultNickName(profile);
        profile.defaultName(defaultNickName);

        profileRepository.save(profile);
        return new NewProfileResponse(profile.getId(), profile.getNickName());
    }

    private String defaultNickName(Profile profile) {
        boolean isDuplicate;
        String randomNickName;
        do {
            randomNickName = profile.createRandomNickName();
            isDuplicate = profileRepository.existsByNickName(randomNickName);
        } while (isDuplicate);
        return randomNickName;
    }

    public GetDetailProfileResponse getDetailProfile(Long id, Optional<Profile> getProfile) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 유저가 없습니다.")
        );
        boolean isEditable = getProfile.filter(value -> profile.getId().equals(value.getId())).isPresent();
        return new GetDetailProfileResponse(
                profile.getId(), profile.getNickName(), Optional.ofNullable(profile.getProfileImg()),
                Optional.ofNullable(profile.getSelfIntroduction()), isEditable,
                profile.followerNum(), profile.followingNum()
        );
    }

    public GetDetailProfileResponse getMyDetailProfile(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 유저가 없습니다.")
        );
        return new GetDetailProfileResponse(
                profile.getId(), profile.getNickName(), Optional.ofNullable(profile.getProfileImg()),
                Optional.ofNullable(profile.getSelfIntroduction()), true,
                profile.followerNum(), profile.followingNum()
        );
    }

    public void updateMyProfile(Long id, UpdateProfileRequest updateProfileRequest) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 유저가 없습니다.")
        );
        if (updateProfileRequest.getImageUrl() != null) {
            updateProfileRequest.getImageUrl().ifPresent(profile::setProfileImg);
        }
        if (updateProfileRequest.getNickname() != null) {
            updateProfileRequest.getNickname().ifPresent(profile::setNickName);
        }
        if (updateProfileRequest.getSelfIntroduction() != null) {
            updateProfileRequest.getSelfIntroduction().ifPresent(profile::setSelfIntroduction);
        }
        profileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 유저가 없습니다.")
        );
        profile.remove(LocalDateTime.now());
        profileRepository.save(profile);
    }

    public GetMyContentsResponse getMyContents(Long profileId, Pageable pageable) {
        Page<Contents> contents = contentRepository.findAllByProfileId(profileId, pageable);
        long totalElements = contents.getTotalElements();
        List<GetMyContentsDto> contentsDto = contents.stream()
                .map(content -> new GetMyContentsDto(
                        content.getId(), content.getTitle(), content.getProfile().getId(),
                        content.getImgUrl(), content.getCreatedAt(), content.getUpdatedAt()))
                .toList();
        PageImpl<GetMyContentsDto> myContentsDtoPage = new PageImpl<>(contentsDto, pageable, totalElements);
        return new GetMyContentsResponse(myContentsDtoPage);
    }

    public GetMyCommentsResponse getMyComments(Long profileId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findAllByProfileId(profileId, pageable);
        long totalElements = comments.getTotalElements();
        List<GetMyCommentsDto> getMyCommentsDtos = comments.stream()
                .map(comment -> new GetMyCommentsDto(comment.getId(), comment.getTexts(), comment.getProfile().getId(), comment.getCreatedAt(), comment.getUpdatedAt()))
                .toList();
        PageImpl<GetMyCommentsDto> myCommentsDtoPage = new PageImpl<>(getMyCommentsDtos, pageable, totalElements);
        return new GetMyCommentsResponse(myCommentsDtoPage);
    }

    public GetLikeContentsResponse getMyLikeContents(Long profileId, Pageable pageable) {
        Page<ContentLike> contentLikes = contentLikeRepository.findAllByProfileId(profileId, pageable);
        long totalElements = contentLikes.getTotalElements();
        List<Long> contentLikesContent = contentLikes.get()
                .map(ContentLike::getContents)
                .map(Contents::getId)
                .toList();
        List<Contents> contents = contentRepository.findAllByIdIn(contentLikesContent);
        List<GetLikesContentsDto> getLikesContentsDtos = contents.stream()
                .map(content -> new GetLikesContentsDto(content.getId(), content.getTitle(), content.getProfile().getId(),
                        content.getImgUrl(), content.getCreatedAt(), content.getUpdatedAt()))
                .toList();
        return new GetLikeContentsResponse(new PageImpl<>(getLikesContentsDtos, pageable, totalElements));
    }
}
