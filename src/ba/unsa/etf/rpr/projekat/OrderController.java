package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderController {
    public TextField fldName;
    public TextField fldAvailQuantity;
    public TextField fldPrice;
    public TextField fldOrderQuantity;
    private Product productToOrder = null;

    @FXML
    public void initialize() {

    }

    public Product getProductToOrder() {
        return productToOrder;
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage currentStage = (Stage) fldName.getScene().getWindow();
        currentStage.close();
    }
}
