
package com.library.service;

import com.library.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/librarydb"; // Replace with your DB details
    private static final String DB_USER = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = ""; // Replace with your DB password

    // Query to fetch books
    private static final String SELECT_ALL_BOOKS = "SELECT BookId,BookName,AuthorName,Category FROM books";

    /**
     * Method to fetch all books from the database.
     *
     * @return List of books
     * @throws SQLException if a database access error occurs
     */
    public List<Book> getBooks() throws SQLException {
    List<Book> books = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);
         ResultSet resultSet = preparedStatement.executeQuery()) {

        // Log to check if the connection is successful
        System.out.println("Connected to the database");

        // Check if there are any records
        while (resultSet.next()) {
            Book book = new Book();
            book.setBookId(resultSet.getInt("BookId"));
            book.setTitle(resultSet.getString("BookName"));
            book.setAuthor(resultSet.getString("AuthorName"));
            book.setCategory(resultSet.getString("Category"));
            books.add(book);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }

    return books;
}
}