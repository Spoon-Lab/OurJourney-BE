package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.auth.ProfileAuthRequest;
import pudding.toy.ourJourney.dto.profile.GetDetailProfileResponse;
import pudding.toy.ourJourney.dto.profile.NewProfileResponse;
import pudding.toy.ourJourney.dto.profile.UpdateProfileRequest;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.mapper.UpdateProfileMapper;
import pudding.toy.ourJourney.repository.ProfileRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UpdateProfileMapper updateProfileMapper;
    public NewProfileResponse createProfile(ProfileAuthRequest profileAuthRequest) {
        profileRepository.findByUserId(profileAuthRequest.getId()).ifPresent(profile -> {
            throw new IllegalStateException("프로필이 존재합니다.");
        });
        Profile profile = Profile.builder().userId(profileAuthRequest.getId()).build();
        profileRepository.save(profile);
        return new NewProfileResponse(profile.getId(),profile.getNickName());
    }
    public GetDetailProfileResponse getDetailProfile(Long id){
        Profile profile = profileRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return new GetDetailProfileResponse(profile.getId(), profile.getNickName(), Optional.ofNullable(profile.getProfileImg()),Optional.ofNullable(profile.getSelfIntroduction()));
    }
    public void updateMyProfile(Long id, UpdateProfileRequest updateProfileRequest){
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


}
