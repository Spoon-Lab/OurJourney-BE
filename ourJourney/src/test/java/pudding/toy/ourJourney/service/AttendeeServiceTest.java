package pudding.toy.ourJourney.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.entity.*;
import pudding.toy.ourJourney.repository.AttendeeRepository;
import pudding.toy.ourJourney.repository.CategoryRepository;
import pudding.toy.ourJourney.repository.ContentRepository;
import pudding.toy.ourJourney.repository.ProfileRepository;

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
        profile = new Profile(3L,"hi");
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
