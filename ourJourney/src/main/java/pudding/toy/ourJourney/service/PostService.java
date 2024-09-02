package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.dto.django.ProfileAuthResponseDto;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.PostRepository;
import pudding.toy.ourJourney.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

}
