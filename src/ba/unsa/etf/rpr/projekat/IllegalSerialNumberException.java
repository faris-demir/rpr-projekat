package ba.unsa.etf.rpr.projekat;

import javafx.scene.control.Alert;

public class IllegalSerialNumberException extends Exception {
    public IllegalSerialNumberException() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Illegal serial number");
        alert.setContentText("You have entered a serial number that already exists! Please try another one.");
        alert.showAndWait();
    }
}
