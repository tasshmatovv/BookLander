package kg.attractor.java.lesson44;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.common.Utils;
import kg.attractor.java.dataModels.Book;
import kg.attractor.java.dataModels.Employee;
import kg.attractor.java.dataModels.Journal;
import kg.attractor.java.server.BasicServer;
import kg.attractor.java.server.ContentType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.*;


public class Handler extends BasicServer {

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

        registerGet("/errorPage", this:: getErrorPage);

        registerGet("/returnBook", this:: getReturnBookPage);
        registerPost("/returnBook", this::returnBookPost);

        registerGet("/journal", this::getJournal);
    }

    private void returnBookPost(HttpExchange exchange) {
        Map<String, String> formData = Utils.parseFormData(exchange);
        String bookIdStr = formData.get("selectedBookId");

        if (bookIdStr == null) {
            redirect303(exchange, "/errorPage");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(bookIdStr);
        } catch (NumberFormatException e) {
            redirect303(exchange, "/errorPage");
            return;
        }

        Employee employee = employees.stream()
                .filter(e -> e.getEmail().equals(getUserEmailFromSession(exchange)))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            redirect303(exchange, "/login");
            return;
        }

        Book bookToReturn = books.stream()
                .filter(book -> book.getId() == bookId)
                .findFirst()
                .orElse(null);

        if (bookToReturn == null || !employee.getListCurrentBooks().contains(bookId)) {
            redirect303(exchange, "/errorPage");
            return;
        }

        employee.getListCurrentBooks().remove((Integer) bookId);
        if (!employee.getListPastBooks().contains(bookId)) {
            employee.getListPastBooks().add(bookId);
        }
        bookToReturn.setStatus("Free");

        Utils.writeFile("data/jsonFiles/Employee.json", employees);
        Utils.writeFile("data/jsonFiles/Book.json", books);

        redirect303(exchange, "/profile");
    }

    private void getReturnBookPage(HttpExchange exchange) {
        String userEmail = getUserEmailFromSession(exchange);
        if (userEmail != null) {
            Employee employee = employees.stream()
                    .filter(e -> e.getEmail().equals(userEmail))
                    .findFirst()
                    .orElse(null);

            if (employee != null) {
                Map<String, Object> dataModel = new HashMap<>();
                dataModel.put("fullName", employee.getFullName());
                dataModel.put("email", userEmail);
                List<Book> currentBooks = books.stream()
                        .filter(book -> employee.getListCurrentBooks().contains(book.getId()))
                        .toList();
                dataModel.put("currentBooks", currentBooks);
                renderTemplate(exchange, "/returnBookPage.ftlh", dataModel);
                return;
            }
        }
        redirect303(exchange, "/login");
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
        String query = exchange.getRequestURI().getQuery();
        if (query == null) {
            redirect303(exchange, "/employees");
            return;
        }
        Map<String, String> params = Utils.parseUrlEncoded(query, "&");
        if (!params.containsKey("id")) {
            redirect303(exchange, "/employees");
            return;
        }
        int employeeId;
        try {
            employeeId = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
            redirect303(exchange, "/employees");
            return;
        }
        Employee employee = employees.stream()
                .filter(e -> e.getId() == employeeId)
                .findFirst()
                .orElse(null);

        if (employee == null) {
            redirect303(exchange, "/employees");
            return;
        }


        Map<String, Object> dataModel = getSingleEmployeeDataModel(employee);
        dataModel.put("currentBooks", getBookNamesByIds(employee.getListCurrentBooks()));
        dataModel.put("pastBooks", getBookNamesByIds(employee.getListPastBooks()));
        renderTemplate(exchange, "employee.ftlh", dataModel);
    }

    private void singleBookHandler(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> params = Utils.parseUrlEncoded(query, "&");

        if (!params.containsKey("id")) {
            redirect303(exchange, "/books");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
            redirect303(exchange, "/books");
            return;
        }

        Book book = books.stream()
                .filter(b -> b.getId() == bookId)
                .findFirst()
                .orElse(null);

        if (book == null) {
            redirect303(exchange, "/books");
            return;
        }

        renderTemplate(exchange, "book.ftlh", getSingleBookDataModel(book));
    }

    private void freemarkerBooksHandler(HttpExchange exchange) {
        Map<String, Object> dataModel = getBooksDataModel();
        String userEmail = getUserEmailFromSession(exchange);
        if (userEmail != null) {
            dataModel.put("email", userEmail);
        }
        renderTemplate(exchange, "books.ftlh", dataModel);
    }

    private void freemarkerEmployeesHandler(HttpExchange exchange) {
        Map<String, Object> dataModel = getEmployeesdataModel();
        String userEmail = getUserEmailFromSession(exchange);
        if (userEmail != null) {
            dataModel.put("email", userEmail);
        }
        renderTemplate(exchange, "employees.ftlh", dataModel);
    }

    private Map<String, Object> getEmployeesdataModel() {
        Map<String, Object> dataModel = new HashMap<>();
        List<Map<String, Object>> employeesWithBooks = employees.stream().map(employee -> {
            Map<String, Object> empData = new HashMap<>();
            empData.put("id", employee.getId());
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

    public List<String> getBookNamesByIds(List<Integer> bookIds) {
        List<String> bookNames = new ArrayList<>();
        for (Integer id : bookIds) {
            books.stream()
                    .filter(book -> book.getId() == id)
                    .findFirst()
                    .ifPresent(book -> bookNames.add(book.getName()));
        }
        return bookNames;
    }

    private void getBookPost(HttpExchange exchange) {
        Map<String, String> formData = Utils.parseFormData(exchange);
        String bookIdStr = formData.get("id");

        if (bookIdStr == null) {
            return;
        }

        int bookId = Integer.parseInt(bookIdStr);

        Employee employee = employees.stream()
                .filter(e -> e.getEmail().equals(getUserEmailFromSession(exchange)))
                .findFirst()
                .orElse(null);

        if (employee == null || employee.getListCurrentBooks().size() >= 3) {
            redirect303(exchange, "/errorPage");
            return;
        }

        if (!employee.getListCurrentBooks().contains(bookId)) {
            employee.getListCurrentBooks().add(bookId);
        }

        books.forEach(book -> {
            if (book.getId() == bookId) {
                book.setStatus("Busy");
            }
        });

        Utils.writeFile("data/jsonFiles/Employee.json", employees);
        Utils.writeFile("data/jsonFiles/Book.json", books);
        redirect303(exchange, "/employees");
    }

    private void getErrorPage(HttpExchange exchange) {
        Path path = makeFilePath("templates/errorPage.ftlh");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void getJournal(HttpExchange exchange) {
        try {
            Type journalListType = new TypeToken<List<Journal>>() {}.getType();
            List<Journal> journalEntries = Utils.readFile("data/jsonFiles/Journal.json", journalListType);
            if (journalEntries == null) {
                journalEntries = new ArrayList<>();
            }

            List<Map<String, Object>> journalData = journalEntries.stream().filter(entry -> entry.isReturned()).map(entry -> {
                Map<String, Object> data = new HashMap<>();
                data.put("id", entry.getId());

                String employeeName = employees.stream()
                        .filter(e -> e.getId() == entry.getEmployeeId())
                        .map(Employee::getFullName)
                        .findFirst()
                        .orElse("Неизвестный сотрудник");

                String bookName = books.stream()
                        .filter(b -> b.getId() == entry.getBookId())
                        .map(Book::getName)
                        .findFirst()
                        .orElse("Неизвестная книга");

                data.put("employeeName", employeeName);
                data.put("bookName", bookName);
                data.put("takeTime", entry.getTakeTime());
                data.put("returnedTime", entry.getReturnedTime());
                data.put("isReturned", entry.isReturned());

                return data;
            }).toList();


            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("journal", journalData);

            renderTemplate(exchange, "journal.ftlh", dataModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
