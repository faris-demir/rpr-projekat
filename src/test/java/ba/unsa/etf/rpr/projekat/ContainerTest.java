package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerTest {
    @Test
    void constructorAndGetters() {
        Container newContainer = new Container(1, "2", 3000);

        assertEquals(1, newContainer.getId());
        assertEquals("2", newContainer.getTag());
        assertEquals(3000, newContainer.getCapacity());
    }

    @Test
    void setters() {
        Container newContainer = new Container();
        newContainer.setProducts(new ArrayList<>());
        newContainer.setId(1);
        newContainer.setCapacity(1000);
        newContainer.setTag("9");

        assertEquals(1, newContainer.getId());
        assertEquals("9", newContainer.getTag());
        assertEquals(1000, newContainer.getCapacity());
        assertEquals(0, newContainer.getProducts().size());
    }

}
