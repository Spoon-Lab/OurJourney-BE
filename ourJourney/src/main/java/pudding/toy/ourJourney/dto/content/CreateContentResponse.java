package pudding.toy.ourJourney.dto.content;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateContentResponse {
    private Long id;
    public CreateContentResponse(Long id){
        this.id = id;
    }
}
