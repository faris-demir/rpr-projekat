package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static ba.unsa.etf.rpr.projekat.ProductDAO.getProductInstance;

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
    private Product productToRegister = null;
    private Product productToModify = null;

    public Product getProductToRegister() {
        return productToRegister;
    }

    public RegisterController(Product productToModify) {
        this.productToModify = productToModify;
    }

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

        fldName.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                fldName.getStyleClass().removeAll("notValidField");
                fldName.getStyleClass().add("validField");
            } else {
                fldName.getStyleClass().removeAll("validField");
                fldName.getStyleClass().add("notValidField");
            }
        }));

        fldQuantity.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                fldQuantity.getStyleClass().removeAll("notValidField");
                fldQuantity.getStyleClass().add("validField");
            } else {
                fldQuantity.getStyleClass().removeAll("validField");
                fldQuantity.getStyleClass().add("notValidField");
            }
        }));

        fldWeight.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                fldWeight.getStyleClass().removeAll("notValidField");
                fldWeight.getStyleClass().add("validField");
            } else {
                fldWeight.getStyleClass().removeAll("validField");
                fldWeight.getStyleClass().add("notValidField");
            }
        }));

        fldHeight.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                fldHeight.getStyleClass().removeAll("notValidField");
                fldHeight.getStyleClass().add("validField");
            } else {
                fldHeight.getStyleClass().removeAll("validField");
                fldHeight.getStyleClass().add("notValidField");
            }
        }));

        fldWidth.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                fldWidth.getStyleClass().removeAll("notValidField");
                fldWidth.getStyleClass().add("validField");
            } else {
                fldWidth.getStyleClass().removeAll("validField");
                fldWidth.getStyleClass().add("notValidField");
            }
        }));

        fldSerial.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                fldSerial.getStyleClass().removeAll("notValidField");
                fldSerial.getStyleClass().add("validField");
            } else {
                fldSerial.getStyleClass().removeAll("validField");
                fldSerial.getStyleClass().add("notValidField");
            }
        }));

        fldPurchasePrice.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                fldPurchasePrice.getStyleClass().removeAll("notValidField");
                fldPurchasePrice.getStyleClass().add("validField");
            } else {
                fldPurchasePrice.getStyleClass().removeAll("validField");
                fldPurchasePrice.getStyleClass().add("notValidField");
            }
        }));

        fldSellingPrice.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                fldSellingPrice.getStyleClass().removeAll("notValidField");
                fldSellingPrice.getStyleClass().add("validField");
            } else {
                fldSellingPrice.getStyleClass().removeAll("validField");
                fldSellingPrice.getStyleClass().add("notValidField");
            }
        }));
    }

    public boolean readyToRegister() {
        return fldName.getStyleClass().contains("validField") && fldQuantity.getStyleClass().contains("validField") && fldWeight.getStyleClass().contains("validField") &&
                fldHeight.getStyleClass().contains("validField") && fldWidth.getStyleClass().contains("validField") && fldSerial.getStyleClass().contains("validField") &&
                fldPurchasePrice.getStyleClass().contains("validField") && fldSellingPrice.getStyleClass().contains("validField");
    }

    public void confirmAction(ActionEvent actionEvent) {
        if (productToModify != null) {

        }
        if (readyToRegister()) {
            productToRegister = new Product();
            productToRegister.setName(fldName.getText());
            productToRegister.setQuantity(Integer.parseInt(fldQuantity.getText()));
            productToRegister.setWeight(Double.parseDouble(fldWeight.getText()));
            productToRegister.setUnit(cbUnit.getSelectionModel().getSelectedItem());
            productToRegister.setPackageHeight(Double.parseDouble(fldHeight.getText()));
            productToRegister.setPackageWidth(Double.parseDouble(fldWidth.getText()));
            productToRegister.setSerialNumber(fldSerial.getText());
            productToRegister.setLocationTag(spnSector.getValue() + spnContainer.getValue());
            productToRegister.setPurchasePrice(Double.parseDouble(fldPurchasePrice.getText()));
            productToRegister.setSellingPrice(Double.parseDouble(fldSellingPrice.getText()));
            productToRegister.setId(getProductInstance().getMaxProductId());
            Stage currentStage = (Stage) fldName.getScene().getWindow();
            currentStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error while registering new product");
            alert.setContentText("All fields must be filled with the right data in order to successfully register a new product!");
            alert.showAndWait();
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage currentStage = (Stage) fldName.getScene().getWindow();
        currentStage.close();
    }
}
