package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class CentralController {
    public Button btnLogout;
    public TextField fldSearch;
    public TableView<Product> tblProducts;
    public TableColumn<Product,Integer> colId;
    public TableColumn<Product,String> colName;
    public TableColumn<Product,Integer> colQuantity;
    public TableColumn<Product,Double> colWeight;
    public TableColumn<Product,String> colUnit;
    public TableColumn<Product,Double> colHeight;
    public TableColumn<Product,Double> colWidth;
    public TableColumn<Product,String> colSerial;
    public TableColumn<Product,String> colLocation;
    public TableColumn<Product,Double> colPurchasePrice;
    public TableColumn<Product,Double> colSellingPrice;
    public ObservableList<Product> products = FXCollections.observableArrayList();
    public Product currentProduct = new Product();

    private WarehouseModel model;

    public WarehouseModel getModel() {
        return model;
    }

    public CentralController(WarehouseModel model) {
        this.model = model;
    }

    @FXML
    void initialize() {
        for (Sector s : model.getSectors()) {
            for (Container c : s.getContainers()) {
                products.addAll(c.getProducts());
            }
        }
        tblProducts.setItems(products);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colHeight.setCellValueFactory(new PropertyValueFactory<>("packageHeight"));
        colWidth.setCellValueFactory(new PropertyValueFactory<>("packageWidth"));
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("locationTag"));
        colPurchasePrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

        tblProducts.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (tblProducts.getSelectionModel().getSelectedItem() == null) {
                currentProduct = null;
            } else {
                currentProduct = tblProducts.getSelectionModel().getSelectedItem();
            }
        }));
    }

    public void logoutAction(ActionEvent actionEvent) {
        Stage theStage = (Stage) btnLogout.getScene().getWindow();
        theStage.close();

        LoginController ctrl = new LoginController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("EasyWMS");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void refreshAction(ActionEvent actionEvent) {
        tblProducts.setItems(products);
        tblProducts.refresh();
    }

    public void nameSearchAction(ActionEvent actionEvent) {
        if (fldSearch.getText().equals("")) return;
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        for (Product product : products) {
            if (product.getName().equals(fldSearch.getText())) {
                filteredProducts.add(product);
            }
        }
        tblProducts.setItems(filteredProducts);
        tblProducts.refresh();
    }

    public void sectorSearchAction(ActionEvent actionEvent) {
        if (fldSearch.getText().equals("")) return;
        if (fldSearch.getText().length() > 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Failed search attempt");
            alert.setContentText("Sector names only contain one letter! \nThey are in the range from A to H inclusive.");
            alert.showAndWait();
            return;
        }
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        for (Product product : products) {
            if (product.getLocationTag().charAt(0) == fldSearch.getText().charAt(0)) {
                filteredProducts.add(product);
            }
        }
        tblProducts.setItems(filteredProducts);
        tblProducts.refresh();
    }

    public void registerAction(ActionEvent actionEvent) {

    }

    public void modifyAction(ActionEvent actionEvent) {

    }

    public void removeAction(ActionEvent actionEvent) {

    }

    public void sellAction(ActionEvent actionEvent) {

    }

    public void orderAction(ActionEvent actionEvent) {

    }

    public void salesReportAction(ActionEvent actionEvent) {

    }

    public void orderReportAction(ActionEvent actionEvent) {

    }
}
