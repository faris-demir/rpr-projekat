package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class CentralControllerTest {
    @Start
    public void start(Stage stage) throws Exception {
        File dbfile = new File("warehouse.db");
        dbfile.delete();
        WarehouseModel model = new WarehouseModel();
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


//    @Test
//    void saveFile(FxRobot robot) {
//        assertDoesNotThrow(() -> {
//            robot.clickOn("#menuFile").clickOn("#optSave");
//            robot.lookup(".dialog-pane").tryQuery().isPresent();
//
//            FileChooser fileChooser = robot.lookup(".file-chooser").queryAs(FileChooser.class);
//            Button saveButton = (Button) dialogPane.lookupButton(ButtonType.APPLY);
//            robot.clickOn(saveButton);
//        });
//    }

    @Test
    void notSelectedException(FxRobot robot) {
        robot.clickOn("#btnRemove");
        robot.lookup(".dialog-pane").tryQuery().isPresent();

        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        assertEquals("Product not selected", dialogPane.getHeaderText());
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);
    }

}
