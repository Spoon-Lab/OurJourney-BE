package pudding.toy.ourJourney.dto.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdateThreadRequest {
    String texts;
    List<Long> tags;
    String threadImg;
}
