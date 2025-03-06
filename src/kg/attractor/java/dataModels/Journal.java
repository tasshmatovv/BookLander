package kg.attractor.java.dataModels;

import java.time.LocalDateTime;

public class Journal {
    private int id;
    private int employeeId;
    private int bookId;
    private String takeTime;
    private String returnedTime;
    private boolean isReturned;

    public Journal(int id, int employeeId, int bookId, String takeTime, String returnedTime, boolean isReturned) {
        this.id = id;
        this.employeeId = employeeId;
        this.bookId = bookId;
        this.takeTime = takeTime;
        this.returnedTime = returnedTime;
        this.isReturned = isReturned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getReturnedTime() {
        return returnedTime;
    }

    public void setReturnedTime(String returnedTime) {
        this.returnedTime = returnedTime;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}
