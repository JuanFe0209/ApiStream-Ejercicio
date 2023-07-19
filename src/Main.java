import domain.dataModel.Customer;
import domain.dataModel.Order;
import domain.dataModel.Product;
import domain.enums.ProductCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Product> products;
    private static List<Customer> customers;

    public static void main(String[] args) {
        initializeData();
        displayMenu();
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("----- Menu -----");
            System.out.println("1. Show the productos");
            System.out.println("2. Show the clients");
            System.out.println("3. Filter products");
            System.out.println("4. Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    displayCustomers();
                    break;
                case 3:
                    System.out.println("Closing Menu");
                    break;
                default:
                    System.out.println("Wrong option, please try again");
            }
        } while (choice != 3);
        scanner.close();
    }

    private static void displayProducts() {
        System.out.println("----- Products -----");
        for (Product product : products) {
            System.out.println(product);
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
        products = new ArrayList<>();
        products.add(new Product(1L, "La Odisea", ProductCategory.BOOKS, 80.00));
        products.add(new Product(2L, "100 años de soledad", ProductCategory.BOOKS, 60.00));
        products.add(new Product(3L, "Tetero", ProductCategory.BABIES, 12.99));
        products.add(new Product(4L, "Baby shoes", ProductCategory.BABIES, 22.99));
        products.add(new Product(5L, "Hotwheels", ProductCategory.TOYS,9.99));
        products.add(new Product(6L, "Spiderman toy", ProductCategory.TOYS, 39.99));

        customers = new ArrayList<>();
        customers.add(new Customer(1L, "Client 1", 1));
        customers.add(new Customer(2L, "Client 2", 2));
    }
}

