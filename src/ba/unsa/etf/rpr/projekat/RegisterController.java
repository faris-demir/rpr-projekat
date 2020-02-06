package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    public TextField fldName;
    public TextField fldQuantity;
    public TextField fldWeight;
    public ChoiceBox<String> cbUnit;
    public TextField fldHeight;
    public TextField fldWidth;
    public TextField fldSerial;
    public Spinner<String> spnSector;
    public Spinner<Integer> spnContainer;
    public TextField fldPurchasePrice;
    public TextField fldSellingPrice;

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> containerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,9,1);
        spnContainer.setValueFactory(containerValueFactory);

        ObservableList<String> sectorObsList = FXCollections.observableArrayList();
        sectorObsList.addAll("A","B","C","D","E","F","G","H");
        SpinnerValueFactory<String> sectorValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(sectorObsList);
        spnSector.setValueFactory(sectorValueFactory);

        ObservableList<String> unitObsList = FXCollections.observableArrayList();
        unitObsList.addAll("tonne","kilogram","gram","liter","milliliter","gallon","barrel","ounce","pound");
        cbUnit.setItems(unitObsList);
        cbUnit.setValue("tonne");
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage currentStage = (Stage) fldName.getScene().getWindow();
        currentStage.close();
    }
}
