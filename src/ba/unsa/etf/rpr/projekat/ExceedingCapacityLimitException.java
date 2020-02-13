package ba.unsa.etf.rpr.projekat;

import javafx.scene.control.Alert;

public class ExceedingCapacityLimitException extends Exception {
    public ExceedingCapacityLimitException() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Exceeded container capacity limit");
        alert.setContentText("The product you wish to register exceeds the capacity of the currently selected container. " +
                            "Please try with another container or try accommodating the package dimensions.");
        alert.showAndWait();
    }
}
