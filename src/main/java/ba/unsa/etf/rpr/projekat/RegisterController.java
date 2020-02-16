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

import static ba.unsa.etf.rpr.projekat.ProductDAO.getProductInstance;

public class RegisterController {
    public TextField fldName;
    public TextField fldQuantity;
    public TextField fldWeight;
    public ChoiceBox<Unit> cbUnit;
    public TextField fldHeight;
    public TextField fldWidth;
    public TextField fldSerial;
    public Spinner<String> spnSector;
    public Spinner<Integer> spnContainer;
    public TextField fldPurchasePrice;
    public TextField fldSellingPrice;
    private Product productToRegister = null;
    private Product productToModify = null;
    private ObservableList<Product> products = FXCollections.observableArrayList();
    private ObservableList<Container> containers = FXCollections.observableArrayList();

    public Product getProductToRegister() {
        return productToRegister;
    }

    public Product getProductToModify() {
        return productToModify;
    }

    public RegisterController(Product productToModify, ObservableList<Product> products, ObservableList<Sector> sectors) {
        this.productToModify = productToModify;
        this.products = products;
        for (Sector sector : sectors) {
            containers.addAll(sector.getContainers());
            break;
        }
    }

    private boolean isSerialNumberLegal(String testSerial) {
        return products.stream().noneMatch(product -> product.getSerialNumber().equals(testSerial));
    }

