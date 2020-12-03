package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Class responsible for loading Table view for Loaned books
 */
public class ViewLoanedBooksController implements Initializable {


    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> clientIdCol;

    @FXML
    private TableColumn<Client, String> firstNameCol;

    @FXML
    private TableColumn<Client, String> lastNameCol;

    @FXML
    private TableColumn<Client, String> addressCol;

    @FXML
    private TableColumn<Client, String> genderCol;

    @FXML
    private TableColumn<Client, String> bookCol;

    @FXML
    private TableColumn<Client, String> dueDateCol;

    /**
     * Loads table by adding to the observable list.
     * @return list of clients
     */
    public List<Client> loadTable() {

        try {

            List<Client> clients = ClientSide.viewLoanedBooksRequest();

            ObservableList<Client> data = FXCollections.observableArrayList();

            for (int i = 0; i < clients.size() ; i++) {
                if (clients.get(i) != null) {
                    data.add(new Client(clients.get(i).getFirstName(), clients.get(i).getLastName(), clients.get(i).getAddress(), clients.get(i).getGender()));
                    data.get(i).setDueDate(clients.get(i).getDueDate());
                    data.get(i).setLoanedBook(clients.get(i).getLoanedBook());
                    data.get(i).setId(clients.get(i).getId());
                }
            }

            return clients;

        } catch (IOException | ClassNotFoundException e) {
            AlertMessage.alert(Alert.AlertType.ERROR, null, null, e.getMessage());

        }
        return null;
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


    /**
     * initialize the columns with object attributes/variables
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clientIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        bookCol.setCellValueFactory(clientName -> new SimpleStringProperty(clientName.getValue().getLoanedBook().getBookTitle()));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        clientTable.getItems().setAll(loadTable());
    }
}
