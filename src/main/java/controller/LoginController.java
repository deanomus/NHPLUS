package controller;

import datastorage.ConnectionBuilder;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCancel;

    private Main main;
    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
        this.main = main;
    }

    @FXML
    public void handleCancel() {
        stage.close();
    }

    @FXML
    public void handleLogin() {

    }

    public void loginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginView.fxml"));
            BorderPane pane = loader.load();

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            LoginController controller = loader.getController();
            controller.initialize(stage);
            this.stage.setTitle("Login");
            this.stage.setScene(scene);
            this.stage.setResizable(false);
            this.stage.show();

            this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    ConnectionBuilder.closeConnection();
                    Platform.exit();
                    System.exit(0);
                }
            });
        } catch (IOException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
    }
}
