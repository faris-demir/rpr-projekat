package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class RegisterControllerModifyTest {
    private RegisterController ctrl;

    @Start
    public void start(Stage stage) throws Exception {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        WarehouseModel model = new WarehouseModel();
        model.loadData();
        Product product = new Product("MacBook Pro", 3, 1.9, Unit.kilogram,
                0.2, 0.7, "AKCS123SWC5", "A3",
                4500, 5200, 10);

        ctrl = new RegisterController(product, model.getProductsDB(), model.getSectors());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"), resourceBundle);
        loader.setController(ctrl);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("EasyWMS");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.setResizable(false);
        stage.toFront();
    }

    @Test
    void modifyProduct(FxRobot robot) {
        robot.clickOn("#fldName").write(" 15 inch");
        robot.clickOn("#btnRegister");

        Product changedProduct = ctrl.getProductToModify();
        assertEquals("MacBook Pro 15 inch", changedProduct.getName());
    }
}
