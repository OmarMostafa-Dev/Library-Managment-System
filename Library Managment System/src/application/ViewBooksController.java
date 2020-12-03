package application;

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
 * Class responsible for loading Table view for books
 */
public class ViewBooksController implements Initializable {

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> bookTitleCol;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> publisherCol ;

    @FXML
    private TableColumn<Book, String> genreCol ;

    @FXML
    private TableColumn<Book, String> yearCol ;


    /**
     * Loads table by adding to the observable list.
     * @return list of books
     */
    public List<Book> loadTable() {

        try {

            List<Book> books = ClientSide.viewBooks();

            ObservableList<Book> data = FXCollections.observableArrayList();

            for (int i = 0; i < books.size() ; i++) {
                if (books.get(i) != null) {
                    data.add(new Book(books.get(i).getBookTitle(), books.get(i).getAuthor(), books.get(i).getPublisher(), books.get(i).getGenre(), books.get(i).getYear()));
                }
            }

            return books;

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

        bookTitleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        bookTable.getItems().setAll(loadTable());
    }
}
