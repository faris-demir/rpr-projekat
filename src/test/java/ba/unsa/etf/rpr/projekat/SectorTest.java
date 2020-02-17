package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectorTest {
    @Test
    void constructorAndGetters() {
        Sector sector = new Sector(4, "B", 9);

        assertEquals(4, sector.getId());
        assertEquals("B", sector.getTag());
        assertEquals(9, sector.getCapacity());
    }

    @Test
    void setters() {
        Sector sector = new Sector();
        ArrayList<Container> containers = new ArrayList<>();
        containers.add(new Container(1, "2", 3000));
        sector.setContainers(containers);
        sector.setCapacity(9);
        sector.setId(1);
        sector.setTag("D");

        assertEquals(1, sector.getId());
        assertEquals("D", sector.getTag());
        assertEquals(9, sector.getCapacity());
        assertEquals(1, sector.getContainers().size());
    }
}
