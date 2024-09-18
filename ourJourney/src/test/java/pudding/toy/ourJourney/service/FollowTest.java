package pudding.toy.ourJourney.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import pudding.toy.ourJourney.repository.FollowRepository;
import pudding.toy.ourJourney.repository.ProfileRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FollowTest {

    @Test
    void followTest(){
        FollowRepository followRepository = Mockito.mock(FollowRepository.class);
        ProfileRepository profileRepository = Mockito.mock(ProfileRepository.class);
        FollowService followService = new FollowService(followRepository,profileRepository);
        when(followRepository.existsByFollowerIdAndFollowingId(1L,2L)).thenReturn(true);
        when(followRepository.existsByFollowerIdAndFollowingId(2L,1L)).thenReturn(true);
        //
        Assertions.assertThat(followService.isFollowBack(1L, 2L)).isTrue();

    }



}
