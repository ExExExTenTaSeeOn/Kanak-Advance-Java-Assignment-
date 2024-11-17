import com.DatabaseConnection.DatabaseConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.library.model.Book;
import com.library.service.BookService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/librarydb";
    private static final String USER = "root";  // replace with your MySQL username
    private static final String PASSWORD = "";  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addBook(request);
        } else if ("update".equals(action)) {
            updateBook(request);
        } else if ("delete".equals(action)) {
            deleteBook(request);
        }

        response.sendRedirect("list.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        // Create an instance of BookService or inject it if using a dependency injection framework
        BookService bookService = new BookService();

        // Fetch the list of books
        List<Book> books = new ArrayList<>();  // Use generics to avoid unchecked warnings

        // Set the books list as a request attribute
        request.setAttribute("books", books);

        // Forward the request to list.jsp
        RequestDispatcher dispatcher = (RequestDispatcher) (List<Book>) request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    } catch (Exception e) {
        // Log the exception for debugging purposes
        e.printStackTrace();

        // Send an error message to the client
        response.getWriter().println("Error retrieving books: " + e.getMessage());
    }
}


    private void addBook(HttpServletRequest request) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        
        // Get connection from the DatabaseConnection class
        conn = DatabaseConnection.getConnection();
        
        // SQL query to insert a new book into the database
        String query = "INSERT INTO books (BookName, AuthorName, Category) VALUES (?, ?, ?)";
        pstmt = conn.prepareStatement(query);
        
        // Set values from the form to the query
        pstmt.setString(1, request.getParameter("BookName"));
        pstmt.setString(2, request.getParameter("AuthorName"));
        pstmt.setString(3, request.getParameter("Category"));
        
        // Execute the query to insert data
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
}

    private void updateBook(HttpServletRequest request) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "UPDATE books SET BookName=?, AuthorName=?, Category=? WHERE BookId=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, request.getParameter("BookName"));
            pstmt.setString(2, request.getParameter("AuthorName"));
            pstmt.setString(3, request.getParameter("Category"));
            pstmt.setInt(4, Integer.parseInt(request.getParameter("BookId")));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private void deleteBook(HttpServletRequest request) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "DELETE FROM books WHERE BookId=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(request.getParameter("BookId")));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM books";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int bookId = rs.getInt("BookId");  // Separate declaration and assignment
                String bookName = rs.getString("BookName");
                String authorName = rs.getString("AuthorName");
                String category = rs.getString("Category");

                Book book = new Book(bookId, bookName, authorName, category);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return books;
    }
}