package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.content.CreateContentRequest;
import pudding.toy.ourJourney.dto.content.DetailContentResponse;
import pudding.toy.ourJourney.dto.content.EditContentRequest;
import pudding.toy.ourJourney.dto.content.ListContentDto;
import pudding.toy.ourJourney.entity.Attendee;
import pudding.toy.ourJourney.entity.Category;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.mapper.EditContentsMapper;
import pudding.toy.ourJourney.repository.AttendeeRepository;
import pudding.toy.ourJourney.repository.CategoryRepository;
import pudding.toy.ourJourney.repository.ContentRepository;
import pudding.toy.ourJourney.repository.ProfileRepository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendeeService {
    private final ContentRepository contentRepository;
    private final AttendeeRepository attendeeRepository;
    private final ProfileRepository profileRepository;
    public boolean isAttendee(Long profileId,Long contentId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        Contents content = contentRepository.findById(profileId).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return attendeeRepository.existsAttendeeByContentsAndProfile(content,profile);
    }

    public void addAttendee(List<Long> profileId,Contents content) {
        List<Profile> profiles = profileRepository.findAllById(profileId);
        if(profiles.size()!=profileId.size()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<Attendee> attendees = profiles.stream()
                .map(profile -> new Attendee(profile,content))
                .toList();
        attendeeRepository.saveAll(attendees);
    }

    public void getDetailAttendee(Long attendeeId) {
        //attendee's profile
    }

    public void updateAttendee(String nickName) {
    }
}
