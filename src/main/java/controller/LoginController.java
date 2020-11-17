package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Caregiver;
import utils.HashMD5;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * The <code>LoginController</code> contains the entire logic of the login view.
 */
public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label txtResult;
    @FXML
    private Button btnLogin;

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
    public void handleLogin() {
    }


    /**
     * Method handles the click on the login button
     *
     * @param mouseEvent
     * @throws NoSuchAlgorithmException
     */
    public void loginClicked(MouseEvent mouseEvent) throws NoSuchAlgorithmException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if (username != null && password != null) {
            String hashedPassword = HashMD5.HashPassword(password);
            if (CheckLogin(username, hashedPassword)) {
                txtResult.setText("Login erfolgreich.");
                EnableMenuButtons();
            } else {
                txtResult.setText("Username/ID oder Passwort nicht korrekt");
            }
        } else {
            txtResult.setText("Login fehlgeschlagen.");
        }
    }

    /**
     * enables the Mainwindow buttons after a successful login
     */
    private void EnableMenuButtons() {
        mainWindowController.getBtnShowAllCaregivers().setDisable(false);
        mainWindowController.getBtnShowAllPatients().setDisable(false);
        mainWindowController.getBtnShowAllTreatments().setDisable(false);
    }

    /**
     * @param username
     * @param hashedPassword
     * @return boolean whether the login is correct
     * checks if the user has entered correct Name and Password
     */
    private boolean CheckLogin(String username, String hashedPassword) {
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

    /**
     * @param mainWindowController
     */
    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
