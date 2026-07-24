public class SearchExample {

    public static void main(String[] args) {

        // Sorted array of products
        Product[] products = {

                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Mobile", "Electronics"),
                new Product(103, "Shoes", "Fashion"),
                new Product(104, "Watch", "Accessories"),
                new Product(105, "Tablet", "Electronics"),
                new Product(106, "Headphones", "Electronics"),
                new Product(107, "Keyboard", "Computer"),
                new Product(108, "Mouse", "Computer")
        };

        int searchId = 104;

        // Linear Search
        System.out.println("===== Linear Search =====");

        Product result1 = SearchAlgorithms.linearSearch(products, searchId);

        if (result1 != null) {
            System.out.println("Product Found!");
            result1.display();
        } else {
            System.out.println("Product Not Found.");
        }

        System.out.println();

        // Binary Search
        System.out.println("===== Binary Search =====");

        Product result2 = SearchAlgorithms.binarySearch(products, searchId);

        if (result2 != null) {
            System.out.println("Product Found!");
            result2.display();
        } else {
            System.out.println("Product Not Found.");
        }
    }
}