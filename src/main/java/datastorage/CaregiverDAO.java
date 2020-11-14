package datastorage;

import model.Caregiver;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaregiverDAO extends DAOimp<Caregiver> {

    public CaregiverDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        try {
            return String.format("INSERT INTO caregiver (firstname, surname, telephoneNumber, password) VALUES ('%s', '%s','%s', '%s')",
                    caregiver.getFirstName(), caregiver.getSurname(), caregiver.getTelephoneNumber(), utils.HashMD5.HashPassword(caregiver.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM caregiver WHERE cid = %d", key);
    }

    public static String getPasswordByUsernameString(String username) {
        return String.format("SELECT password FROM caregiver WHERE surname = %s", username);
    }

    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver c = null;
        c = new Caregiver(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
        return c;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM caregiver";
    }

    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver c = null;
        while (result.next()) {
            c = new Caregiver(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4), result.getString(5));
            list.add(c);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {
        return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s', telephoneNumber = '%s', password = '%s' WHERE cid = %d",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getTelephoneNumber(), caregiver.getPassword(), caregiver.getCid());
    }

    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM caregiver WHERE cid=%d", key);
    }
}
