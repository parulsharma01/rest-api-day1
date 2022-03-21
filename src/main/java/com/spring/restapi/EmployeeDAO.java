package com.spring.restapi;

import com.spring.restapi.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class EmployeeDAO {
    static int lastGeneratedID=34567;
    static List<EmployeeDTO> empList=new ArrayList<>();



    static {
        addEmployee(new EmployeeDTO(0, "Parul Sharma", 32));
        addEmployee(new EmployeeDTO(0, "Govind", 30));
        addEmployee(new EmployeeDTO(0, "Aashma", 23));
    }

    public static List<EmployeeDTO> getEmployeeList()
    {
        return empList;
    }

    public static EmployeeDTO addEmployee(EmployeeDTO employeeDTO)
    {
        employeeDTO.setId(lastGeneratedID++);
        if(employeeExists(lastGeneratedID-1)) return null;
        empList.add(employeeDTO);
        return employeeDTO;
    }

    public static boolean employeeExists(Integer empID)
    {
        for(EmployeeDTO emp : empList)
        {
            if(emp.getId().equals(empID)) return true;
        }
        return false;
    }

    public static EmployeeDTO getEmployee(Integer empID)
    {
        if(empID==null) throw new EmployeeNotFoundException("Employee not found with ID: "+ empID);;
        if(!employeeExists(empID)) throw new EmployeeNotFoundException("Employee not found with ID: "+ empID);
        for(EmployeeDTO emp: empList)
        {
            if(emp.getId().equals(empID)) return emp;
        }
        return null;
    }

    public static EmployeeDTO deleteEmployeeByID(Integer empID)
    {
        if(empID==null) throw new EmployeeNotFoundException("Employee not found with ID: "+ empID);;
        if(!employeeExists(empID)) throw new EmployeeNotFoundException("Employee not found with ID: "+ empID);
        Iterator<EmployeeDTO> iterator= empList.iterator();
        while(iterator.hasNext())
        {
            EmployeeDTO employee= iterator.next();
            if(employee.getId().equals(empID))
            {
                iterator.remove();
                return employee;
            }
        }
        return null;
    }

    public static EmployeeDTO updateEmployee(EmployeeDTO employee)
    {
        Integer empID=employee.getId();
        if(empID==null) throw new EmployeeNotFoundException("Employee not found with ID: "+ empID);;
        if(!employeeExists(empID)) throw new EmployeeNotFoundException("Employee not found with ID: "+ empID);
        Iterator<EmployeeDTO> iterator= empList.iterator();
        while(iterator.hasNext())
        {
            EmployeeDTO tmpEmployee= iterator.next();
            if(tmpEmployee.getId().equals(empID))
            {
                tmpEmployee.setAge(employee.getAge());
                tmpEmployee.setName(employee.getName());
                return tmpEmployee;
            }
        }
        return null;
    }

}
