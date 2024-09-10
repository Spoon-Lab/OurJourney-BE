package pudding.toy.ourJourney.dto.thread;

import lombok.Data;

import java.util.List;

@Data
public class CreateThreadRequest {
    String texts;
    List<Long> tags;
    String threadImg;

}
