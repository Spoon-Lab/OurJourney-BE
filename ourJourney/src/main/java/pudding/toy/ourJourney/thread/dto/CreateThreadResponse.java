package pudding.toy.ourJourney.thread.dto;

import lombok.Data;

@Data
public class CreateThreadResponse {
    private Long threadId;

    public CreateThreadResponse(Long threadId) {
        this.threadId = threadId;
    }
}
