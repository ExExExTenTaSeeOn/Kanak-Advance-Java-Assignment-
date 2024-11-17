package com.library.model;
public class Book {
    private int BookId;
    private String BookName;
    private String AuthorName;
    private String Category;

    public Book(int BookId, String BookName, String AuthorName, String Category) {
        this.BookId = BookId;
        this.BookName = BookName;
        this.AuthorName = AuthorName;
        this.Category = Category;
    }

    public Book() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getBookId() {
        return BookId;
    }

    public String getBookName() {
        return BookName;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public String getCategory() {
        return Category;
    }

    public void setBookId(int bookId) {
        this.BookId = BookId;
    }

    public void setBookName(String bookName) {
        this.BookName = BookName;
    }

    public void setAuthorName(String authorName) {
        this.AuthorName = AuthorName;
    }

    public void setCategory(String category) {
        this.Category = Category;
    }

    public void setTitle(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAuthor(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}