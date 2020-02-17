package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleTest {
    @Test
    void constructorAndGetters() {
        LocalDateTime dateTime = LocalDateTime.now();
        Sale sale = new Sale(3, "milk", 10, dateTime, 2.45, 24.5);

        assertEquals(3, sale.getId());
        assertEquals("milk", sale.getProductName());
        assertEquals(10, sale.getSoldQuantity());
        assertEquals(dateTime, sale.getSaleDate());
        assertEquals(2.45, sale.getPrice());
        assertEquals(24.5, sale.getTotalPrice());
    }

    @Test
    void setters() {
        LocalDateTime dateTime = LocalDateTime.now();
        Sale sale = new Sale();
        sale.setId(2);
        sale.setProductName("potatoes");
        sale.setSoldQuantity(100);
        sale.setSaleDate(dateTime);
        sale.setPrice(3.55);
        sale.setTotalPrice(355);

        assertEquals(2, sale.getId());
        assertEquals("potatoes", sale.getProductName());
        assertEquals(100, sale.getSoldQuantity());
        assertEquals(dateTime, sale.getSaleDate());
        assertEquals(3.55, sale.getPrice());
        assertEquals(355, sale.getTotalPrice());
    }

    @Test
    void toStringTest() {
        LocalDateTime dateTime = LocalDateTime.now();
        Sale sale = new Sale(1, "chips", 10, dateTime, 2.45, 24.5);
        String actualText = "" + sale;
        String expectedTextUS = "Product name: chips, sold quantity: 10, sale date: " + dateTime + ", price of product: 2.45, total amount sold: 24.5";
        String expectedTextBS = "Naziv artikla: chips, prodana količina: 10, datum i vrijeme prodaje: " + dateTime + ", cijena artikla: 2.45, ukupna cijena: 24.5";

        if (Locale.getDefault().getCountry().equals("US")) {
            assertEquals(expectedTextUS, actualText);
        } else {
            assertEquals(expectedTextBS, actualText);
        }
    }
}
