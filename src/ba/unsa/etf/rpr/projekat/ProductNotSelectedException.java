package ba.unsa.etf.rpr.projekat;

import javafx.scene.control.Alert;

import java.util.Locale;

public class ProductNotSelectedException extends Exception {
    public ProductNotSelectedException() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (Locale.getDefault().getCountry().equals("bs")) {
            alert.setTitle("Uputa");
            alert.setHeaderText("Proizvod nije selektovan");
            alert.setContentText("Kako bi prodali/nabavili/obrisali/izmijenili artikal trebate ga prvobitno odabrati iz liste artikala!");
        } else {
            alert.setTitle("Information");
            alert.setHeaderText("Product not selected");
            alert.setContentText("In order to sell/order/delete/modify a product you need to select it from the table of products!");
        }

        alert.showAndWait();
    }
}
