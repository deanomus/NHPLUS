package model;

public class Caregiver extends Person {

    private long cid;
    private String telephoneNumber;

    public Caregiver(String firstName, String surname, String telephoneNumber) {
        super(firstName, surname);
        this.telephoneNumber = telephoneNumber;
    }

    public Caregiver(long cid, String firstName, String surname, String telephoneNumber) {
        super(firstName, surname);
        this.cid = cid;
        this.telephoneNumber = telephoneNumber;
    }
    public long getCid() {
        return cid;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String toString() {
        return "Caregiver" + "\nID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nTelephone Number: " + this.getTelephoneNumber() +
                "\n";
    }
}
