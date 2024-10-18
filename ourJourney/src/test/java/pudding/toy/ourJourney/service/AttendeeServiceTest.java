package pudding.toy.ourJourney.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.attendee.entity.Attendee;
import pudding.toy.ourJourney.attendee.repository.AttendeeRepository;
import pudding.toy.ourJourney.category.entity.Category;
import pudding.toy.ourJourney.category.repository.CategoryRepository;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.content.repository.ContentRepository;
import pudding.toy.ourJourney.profile.entity.Profile;
import pudding.toy.ourJourney.profile.repository.ProfileRepository;
import pudding.toy.ourJourney.tags.entity.ContentTag;

@Transactional
@SpringBootTest
public class AttendeeServiceTest {
    @Autowired
    AttendeeRepository attendeeRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    CategoryRepository categoryRepository;
    Contents content;
    Profile profile;
    Attendee attendee;


    @BeforeEach
    public void setUp() {
        profile = new Profile(3L);
        Category category = new Category("hhhh");
        categoryRepository.save(category);
        profileRepository.save(profile);
        content = new Contents("title", category, new ContentTag(), profile);
        contentRepository.save(content);
        attendee = new Attendee(profile, content);
        attendeeRepository.save(attendee);
    }

    @Test
    void exitsByContentAndProfileTest() {
        boolean status = attendeeRepository.existsAttendeeByContentsAndProfile(content, profile);
        System.out.println(status);
    }
}
