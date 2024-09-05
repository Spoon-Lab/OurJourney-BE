package pudding.toy.ourJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long>{
}
