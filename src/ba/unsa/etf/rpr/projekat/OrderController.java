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

    public OrderController(Product productToOrder) {
        this.productToOrder = productToOrder;
    }

    @FXML
    public void initialize() {
        fldName.setText(productToOrder.getName());
        fldAvailQuantity.setText(String.valueOf(productToOrder.getQuantity()));
        fldPrice.setText(String.valueOf(productToOrder.getPurchasePrice()));
        fldName.setDisable(true);
        fldAvailQuantity.setDisable(true);
        fldPrice.setDisable(true);
    }

    public Product getProductToOrder() {
        return productToOrder;
    }

    public void confirmAction(ActionEvent actionEvent) {
        productToOrder.setQuantity(productToOrder.getQuantity() + Integer.parseInt(fldOrderQuantity.getText()));
        Stage currentStage = (Stage) fldName.getScene().getWindow();
        currentStage.close();
    }

    public void cancelAction(ActionEvent actionEvent) {
        productToOrder = null;
        Stage currentStage = (Stage) fldName.getScene().getWindow();
        currentStage.close();
    }
}
