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
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

import static ba.unsa.etf.rpr.projekat.ProductDAO.getProductInstance;
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
    public Product currentProduct = null;

    public Product getCurrentProduct() {
        return currentProduct;
    }

    private WarehouseModel model;

    public WarehouseModel getModel() {
        return model;
    }

    public CentralController(WarehouseModel model) {
        this.model = model;
    }

    public ObservableList<Product> getProductsDB() {
        ObservableList<Product> productsDB = FXCollections.observableArrayList();
        for (Sector s : model.getSectors()) {
            for (Container c : s.getContainers()) {
                productsDB.addAll(c.getProducts());
            }
        }
        return productsDB;
    }

    public void refreshListContent() {
        model.loadData();
        products.setAll(getProductsDB());
        tblProducts.setItems(products);
        tblProducts.refresh();
    }

    @FXML
    void initialize() {
        products = getProductsDB();
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
        RegisterController ctrl = new RegisterController(null);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Register new product");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setOnHiding(windowEvent -> {
            if (ctrl.getProductToRegister() != null) {
                getProductInstance().registerNewProduct(ctrl.getProductToRegister());
                products.add(ctrl.getProductToRegister());
                tblProducts.setItems(products);
                tblProducts.refresh();
            }
        });
        primaryStage.show();
    }

    public void modifyAction(ActionEvent actionEvent) {
        if (currentProduct == null) return;
        RegisterController ctrl = new RegisterController(currentProduct);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Register new product");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setOnHiding(windowEvent -> {
            if (ctrl.getProductToModify() != null) {
                products.removeAll(getProductsDB());
                getProductInstance().modifyProduct(ctrl.getProductToModify());
                refreshListContent();
            }
        });
        primaryStage.show();
    }

    public void removeAction(ActionEvent actionEvent) {
        if (currentProduct == null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deleting selected product");
        alert.setContentText("This action will delete the product you have selected. Are you sure you would like to proceed?");
        alert.showAndWait();
        getProductInstance().deleteProduct(currentProduct);
        refreshListContent();
    }

    public void sellAction(ActionEvent actionEvent) {
        if (currentProduct == null) return;
        SellController ctrl = new SellController(currentProduct);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sell.fxml"));
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Sell product");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setOnHiding(windowEvent -> {
            if (ctrl.getProductForSale() != null) {
                model.addSale(ctrl.getNewSale());
                model.insertSaleDB(ctrl.getNewSale());
                products.removeAll(getProductsDB());
                getProductInstance().modifyProduct(ctrl.getProductForSale());
                refreshListContent();
            }
        });
        primaryStage.show();
    }

    public void orderAction(ActionEvent actionEvent) {
        if (currentProduct == null) return;
        OrderController ctrl = new OrderController(currentProduct);
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/order.fxml"));
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Order product");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setOnHiding(windowEvent -> {
            if (ctrl.getProductToOrder() != null) {
                model.addOrder(ctrl.getNewOrder());
                model.insertOrderDB(ctrl.getNewOrder());
                products.removeAll(getProductsDB());
                getProductInstance().modifyProduct(ctrl.getProductToOrder());
                refreshListContent();
            }
        });
        primaryStage.show();
    }

    public void salesReportAction(ActionEvent actionEvent) {
        try {
            new SalesReport().showReport(WarehouseModel.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }

    }

    public void orderReportAction(ActionEvent actionEvent) {

    }
}
