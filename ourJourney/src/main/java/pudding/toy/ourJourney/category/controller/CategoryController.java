package pudding.toy.ourJourney.category.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pudding.toy.ourJourney.category.dto.GetCategoriesResponse;
import pudding.toy.ourJourney.category.service.CategoryService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Category API", description = "Category API 입니다.")
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    @Operation(summary = "category 목록 보기", description = "category의 모든 목록을 본다.")
    public GetCategoriesResponse getAllCategories() {
        return new GetCategoriesResponse(categoryService.getGetCategories());
    }
}
