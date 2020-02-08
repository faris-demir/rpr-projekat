package ba.unsa.etf.rpr.projekat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class ProductDAO {
    private Connection connection;
    private PreparedStatement insertSector, insertContainer, insertProduct, getMaxSectorId, getMaxContainerId, getMaxProductId;

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

    public ProductDAO() {
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
            insertSector = connection.prepareStatement("insert into sector values (?,?,?);");
            insertContainer = connection.prepareStatement("insert into container values (?,?,?,?);");
            insertProduct = connection.prepareStatement("insert into product values (?,?,?,?,?,?,?,?,?,?,?,?);");
            getMaxSectorId = connection.prepareStatement("select MAX(id)+1 from sector;");
            getMaxContainerId = connection.prepareStatement("select MAX(id)+1 from container;");
            getMaxProductId = connection.prepareStatement("select MAX(id)+1 from product;");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void registerNewProduct(Product product) {
        try {
            insertProduct.clearParameters();
            int productId = getMaxProductId.executeQuery().getInt(1);

            insertProduct.setInt(1, productId);
            insertProduct.setString(2, product.getName());
            insertProduct.setInt(3, product.getQuantity());
            insertProduct.setDouble(4, product.getWeight());
            insertProduct.setString(5, product.getUnit());
            insertProduct.setDouble(6, product.getPackageHeight());
            insertProduct.setDouble(7, product.getPackageWidth());
            insertProduct.setString(8, product.getSerialNumber());
            insertProduct.setString(9, product.getLocationTag());
            insertProduct.setDouble(10, product.getPurchasePrice());
            insertProduct.setDouble(11, product.getSellingPrice());
            insertProduct.setInt(12, product.getLocationTag().charAt(1) - '0');
            insertProduct.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
