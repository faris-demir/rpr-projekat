package ba.unsa.etf.rpr.projekat;

public class Product {
    private String name;
    private int quantity;
    private double weight;
    private Unit unit;
    private double packageHeight;
    private double packageWidth;
    private String serialNumber;
    private String locationTag;
    private double purchasePrice;
    private double sellingPrice;
    private int id;

    public Product(String name, int quantity, double weight, Unit unit, double packageHeight,
                   double packageWidth, String serialNumber, String locationTag,
                   double purchasePrice, double sellingPrice, int id) {
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.unit = unit;
        this.packageHeight = packageHeight;
        this.packageWidth = packageWidth;
        this.serialNumber = serialNumber;
        this.locationTag = locationTag;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = Unit.valueOf(unit);
    }

    public double getPackageHeight() {
        return packageHeight;
    }

    public void setPackageHeight(double packageHeight) {
        this.packageHeight = packageHeight;
    }

    public double getPackageWidth() {
        return packageWidth;
    }

    public void setPackageWidth(double packageWidth) {
        this.packageWidth = packageWidth;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLocationTag() {
        return locationTag;
    }

    public void setLocationTag(String locationTag) {
        this.locationTag = locationTag;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
