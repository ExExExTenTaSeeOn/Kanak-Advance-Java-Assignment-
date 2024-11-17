package com.library.dao;

import com.library.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connect() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/librarydb";
        String dbUser = "root";
        String dbPassword = "";
        return DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
    }

    // Method to add a book to the database
    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (BookId, BookName, AuthorName, Category) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getBookName());
            stmt.setString(3, book.getAuthorName());
            stmt.setString(4, book.getCategory());
            stmt.executeUpdate();
        }
    }

    // Method to retrieve all books from the database
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("BookId"));
                book.setBookName(rs.getString("BookName"));
                book.setAuthorName(rs.getString("AuthorName"));
                book.setCategory(rs.getString("Category"));
                books.add(book);
            }
        }
        return books;
    }

    // Additional methods for update and delete can be added similarly
}