package domain.enums;

public enum ProductCategory {
    BOOKS,
    TOYS {
        @Override
        public double applyDiscount(double price) {
            return price * 0.9;
        }
    },
    BABIES;

    public double applyDiscount(double price) {
        return price;
    }

    public static ProductCategory fromCategoryName(String categoryName) {
        for (ProductCategory category : ProductCategory.values()) {
            if (category.name().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category: " + categoryName);
    }
}
