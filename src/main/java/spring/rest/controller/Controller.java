package spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.rest.entity.Employee;
import spring.rest.exception_handling.EmployeeIncorrectData;
import spring.rest.exception_handling.NoSuchEmployeeException;
import spring.rest.service.EmpService;

import javax.xml.crypto.Data;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private EmpService empService;

    @GetMapping("/employees")
    public List<Employee> showAllEmp(){
        List<Employee> allEmp=empService.getAllEmployees();

        return allEmp;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee employee =empService.getEmployee(id);

        if(employee==null) throw new NoSuchEmployeeException("Работника под номером "+id+
                " не существует.");

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        empService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        empService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){

        Employee employee=empService.getEmployee(id);
        if(employee==null) throw new NoSuchEmployeeException("No such employee with id="+id+" in DB.");

        empService.deleteEmployee(employee);

        return "Employee with id="+id+" deleted from DB";
    }
}
