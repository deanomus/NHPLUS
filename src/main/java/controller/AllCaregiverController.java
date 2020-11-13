package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Caregiver;

import java.sql.SQLException;
import java.util.List;

/**
 * The <code>AllCaregiverController</code> contains the entire logic of the caregiver view. It determines which data is displayed and how to react to events.
 */
public class AllCaregiverController {
    @FXML
    private TableView<Caregiver> tableView;
    @FXML
    private TableColumn<Caregiver, Integer> colID;
    @FXML
    private TableColumn<Caregiver, String> colFirstName;
    @FXML
    private TableColumn<Caregiver, String> colSurname;
    @FXML
    private TableColumn<Caregiver, String> colTelephoneNumber;
    @FXML
    private TableColumn<Caregiver, String> colPassword;

    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txtFirstname;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtTelephoneNumber;
    @FXML
    TextField txtPassword;

    private ObservableList<Caregiver> tableviewContent = FXCollections.observableArrayList();
    private CaregiverDAO dao;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<Caregiver, Integer>("cid"));

        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("firstName"));
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelephoneNumber.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("telephoneNumber"));
        this.colTelephoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tableView.setItems(this.tableviewContent);
    }

    /**
     * handles new firstname of caregiver value
     *
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Caregiver, String> event) {
        event.getRowValue().setFirstName(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new surname of caregiver value
     *
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Caregiver, String> event) {
        event.getRowValue().setSurname(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new telephone number value
     *
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditTelephoneNumber(TableColumn.CellEditEvent<Caregiver, String> event) {
        event.getRowValue().setTelephoneNumber(event.getNewValue());
        doUpdate(event);
    }

    /**
     * updates a caregiver by calling the update-Method in the {@link CaregiverDAO}
     *
     * @param t row to be updated by the user (includes the caregiver)
     */
    private void doUpdate(TableColumn.CellEditEvent<Caregiver, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * calls readAll in {@link CaregiverDAO} and shows caregivers in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        List<Caregiver> allCaregivers;
        try {
            allCaregivers = dao.readAll();
            for (Caregiver c : allCaregivers) {
                this.tableviewContent.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a delete-click-event. Calls the delete methods in the {@link CaregiverDAO}
     */
    @FXML
    public void handleDeleteRow() {
        Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        this.tableView.getItems().remove(selectedItem);
        try {
            dao.deleteById((int) selectedItem.getCid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a add-click-event. Creates a caregiver and calls the create method in the {@link CaregiverDAO}
     */
    @FXML
    public void handleAdd() {
        String firstname = this.txtFirstname.getText();
        String surname = this.txtSurname.getText();
        String telephoneNumber = this.txtTelephoneNumber.getText();
        String password = this.txtPassword.getText();
        try {
            Caregiver c = new Caregiver(firstname, surname, telephoneNumber, password);
            dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    /**
     * removes content from all textfields
     */
    private void clearTextfields() {
        this.txtFirstname.clear();
        this.txtSurname.clear();
        this.txtTelephoneNumber.clear();
        this.txtPassword.clear();
    }
}
