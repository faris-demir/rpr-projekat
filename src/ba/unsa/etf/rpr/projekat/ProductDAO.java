package ba.unsa.etf.rpr.projekat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProductDAO {
    private static ProductDAO productInstance = null;
    private Connection connection;
    private PreparedStatement insertProduct, getMaxProductId, updateProduct, deleteProduct;

    public Connection getConnection() {
        return connection;
    }

    public static ProductDAO getProductInstance() {
        if (productInstance == null) productInstance = new ProductDAO();
        return productInstance;
    }

    public static void removeProductInstance() {
        if (productInstance != null) {
            try {
                productInstance.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        productInstance = null;
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
        connection = WarehouseModel.getConnection();

        try {
            insertProduct = connection.prepareStatement("insert into product values (?,?,?,?,?,?,?,?,?,?,?,?);");
            getMaxProductId = connection.prepareStatement("select MAX(id)+1 from product;");
            updateProduct = connection.prepareStatement("update product set name=?, quantity=?, weight=?, unit=?, package_height=?, " +
                                                             "package_width=?, serial_number=?, location_tag=?, purchase_price=?, " +
                                                             "selling_price=?, container_id=? where id=?");
            deleteProduct = connection.prepareStatement("delete from product where id=?;");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getMaxProductId() {
        try {
            return getMaxProductId.executeQuery().getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteProduct(Product product) {
        try {
            deleteProduct.setInt(1, product.getId());
            deleteProduct.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyProduct(Product product) {
        try {
            updateProduct.clearParameters();
            updateProduct.setString(1, product.getName());
            updateProduct.setInt(2, product.getQuantity());
            updateProduct.setDouble(3, product.getWeight());
            updateProduct.setString(4, product.getUnit().toString());
            updateProduct.setDouble(5, product.getPackageHeight());
            updateProduct.setDouble(6, product.getPackageWidth());
            updateProduct.setString(7, product.getSerialNumber());
            updateProduct.setString(8, product.getLocationTag());
            updateProduct.setDouble(9, product.getPurchasePrice());
            updateProduct.setDouble(10, product.getSellingPrice());
            updateProduct.setInt(11, product.getLocationTag().charAt(1) - '0');
            updateProduct.setInt(12, product.getId());
            updateProduct.executeUpdate();
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
            insertProduct.setString(5, product.getUnit().toString());
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
