package pudding.toy.ourJourney.thread.dto;

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
