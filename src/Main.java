import domain.dataModel.Customer;
import domain.dataModel.Order;
import domain.dataModel.Product;
import domain.enums.ProductCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
            System.out.println("6. Exit");
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
                case 5:applyDiscountToToys();
                    break;
                case 6:
                    System.out.println("Closing Menu");
                    break;
                default:
                    System.out.println("Wrong option, please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
        private static void displayProducts (List<Product> products) {
            System.out.println("----- Products -----");
            for (Product product : Main.products) {
                System.out.println(product);
            }
            System.out.println();
        }
        private static void displayOrders () {
            System.out.println("----- Orders -----");
            for (Order order : orders) {
                System.out.println(order);
            }
            System.out.println();
        }
        private static void displayCustomers () {
            System.out.println("----- Clients -----");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
            System.out.println();
        }
        private static void initializeData () {

            customers = new ArrayList<>();
            customers.add(new Customer(1L, "Client 1", 1));
            customers.add(new Customer(2L, "Client 2", 2));
            customers.add(new Customer(3L, "Client 3", 3));

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

        }
    private static List<Product> filterProductsByCategory(ProductCategory category) {
        return products.stream()
                .filter(product -> product.getCategory() == category)
                .collect(Collectors.toList());
    }
}







