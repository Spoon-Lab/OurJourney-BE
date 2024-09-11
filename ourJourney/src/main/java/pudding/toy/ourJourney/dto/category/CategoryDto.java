package pudding.toy.ourJourney.dto.category;

import lombok.Data;

@Data
public record CategoryDto(
        Long categoryId,
        String categoryName
) {
}
