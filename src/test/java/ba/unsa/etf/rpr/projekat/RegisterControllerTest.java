package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
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
public class RegisterControllerTest {
    @Start
    public void start(Stage stage) throws Exception {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        WarehouseModel model = new WarehouseModel();
        model.loadData();

        RegisterController ctrl = new RegisterController(null, model.getProductsDB(), model.getSectors());
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
    void serialNumberException(FxRobot robot) {
        robot.clickOn("#fldName").write("laptop");
        robot.clickOn("#fldQuantity").write("12");
        robot.clickOn("#fldWeight").write("1.5");
        robot.clickOn("#fldHeight").write("0.5");
        robot.clickOn("#fldWidth").write("0.9");
        robot.clickOn("#fldPurchasePrice").write("2300");
        robot.clickOn("#fldSellingPrice").write("3000");

        robot.clickOn("#fldSerial").write("1234ABC1");
        robot.clickOn("#btnConfirm");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        assertEquals("Illegal serial number", dialogPane.getHeaderText());
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);
    }

    @Test
    void exceedsCapacityException(FxRobot robot) {
        robot.clickOn("#fldHeight").write("5000");
        robot.clickOn("#fldWidth").write("2000");
        robot.clickOn("#btnConfirm");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        assertEquals("Exceeded container capacity limit", dialogPane.getHeaderText());
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);
    }

}
