package pudding.toy.ourJourney.attendee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.attendee.entity.Attendee;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.profile.entity.Profile;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    Boolean existsAttendeeByContentsAndProfile(Contents content, Profile profile);
}
