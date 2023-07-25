package domain.enums;

public enum ProductCategory {
    BOOKS("Books"),
    TOYS("Toys"),
    BABIES("Babies");

    private final String categoryName;

    ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public static ProductCategory fromCategoryName(String categoryName) {
        for (ProductCategory category : ProductCategory.values()) {
            if (category.getCategoryName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category: " + categoryName);
    }

}
