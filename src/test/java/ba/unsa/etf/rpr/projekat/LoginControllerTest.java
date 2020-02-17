package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public class LoginControllerTest {

    @Start
    public void start(Stage stage) throws Exception {
        LoginController ctrl = new LoginController();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"), resourceBundle);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("EasyWMS");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.setResizable(false);
        stage.toFront();
    }

    @Test
    void login(FxRobot robot) {
        robot.clickOn("#fldUsername").write("admin");
        robot.clickOn("#fldPassword").write("admin");
        TextField username = robot.lookup("#fldUsername").queryAs(TextField.class);
        assertTrue(username.getStyleClass().contains("validField"));
        robot.clickOn("#btnLogin");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Button btnSell = robot.lookup("#btnLogout").queryButton();

        assertEquals("Log out", btnSell.getText());
    }

}
