package ba.unsa.etf.rpr.projekat;

import java.time.LocalDateTime;

public class Sale extends Transactions {
    private int id;
    private String productName;
    private int soldQuantity;
    private LocalDateTime saleDate;
    private double price;
    private double totalPrice;

    public Sale() {
    }

    public Sale(int id, String productName, int soldQuantity, LocalDateTime saleDate, double price, double totalPrice) {
        this.id = id;
        this.productName = productName;
        this.soldQuantity = soldQuantity;
        this.saleDate = saleDate;
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

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
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

    @Override
    public String toString() {
        return "Product name: " + getProductName() + ", sold qunatity: " + getSoldQuantity() + ", sale date: " + getSaleDate() +
                ", price of product: " + getPrice() + ", total amount sold: " + getTotalPrice();
    }
}
