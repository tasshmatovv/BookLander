package kg.attractor.java.dataModels;

public class Book {
    private int id;
    private String name;
    private String author;
    private String status;

    public Book(int id, String name, String author, String status) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
