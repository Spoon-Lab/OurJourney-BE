package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.repository.ContentRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final ContentRepository contentRepository;

}
