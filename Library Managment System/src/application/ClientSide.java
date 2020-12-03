package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * this class is responsible for handling all the client requests to the server
 */
public class ClientSide {

    /** Server socket*/
    private static Socket socket;
    /** Server Port */
    private static final int PORT = 7590;
    /** Client output stream */
    private static ObjectOutputStream out;
    /** Client input stream */
    private static ObjectInputStream in;


    /**
     * used to establish a connection to the server.
     * it initializes the client socket with the
     * server address and port number
     * and initializes the IO stream so that
     * the client can write to/ from the server.
     * @throws IOException if there is an interruption in the IO stream the
     * an io exception is thrown to the caller to handle.
     */
    public static void establishConnection() throws IOException {
        socket = new Socket("127.0.0.1", PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }


    /**
     * request sign in validation from the server.
     * the method writes the request type,
     * clients user name and password
     * to the output stream
     * and the client reads what the server wrote
     * in the input stream.
     * @param username client's username
     * @param pass client's password
     * @return validation from the database
     * @throws IOException if there is an interruption in the IO stream then
     * an IO exception is thrown to the caller to handle.
     * @throws ClassNotFoundException if a class object is not serialized.
     */
    public static Boolean signInRequest(String username, String pass) throws IOException,ClassNotFoundException {
        out.writeObject("Sign in");
        out.writeObject(username);
        out.writeObject(pass);
        return (Boolean) in.readObject();

    }

    /**
     * request if the book data can be added or not to the database.
     * it writes the request type and the book data
     * to the output stream and the client input stream reads what the server wrote
     * @param book book data
     * @return validation from the database if book was added or not
     * @throws IOException if there is an interruption in the IO stream then an IO exception
     * is thrown to the caller to handle
     * @throws ClassNotFoundException if class object is not serialized/ implements serializable interface
     */
    public static Boolean addBookRequest(Book book) throws IOException, ClassNotFoundException {
        out.writeObject("Add Book");
        out.writeObject(book);
        return (Boolean) in.readObject();

    }

    /**
     * requests for the book data in the database.
     * it writes the request type in the output stream and
     * the server responds with lists of book objects
     * @return the list of books from the database result set
     * @throws IOException if there is an interruption in the IO stream then an IO exception is
     * thrown to the caller to handle
     * @throws ClassNotFoundException if class object is not serialized/ implements serializable
     */
    public static List<Book> viewBooks() throws IOException, ClassNotFoundException {
        out.writeObject("View books");
        return (ArrayList<Book>) in.readObject();
    }


    /**
     * request sign up validation from the server.
     * the method writes the request type,
     * clients user name and password
     * to the output stream
     * and the client reads what the server wrote
     * in the input stream.
     * @param username client's username
     * @param pass client's password
     * @return validation from the database
     * @throws IOException if there is an interruption in the IO stream then
     * an IO exception is thrown to the caller to handle.
     * @throws ClassNotFoundException if a class object is not serialized.
     */
    public static Boolean signUpRequest(String username, String pass) throws IOException, ClassNotFoundException {
        out.writeObject("Sign Up");
        out.writeObject(username);
        out.writeObject(pass);
        return (Boolean) in.readObject();

    }


    /**
     * request if the client data can be added or not to the database.
     * it writes the request type and the client data
     * to the output stream and the client input stream reads what the server wrote
     * @param client client data
     * @return validation from the database if client was added or not
     * @throws IOException if there is an interruption in the IO stream then an IO exception
     * is thrown to the caller to handle
     * @throws ClassNotFoundException if class object is not serialized/ implements serializable interface
     */
    public static Boolean loanBookRequest(Client client) throws IOException, ClassNotFoundException {
        out.writeObject("Loan Book");
        out.writeObject(client);
        return (Boolean) in.readObject();
    }


    /**
     * requests for the client data in the database.
     * it writes the request type in the output stream and
     * the server responds with lists of client objects
     * @return the list of clients from the database result set
     * @throws IOException if there is an interruption in the IO stream then an IO exception is
     * thrown to the caller to handle
     * @throws ClassNotFoundException if class object is not serialized/ implements serializable
     */
    public static List<Client> viewLoanedBooksRequest() throws IOException, ClassNotFoundException {
        out.writeObject("View Loaned Books");
        return (ArrayList<Client>) in.readObject();
    }


    /**
     * request if the librarian data can be added or not to the database.
     * it writes the request type and the librarian data
     * to the output stream and the client input stream reads what the server wrote
     * @param librarian librarian data
     * @return validation from the database if librarian was added or not
     * @throws IOException if there is an interruption in the IO stream then an IO exception
     * is thrown to the caller to handle
     * @throws ClassNotFoundException if class object is not serialized/ implements serializable interface
     */
    public static Boolean addLibrarianRequest(Librarian librarian) throws IOException, ClassNotFoundException {
        out.writeObject("Add Librarian");
        out.writeObject(librarian);
        return (Boolean) in.readObject();
    }


    /**
     * requests for the librarians data in the database.
     * it writes the request type in the output stream and
     * the server responds with lists of librarian objects
     * @return the list of librarians from the database result set
     * @throws IOException if there is an interruption in the IO stream then an IO exception is
     * thrown to the caller to handle
     * @throws ClassNotFoundException if class object is not serialized/ implements serializable
     */
    public static List<Librarian> viewLibrariansRequest() throws IOException, ClassNotFoundException {
        out.writeObject("View Librarian");
        return (ArrayList<Librarian>) in.readObject();
    }

}