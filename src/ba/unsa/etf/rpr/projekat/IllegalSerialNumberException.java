package ba.unsa.etf.rpr.projekat;

import javafx.scene.control.Alert;

import java.util.Locale;

public class IllegalSerialNumberException extends Exception {
    public IllegalSerialNumberException() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (Locale.getDefault().getCountry().equals("US")) {
            alert.setTitle("Error");
            alert.setHeaderText("Illegal serial number");
            alert.setContentText("You have entered a serial number that already exists! Please try another one.");
        } else {
            alert.setTitle("Greška");
            alert.setHeaderText("Zabranjen serijski broj");
            alert.setContentText("Unijeli ste serijski broj koji je već registrovan! Molimo pokušajte neki drugi.");
        }
        alert.showAndWait();
    }
}
