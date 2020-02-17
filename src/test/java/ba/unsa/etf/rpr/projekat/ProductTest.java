package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    void constructorAndGetters() {
        Product product = new Product("MacBook Pro", 3, 1.9, Unit.kilogram,
                0.2, 0.7, "AKCS123SWC5", "A3",
                4500, 5200, 1);

        assertEquals("MacBook Pro", product.getName());
        assertEquals(3, product.getQuantity());
        assertEquals(1.9, product.getWeight());
        assertEquals(Unit.kilogram, product.getUnit());
        assertEquals(0.2, product.getPackageHeight());
        assertEquals(0.7, product.getPackageWidth());
        assertEquals("AKCS123SWC5", product.getSerialNumber());
        assertEquals("A3", product.getLocationTag());
        assertEquals(4500, product.getPurchasePrice());
        assertEquals(5200, product.getSellingPrice());
        assertEquals(1, product.getId());
    }

    @Test
    void setters() {
        Product product = new Product();
        product.setId(1);
        product.setName("MacBook Pro");
        product.setQuantity(3);
        product.setWeight(1.9);
        product.setUnit(Unit.kilogram.toString());
        product.setPackageHeight(0.2);
        product.setPackageWidth(0.7);
        product.setSerialNumber("AKCS123SWC5");
        product.setLocationTag("A3");
        product.setPurchasePrice(4500);
        product.setSellingPrice(5200);

        assertEquals("MacBook Pro", product.getName());
        assertEquals(3, product.getQuantity());
        assertEquals(1.9, product.getWeight());
        assertEquals(Unit.kilogram, product.getUnit());
        assertEquals(0.2, product.getPackageHeight());
        assertEquals(0.7, product.getPackageWidth());
        assertEquals("AKCS123SWC5", product.getSerialNumber());
        assertEquals("A3", product.getLocationTag());
        assertEquals(4500, product.getPurchasePrice());
        assertEquals(5200, product.getSellingPrice());
        assertEquals(1, product.getId());
    }
}
