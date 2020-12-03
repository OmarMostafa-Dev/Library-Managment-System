package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;


/**
 * handles the loan book page.
 */
public class LoanBookController {


    /**
     * client first name
     */
    @FXML
    private TextField firstNm;

    /**
     * client last name
     */
    @FXML
    private TextField lastNm;

    /**
     * client address
     */
    @FXML
    private TextField address;

    /**
     * client gender
     */
    @FXML
    private TextField gender;

    /**
     * loaned book
     */
    @FXML
    private TextField book;

    /**
     * loaned book due date
     */
    @FXML
    private TextField dueD;


    /**
     * Handles the loan book request.
     * The method first check if the gender is entered in the right format or left empty,
     * then check if the due date field and book field are left empty if they are empty the user
     * is alerted then all the fields entered are passed onto a client object and then
     * passed to the server to be added into the database and any interruption in
     * the IO stream the user is alerted.
     * @param event handles the mouse click from the user
     */
    @FXML
    public void loanBook(ActionEvent event) {

        if( !(gender.getText().equals("") || gender.getText().equals("f")||gender.getText().equals("F")||gender.getText().equals("m")||gender.getText().equals("M"))) {
            AlertMessage.alert(Alert.AlertType.CONFIRMATION,"Please enter gender as m/M for male and f/F for female");
            return;
        }

        if (book.getText().equals("") || dueD.getText().equals("")) {
            AlertMessage.alert(Alert.AlertType.CONFIRMATION, "Due date/book empty field");
            return;
        }

        Client client = new Client(firstNm.getText(), lastNm.getText(), address.getText(), gender.getText());
        client.setLoanedBook(new Book(book.getText()));
        client.setDueDate(dueD.getText());
        try {
            if (ClientSide.loanBookRequest(client))
                AlertMessage.alert(Alert.AlertType.INFORMATION,"Book loaned");
            else
                AlertMessage.alert(Alert.AlertType.INFORMATION, "Book can't be loaned");
        } catch (IOException | ClassNotFoundException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Server offline");
            new MenuController().signOut(event);
        }

    }

    /**
     * load the menu page.
     * checks if connection is
     * available by re-establishing a connection to
     * the server, if connection is successful the Menu page
     * is loaded, if something is wrong with the server
     * the user receives an alert message and sent
     * back to the sign in/ sign up page
     * @param event handles the mouse click from the user
     */
    @FXML
    public void back(ActionEvent event) {
        new MenuController().back(event);
    }

}
