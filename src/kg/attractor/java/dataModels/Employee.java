package kg.attractor.java.dataModels;

import java.util.List;

public class Employee {
    private int id;
    private String fullName;
    private List<Integer> listCurrentBooks;
    private List<Integer> listPastBooks;
    private String email;
    private String password;

    public Employee(int id, String fullName, List<Integer> listCurrentBooks, List<Integer> listPastBooks, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.listCurrentBooks = listCurrentBooks;
        this.listPastBooks = listPastBooks;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Integer> getListCurrentBooks() {
        return listCurrentBooks;
    }

    public void setListCurrentBooks(List<Integer> listCurrentBooks) {
        this.listCurrentBooks = listCurrentBooks;
    }

    public List<Integer> getListPastBooks() {
        return listPastBooks;
    }

    public void setListPastBooks(List<Integer> listPastBooks) {
        this.listPastBooks = listPastBooks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
