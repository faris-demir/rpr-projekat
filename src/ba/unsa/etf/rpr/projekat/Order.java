package ba.unsa.etf.rpr.projekat;

import java.time.LocalDateTime;

public class Order extends Transactions {
    private int id;
    private String productName;
    private int orderedQuantity;
    private LocalDateTime orderDate;
    private double price;
    private double totalPrice;

    public Order(int id, String productName, int orderedQuantity, LocalDateTime orderDate, double price, double totalPrice) {
        this.id = id;
        this.productName = productName;
        this.orderedQuantity = orderedQuantity;
        this.orderDate = orderDate;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
