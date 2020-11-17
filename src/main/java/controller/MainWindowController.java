package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.io.IOException;

public class MainWindowController {

    @FXML
    private Button showPatients;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Button btnShowAllPatients;
    @FXML
    private Button btnShowAllCaregivers;
    @FXML
    private Button btnShowAllTreatments;
    @FXML
    private Button btnShowLoginWindow;

    /**
     * @param e handles the button click event of Patients button
     */
    @FXML
    private void handleShowAllPatients(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllPatientController controller = loader.getController();
    }

    /**
     * @param e handles the button click event of Caregivers button
     */
    @FXML
    private void handleShowAllCaregivers(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllCaregiverView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllCaregiverController controller = loader.getController();
    }

    /**
     * @param e handles the button click event of Treatments button
     */
    @FXML
    private void handleShowAllTreatments(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllTreatmentController controller = loader.getController();
    }

    /**
     * @param e handles the button click event of Login button
     */
    @FXML
    private void handleLoginWindow(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        LoginController controller = loader.getController();
        controller.setMainWindowController(this);
    }

    public Button getBtnShowAllPatients() {
        return btnShowAllPatients;
    }

    public Button getBtnShowAllCaregivers() {
        return btnShowAllCaregivers;
    }

    public Button getBtnShowAllTreatments() {
        return btnShowAllTreatments;
    }
}
