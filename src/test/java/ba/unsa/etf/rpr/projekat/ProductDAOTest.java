package ba.unsa.etf.rpr.projekat;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDAOTest {

    @Test
    void registerProduct() {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        WarehouseModel model = new WarehouseModel();
        ProductDAO.removeProductInstance();
        ProductDAO dao = ProductDAO.getProductInstance();

        Product product = new Product("MacBook Pro", 3, 1.9, Unit.kilogram,
                0.2, 0.7, "AKCS123SWC5", "A3",
                4500, 5200, 10);
        dao.registerNewProduct(product);
        model.loadData();

        ObservableList<Product> products = model.getProductsDB();

        assertEquals("MacBook Pro", products.get(5).getName());
        assertEquals(6, products.size());
    }

    @Test
    void modifyProduct() {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        WarehouseModel model = new WarehouseModel();
        ProductDAO.removeProductInstance();
        ProductDAO dao = ProductDAO.getProductInstance();
        model.loadData();
        ObservableList<Product> products = model.getProductsDB();

        Product productToModify = products.get(0);
        productToModify.setName("iPhone");

        dao.modifyProduct(productToModify);

        model.loadData();
        products = model.getProductsDB();

        assertEquals(5, products.size());
        assertEquals("iPhone", products.get(0).getName());
    }

    @Test
    void deleteProduct() {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        WarehouseModel model = new WarehouseModel();
        ProductDAO.removeProductInstance();
        ProductDAO dao = ProductDAO.getProductInstance();
        model.loadData();
        ObservableList<Product> products = model.getProductsDB();

        Product productForDeletion = products.get(2);
        dao.deleteProduct(productForDeletion);

        model.loadData();
        products = model.getProductsDB();

        assertEquals(4, products.size());
        assertEquals("Pudding", products.get(2).getName());
    }

}
