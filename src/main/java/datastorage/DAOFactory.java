package datastorage;

public class DAOFactory {

    private static DAOFactory instance;

    /**
     * constructs a DAOFactory
     */
    private DAOFactory() {

    }

    /**
     * @return current DAOFactory
     */
    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    /**
     * @return new TreatmentDAO
     */
    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }

    /**
     * @return new PatientDAO
     */
    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }

    /**
     * @return new CaregiverDAO
     */
    public CaregiverDAO createCaregiverDAO() {
        return new CaregiverDAO(ConnectionBuilder.getConnection());
    }
}