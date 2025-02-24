package kg.attractor.java.dataModels;

import java.util.ArrayList;
import java.util.List;

public class BookDataModel {
    Book book = new Book(1,"Harry Potter", "Joanne Rowling" ,"Free");
    List <Book> books = new ArrayList<>();

    public BookDataModel() {
        books.add(book);
        books.add(new Book(2, "Sherlock Holmes", "Arthur Conan Doyle", "Busy"));
        books.add(new Book(3, "Manas", "folk art", "Free"));
        books.add(new Book(4, "Ak-Keme", "Chyngyz Aitmatov", "Free"));
        books.add(new Book(5, "Akbara's tears", "Chyngyz Aitmatov", "Busy"));
    }

    public void addBook(Book book) {
        books.add(book);
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}
