package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerTest {
    @Test
    void constructorAndGetters() {
        Manager manager = new Manager("fdemir1", "zamger");

        assertEquals("fdemir1", manager.getUsername());
        assertEquals("zamger", manager.getPassword());
    }

    @Test
    void setters() {
        Manager manager = new Manager();
        manager.setUsername("vljubovic");
        manager.setPassword("javarocks");

        assertEquals("vljubovic", manager.getUsername());
        assertEquals("javarocks", manager.getPassword());
    }
}
