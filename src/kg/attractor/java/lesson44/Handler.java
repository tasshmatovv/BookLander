package kg.attractor.java.lesson44;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.common.Utils;
import kg.attractor.java.dataModels.Book;
import kg.attractor.java.dataModels.Employee;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Handler extends Lesson44Server{

    private List<Book> books;
    protected static List<Employee> employees;
    protected static final Map<String, String> sessionStorage = new HashMap<>();

    public Handler(String host, int port) throws IOException {
        super(host, port);
        Type booksListType = new TypeToken<List<Book>>() {}.getType();
        books = Utils.readFile("data/jsonFiles/Book.json", booksListType);
        registerGet("/books", this::freemarkerBooksHandler);

        Type employeesListType = new TypeToken<List<Employee>>() {}.getType();
        employees = Utils.readFile("data/jsonFiles/Employee.json",employeesListType);
        registerGet("/employees",this::freemarkerEmployeesHandler);

        registerGet("/employee", this::singleEmployeeHandler);
        registerGet("/book",this::singleBookHandler);

        registerGet("/getBook", this:: getBookPage);
        registerPost("/getBook", this::getBookPost);

    }

    private void getBookPage(HttpExchange exchange) {
        String userEmail = getUserEmailFromSession(exchange);
        if (userEmail != null) {
            Map<String, Object> dataModel = getFreeBooksName();
            dataModel.put("email", userEmail);
            renderTemplate(exchange, "getBookPage.ftlh", dataModel);
        } else {
            redirect303(exchange, "/login");
        }
    }

    private Map<String, Object> getFreeBooksName() {
        Map<String, Object> dataModel = new HashMap<>();
        List<Book> freeBooks = books.stream()
                .filter(book -> "free".equalsIgnoreCase(book.getStatus()))
                .toList();
        dataModel.put("books", freeBooks);
        return dataModel;
    }

    private void singleEmployeeHandler(HttpExchange exchange) {
        Employee employee = employees.get(0);
        Map<String, Object> dataModel = getSingleEmployeeDataModel(employee);
        dataModel.put("currentBooks", getBookNamesByIds(employee.getListCurrentBooks()));
        dataModel.put("pastBooks", getBookNamesByIds(employee.getListPastBooks()));
        renderTemplate(exchange, "employee.ftlh", dataModel);
    }

    private void singleBookHandler(HttpExchange exchange) {
        renderTemplate(exchange, "book.ftlh", getSingleBookDataModel(books.get(0)));
    }


    private void freemarkerBooksHandler(HttpExchange exchange) {
        renderTemplate(exchange, "books.ftlh", getBooksDataModel());
    }

    private void freemarkerEmployeesHandler(HttpExchange exchange){
        renderTemplate(exchange,"employees.ftlh", getEmployeesdataModel());
    }

    private Map<String, Object> getEmployeesdataModel() {
        Map<String, Object> dataModel = new HashMap<>();
        List<Map<String, Object>> employeesWithBooks = employees.stream().map(employee -> {
            Map<String, Object> empData = new HashMap<>();
            empData.put("fullName", employee.getFullName());
            empData.put("currentBooks", getBookNamesByIds(employee.getListCurrentBooks()));
            empData.put("pastBooks", getBookNamesByIds(employee.getListPastBooks()));
            return empData;
        }).toList();

        dataModel.put("employees", employeesWithBooks);
        return dataModel;
    }

    private Map<String, Object> getBooksDataModel() {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("books", books);
        return dataModel;
    }

    private Map<String, Object> getSingleBookDataModel(Book book) {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("book", book);
        return dataModel;
    }

    private Map<String , Object> getSingleEmployeeDataModel(Employee employee){
        Map<String ,Object> dataModel = new HashMap<>();
        dataModel.put("employee",employee);
        return dataModel;
    }

    protected String getUserEmailFromSession(HttpExchange exchange) {
        List<String> cookies = exchange.getRequestHeaders().getOrDefault("Cookie", Collections.emptyList());
        for (String cookie : cookies) {
            String[] parts = cookie.split("; ");
            for (String part : parts) {
                if (part.startsWith("session=")) {
                    String sessionId = part.substring("session=".length());
                    return sessionStorage.get(sessionId);
                }
            }
        }
        return null;
    }

    private List<String> getBookNamesByIds(List<Integer> bookIds) {
        return books.stream()
                .filter(book -> bookIds.contains(book.getId()))
                .map(Book::getName)
                .toList();
    }


    private void getBookPost(HttpExchange exchange) {
        String userEmail = getUserEmailFromSession(exchange);
        if (userEmail == null) {
            redirect303(exchange, "/login");
            return;
        }

        Map<String, String> formData = Utils.parseFormData(exchange);
        if (!formData.containsKey("id")) {
            return;
        }

        int bookId = Integer.parseInt(formData.get("id"));

        Employee employee = employees.stream()
                .filter(e -> e.getEmail().equals(userEmail))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            return;
        }

        Book book = books.stream()
                .filter(b -> b.getId() == bookId && "Free".equalsIgnoreCase(b.getStatus()))
                .findFirst()
                .orElse(null);

        if (book == null) {
            return;
        }

        employee.getListCurrentBooks().add(bookId);
        book.setStatus("Busy");
        Utils.writeFile("data/jsonFiles/Employee.json", employees);
        Utils.writeFile("data/jsonFiles/Book.json", books);
        redirect303(exchange, "/employees");
    }

}
