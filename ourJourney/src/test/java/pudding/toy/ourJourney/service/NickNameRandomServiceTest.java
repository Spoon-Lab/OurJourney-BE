package pudding.toy.ourJourney.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pudding.toy.ourJourney.dto.auth.ProfileAuthRequest;
import pudding.toy.ourJourney.dto.profile.NewProfileResponse;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.ProfileRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class NickNameRandomServiceTest {
    @Mock
    ProfileRepository profileRepository;
    @InjectMocks
    ProfileService profileService;
    @Mock
    Profile profile1;
    @Mock
    Profile profile2;

    @BeforeEach
    void setUp() {
        profile1 = Mockito.spy(Profile.builder().userId(2L).build());
        profile2 = Mockito.spy(Profile.builder().userId(3L).build());
        MockitoAnnotations.openMocks(this); //mock 객체 초기화.
    }

    @Test
    @DisplayName("닉네임 중복 없이 프로필 생성")
    void createNoDuplicateNickName() {
        ProfileAuthRequest profileAuthRequest = new ProfileAuthRequest(1L);
        Profile profile = Profile.builder().userId(profileAuthRequest.getId()).build();

        when(profileRepository.existsByNickName(anyString())).thenReturn(false); //중복 X
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        NewProfileResponse response = profileService.createProfile(profileAuthRequest);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(profile.getNickName(), response.getNickName());
        verify(profileRepository).save(any(Profile.class)); // 저장이 호출되었는지 확인
    }

    @Test
    @DisplayName("닉네임 중복 있는 프로필 생성")
    void createDuplicateName() {
        ProfileAuthRequest request = new ProfileAuthRequest(2L);
        Profile profile = Profile.builder().userId(request.getId()).build();

        when(profileRepository.existsByNickName(anyString())).thenReturn(true).thenReturn(false);
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        NewProfileResponse response = profileService.createProfile(request);
        Assertions.assertNotNull(response);

        verify(profileRepository,times(2)).existsByNickName(anyString()); //두번 호출?
    }
}
