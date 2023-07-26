import domain.dataModel.*;
import domain.enums.ClientType;
import domain.enums.ProductCategory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Product> products;
    private static List<Order> orders;
    private static List<Customer> customers;

    public static void main(String[] args) {
        customers = new ArrayList<>();
        initializeData();
        displayMenu();
    }

    private static void applyDiscountToToys() {
        System.out.println("----- Applying 10% Discount to Toys -----");
        List<Product> toys = filterProductsByCategory(ProductCategory.TOYS);
        List<Product> discountedToys = toys.stream()
                .map(product -> new Product(
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getPrice() * 0.9
                ))
                .collect(Collectors.toList());

        displayProducts(discountedToys);
    }
    private static void displayMostRecentOrders() {
        System.out.println("----- 3 Most Recent Orders -----");
        List<Order> mostRecentOrders = getMostRecentOrders(3);
        for (Order order : mostRecentOrders) {
            System.out.println(order);
        }
        System.out.println();
    }
    private static List<Order> getMostRecentOrders(int n) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("----- Menu -----");
            System.out.println("1. Show products");
            System.out.println("2. Show customers");
            System.out.println("3. Show orders");
            System.out.println("4. Filter products by category");
            System.out.println("5. Apply discount to toys");
            System.out.println("6. Show a list of orders made by client 2 between february 1st 2021 and april 1st 2021");
            System.out.println("7. Show the cheapest product");
            System.out.println("8. Show the 3 most recent products");
            System.out.println("9. Get most expensive product by category");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayProducts(products);
                    break;
                case 2:
                    displayCustomers();
                    break;
                case 3:
                    displayOrders();
                    break;
                case 4:
                    System.out.print("Enter the category to filter (BOOKS, BABIES, TOYS): ");
                    String categoryName = scanner.next();
                    try {
                        ProductCategory category = ProductCategory.fromCategoryName(categoryName);
                        List<Product> filteredProducts = filterProductsByCategory(category);
                        displayProducts(filteredProducts);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category name. Please try again.");
                    }
                    break;
                case 5:
                    applyDiscountToToys();
                    break;
                case 6:
                    displayOrdersByCustomerLevel2();
                    break;
                case 7:
                    List<Product> cheapestBooks = getCheapestProductsInCategory(ProductCategory.BOOKS);
                    displayProducts(cheapestBooks);
                    break;
                case 8:displayMostRecentOrders();
                break;
                case 9:System.out.println("Enter the category to display the most expensive product (BOOKS, BABIES, TOYS):");
                    String categorySelected = scanner.next();
                    try {
                        ProductCategory category = ProductCategory.fromCategoryName(categorySelected);
                        Product mostExpensiveProduct = getMostExpensiveProductByCategory(category);
                        if (mostExpensiveProduct != null) {
                            displayProducts(Collections.singletonList(mostExpensiveProduct));
                        } else {
                            System.out.println("No product found in the specified category.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category.");
                    }
                    break;
                case 10:
                    System.out.println("Closing Menu");
                    break;
                default:
                    System.out.println("Wrong option, please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

    private static void displayProducts(List<Product> products) {
        System.out.println("----- Products -----");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println();
    }

    private static void displayOrders() {
        System.out.println("----- Orders -----");
        for (Order order : orders) {
            System.out.println(order);
        }
        System.out.println();
    }

    private static void displayCustomers() {
        System.out.println("----- Clients -----");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println();
    }

    private static void initializeData() {
        customers = new ArrayList<>();
        customers.add(new Customer(1L, "Client 1", ClientType.LEVEL_1));
        customers.add(new Customer(2L, "Client 2", ClientType.LEVEL_2));
        customers.add(new Customer(3L, "Client 3", ClientType.LEVEL_3));

        products = new ArrayList<>();
        products.add(new Product(1L, "La Odisea", ProductCategory.BOOKS, 80.00));
        products.add(new Product(2L, "100 años de soledad", ProductCategory.BOOKS, 60.00));
        products.add(new Product(3L, "Tetero", ProductCategory.BABIES, 12.99));
        products.add(new Product(4L, "Baby shoes", ProductCategory.BABIES, 22.99));
        products.add(new Product(5L, "Hotwheels", ProductCategory.TOYS, 9.99));
        products.add(new Product(6L, "Spiderman toy", ProductCategory.TOYS, 39.99));

        orders = new ArrayList<>();
        orders.add(new Order(1L, "Pending", LocalDate.of(2022, 3, 1), LocalDate.of(2022, 3, 5), Arrays.asList(products.get(0), products.get(3)), customers.get(0)));
        orders.add(new Order(2L, "Sent", LocalDate.of(2022, 3, 2), LocalDate.of(2022, 3, 10), Arrays.asList(products.get(1), products.get(4)), customers.get(1)));
        orders.add(new Order(3L, "Pending", LocalDate.of(2022, 3, 3), LocalDate.of(2022, 3, 15), Arrays.asList(products.get(2), products.get(3), products.get(4)), customers.get(2)));
        orders.add(new Order(4L, "Sent", LocalDate.of(2021, 2, 18), LocalDate.of(2021, 2, 20), Arrays.asList(products.get(2), products.get(3), products.get(4)), customers.get(2)));
        orders.add(new Order(5L, "Sent", LocalDate.of(2021, 3, 5), LocalDate.of(2021, 3, 10), Arrays.asList(products.get(0), products.get(5)), customers.get(1)));

    }

    private static List<Product> filterProductsByCategory(ProductCategory category) {
        return products.stream()
                .filter(product -> product.getCategory() == category)
                .collect(Collectors.toList());
    }

    private static void displayOrdersByCustomerLevel2() {
        System.out.println("----- Orders by Customer Level 2 (between Feb 1, 2021, and Apr 1, 2021) -----");
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);
        List<Order> ordersByCustomerLevel2 = getOrdersByCustomerLevel2(startDate, endDate);
        for (Order order : ordersByCustomerLevel2) {
            System.out.println(order);
        }
        System.out.println();
    }

    private static List<Order> getOrdersByCustomerLevel2(LocalDate startDate, LocalDate endDate) {
        return orders.stream()
                .filter(order -> order.getCustomer().getTier() == ClientType.LEVEL_2 &&
                        order.getOrderDate().isAfter(startDate) &&
                        order.getOrderDate().isBefore(endDate))
                .collect(Collectors.toList());
    }
    private static List<Product> getCheapestProductsInCategory(ProductCategory category) {
        return products.stream()
                .filter(product -> product.getCategory() == category)
                .min(Comparator.comparingDouble(Product::getPrice))
                .map(minProduct -> products.stream()
                        .filter(product -> product.getPrice() == minProduct.getPrice())
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }
    private static Product getMostExpensiveProductByCategory(ProductCategory category) {
        return products.stream()
                .filter(product -> product.getCategory() == category)
                .max(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
    }

}








