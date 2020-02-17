package ba.unsa.etf.rpr.projekat;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {
    public TextField fldUsername;
    public TextField fldPassword;
    Manager manager = new Manager();

    @FXML
    void initialize() {

        fldUsername.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty() && newVal.equals(manager.getUsername())) {
                fldUsername.getStyleClass().removeAll("notValidField");
                fldUsername.getStyleClass().add("validField");
            } else {
                fldUsername.getStyleClass().removeAll("validField");
                fldUsername.getStyleClass().add("notValidField");
            }
        });

        fldPassword.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty() && newVal.equals(manager.getPassword())) {
                fldPassword.getStyleClass().removeAll("notValidField");
                fldPassword.getStyleClass().add("validField");
            } else {
                fldPassword.getStyleClass().removeAll("validField");
                fldPassword.getStyleClass().add("notValidField");
            }
        });

    }

    public void loginAction(ActionEvent actionEvent) {
        if (fldPassword.getText().equals(manager.getPassword()) && fldUsername.getText().equals(manager.getUsername())) {
            Stage theStage = (Stage) fldUsername.getScene().getWindow();
            theStage.close();

            WarehouseModel model = new WarehouseModel();
            model.loadData();
            CentralController ctrl = new CentralController(model);
            Stage primaryStage = new Stage();
            ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
            final Parent[] root = {null};

            Task<Boolean> loadingTask = new Task<>() {
                @Override
                protected Boolean call() {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/central.fxml"), resourceBundle);
                    loader.setController(ctrl);
                    try {
                        root[0] = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            };

            loadingTask.setOnSucceeded(workerStateEvent -> {
                primaryStage.setTitle("EasyWMS");
                primaryStage.setScene(new Scene(root[0], USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                primaryStage.show();
                primaryStage.setResizable(false);
            });

            Parent fillRoot = null;
            try {
                fillRoot = FXMLLoader.load(getClass().getResource("/fxml/loading.fxml"), resourceBundle);
                fillRoot.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setTitle("EasyWMS");
            primaryStage.setScene(new Scene(fillRoot, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            primaryStage.setResizable(false);
            primaryStage.show();

            Thread thread = new Thread(loadingTask);
            thread.start();

        }
    }
}
