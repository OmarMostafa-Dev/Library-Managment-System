package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * this class is responsible for displaying
 * the main menu and handling all the user events
 */
public class MenuController {

    /**
     * load the add book fxml file and prompts the user to enter the data.
     * checks if connection is
     * available by re-establishing a connection to
     * the server, if connection is successful the AddBook page
     * is loaded, if something is wrong with the server
     * the user receives an alert message and sent
     * back to the sign in/ sign up page
     * @param event handles the mouse click from the user
     */
    @FXML
    public void addBook(ActionEvent event)  {
        try {
            ClientSide.establishConnection();
            Parent root = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root,600,400));
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Connection to server is lost", e.getMessage());
            signOut(event);
        }
    }

    /**
     * Loads the view books fxml file.
     * it checks if connection is
     * available by re-establishing a connection to
     * the server, if connection is successful the ViewBooks page
     * and the TableView is loaded with the data
     * if something is wrong with the server
     * the user receives an alert message and sent
     * back to the sign in/ sign up page
     * @param event handles the mouse click from the user
     */
    @FXML
    public void viewBook(ActionEvent event) {
        try {
            ClientSide.establishConnection();
            Parent root = FXMLLoader.load(getClass().getResource("ViewBooks.fxml"));
            new ViewBooksController().loadTable();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 605, 400));
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Connection to server is lost", e.getMessage());
            signOut(event);
        }
    }

    /**
     * Loads the loan book fxml file and prompts the user to enter the book data.
     * it checks if connection is
     * available by re-establishing a connection to
     * the server, if connection is successful the LoanBook page is loaded,
     * if something is wrong with the server
     * the user receives an alert message and sent
     * back to the sign in/ sign up page
     * @param event handles the mouse click from the user
     */
    @FXML
    public void loanBook(ActionEvent event) {
        try {
            ClientSide.establishConnection();
            Parent root = FXMLLoader.load(getClass().getResource("LoanBook.fxml"));
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Connection to server is lost", e.getMessage());
            signOut(event);
        }
    }


    /**
     * Loads the view loan book page.
     * the method first re-establishes a connection
     * and if there's an error an alert message
     * is show to the user and the user is kicked to
     * the sign in/ sign up page.
     * if there was no connection error the table view is loaded
     * with the data and displayed to the user.
     * @param event handles the mouse click from the user
     */
    @FXML
    public void viewLoanedBooks(ActionEvent event) {
        try {
            ClientSide.establishConnection();
            Parent root = FXMLLoader.load(getClass().getResource("ViewLoanedBooks.fxml"));
            new ViewLoanedBooksController().loadTable();
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 605, 400));
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Connection to server is lost", e.getMessage());
            signOut(event);
        }
    }

    /**
     * load the add librarian page and prompts the user to enter the data.
     * checks if connection is
     * available by re-establishing a connection to
     * the server, if connection is successful the AddLibrarian page
     * is loaded, if something is wrong with the server
     * the user receives an alert message and sent
     * back to the sign in/ sign up page
     * @param event handles the mouse click from the user
     */
    @FXML
    public void addLibrarian(ActionEvent event) {
        try {
            ClientSide.establishConnection();
            Parent root = FXMLLoader.load(getClass().getResource("AddLibrarian.fxml"));
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Connection to server is lost", e.getMessage());
            signOut(event);
        }
    }



     /**
     * Loads the view librarian page.
     * the method first re-establishes a connection
     * and if there's an error an alert message
     * is show to the user and the user is kicked to
     * the sign in/ sign up page.
     * if there was no connection error the table view is loaded
     * with the data and displayed to the user.
     * @param event handles the mouse click from the user
     */
    @FXML
    public void viewLibrarian(ActionEvent event) {
        try {
            ClientSide.establishConnection();

            Parent root = FXMLLoader.load(getClass().getResource("ViewLibrarian.fxml"));

            new ViewLibrarianController().loadTable();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root, 605, 400));

            stage.show();
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Connection to server is lost", e.getMessage());
            signOut(event);
        }
    }


    /**
     * Kicks the user to the sign in/ sign up page.
     * load the SignInSignUp fxml and if an error occurs with
     * the file it alerts the user with the error
     * @param event handles the mouse click from the user
     */
    @FXML
    public void signOut(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("SignInSignUp.fxml"));
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root,560,310));

        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Error loading page", "IO Exception", e.getMessage());
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
        try {
            ClientSide.establishConnection();
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));

        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Connection to server is lost", e.getMessage());
            signOut(event);
        }
    }

}
