package model;

/**
 * Caregivers work in a NURSING home and treat their patients.
 */
public class Caregiver extends Person {
    private long cid;
    private String telephoneNumber;

    /**
     * constructs a caregiver from the given params.
     * @param firstName
     * @param surname
     * @param telephoneNumber
     */
    public Caregiver(String firstName, String surname, String telephoneNumber) {
        super(firstName, surname);
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * constructs a caregiver from the given params.
     * @param cid
     * @param firstName
     * @param surname
     * @param telephoneNumber
     */
    public Caregiver(long cid, String firstName, String surname, String telephoneNumber) {
        super(firstName, surname);
        this.cid = cid;
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * @return caregiver id
     */
    public long getCid() {
        return cid;
    }

    /**
     * @return telephone number of caregiver
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * @param telephoneNumber new telephone number
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * @return string-representation of the caregiver
     */
    public String toString() {
        return "Caregiver" + "\nID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nTelephone Number: " + this.getTelephoneNumber() +
                "\n";
    }
}
