package pudding.toy.ourJourney.thread.dto;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class CreateThreadRequest {
    String texts;
    Optional<List<Long>> tagIds;
    Optional<String> threadImg;
}
