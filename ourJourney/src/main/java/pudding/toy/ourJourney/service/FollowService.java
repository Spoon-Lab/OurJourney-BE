package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.entity.Follow;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.FollowRepository;
import pudding.toy.ourJourney.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final ProfileRepository profileRepository;
    public void followProfile(Profile follower, Long followId){
        Profile following = profileRepository.findById(followId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if(followRepository.existsByFollowerIdAndFollowingId(follower.getId(),following.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        Follow follow = new Follow(follower,following);
        if(isFollowBack(follower.getId(),followId)){
            System.out.println("맞팔되었어요^_^");
        }

        followRepository.save(follow);
    }
    public void unFollow(Profile follower, Long followId){
        Profile following = profileRepository.findById(followId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if(!followRepository.existsByFollowerIdAndFollowingId(follower.getId(),following.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Follow follow = followRepository.findByFollowerIdAndFollowingId(follower.getId(),following.getId())
                .orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
        followRepository.delete(follow);
    }
    public boolean isFollowBack(Long followerId,Long followId){
        if(followRepository.existsByFollowerIdAndFollowingId(followerId,followId)
        && followRepository.existsByFollowerIdAndFollowingId(followId,followerId)){
            return true;
        }
        return false;
    }
}
