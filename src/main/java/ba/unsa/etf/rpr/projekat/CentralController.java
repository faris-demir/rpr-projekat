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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

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

    private void refreshListContent() {
        model.loadData();
        products.setAll(model.getProductsDB());
        tblProducts.setItems(products);
        tblProducts.refresh();
    }

    private void saveTextToFile(Transactions transactions, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            if (Locale.getDefault().getCountry().equals("en")) {
                writer.println(">> LOCAL COPY OF TRANSACTIONS <<");
                writer.println("--------------------------------");
                writer.println("");
                writer.println(">> SALES <<");
                writer.println("");
            } else {
                writer.println(">> LOKALNA KOPIJA SVIH TRANSAKCIJA <<");
                writer.println("-------------------------------------");
                writer.println("");
                writer.println(">> PRODAJE <<");
                writer.println("");
            }
            transactions.getSales().forEach(writer::println);
            writer.println("");
            if (Locale.getDefault().getCountry().equals("en")) {
                writer.println(">> ORDERS <<");
            } else {
                writer.println(">> NARUDŽBE <<");
            }
            writer.println("");
            transactions.getOrders().forEach(writer::println);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        products = model.getProductsDB();
        tblProducts.setItems(products);
        Transactions transactions = new Transactions();
        transactions.setOrders(model.getOrdersDB());
        transactions.setSales(model.getSalesDB());
        model.setTransactions(transactions);

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
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"), resourceBundle);
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
            if (Locale.getDefault().getCountry().equals("en")) {
                alert.setHeaderText("Failed search attempt");
                alert.setContentText("Sector names only contain one capital letter! \nThey are in the range from A to H inclusive.");
            } else {
                alert.setHeaderText("Greška u pretrazi");
                alert.setContentText("Imena sektora se sastoje samo iz jednog velikog slova! \nU opsegu su od slova A do H.");
            }

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
        RegisterController ctrl = new RegisterController(null, products, model.getSectors());
        Stage primaryStage = new Stage();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"), resourceBundle);
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Locale.getDefault().getCountry().equals("en")) {
            primaryStage.setTitle("Register new product");
        } else {
            primaryStage.setTitle("Dodavanje novog proizvoda");
        }
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
        if (currentProduct == null) try {
            throw new ProductNotSelectedException();
        } catch (ProductNotSelectedException e) {
            return;
        }
        RegisterController ctrl = new RegisterController(currentProduct, products, model.getSectors());
        Stage primaryStage = new Stage();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"), resourceBundle);
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Locale.getDefault().getCountry().equals("en")) {
            primaryStage.setTitle("Register new product");
        } else {primaryStage.setTitle("Dodavanje novog proizvoda");

        }
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setOnHiding(windowEvent -> {
            if (ctrl.getProductToModify() != null) {
                products.removeAll(model.getProductsDB());
                getProductInstance().modifyProduct(ctrl.getProductToModify());
                refreshListContent();
            }
        });
        primaryStage.show();
    }

    public void removeAction(ActionEvent actionEvent) {
        if (currentProduct == null) try {
            throw new ProductNotSelectedException();
        } catch (ProductNotSelectedException e) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (Locale.getDefault().getCountry().equals("US")) {
            alert.setHeaderText("Deleting selected product");
            alert.setContentText("This action will delete the product you have selected. Are you sure you would like to proceed?");
        } else {
            alert.setHeaderText("Brisanje odabranog artikla");
            alert.setContentText("Ova akcija će obrisati proizvod kojeg ste odabrali. Jeste li sigurni da želite nastaviti?");
        }
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            getProductInstance().deleteProduct(currentProduct);
        }
        refreshListContent();
    }

    public void sellAction(ActionEvent actionEvent) {
        if (currentProduct == null) try {
            throw new ProductNotSelectedException();
        } catch (ProductNotSelectedException e) {
            return;
        }
        SellController ctrl = new SellController(currentProduct);
        Stage primaryStage = new Stage();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sell.fxml"), resourceBundle);
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Locale.getDefault().getCountry().equals("en")) {
            primaryStage.setTitle("Sell product");
        } else {
            primaryStage.setTitle("Prodaja proizvoda");
        }
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setOnHiding(windowEvent -> {
            if (ctrl.getProductForSale() != null) {
                model.addSale(ctrl.getNewSale());
                model.insertSaleDB(ctrl.getNewSale());
                products.removeAll(model.getProductsDB());
                getProductInstance().modifyProduct(ctrl.getProductForSale());
                refreshListContent();
            }
        });
        primaryStage.show();
    }

    public void orderAction(ActionEvent actionEvent) {
        if (currentProduct == null) try {
            throw new ProductNotSelectedException();
        } catch (ProductNotSelectedException e) {
            return;
        }
        OrderController ctrl = new OrderController(currentProduct);
        Stage primaryStage = new Stage();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/order.fxml"), resourceBundle);
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Locale.getDefault().getCountry().equals("en")) {
            primaryStage.setTitle("Order product");
        } else {
            primaryStage.setTitle("Narudžba proizvoda");
        }
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setOnHiding(windowEvent -> {
            if (ctrl.getProductToOrder() != null) {
                model.addOrder(ctrl.getNewOrder());
                model.insertOrderDB(ctrl.getNewOrder());
                products.removeAll(model.getProductsDB());
                getProductInstance().modifyProduct(ctrl.getProductToOrder());
                refreshListContent();
            }
        });
        primaryStage.show();
    }

    public void salesReportAction(ActionEvent actionEvent) {
        try {
            if (model.getSalesDB().size() == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                if (Locale.getDefault().getCountry().equals("US")) {
                    alert.setTitle("Warning");
                    alert.setHeaderText("No sales made");
                    alert.setContentText("You haven't made any sales yet for the report to show!");
                } else {
                    alert.setTitle("Upozorenje");
                    alert.setHeaderText("Nema izvršenih prodaja");
                    alert.setContentText("Niste izvršili nijednu prodaju koju bi izvještaj mogao prikazati!");
                }
                alert.showAndWait();
                return;
            }
            new SalesReport().showReport(WarehouseModel.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void orderReportAction(ActionEvent actionEvent) {
        try {
            if (model.getOrdersDB().size() == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                if (Locale.getDefault().getCountry().equals("US")) {
                    alert.setTitle("Warning");
                    alert.setHeaderText("No orders made");
                    alert.setContentText("You haven't made any orders yet for the report to show!");
                } else {
                    alert.setTitle("Upozorenje");
                    alert.setHeaderText("Nema izvršenih narudžbi");
                    alert.setContentText("Niste izvršili nijednu narudžbu koju bi izvještaj mogao prikazati!");
                }
                alert.showAndWait();
                return;
            }
            new OrdersReport().showReport(WarehouseModel.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void saveTransactionsAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        if (Locale.getDefault().getCountry().equals("en")) {
            fileChooser.setTitle("Save file");
        } else {
            fileChooser.setTitle("Spasi fajl");
        }
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(btnLogout.getScene().getWindow());
        if (file != null) {
            saveTextToFile(model.getTransactions(), file);
        }
    }

    public void refreshLanguageGlobally() {
        Stage currentStage = (Stage) btnLogout.getScene().getWindow();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/central.fxml"), resourceBundle);
        loader.setController(this);
        try {
            currentStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void itmEnglishAction(ActionEvent actionEvent) {
        Locale.setDefault(new Locale("en", "US"));
        refreshLanguageGlobally();
    }

    public void itmBosanskiAction(ActionEvent actionEvent) {
        Locale.setDefault(new Locale("bs"));
        refreshLanguageGlobally();
    }

    public void aboutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (Locale.getDefault().getCountry().equals("US")) {
            alert.setTitle("About");
            alert.setHeaderText("Basic information about the software");
            alert.setContentText("EasyWMS is a software solution for managing a warehouse. \nIt allows one manager to add, modify, remove, order, sell, search products " +
                                "and generate reports for the sales and orders he made.\n \nEasyWMS was created from scratch by Faris Demir, a student at the Faculty of Electrical Engineering " +
                                "in Sarajevo as a project. \n\nThe current version of the program is 1.0 \n \nAll rights reserved");
        } else {
            alert.setTitle("Informacije");
            alert.setHeaderText("Osnovne informacije o aplikaciji");
            alert.setContentText("EasyWMS je softversko rješenje za upravljanje skladištem. \nOmogućava upravitelju/ici da dodaje, mijenja, uklanja, naručuje, prodaje, pretražuje artikle " +
                    "i da generiše o prodajama i narudžbama koje je on/ona izvršio/la.\n \nEasyWMS je napisan od nule od strane Farisa Demira, studenta Elektrotehničkog fakulteta u Sarajevu " +
                    "kao projekat. \n\nTrenutna verzija programa je 1.0 \n \nSva prava zadržana");
        }
        alert.showAndWait();
    }
}
