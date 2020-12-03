package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class responsible for handling
 * AddBook page events.
 */
public class AddBookController {

    /**
     * Book title field
     */
    @FXML
    private TextField bookTitle;

    /**
     * Book author field
     */
    @FXML
    private TextField author;

    /**
     * Book publisher field
     */
    @FXML
    private TextField publisher;

    /**
     * Book genre field
     */
    @FXML
    private TextField genre;

    /**
     * book year field
     */
    @FXML
    private TextField year;

    /**
     * when the user clicks the button.
     * the method starts by checking if the book title and author
     * fields were empty
     * requests from the database to add
     * the book data
     * if data was added it alerts the
     * user that the book was successfully added
     * then changes the scene to Menu.fxml.
     * if an interruption in the stream happens then the
     * user is kicked back the sign in/sign up page
     * @param event button click event
     */
    @FXML
    public void bookAddBtn(ActionEvent event) {

        try {

            if (bookTitle.getText().equals("")||author.getText().equals("")) {
                AlertMessage.alert(Alert.AlertType.CONFIRMATION, "Book title or Author field is empty");
                return;
            }

            if (ClientSide.addBookRequest(new Book(bookTitle.getText(),author.getText(),publisher.getText(),genre.getText(),year.getText()))) {

                AlertMessage.alert(Alert.AlertType.CONFIRMATION, "Book Added");

                Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

                stage.setScene(new Scene(root, 600, 400));

            } else {

                AlertMessage.alert(Alert.AlertType.WARNING,"Can't add book");

            }
        }catch (IOException | ClassNotFoundException ex) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Server offline");
            new MenuController().signOut(event);
        }
        catch (NumberFormatException e) {
            AlertMessage.alert(Alert.AlertType.WARNING,"Please enter a number in the year field");
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
