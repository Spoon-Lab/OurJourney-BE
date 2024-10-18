package pudding.toy.ourJourney.attendee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.attendee.entity.Attendee;
import pudding.toy.ourJourney.attendee.repository.AttendeeRepository;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.content.repository.ContentRepository;
import pudding.toy.ourJourney.profile.entity.Profile;
import pudding.toy.ourJourney.profile.repository.ProfileRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendeeService {
    private final ContentRepository contentRepository;
    private final AttendeeRepository attendeeRepository;
    private final ProfileRepository profileRepository;

    public boolean isAttendee(Long profileId, Long contentId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        Contents content = contentRepository.findById(profileId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return attendeeRepository.existsAttendeeByContentsAndProfile(content, profile);
    }

    public void addAttendee(List<Long> profileId, Contents content) {
        List<Profile> profiles = profileRepository.findAllById(profileId);
        if (profiles.size() != profileId.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<Attendee> attendees = profiles.stream()
                .map(profile -> new Attendee(profile, content))
                .toList();
        attendeeRepository.saveAll(attendees);
    }

    public void getDetailAttendee(Long attendeeId) {
        //attendee's profile
    }

    public void updateAttendee(String nickName) {
    }
}
