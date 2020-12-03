package application;

import java.io.Serializable;


/**
 * this class contains client data, uses getters and setters and implements serializable so that it
 * can be used by the object IO stream
 */
public class Client extends Person implements Serializable {


    private Book loanedBook;
    private String dueDate;
    private String id;

    public Client(String firstName, String lastName, String address, String gender) {
        super(firstName, lastName, address, gender);
    }

    public Book getLoanedBook() {
        return loanedBook;
    }

    public void setLoanedBook(Book loanedBook) {
        this.loanedBook = loanedBook;
    }


    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * prints client info in string format and if the client hasn't loaned a book then
     * it prints the client info without the book
     * @return client string format
     */
    @Override
    public String toString() {
        return loanedBook != null ? super.toString() + "\nBook: " + loanedBook.getBookTitle() + '\n' : super.toString();
    }


}
