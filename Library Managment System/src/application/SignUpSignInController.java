package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class is responsible for handling sign in sign up page events
 */
public class SignUpSignInController {


    /**
     * Client password
     */
    @FXML
    private PasswordField password;

    /**
     * Client username
     */
    @FXML
    private TextField username;


    /**
     * This method signs the user by first
     * checking if the username and password aren't empty
     * if they aren't it try to establish a connection to the server
     * if it connects the username and password are then passed to server
     * to be validated and if its true it loads the menu page.
     * any interruption in the IO stream an alert message is shown to the user
     * @param event click event
     */
    @FXML
    public void signIn(ActionEvent event) {
        String user = username.getText();
        String pass = password.getText();

        if (user.equals("")|| pass.equals("")) {
            AlertMessage.alert(Alert.AlertType.WARNING, null, null, "Please enter your sign in info");
            return;
        }

        try {

            ClientSide.establishConnection();

            if (ClientSide.signInRequest(username.getText(), password.getText())) {

                Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(new Scene(root, 600, 400));

            } else {
                AlertMessage.alert(Alert.AlertType.WARNING, "Invalid Username or password", "Username or password are incorrect");

            }
        }catch (IOException | ClassNotFoundException ex) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Server is offline");
        }
    }

    /**
     * This method signs up the user by first
     * checking if the username and password aren't empty
     * if they aren't it try to establish a connection to the server
     * if it connects the username and password are then passed to server
     * to be validated and if its true it confirms to the user that the
     * account was added.
     * any interruption in the IO stream an alert message is shown to the user
     * @param event click event
     */
    @FXML
    public void signUp(ActionEvent event) {

        String user = username.getText();
        String pass = password.getText();


        if (user.equals("")|| pass.equals("")) {
            AlertMessage.alert(Alert.AlertType.WARNING, "Please enter your sign up info");
            return;
        }

        try {

            ClientSide.establishConnection();

            if (ClientSide.signUpRequest(username.getText(), password.getText())) {
                AlertMessage.alert(Alert.AlertType.CONFIRMATION, "Account added");
            }
            else {

                AlertMessage.alert(Alert.AlertType.WARNING, "Username already taken");

            }

        } catch (IOException | ClassNotFoundException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Server is offline");
        }


    }
}
