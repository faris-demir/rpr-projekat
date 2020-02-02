package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class CentralController {
    public Button btnLogout;

    public void logoutAction(ActionEvent actionEvent) {
        Stage theStage = (Stage) btnLogout.getScene().getWindow();
        theStage.close();

        LoginController ctrl = new LoginController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("EasyWMS");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void registerAction(ActionEvent actionEvent) {

    }

    public void modifyAction(ActionEvent actionEvent) {

    }

    public void removeAction(ActionEvent actionEvent) {

    }

    public void sellAction(ActionEvent actionEvent) {

    }

    public void orderAction(ActionEvent actionEvent) {

    }

    public void salesReportAction(ActionEvent actionEvent) {

    }

    public void orderReportAction(ActionEvent actionEvent) {

    }
}
