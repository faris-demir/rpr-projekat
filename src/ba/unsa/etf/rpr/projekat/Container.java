package ba.unsa.etf.rpr.projekat;

import java.util.ArrayList;

public class Container {
    private String tag;
    private ArrayList<Product> products;

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
