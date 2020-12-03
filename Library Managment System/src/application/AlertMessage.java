package application;

import javafx.scene.control.Alert;

/**
 * this class is responsible for dealing with alert messages.
 */
public class AlertMessage {


    /**
     * Displays an alert based on the parameters
     * @param type alert type
     * @param title alert title
     * @param header alert header
     * @param content alert content
     */
    public static void alert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    /**
     * Displays an alert based on the parameters
     * @param type alert type
     * @param content alert content
     */
    public static void alert(Alert.AlertType type, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Displays an alert based on the parameters
     * @param type alert type
     * @param content alert content
     * @param header alert header
     */
    public static void alert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
