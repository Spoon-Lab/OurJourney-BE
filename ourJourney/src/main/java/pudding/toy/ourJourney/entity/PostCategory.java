package pudding.toy.ourJourney.entity;

public enum PostCategory {
    FOREIGN("국외"),
    KOREA("국내");
    private final String categoryName;

    PostCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
