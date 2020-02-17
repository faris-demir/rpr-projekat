package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    @Test
    void constructorAndGetters() {
        LocalDateTime dateTime = LocalDateTime.now();
        Order order = new Order(1, "milk", 10, dateTime, 2.45, 24.5);

        assertEquals(1, order.getId());
        assertEquals("milk", order.getProductName());
        assertEquals(10, order.getOrderedQuantity());
        assertEquals(dateTime, order.getOrderDate());
        assertEquals(2.45, order.getPrice());
        assertEquals(24.5, order.getTotalPrice());
    }

    @Test
    void setters() {
        LocalDateTime dateTime = LocalDateTime.now();
        Order order = new Order();
        order.setId(4);
        order.setProductName("bread");
        order.setOrderedQuantity(100);
        order.setOrderDate(dateTime);
        order.setPrice(3.55);
        order.setTotalPrice(355);

        assertEquals(4, order.getId());
        assertEquals("bread", order.getProductName());
        assertEquals(100, order.getOrderedQuantity());
        assertEquals(dateTime, order.getOrderDate());
        assertEquals(3.55, order.getPrice());
        assertEquals(355, order.getTotalPrice());
    }

    @Test
    void toStringTest() {
        LocalDateTime dateTime = LocalDateTime.now();
        Order order = new Order(1, "milk", 10, dateTime, 2.45, 24.5);
        String actualText = "" + order;
        String expectedTextUS = "Product name: milk, ordered quantity: 10, order date: " + dateTime + ", price of product: 2.45, total price of order: 24.5";
        String expectedTextBS = "Naziv artikla: milk, naručena količina: 10, datum i vrijeme narudžbe: " + dateTime + ", cijena artikla: 2.45, ukupna cijena narudžbe: 24.5";

        if (Locale.getDefault().getCountry().equals("US")) {
            assertEquals(expectedTextUS, actualText);
        } else {
            assertEquals(expectedTextBS, actualText);
        }
    }
}
