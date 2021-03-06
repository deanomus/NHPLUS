package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>NewTreatmentController</code> contains the entire logic of the treatment view when creating a new treatment.
 */
public class NewTreatmentController {
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;
    @FXML
    private ComboBox<String> comboBox;

    private ObservableList<String> caregiverComboBoxData = FXCollections.observableArrayList();
    private ArrayList<Caregiver> caregiverList;

    private AllTreatmentController controller;
    private String caregiver;
    private Patient patient;
    private Stage stage;

    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        this.controller = controller;
        this.patient = patient;
        this.caregiver = caregiver;
        this.stage = stage;
        createComboBoxData();
        comboBox.setItems(caregiverComboBoxData);
        showPatientData();
    }

    /**
     * Shows the data of selected patient
     */
    private void showPatientData() {
        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());
    }

    /**
     * handles when a new treatment is added
     */
    @FXML
    public void handleAdd() {
        LocalDate date = this.datepicker.getValue();
        String s_begin = txtBegin.getText();
        LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();
        caregiver = comboBox.getValue();
        Treatment treatment = new Treatment(patient.getPid(), date,
                begin, end, description, remarks, caregiver);
        createTreatment(treatment);
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * creates the new treatment
     *
     * @param treatment
     */
    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles the button click event of cancel button
     */
    @FXML
    public void handleCancel() {
        stage.close();
    }

    /**
     * adds the correct caregiver data to the combobox
     */
    private void createComboBoxData() {
        CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            caregiverList = (ArrayList<Caregiver>) dao.readAll();
            for (Caregiver caregiver : caregiverList) {
                this.caregiverComboBoxData.add(caregiver.getSurname() + ", " + caregiver.getFirstName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles the usage of the combobox
     */
    @FXML
    public void handleComboBox() {
        String p = this.comboBox.getSelectionModel().getSelectedItem();
        List<Caregiver> allCaregivers;
        Caregiver caregiver = searchInList(p);
    }

    /**
     * searches in the caregiverlist for the caregiver
     *
     * @param surname
     * @return
     */
    private Caregiver searchInList(String surname) {
        for (int i = 0; i < this.caregiverList.size(); i++) {
            if (this.caregiverList.get(i).getSurname().equals(surname)) {
                return this.caregiverList.get(i);
            }
        }
        return null;
    }
}