package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Starting point of the client app
 */
public class Main extends Application {

    /**
     * loads the sign in/ sign up page
     * @param primaryStage the main client stage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SignInSignUp.fxml"));
            primaryStage.setTitle("Library Management System CS244");
            primaryStage.setScene(new Scene(root, 560, 310));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, "Error loading page", "IO Exception", e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
