package model;

/**
 * Both patients and caregivers are persons and inherit params from this class.
 */
public abstract class Person {
    private String firstName;
    private String surname;

    /**
     * constructs a person from the given params.
     * @param firstName
     * @param surname
     */
    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName new firstname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname new surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
