package pudding.toy.ourJourney.dto.category;

import lombok.Data;

import java.util.List;

@Data
public class GetCategoriesResponse {
    List<CategoryDto> categoryDtos;

}
