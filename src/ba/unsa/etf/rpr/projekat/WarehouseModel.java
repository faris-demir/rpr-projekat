package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class WarehouseModel {
    private ObservableList<Sector> sectors = FXCollections.observableArrayList();
    private SimpleObjectProperty<Product> currentProduct = new SimpleObjectProperty<>();
    private Connection connection;
    private PreparedStatement getAllData;

    public Connection getConnection() {
        return connection;
    }

    private void regenerateDB() {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream("korisnici.db.sql"));
            String sqlQuery = "";
            while (input.hasNext()) {
                sqlQuery += input.nextLine();
                if ( sqlQuery.length() > 1 && sqlQuery.charAt( sqlQuery.length()-1 ) == ';') {
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(sqlQuery);
                        sqlQuery = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public WarehouseModel() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:korisnici.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            getAllData = connection.prepareStatement("SELECT * FROM sector s, container c, product p where c.sector_id = s.id and p.container_id = c.id;");
        } catch (SQLException e) {
            regenerateDB();
            try {
                getAllData = connection.prepareStatement("SELECT * FROM sector s, container c, product p where c.sector_id = s.id and p.container_id = c.id;");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void loadData() {
        try {
            ResultSet rs = getAllData.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(ObservableList<Sector> sectors) {
        this.sectors = sectors;
    }

    public Product getCurrentProduct() {
        return currentProduct.get();
    }

    public SimpleObjectProperty<Product> currentProductProperty() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct.set(currentProduct);
    }
}
