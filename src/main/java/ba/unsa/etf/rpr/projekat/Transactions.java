package ba.unsa.etf.rpr.projekat;

import java.util.ArrayList;

public class Transactions {
    private ArrayList<Sale> sales = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public Transactions() {
    }

    public Transactions(ArrayList<Sale> sales, ArrayList<Order> orders) {
        this.sales = sales;
        this.orders = orders;
    }

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
