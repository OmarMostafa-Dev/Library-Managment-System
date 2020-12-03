package application;

import java.io.Serializable;

/**
 * this class contains librarian data, uses getters and setters and implements serializable so that it
 * can be used by the object IO stream
 */
public class Librarian extends Person implements Serializable {

    private int id;
    private String email;

    public Librarian(String firstName, String lastName, String address, String gender, int id, String email) {
        super(firstName, lastName, address, gender);
        this.id = id;
        this.email = email;
    }

    public Librarian(String firstName, String lastName, String address, String gender) {
        super(firstName, lastName, address, gender);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
