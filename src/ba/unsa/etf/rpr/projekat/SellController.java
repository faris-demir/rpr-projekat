package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SellController {
    public TextField fldName;
    public TextField fldAvailQuantity;
    public TextField fldPrice;
    public Spinner<Integer> spnSellQuantity;
    private Product productForSale;

    public SellController(Product productForSale) {
        this.productForSale = productForSale;
    }

    @FXML
    public void initialize() {
        fldName.setText(productForSale.getName());
        fldAvailQuantity.setText(String.valueOf(productForSale.getQuantity()));
        fldPrice.setText(String.valueOf(productForSale.getSellingPrice()));
        SpinnerValueFactory<Integer> quantityValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, productForSale.getQuantity(),1);
        spnSellQuantity.setValueFactory(quantityValueFactory);
        fldName.setDisable(true);
        fldAvailQuantity.setDisable(true);
        fldPrice.setDisable(true);
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage currentStage = (Stage) fldName.getScene().getWindow();
        currentStage.close();
    }

}
