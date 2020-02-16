package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class OrderController {
    public TextField fldName;
    public TextField fldAvailQuantity;
    public TextField fldPrice;
    public TextField fldOrderQuantity;
    private Product productToOrder = null;
    private Order newOrder = null;

    public OrderController(Product productToOrder) {
        this.productToOrder = productToOrder;
    }

    public Order getNewOrder() {
        return newOrder;
    }

    @FXML
    public void initialize() {
        fldName.setText(productToOrder.getName());
        fldAvailQuantity.setText(String.valueOf(productToOrder.getQuantity()));
        fldPrice.setText(String.valueOf(productToOrder.getPurchasePrice()));
        fldName.setDisable(true);
        fldAvailQuantity.setDisable(true);
        fldPrice.setDisable(true);

        fldOrderQuantity.textProperty().addListener(((observableValue, oldVal, newVal) -> {
            int quantityToOrder;
            try{
                quantityToOrder = Integer.parseInt(newVal);
            } catch (Exception e) {
                quantityToOrder = 0;
            }
            if (!newVal.isEmpty() && quantityToOrder > 0) {
                fldOrderQuantity.getStyleClass().removeAll("notValidField");
                fldOrderQuantity.getStyleClass().add("validField");
            } else {
                fldOrderQuantity.getStyleClass().removeAll("validField");
                fldOrderQuantity.getStyleClass().add("notValidField");
            }
        }));
    }

    public Product getProductToOrder() {
        return productToOrder;
    }

    public void confirmAction(ActionEvent actionEvent) {
        if (fldOrderQuantity.getStyleClass().contains("validField")) {
            newOrder = new Order();
            newOrder.setProductName(productToOrder.getName());
            newOrder.setOrderedQuantity(Integer.parseInt(fldOrderQuantity.getText()));
            newOrder.setPrice(productToOrder.getPurchasePrice());
            newOrder.setOrderDate(LocalDateTime.now());
            newOrder.setTotalPrice(Math.round(newOrder.getPrice() * newOrder.getOrderedQuantity() * 100)/100.);
            productToOrder.setQuantity(productToOrder.getQuantity() + Integer.parseInt(fldOrderQuantity.getText()));
            Stage currentStage = (Stage) fldName.getScene().getWindow();
            currentStage.close();
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        productToOrder = null;
        Stage currentStage = (Stage) fldName.getScene().getWindow();
        currentStage.close();
    }
}
