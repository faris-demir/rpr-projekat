package ba.unsa.etf.rpr.projekat;

import javafx.scene.control.Alert;

public class IllegalSerialNumber extends Error {
    public IllegalSerialNumber() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Illegal serial number");
        alert.setContentText("You have entered a serial number that already exists! Please try with another one.");
        alert.showAndWait();
    }
}
