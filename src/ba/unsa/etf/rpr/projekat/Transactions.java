package ba.unsa.etf.rpr.projekat;

import java.util.ArrayList;

public class Transactions {
    private ArrayList<Sale> sales;
    private ArrayList<Order> orders;

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
