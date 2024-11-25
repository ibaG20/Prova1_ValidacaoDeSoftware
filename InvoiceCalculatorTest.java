import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class InvoiceCalculatorTest {

    @Test
    void shouldCalculateTotalWithoutAdditionalDiscount() {
        List<Product> products = List.of(
            new Product("Product A", 100.0, 2),
            new Product("Product B", 50.0, 4)
        );
        double discount = 10.0;

        double result = InvoiceCalculator.calculateInvoice(products, discount);
        assertEquals(450.0, result, 0.01);
    }

    @Test
    void shouldCalculateTotalWithAdditionalDiscount() {
        List<Product> products = List.of(
            new Product("Product A", 600.0, 2),
            new Product("Product B", 200.0, 3)
        );
        double discount = 10.0;

        double result = InvoiceCalculator.calculateInvoice(products, discount);
        assertEquals(1890.0, result, 0.01);
    }

    @Test
    void shouldThrowExceptionForNegativeQuantity() {
        List<Product> products = List.of(
            new Product("Product A", 100.0, -2)
        );
        double discount = 10.0;

        assertThrows(InvalidProductException.class, 
            () -> InvoiceCalculator.calculateInvoice(products, discount));
    }

    @Test
    void shouldThrowExceptionForNegativePrice() {
        List<Product> products = List.of(
            new Product("Product A", -50.0, 2)
        );
        double discount = 10.0;

        assertThrows(InvalidProductException.class, 
            () -> InvoiceCalculator.calculateInvoice(products, discount));
    }

    @Test
    void shouldReturnZeroForEmptyProductList() {
        List<Product> products = List.of();
        double discount = 10.0;

        double result = InvoiceCalculator.calculateInvoice(products, discount);
        assertEquals(0.0, result, 0.01);
    }
}
