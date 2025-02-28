package kg.attractor.java.lesson44;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import kg.attractor.java.common.Utils;
import kg.attractor.java.dataModels.Book;
import kg.attractor.java.dataModels.Employee;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Handler extends Lesson44Server{

    private List<Book> books;
    protected static List<Employee> employees;

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


    }

    private void singleEmployeeHandler(HttpExchange exchange) {
        renderTemplate(exchange,"employee.ftlh", getSingleEmployeeDataModel(employees.get(0)));
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

    private Map<String , Object> getEmployeesdataModel() {
        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("employees", employees);
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
}
