package spring.rest.service;


import spring.rest.entity.Employee;

import java.util.List;

public interface EmpService {
    public List<Employee> getAllEmployees();
    public void saveEmployee(Employee employee);
    public Employee getEmployee(int id);
    public void deleteEmployee(Employee employee);
}
