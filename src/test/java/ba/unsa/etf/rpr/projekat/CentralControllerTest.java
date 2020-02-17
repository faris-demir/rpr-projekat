package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class CentralControllerTest {
    private WarehouseModel model;

    @Start
    public void start(Stage stage) throws Exception {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        model = new WarehouseModel();
        model.loadData();

        CentralController ctrl = new CentralController(model);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/central.fxml"), resourceBundle);
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
    void tabelView(FxRobot robot) {
        TableView productList = robot.lookup("#tblProducts").queryAs(TableView.class);
        assertEquals(5, productList.getItems().size());
    }

    @Test
    void about(FxRobot robot) {
        assertDoesNotThrow(() -> {
            robot.clickOn("#menuHelp").clickOn("#itmAbout");
            robot.lookup(".dialog-pane").tryQuery().isPresent();

            DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
            Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
            robot.clickOn(okButton);
        });
    }

    @Test
    void languageBOS(FxRobot robot) {
        robot.clickOn("#menuFile").clickOn("#menuLanguage").clickOn("#menuBosanski");
        Button btnSearch = robot.lookup("#btnNameSearch").queryButton();

        assertEquals("Traži naziv", btnSearch.getText());
    }

    @Test
    void languageUS(FxRobot robot) {
        robot.clickOn("#menuFile").clickOn("#menuLanguage").clickOn("#menuEnglish");
        Button btnSearch = robot.lookup("#btnNameSearch").queryButton();

        assertEquals("Search name", btnSearch.getText());
    }

    @Test
    void notSelectedException(FxRobot robot) {
        robot.clickOn("#btnRemove");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        assertEquals("Product not selected", dialogPane.getHeaderText());
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);
    }

    @Test
    void sellProduct(FxRobot robot) {
        robot.clickOn("Milk");
        robot.clickOn("#btnSell");

        robot.clickOn("#btnConfirm");
        robot.clickOn("Milk");

        assertEquals(9, model.getProductsDB().get(0).getQuantity());
    }

    @Test
    void removeProduct(FxRobot robot) {
        robot.clickOn("Milk");
        robot.clickOn("#btnRemove");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        assertEquals("Deleting selected product", dialogPane.getHeaderText());
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);

        TableView productList = robot.lookup("#tblProducts").queryAs(TableView.class);
        assertEquals(4, productList.getItems().size());
    }

    @Test
    void orderProduct(FxRobot robot) {
        robot.clickOn("Milk");
        robot.clickOn("#btnOrder");

        robot.clickOn("#fldOrderQuantity").write("100");
        robot.clickOn("#btnConfirm");
        robot.clickOn("Milk");

        assertEquals(110 , model.getProductsDB().get(0).getQuantity());
    }

    @Test
    void logOut(FxRobot robot) {
        robot.clickOn("#btnLogout");
        Button btnLogin = robot.lookup("#btnLogin").queryButton();

        if (Locale.getDefault().getCountry().equals("US")) {
            assertEquals("Login", btnLogin.getText());
        } else {
            assertEquals("Uloguj se", btnLogin.getText());
        }
    }


    @Test
    void allSearches(FxRobot robot) {
        robot.clickOn("#fldSearch").write("Milk");
        robot.clickOn("#btnNameSearch");
        TableView productList = robot.lookup("#tblProducts").queryAs(TableView.class);
        assertEquals(1, productList.getItems().size());

        robot.clickOn("#btnRefresh");
        productList = robot.lookup("#tblProducts").queryAs(TableView.class);
        assertEquals(5, productList.getItems().size());

        KeyCode ctrl = KeyCode.CONTROL;
        if (System.getProperty("os.name").contains("Mac")) ctrl = KeyCode.COMMAND;
        robot.clickOn("#fldSearch");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl).write("A");
        robot.clickOn("#btnSectorSearch");
        productList = robot.lookup("#tblProducts").queryAs(TableView.class);
        assertEquals(4, productList.getItems().size());
    }

    @Test
    void modifyProduct(FxRobot robot) {
        robot.clickOn("Milk");
        robot.clickOn("#btnModify");

        KeyCode ctrl = KeyCode.CONTROL;
        if (System.getProperty("os.name").contains("Mac")) ctrl = KeyCode.COMMAND;
        robot.clickOn("#fldName");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl).write("MLIJEKO");
        robot.clickOn("#btnConfirm");
        assertEquals("MLIJEKO", model.getProductsDB().get(0).getName());
    }

}
