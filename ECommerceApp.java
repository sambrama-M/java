import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ECommerceApp {
    private static List<Product> productCatalog = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static int orderCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeCatalog();

        while (true) {
            System.out.println("Welcome to E-Commerce App!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerCustomer(scanner);
                    break;
                case 2:
                    loginCustomer(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the app!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void initializeCatalog() {
        productCatalog.add(new Product(1, "Laptop", 1200.00));
        productCatalog.add(new Product(2, "Smartphone", 800.00));
        productCatalog.add(new Product(3, "Headphones", 150.00));
    }

    private static void registerCustomer(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your email: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        customers.add(new Customer(customers.size() + 1, name, email, password));
        System.out.println("Registration successful!");
    }

    private static void loginCustomer(Scanner scanner) {
        System.out.print("Enter your email: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.validatePassword(password)) {
                System.out.println("Login successful!");
                customerMenu(scanner, customer);
                return;
            }
        }
        System.out.println("Invalid email or password.");
    }

    private static void customerMenu(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1. View Products");
            System.out.println("2. Place Order");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    placeOrder(scanner, customer);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void viewProducts() {
        System.out.println("Product Catalog:");
        for (Product product : productCatalog) {
            System.out.println(product);
        }
    }

    private static void placeOrder(Scanner scanner, Customer customer) {
        Order order = new Order(orderCounter++, customer);
        while (true) {
            System.out.print("Enter Product ID to add to the order (0 to finish): ");
            int productId = scanner.nextInt();

            if (productId == 0) break;

            Product product = findProductById(productId);
            if (product != null) {
                order.addProduct(product);
                System.out.println("Product added to the order.");
            } else {
                System.out.println("Invalid Product ID.");
            }
        }
        orders.add(order);
        System.out.println("Order placed successfully!");
        order.viewOrderDetails();
    }

    private static Product findProductById(int productId) {
        for (Product product : productCatalog) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
}

