package kg.attractor.java.dataModels;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    Book book = new Book(1,"Harry Potter", "Joanne Rowling" ,"Free");
    List <Book> books = new ArrayList<>();

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
