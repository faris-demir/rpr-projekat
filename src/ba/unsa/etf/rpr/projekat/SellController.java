package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class SellController {
    public Label lbName;
    public Label lbAvailQuantity;
    public Label lbPrice;
    public Spinner<Integer> spnSellQuantity;
    private Product productForSale = new Product();

    public SellController(Product productForSale) {
        this.productForSale = productForSale;
    }

    @FXML
    public void initialize() {
        lbName.setText(productForSale.getName());
        lbAvailQuantity.setText(String.valueOf(productForSale.getQuantity()));
        lbPrice.setText(String.valueOf(productForSale.getSellingPrice()));

    }


}
