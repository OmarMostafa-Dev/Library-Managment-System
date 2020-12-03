package application.server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.AlertMessage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * this class is responsible for handling all
 * the back end tasks
 */
public class ServerSide extends Application implements ThreadHandler {

    /** Server port */
    private static final int PORT = 7590;
    /** Server text area */
    private static TextArea textArea;
    /** Client thread pool */
    private ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * creates a GUI for the server Application.
     * it creates a text area sets it to
     * not editable and creates
     * password and user text field and
     * label then adds the text field and label
     * to an HBox and the two HBoxes are added to
     * a VBox which is used as the scene
     * the user is then prompted to
     * enter the user and password
     * of the database if it's wrong
     * the user is alerted that the username
     * or password is wrong if its correct
     * the server can begin to execute clients threads
     * the setOnClose() is used so that
     * if the user exits the stage the server terminates all
     * running processes and exits
     */
    @Override
    public void start(Stage primaryStage) {

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefHeight(399);
        textArea.setPrefWidth(598);

        TextField userTxt = new TextField();
        PasswordField passTxt = new PasswordField();
        Label userLabel = new Label("Username:");
        Label passLabel = new Label("Password:");
        Button submit = new Button("Submit");

        HBox username = new HBox(5);
        username.getChildren().addAll(userLabel, userTxt);
        username.setAlignment(Pos.CENTER);
        HBox password = new HBox(5);
        password.getChildren().addAll(passLabel, passTxt);
        password.setAlignment(Pos.CENTER);
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(username, password, submit);
        primaryStage.setTitle("Enter database info");
        primaryStage.setScene(new Scene(box, 300, 130));
        primaryStage.setResizable(false);
        primaryStage.show();

        submit.setOnAction(actionEvent-> {
            Database.setUser(userTxt.getText());
            Database.setPassword(passTxt.getText());
            try {
                Database.initializeDB();
                Database.getConnection().close();
                executeThreads();
                //ScrollPane root = new ScrollPane(textArea);
                primaryStage.setTitle("Library Server");
                primaryStage.setScene(new Scene(textArea,600,400));

            }catch (SQLException | ClassNotFoundException e) {
                AlertMessage.alert(Alert.AlertType.ERROR, "Wrong username or password");
            }
        });

        primaryStage.setOnCloseRequest(e->{
                Platform.exit();
                System.exit(0);
        });

    }

    /**
     * updates the server text area.
     * used to update server text area about
     * clients activity using Platform.runLater()
     * @param message the message to be appended to the text area
     */
    public static void updateTextArea(String message) {
        Platform.runLater(() ->{
            textArea.appendText(message);
        });
    }


    /**
     * responsible for accepting and executing client threads
     */
    @Override
    public void executeThreads() {
        executor.execute(() -> {

            try {
                ServerSocket socket = new ServerSocket(PORT);

                while (true) {
                    Socket clientSocket = socket.accept();
                    executor.execute(new ClientTaskHandler(clientSocket));
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
    }
}
