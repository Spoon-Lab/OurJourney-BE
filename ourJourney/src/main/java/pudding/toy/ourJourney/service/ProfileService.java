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
import pudding.toy.ourJourney.entity.*;
import pudding.toy.ourJourney.mapper.UpdateProfileMapper;
import pudding.toy.ourJourney.repository.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UpdateProfileMapper updateProfileMapper;
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
        return new NewProfileResponse(profile.getId(),profile.getNickName());
    }
    private String defaultNickName(Profile profile){
        boolean isDuplicate;
        String randomNickName;
        do{
            randomNickName = profile.createRandomNickName();
            isDuplicate = profileRepository.existsByNickName(randomNickName);
        }while(isDuplicate);
        return randomNickName;
    }
    public GetDetailProfileResponse getDetailProfile(Long id){
        Profile profile = profileRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return new GetDetailProfileResponse(
                profile.getId(), profile.getNickName(), Optional.ofNullable(profile.getProfileImg()),
                Optional.ofNullable(profile.getSelfIntroduction()),
                profile.followerNum(), profile.followingNum()
        );
    }
    public void updateMyProfile(Long id, UpdateProfileRequest updateProfileRequest){
        //todo: login_required && is_owner?
        Profile profile = profileRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        updateProfileMapper.updateEntityFromDto(updateProfileRequest,profile);
    }
    public void deleteProfile(Long id){
        //todo: login_required && is_owner?
        Profile profile = profileRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        profile.remove(LocalDateTime.now());
        profileRepository.save(profile);
    }
    public GetMyContentsResponse getMyContents(Long profileId, Pageable pageable){
        //todo: login_required && is_owner?
        Page<Contents> contents = contentRepository.findAllByProfileId(profileId,pageable);
        List <GetMyContentsDto> contentsDto = contents.stream()
                .map(content -> new GetMyContentsDto(
                        content.getId(),content.getTitle(),content.getProfile().getId(),
                        content.getImgUrl(),content.getCreatedAt(),content.getUpdateAt()))
                .toList();
        PageImpl<GetMyContentsDto> myContentsDtoPage = new PageImpl<>(contentsDto,pageable,contentsDto.size());
        return new GetMyContentsResponse(myContentsDtoPage);
    }
    public GetMyCommentsResponse getMyComments(Long profileId, Pageable pageable){
        //todo: login_required && is_owner?
       Page<Comment> comments =  commentRepository.findAllByProfileId(profileId,pageable);

       List<GetMyCommentsDto> getMyCommentsDtos = comments.stream()
               .map(comment -> new GetMyCommentsDto(comment.getId(),comment.getTexts(), comment.getProfile().getId(), comment.getCreatedAt(),comment.getUpdateAt()))
               .toList();
       PageImpl<GetMyCommentsDto> myCommentsDtoPage = new PageImpl<>(getMyCommentsDtos,pageable,getMyCommentsDtos.size());
        return new GetMyCommentsResponse(myCommentsDtoPage);
    }
    public GetLikeContentsResponse getMyLikeContents(Long profileId,Pageable pageable) {
        //todo: login_required && is_owner?
        Page<Long> contentLikesId = contentLikeRepository.findAllByProfileId(profileId, pageable);
        List<Long> contentLikes = contentLikesId.getContent();
        Page<Contents> contents = contentRepository.findAllByContentLikesIdIn(contentLikes, pageable);
        List<GetLikesContentsDto> getLikesContentsDtos = contents.stream()
                .map(content -> new GetLikesContentsDto(content.getId(), content.getTitle(), content.getProfile().getId(),
                        content.getImgUrl(), content.getCreatedAt(), content.getUpdateAt()))
                .toList();
        return new GetLikeContentsResponse(new PageImpl<>(getLikesContentsDtos, pageable, getLikesContentsDtos.size()));
    }
}
