
<%@ page import="java.util.List" %>
<%@ page import="com.library.model.Book" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Book</title>
</head>
<body>
    <h2>Add New Book</h2>
    <!-- Form to add a book -->
    <form action="BookServlet" method="post">
        <!-- Hidden input to specify the action as "add" -->
        <input type="hidden" name="action" value="add">
        
        <!-- Input fields for book details -->
        Book Name: <input type="text" name="BookName" required><br>
        Author Name: <input type="text" name="AuthorName" required><br>
        Category: <input type="text" name="Category" required><br>
        
        <!-- Submit button to submit the form -->
        <input type="submit" value="Add Book">
    </form>
</body>
</html>
