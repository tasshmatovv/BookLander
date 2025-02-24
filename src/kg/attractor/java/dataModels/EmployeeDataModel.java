package kg.attractor.java.dataModels;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataModel {
    Employee employee =  new Employee(1, "Дмитрий Сергеевич", 1, 5);
    List<Employee> employees = new ArrayList<>();

    public EmployeeDataModel(){
        employees.add(employee);
        employees.add(new Employee(2, "Василий Георгеевич", 1, 2));
        employees.add(new Employee(3, "Иванов Иван", 2, 3));
        employees.add(new Employee(4, "Петров Петр", 3, 4));
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public List<Employee> getEmployees(){
        return new ArrayList<>(employees);
    }
}
