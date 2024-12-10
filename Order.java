import java.util.ArrayList;
import java.util.List;

class Order {
    private int orderId;
    private Customer customer;
    private List<Product> productList;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void viewOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Products:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}
