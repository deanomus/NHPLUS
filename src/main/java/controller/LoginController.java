package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Caregiver;
import utils.HashMD5;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label txtResult;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCancel;

    private Main main;
    private CaregiverDAO cDao;
    public MainWindowController mainWindowController;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        this.main = main;
        this.cDao = DAOFactory.getDAOFactory().createCaregiverDAO();
    }

    @FXML
    public void handleCancel() {
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

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
    }

    public void loginClicked(MouseEvent mouseEvent) throws NoSuchAlgorithmException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if (username != null && password != null) {
            String hashedPassword = HashMD5.HashPassword(password);
            if (checkLogin(username, hashedPassword)) {
                txtResult.setText("Login erfolgreich.");
                enableMenuButtons();
            } else {
                txtResult.setText("Username/ID oder Passwort nicht korrekt");
            }
        } else {
            txtResult.setText("Login fehlgeschlagen.");
        }
    }

    private void enableMenuButtons() {
        mainWindowController.getBtnShowAllCaregivers().setDisable(false);
        mainWindowController.getBtnShowAllPatients().setDisable(false);
        mainWindowController.getBtnShowAllTreatments().setDisable(false);
    }

    private boolean checkLogin(String username, String hashedPassword) {
        List<Caregiver> allCaregiver = null;

        try {
            allCaregiver = cDao.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (allCaregiver != null) {
            for (Caregiver caregiver : allCaregiver) {
                if (caregiver.getSurname().equals(username)) {
                    if (caregiver.getPassword().equals(hashedPassword)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
