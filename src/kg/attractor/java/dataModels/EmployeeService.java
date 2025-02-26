package kg.attractor.java.dataModels;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    Employee employee =  new Employee(1, "Дмитрий Сергеевич", 1, 5, "q@q.q", "qweqwe");
    List<Employee> employees = new ArrayList<>();

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
