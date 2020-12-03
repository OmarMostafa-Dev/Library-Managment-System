package application;

import java.io.Serializable;

/**
 * this class store person info
 */
public abstract class Person implements Serializable {

    /**
     * person first name
     */
    private String firstName;
    /**
     * person last name
     */
    private String lastName;
    /**
     * person address
     */
    private String address;
    /**
     * person gender
     */
    private String gender;

    public Person(String firstName, String lastName, String address, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * overrides to string to display all person info
     * @return string with all the person data
     */
    @Override
    public String toString() {
        return "Person first name: " + firstName +"\nLast name: " +lastName+"\nGender: " +gender;
    }
}
