package ba.unsa.etf.rpr.projekat;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseModelTest {

    @Test
    void konstruktor() {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        WarehouseModel m = new WarehouseModel();
        assertNull(m.getCurrentProduct());
        assertTrue(m.getSectors().isEmpty());
    }

    @Test
    void load() {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        WarehouseModel model = new WarehouseModel();
        model.loadData();
        ObservableList<Product> products = model.getProductsDB();
        assertEquals(5, products.size());
        assertNull(model.getCurrentProduct());
    }

    @Test
    void insertOrder() {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        LocalDateTime dateTime = LocalDateTime.now();
        WarehouseModel model = new WarehouseModel();
        model.loadData();
        Order order = new Order(1, "milk", 10, dateTime, 2.45, 24.5);
        model.insertOrderDB(order);

        ArrayList<Order> orders = model.getOrdersDB();

        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals("milk", orders.get(0).getProductName());
    }

    @Test
    void insertSale() {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        LocalDateTime dateTime = LocalDateTime.now();
        WarehouseModel model = new WarehouseModel();
        model.loadData();
        Sale sale = new Sale(1, "bread", 10, dateTime, 2.45, 24.5);
        model.insertSaleDB(sale);

        ArrayList<Sale> sales = model.getSalesDB();

        assertNotNull(sales);
        assertEquals(1, sales.size());
        assertEquals("bread", sales.get(0).getProductName());
    }

    @Test
    void transactions() {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        LocalDateTime dateTime = LocalDateTime.now();
        WarehouseModel model = new WarehouseModel();
        model.loadData();
        Sale sale = new Sale(1, "bread", 10, dateTime, 2.45, 24.5);
        Order order = new Order(1, "milk", 10, dateTime, 2.45, 24.5);
        model.addSale(sale);
        model.addOrder(order);

        assertEquals(1, model.getTransactions().getOrders().size());
        assertEquals(1, model.getTransactions().getSales().size());
        assertEquals("bread", model.getTransactions().getSales().get(0).getProductName());

        Transactions transactions = new Transactions();
        model.setTransactions(transactions);

        assertEquals(0, model.getTransactions().getSales().size());
        assertEquals(0, model.getTransactions().getOrders().size());
    }
}
