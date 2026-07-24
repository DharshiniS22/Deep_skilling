public class Product {

    int productId;
    String productName;
    String category;

    // Constructor
    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    // Display Product Details
    public void display() {
        System.out.println("Product ID   : " + productId);
        System.out.println("Product Name : " + productName);
        System.out.println("Category     : " + category);
    }
}