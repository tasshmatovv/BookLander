package kg.attractor.java.dataModels;

public class Employee {
    private int id;
    private String fullName;
    private int listCurrentBooks;
    private int listPastBooks;

    public Employee(int id, String fullName, int listCurrentBooks, int listPastBooks) {
        this.id = id;
        this.fullName = fullName;
        this.listCurrentBooks = listCurrentBooks;
        this.listPastBooks = listPastBooks;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getListCurrentBooks() {
        return listCurrentBooks;
    }

    public int getListPastBooks() {
        return listPastBooks;
    }
}