    private boolean isItANumber(String text) {
        try {
            Double number = Double.parseDouble(text);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //todo ovdje mozda mozes neku lambdu napisat ili nesto tako
    private boolean canThePackageFit(double testWidth, double testHeight, String containerTag) {
        double maxContainerCapacity = containers.stream().
                filter(container -> container.getTag().equals(containerTag)).
                mapToDouble(Container::getCapacity).
                sum();
        double occupiedSpaceVolume = products.stream().
                filter(product -> product.getLocationTag().charAt(1) == containerTag.charAt(0)).
                mapToDouble(product -> product.getPackageWidth() * product.getPackageWidth() * product.getPackageHeight()).
                sum();
        return occupiedSpaceVolume + (testWidth * testWidth * testHeight) <= maxContainerCapacity;
    }

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> containerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,9,1);
        spnContainer.setValueFactory(containerValueFactory);

        ObservableList<String> sectorObsList = FXCollections.observableArrayList();
        sectorObsList.addAll("A","B","C","D","E","F","G","H");
        SpinnerValueFactory<String> sectorValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(sectorObsList);
        spnSector.setValueFactory(sectorValueFactory);

        ObservableList<Unit> unitObsList = FXCollections.observableArrayList();
        unitObsList.addAll(Unit.values());
        cbUnit.setItems(unitObsList);
        cbUnit.setValue(Unit.tonne);

        if (productToModify != null) {
            fldName.setText(productToModify.getName());
            fldQuantity.setText(String.valueOf(productToModify.getQuantity()));
            fldWeight.setText(String.valueOf(productToModify.getWeight()));
            cbUnit.setValue(productToModify.getUnit());
            fldHeight.setText(String.valueOf(productToModify.getPackageHeight()));
            fldWidth.setText(String.valueOf(productToModify.getPackageWidth()));
            fldSerial.setText(productToModify.getSerialNumber());
            spnSector.getValueFactory().setValue(String.valueOf(productToModify.getLocationTag().charAt(0)));
            spnContainer.getValueFactory().setValue(productToModify.getLocationTag().charAt(1) - '0');
            fldPurchasePrice.setText(String.valueOf(productToModify.getPurchasePrice()));
            fldSellingPrice.setText(String.valueOf(productToModify.getSellingPrice()));
        }

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
            if (!isItANumber(newVal)) {
                fldQuantity.getStyleClass().removeAll("validField");
                fldQuantity.getStyleClass().add("notValidField");
            }
            else if (!newVal.isEmpty() || Integer.parseInt(newVal) > 0) {
                fldQuantity.getStyleClass().removeAll("notValidField");
                fldQuantity.getStyleClass().add("validField");
            } else {
                fldQuantity.getStyleClass().removeAll("validField");
                fldQuantity.getStyleClass().add("notValidField");
            }
        }));

        fldWeight.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!isItANumber(newVal)) {
                fldWeight.getStyleClass().removeAll("validField");
                fldWeight.getStyleClass().add("notValidField");
            }
            else if (!newVal.isEmpty() || Integer.parseInt(newVal) > 0) {
                fldWeight.getStyleClass().removeAll("notValidField");
                fldWeight.getStyleClass().add("validField");
            } else {
                fldWeight.getStyleClass().removeAll("validField");
                fldWeight.getStyleClass().add("notValidField");
            }
        }));

        fldHeight.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!isItANumber(newVal)) {
                fldHeight.getStyleClass().removeAll("validField");
                fldHeight.getStyleClass().add("notValidField");
            }
            else if (!newVal.isEmpty() || Integer.parseInt(newVal) > 0) {
                fldHeight.getStyleClass().removeAll("notValidField");
                fldHeight.getStyleClass().add("validField");
            } else {
                fldHeight.getStyleClass().removeAll("validField");
                fldHeight.getStyleClass().add("notValidField");
            }
        }));

        fldWidth.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!isItANumber(newVal)) {
                fldWidth.getStyleClass().removeAll("validField");
                fldWidth.getStyleClass().add("notValidField");
            }
            else if (!newVal.isEmpty() || Integer.parseInt(newVal) > 0) {
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
            if (!isItANumber(newVal)) {
                fldPurchasePrice.getStyleClass().removeAll("validField");
                fldPurchasePrice.getStyleClass().add("notValidField");
            }
            else if (!newVal.isEmpty() || Integer.parseInt(newVal) > 0) {
                fldPurchasePrice.getStyleClass().removeAll("notValidField");
                fldPurchasePrice.getStyleClass().add("validField");
            } else {
                fldPurchasePrice.getStyleClass().removeAll("validField");
                fldPurchasePrice.getStyleClass().add("notValidField");
            }
        }));

        fldSellingPrice.textProperty().addListener(((obs, oldVal, newVal) -> {
            if (!isItANumber(newVal)) {
                fldSellingPrice.getStyleClass().removeAll("validField");
                fldSellingPrice.getStyleClass().add("notValidField");
            }
            else if (!newVal.isEmpty() || Integer.parseInt(newVal) > 0) {
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

        try {
            if (!isSerialNumberLegal(fldSerial.getText())) throw new IllegalSerialNumberException();
        } catch (Exception e) {
            fldSerial.getStyleClass().removeAll("validField");
            fldSerial.getStyleClass().add("notValidField");
            return;
        }
        try {
            double width = Double.parseDouble(fldWidth.getText());
            double height = Double.parseDouble(fldHeight.getText());
            if (!canThePackageFit(width, height, spnContainer.getValue().toString())) throw new ExceedingCapacityLimitException();
        } catch (Exception e) {
            fldWidth.getStyleClass().removeAll("validField");
            fldWidth.getStyleClass().add("notValidField");
            fldHeight.getStyleClass().removeAll("validField");
            fldHeight.getStyleClass().add("notValidField");
            return;
        }
        if (readyToRegister() || productToModify != null) {
            productToRegister = new Product();
            productToRegister.setName(fldName.getText());
            productToRegister.setQuantity(Integer.parseInt(fldQuantity.getText()));
            productToRegister.setWeight(Double.parseDouble(fldWeight.getText()));
            productToRegister.setUnit(cbUnit.getSelectionModel().getSelectedItem().toString());
            productToRegister.setPackageHeight(Double.parseDouble(fldHeight.getText()));
            productToRegister.setPackageWidth(Double.parseDouble(fldWidth.getText()));
            productToRegister.setSerialNumber(fldSerial.getText());
            productToRegister.setLocationTag(spnSector.getValue() + spnContainer.getValue());
            productToRegister.setPurchasePrice(Double.parseDouble(fldPurchasePrice.getText()));
            productToRegister.setSellingPrice(Double.parseDouble(fldSellingPrice.getText()));

            if (productToModify == null) productToRegister.setId(getProductInstance().getMaxProductId());
            if (productToModify != null) {
                productToRegister.setId(productToModify.getId());
                productToModify = productToRegister;
            }
            Stage currentStage = (Stage) fldName.getScene().getWindow();
            currentStage.close();
        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("Error while registering new product");
//            alert.setContentText("All fields must be filled with the right data in order to successfully register a new product!");
//            alert.showAndWait();
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        productToModify = null;
        Stage currentStage = (Stage) cbUnit.getScene().getWindow();
        currentStage.close();
    }
}
