package application.server;

import application.Book;
import application.Client;
import application.Librarian;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;


/** this class is responsible for handling class client requests*/
public class ClientTaskHandler implements Runnable {

    /** Server output stream */
    private ObjectInputStream inObj;
    /** Server input stream */
    private ObjectOutputStream outObj;


    /** Initializes the IO stream with client input and output stream */
    public ClientTaskHandler(Socket clientSocket) throws IOException {
        inObj = new ObjectInputStream(clientSocket.getInputStream());
        outObj = new ObjectOutputStream(clientSocket.getOutputStream());
    }


    /**
     * reads the request type and handles it accordingly.
     * if the user requests to sign in, the server passes the
     * username and password and returns the validation from the
     * database, if it was an add book request the book is passed to
     * the database and returns to the user if the book was added or
     * not, to view book server requests a list of books from the database and returns
     * it to the client, to sign up the username and password are passed to the
     * database and the server is returns to the user if the account was
     * added or not, client details is added to the database
     * when user requests to loan a book, to view the loaned books
     * the database returns the list of clients, the add librarian
     * passes the librarian details to the database to be added,
     * the view librarian returns the list of librarians to the user.
     */
    @Override
    public void run() {
        try {

            while (true) {
                String type = (String) inObj.readObject();
                ServerSide.updateTextArea("Client is requesting to " + type+'\n');
                if (type.equals("Sign in")) {
                    String username = (String) inObj.readObject();
                    String password = (String) inObj.readObject();
                    Database.initializeDB();
                    outObj.writeObject(Database.signInDB(username, password));
                }
                else if (type.equals("Add Book")) {
                    Book book = (Book) inObj.readObject();
                    Database.initializeDB();
                    outObj.writeObject(Database.addBookDB(book));

                }
                else if (type.equals("View books")){
                    Database.initializeDB();
                    outObj.writeObject(Database.viewBookDB());
                }
                else if(type.equals("Sign Up")) {
                    String username = (String) inObj.readObject();
                    String password = (String) inObj.readObject();
                    Database.initializeDB();
                    outObj.writeObject(Database.signUpDB(username, password));
                }
                else if (type.equals("Loan Book")) {
                    Client client = (Client) inObj.readObject();
                    Database.initializeDB();
                    outObj.writeObject(Database.loanBookDB(client));
                }
                else if(type.equals("View Loaned Books")) {
                    Database.initializeDB();
                    outObj.writeObject(Database.viewLoanedBooksDB());
                }
                else if(type.equals("Add Librarian")) {
                    Librarian librarian = (Librarian) inObj.readObject();
                    Database.initializeDB();
                    outObj.writeObject(Database.addLibrarianDB(librarian));
                }
                else if(type.equals("View Librarian")) {
                    Database.initializeDB();
                    outObj.writeObject(Database.viewLibrarianDB());
                }

            }

        }catch (SQLException ex) {
            try {
                outObj.writeObject(false);
            } catch (IOException e) {
                ServerSide.updateTextArea("IO Exception: " + e.getMessage() +'\n');
            }
        }
        catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
