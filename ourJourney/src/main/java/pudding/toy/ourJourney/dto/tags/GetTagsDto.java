package pudding.toy.ourJourney.dto.tags;

import lombok.Data;

@Data
public class GetTagsDto {
    private Long tagId;
    private String tagName;

    public GetTagsDto(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
