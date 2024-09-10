package pudding.toy.ourJourney.dto.thread;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class EditThreadRequest {
    Optional<String> texts;
    Optional<List<Long>> tags;
    Optional<String> threadImg; //todo: JsonNullable
}
