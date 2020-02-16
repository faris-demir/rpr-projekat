package ba.unsa.etf.rpr.projekat;

import java.util.ArrayList;

public class Container {
    private String tag;
    private ArrayList<Product> products = new ArrayList<>();
    private int id;
    private double capacity;

    public Container(int id, String tag, double capacity) {
        this.tag = tag;
        this.id = id;
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
