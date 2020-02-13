package ba.unsa.etf.rpr.projekat;

import javafx.scene.control.Alert;

import java.util.Locale;

public class ExceedingCapacityLimitException extends Exception {
    public ExceedingCapacityLimitException() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (Locale.getDefault().getCountry().equals("bs")) {
            alert.setTitle("Greška");
            alert.setHeaderText("Premašen kapacitet kontejnera");
            alert.setContentText("Paket artikla kojeg želite dodati premašuje kapacitet trenutno odabranog kontejnera za skladištenje. " +
                    "Pokušajte sa drugim kontejnerom ili prilagodite dimenzije paketa.");
        } else {
            alert.setTitle("Error");
            alert.setHeaderText("Exceeded container capacity limit");
            alert.setContentText("The packaging of the product you wish to register exceeds the capacity of the currently selected container. " +
                    "Please try with another container or try accommodating the package dimensions.");
        }

        alert.showAndWait();
    }
}
