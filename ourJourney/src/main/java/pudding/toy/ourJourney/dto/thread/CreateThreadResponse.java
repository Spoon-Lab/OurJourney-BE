package pudding.toy.ourJourney.dto.thread;

import lombok.Data;

@Data
public class CreateThreadResponse {
    private Long threadId;

    public CreateThreadResponse(Long threadId) {
        this.threadId = threadId;
    }
}
