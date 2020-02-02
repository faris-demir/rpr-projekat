package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WarehouseModel {
    private ObservableList<Sector> sectors = FXCollections.observableArrayList();
    private SimpleObjectProperty<Product> currentProduct = new SimpleObjectProperty<>();

    public ObservableList<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(ObservableList<Sector> sectors) {
        this.sectors = sectors;
    }

    public Product getCurrentProduct() {
        return currentProduct.get();
    }

    public SimpleObjectProperty<Product> currentProductProperty() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct.set(currentProduct);
    }
}
