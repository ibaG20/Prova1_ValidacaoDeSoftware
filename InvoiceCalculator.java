import java.util.List;

class InvalidProductException extends RuntimeException {
    public InvalidProductException(String message) {
        super(message);
    }
}

class Product {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        if (price < 0 || quantity < 0) {
            throw new InvalidProductException("Product price or quantity cannot be negative.");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class InvoiceCalculator {

    public static double calculateInvoice(List<Product> products, double discount) {
        double total = 0.0;

        // Calculating total price
        for (Product product : products) {
            total += product.price * product.quantity;
        }

        // Apply discount
        total -= total * (discount / 100);

        // Additional discount if total exceeds R$ 1,000.00
        if (total > 1000.0) {
            total -= 100.0;
        }

        return total;
    }
}
