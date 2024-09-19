package pudding.toy.ourJourney.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pudding.toy.ourJourney.dto.profile.GetDetailProfileResponse;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.FollowRepository;
import pudding.toy.ourJourney.repository.ProfileRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class FollowServiceTest {
    @Mock
    ProfileRepository profileRepository;
    @InjectMocks
    ProfileService profileService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void isBackFollowTest(){
        FollowRepository followRepository = Mockito.mock(FollowRepository.class);
        ProfileRepository profileRepository = Mockito.mock(ProfileRepository.class);
        FollowService followService = new FollowService(followRepository,profileRepository);
        when(followRepository.existsByFollowerIdAndFollowingId(1L,2L)).thenReturn(true);
        when(followRepository.existsByFollowerIdAndFollowingId(2L,1L)).thenReturn(true);
        Assertions.assertThat(followService.isMutualFollow(1L, 2L)).isTrue();
    }
    @Test
    void isFollowingTest(){
        Profile profile = Mockito.mock(Profile.class); //mock 객체 생성

        when(profile.followingNum()).thenReturn(1);
        when(profile.getId()).thenReturn(1L);
        when(profile.followingNum()).thenReturn(1);
        when(profile.followerNum()).thenReturn(1);
        when(profile.getNickName()).thenReturn("배고픈 무지3");
        when(profileRepository.findById(anyLong())).thenReturn(Optional.of(profile));

        GetDetailProfileResponse getDetailProfileResponse = profileService.getDetailProfile(profile.getId());
        Assertions.assertThat(getDetailProfileResponse.getFollowerNum()).isEqualTo(1);
        Assertions.assertThat(getDetailProfileResponse.getFollowingNum()).isEqualTo(1);
        Assertions.assertThat(getDetailProfileResponse.getNickname()).isEqualTo("배고픈 무지3");
    }


}
