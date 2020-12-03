package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * handles the librarian page events
 */
public class AddLibrarianController {

    /**
     * librarian ID
     */
    @FXML
    private TextField id;

    /**
     * Librarian first name
     */
    @FXML
    private TextField firstName;

    /**
     * librarian last name
     */
    @FXML
    private TextField lastName;

    /**
     * librarian email
     */
    @FXML
    private TextField email;

    /**
     * librarian address
     */
    @FXML
    private TextField address;

    /**
     * librarian gender
     */
    @FXML
    private TextField gender;


    /**
     * this method first checks if field were empty and alerts the user if so,
     * if not it checks if the gender is written in the right format
     * then passes request to the server to add a librarian
     * if it was successful it alerts the user with librarian add message and
     * the menu page is loaded if it alerts the user with ID not unique
     * if any interruption in the IO stream that indicates server is offline
     * and kicks the user to the sign in/ sign up page
     * if a user enters a string or any incorrect data type
     * the user receives an alert to enter an integer
     * @param event mouse click event
     */
    @FXML
    public void submit(ActionEvent event) {

        try {

            if (id.getText().equals("")|| firstName.getText().equals("")|| lastName.getText().equals("")|| gender.getText().equals("") || email.getText().equals("")) {
                AlertMessage.alert(Alert.AlertType.CONFIRMATION, "Missing information");
                return;
            }

            if( !(gender.getText().equals("f")||gender.getText().equals("F")||gender.getText().equals("m")||gender.getText().equals("M"))) {
                AlertMessage.alert(Alert.AlertType.CONFIRMATION, "Please enter gender as m/M for male and f/F for female");
                return;
            }


            if (ClientSide.addLibrarianRequest(new Librarian(firstName.getText(), lastName.getText(), address.getText(), gender.getText(), Integer.parseInt(id.getText().trim()), email.getText()))) {

                AlertMessage.alert(Alert.AlertType.CONFIRMATION, "Librarian added");

                Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(new Scene(root, 600, 400));

            }
            else {
                AlertMessage.alert(Alert.AlertType.CONFIRMATION,"ID must be unique");
            }
        } catch (IOException | ClassNotFoundException e) {
            AlertMessage.alert(Alert.AlertType.ERROR,"Server offline");
            new MenuController().signOut(event);
        }
        catch (NumberFormatException ex) {
            AlertMessage.alert(Alert.AlertType.CONFIRMATION, "ID must be an integer");
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
