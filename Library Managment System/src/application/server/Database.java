package application.server;

import application.Book;
import application.Client;
import application.Librarian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * handles all events related to MySQL Database
 */
public class Database {

    private static Statement statement;
    private static Connection connection;
    private static String user;
    private static String password;

    public static Connection getConnection() {
        return connection;
    }

    /**
     * Sets the database user.
     * @param user database user
     */
    public static void setUser(String user) {
        Database.user = user;
    }

    /**
     * Sets the database password.
     * @param password database password
     */
    public static void setPassword(String password) {
        Database.password = password;
    }

    /**
     * Initializes the database by setting the driver
     * and setting a connection to the database.
     * @throws SQLException if the user name or password were wrong
     * @throws ClassNotFoundException if the class can't be located
     */
    public static void initializeDB() throws SQLException, ClassNotFoundException {
         Class.forName("com.mysql.cj.jdbc.Driver");
         ServerSide.updateTextArea("Database driver loaded\n");
         connection = DriverManager.getConnection("jdbc:mysql://localhost/library", user, password);
         ServerSide.updateTextArea("Connected to database\n");
         statement = connection.createStatement();

    }


    /**
     * validates the credentials by pulling the data from the database
     * and comparing it. if it equals true then the user can sign in
     * if it's false the user can't sign in
     * @param username client username
     * @param password client password
     * @return the credentials validations
     * @throws SQLException database access error occurs
     */
    public static Boolean signInDB(String username, String password) throws SQLException {

        String query = "SELECT *" +
                "FROM admins " +
                "WHERE username = '" + username + "';";

        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            String pass = resultSet.getString("password");
            if (pass.equals(password)) {
                connection.close();
                ServerSide.updateTextArea("Client is signed in\n");
                return true;
            }
        }
        return false;
    }

    /**
     * Add book details by inserting it into database.
     * @param book book details
     * @return validation
     * @throws SQLException database access error occurs
     */
    public static Boolean addBookDB(Book book) throws SQLException {

        String query = "INSERT INTO book(book_title, author, publisher, genre, year)" +
                "VALUES('"+ book.getBookTitle() + "', '" +book.getAuthor()+ "'" +
                ",'"+ book.getPublisher()+ "','"+book.getGenre()+"','" + book.getYear()+"');";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.executeUpdate();
        connection.close();
        ServerSide.updateTextArea("Client added book\n" + book.toString());
        return true;

    }

    /**
     * Requests data stored in the book table
     * @return list of books
     * @throws SQLException database access error occurs
     */
    public static List<Book> viewBookDB() throws SQLException {

        String query = "SELECT book_title, author, publisher, genre, year FROM book;";

        ResultSet rs = statement.executeQuery(query);

        List<Book> books = new ArrayList<>();

        while (rs.next()) {
            books.add(new Book(rs.getString("book_title"), rs.getString("author"), rs.getString("publisher"), rs.getString("genre"), rs.getString("year")));
        }
        connection.close();

        return books;

    }

    /**
     * Inserts username and password of the admin into admins table in the database.
     * @param username admin username
     * @param password admin password
     * @return validation if data were inserted or not
     * @throws SQLException database access error occurs
     */
    public static boolean signUpDB(String username, String password) throws SQLException {


        String query = "INSERT INTO admins(username, password)" +
                "VALUES('"+ username + "', '" +password +"');";
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.executeUpdate();
        connection.close();
        ServerSide.updateTextArea("New client added to DB\n");
        return true;


    }


    /**
     * This method first checks if the book is in the book table
     * if its not the client can't be loaned a book that doesn't exist
     * in the database and if it does exist the method check if the book
     * if loaned and if not loaned the book can be loaned to other free clients
     * and client record is then added to the client table with the loaned book
     * @param client client data
     * @return if the book/client record is added it returns true if not it
     * returns false
     * @throws SQLException database access error occurs
     */
    public static Boolean loanBookDB(Client client) throws SQLException {

        String query1 = "select book_title from book where book_title = '" + client.getLoanedBook().getBookTitle() + "';";

        ResultSet rs1 = statement.executeQuery(query1);


        if (!rs1.next())
            return false;

        String query2 = "Select loaned_book from client where loaned_book = '"+client.getLoanedBook().getBookTitle()+"';";

        ResultSet rs2 = statement.executeQuery(query2);

        if (rs2.next()) {
            if (client.getLoanedBook().getBookTitle().equals(rs2.getString("loaned_book"))) {
                ServerSide.updateTextArea("Failed to add client record, book already loaned\n");
                connection.close();
                return false;
            }
        }

        String query3 = "insert into client(first_name, last_name, address, gender, due_date, loaned_book)" +
                "Values('"+client.getFirstName()+"','"+client.getLastName()+"','"+client.getAddress()+"','"+client.getGender()+"','"+client.getDueDate()+"','"+client.getLoanedBook().getBookTitle()+"');";

        PreparedStatement preparedStatement = connection.prepareStatement(query3);
        preparedStatement.executeUpdate();
        connection.close();
        ServerSide.updateTextArea("Client record added to DB\n"+ client.toString() +'\n');
        return true;

    }

    /**
     * This method returns the list of clients record from the database
     * so that i can be viewed in the table view in the client side
     * @return lists of clients in the DB
     * @throws SQLException database access error occurs
     */
    public static List<Client> viewLoanedBooksDB() throws SQLException {

        String query = "SELECT * FROM client;";

        ResultSet rs = statement.executeQuery(query);

        List<Client> clients = new ArrayList<>();

        int i = 0;


        while (rs.next()) {

            clients.add(new Client(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("gender")));
            clients.get(i).setDueDate(rs.getString("due_date"));
            clients.get(i).setLoanedBook(new Book(rs.getString("loaned_book")));
            clients.get(i).setId(rs.getString("client_id"));
            i++;
        }
        connection.close();

        ServerSide.updateTextArea("Client is requesting to view loaned books\n");

        return clients;
    }

    /**
     * Inserts librarian data into database.
     * @param librarian librarian data
     * @return validation if data were inserted or not
     * @throws SQLException database access error occurs
     */
    public static Boolean addLibrarianDB(Librarian librarian) throws SQLException {

        String query = "INSERT INTO librarian(librarian_id, first_name, last_name, email, address, gender)" +
                "VALUES("+ librarian.getId() + ", '" +librarian.getFirstName()+ "'" +
                ",'"+ librarian.getLastName()+ "','"+librarian.getEmail()+"','" + librarian.getAddress()+"','"+librarian.getGender()+"');";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        connection.close();
        ServerSide.updateTextArea("Librarian info added\n");
        return true;


    } 

    /**
     * This method returns the list of librarians record from the database
     * so that i can be viewed in the table view in the client side
     * @return lists of librarian details
     * @throws SQLException database access error occurs
     */
    public static List<Librarian> viewLibrarianDB() throws SQLException {

        String query = "SELECT * FROM librarian;";

        ResultSet rs = statement.executeQuery(query);

        List<Librarian> librarians = new ArrayList<>();

        while (rs.next()) {
            librarians.add(new Librarian(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("gender"), rs.getInt("librarian_id"), rs.getString("email")));
        }
        connection.close();
        ServerSide.updateTextArea("Client is requesting to view librarians information");

        return librarians;
    }

}

