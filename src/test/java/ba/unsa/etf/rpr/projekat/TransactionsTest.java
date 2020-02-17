package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionsTest {

    @Test
    void constructorAndGetters() {
        LocalDateTime dateTime = LocalDateTime.now();
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        sales.add(new Sale(3, "milk", 10, dateTime, 2.45, 24.5));
        sales.add(new Sale(1, "chips", 10, dateTime, 2.65, 26.5));
        orders.add(new Order(2, "bread", 100, dateTime, 1.5, 150));
        Transactions transactions = new Transactions(sales, orders);

         assertEquals(2.65 , transactions.getSales().get(1).getPrice());
         assertEquals("bread", transactions.getOrders().get(0).getProductName());
    }

    @Test
    void setters() {
        LocalDateTime dateTime = LocalDateTime.now();
        Transactions transactions = new Transactions();
        ArrayList<Sale> sales = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        sales.add(new Sale(3, "flour", 10, dateTime, 5.45, 54.5));
        sales.add(new Sale(1, "eggs", 10, dateTime, 7.65, 76.5));
        orders.add(new Order(2, "bread", 100, dateTime, 1.5, 150));
        transactions.setSales(sales);
        transactions.setOrders(orders);

        assertEquals("eggs", transactions.getSales().get(1).getProductName());
        assertEquals(1, transactions.getOrders().size());
    }
}
