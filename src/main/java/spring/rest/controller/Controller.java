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

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception){
        EmployeeIncorrectData data=new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception){
        EmployeeIncorrectData data=new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);

    }
}
