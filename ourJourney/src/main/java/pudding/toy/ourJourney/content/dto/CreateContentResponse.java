package pudding.toy.ourJourney.content.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class CreateContentResponse {
    private Long id;

    public CreateContentResponse(Long id) {
        this.id = id;
    }
}
