
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book List</title>
</head>
<body>
    <h1>Book List</h1>

    <!-- Check if the 'books' attribute is not empty -->
    <c:if test="${not empty books}">
        <ul>
            <!-- Loop through each book and display its details -->
            <c:forEach var="book" items="${books}">
                <li>
                    <strong>Title:</strong> ${book.BookName} <br>
                    <strong>Author:</strong> ${book.AuthorName} <br>
                    <strong>Category:</strong> ${book.Category}
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <!-- If no books are found, show a message -->
    <c:if test="${empty books}">
        <p>No books available in the database.</p>
    </c:if>

</body>
</html>

