package com.spring.restapi;

import com.spring.restapi.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EmployeeResource {

    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping("/employees")
    public List<EmployeeDTO> retrieveAllEmployees()
    {
        return employeeDAO.getEmployeeList();
    }

    @GetMapping("/employees/{id}")
    public EmployeeDTO retrieveEmployee(@PathVariable Integer id)
    {
        EmployeeDTO employee= employeeDAO.getEmployee(id);
        if(employee==null) throw new EmployeeNotFoundException("Employee with ID: "+id+" does not exists");
        return employeeDAO.getEmployee(id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Integer id)
    {
        EmployeeDTO employee= employeeDAO.deleteEmployeeByID(id);
        if(employee==null) throw new EmployeeNotFoundException("Employee with ID: "+id+" does not exists");
    }


    @PostMapping("/employees")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO)
    {
        EmployeeDTO addedEmployee= EmployeeDAO.addEmployee(employeeDTO);
        URI location= ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(addedEmployee.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/employees")
    public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO)
    {
        EmployeeDTO addedEmployee= EmployeeDAO.updateEmployee(employeeDTO);
        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedEmployee.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
