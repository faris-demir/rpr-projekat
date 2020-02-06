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
    private PreparedStatement getAllSectors, getAllContainers, getAllProducts, findByName;

    public Connection getConnection() {
        return connection;
    }

    private void regenerateDB() {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream("warehouse.db.sql"));
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
            connection = DriverManager.getConnection("jdbc:sqlite:warehouse.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement getAllData = connection.prepareStatement("SELECT * FROM sector s, container c, product p where c.sector_id = s.id and p.container_id = c.id;");
        } catch (SQLException e) {
            regenerateDB();
            try {
                PreparedStatement getAllData = connection.prepareStatement("SELECT * FROM sector s, container c, product p where c.sector_id = s.id and p.container_id = c.id;");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        try {
            getAllSectors = connection.prepareStatement("select * from sector");
            getAllContainers = connection.prepareStatement("select c.id, c.name, c.capacity, c.sector_id from container c, sector s where c.sector_id = s.id;");
            getAllProducts = connection.prepareStatement("select p.id, p.name, p.quantity, p.weight, p.unit, p.package_height, p.package_width, p.serial_number, " +
                                                              " p.location_tag, p.purchase_price, p.selling_price, container_id from product p, container c, sector s " +
                                                              "where p.container_id = c.id and c.sector_id = s.id;");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void loadData() {
        try {
            ResultSet rs1 = getAllSectors.executeQuery();
            while (rs1.next()) {
                Sector newSector = new Sector(rs1.getInt(1), rs1.getString(2), rs1.getInt(3));
                sectors.add(newSector);
            }

            ResultSet rs2 = getAllContainers.executeQuery();
            while (rs2.next()) {
                Container newContainer = new Container(rs2.getInt(1), rs2.getString(2), rs2.getDouble(3));
                for (Sector s : sectors) {
                    if (s.getId() == rs2.getInt(4)) {
                        s.getContainers().add(newContainer);
                        break;
                    }
                }
            }

            ResultSet rs3 = getAllProducts.executeQuery();
            while (rs3.next()) {
                Product newProduct = new Product();
                newProduct.setId(rs3.getInt(1));
                newProduct.setName(rs3.getString(2));
                newProduct.setQuantity(rs3.getInt(3));
                newProduct.setWeight(rs3.getDouble(4));
                newProduct.setUnit(rs3.getString(5));
                newProduct.setPackageHeight(rs3.getDouble(6));
                newProduct.setPackageWidth(rs3.getDouble(7));
                newProduct.setSerialNumber(rs3.getString(8));
                newProduct.setLocationTag(rs3.getString(9));
                newProduct.setPurchasePrice(rs3.getDouble(10));
                newProduct.setSellingPrice(rs3.getDouble(11));
                boolean productAdded = false;
                for (Sector s : sectors) {
                    for (Container c : s.getContainers()) {
                        if (c.getId() == rs3.getInt(12)) {
                            c.getProducts().add(newProduct);
                            productAdded = true;
                            break;
                        }
                    }
                    if (productAdded) break;
                }
            }
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
