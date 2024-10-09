package pudding.toy.ourJourney.category.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetCategoriesResponse {
    List<CategoryDto> categoryDtos;

    public GetCategoriesResponse(List<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }
}
