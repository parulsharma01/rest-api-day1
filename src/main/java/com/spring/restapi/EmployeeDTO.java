package com.spring.restapi;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeDTO {

    @NotNull
    Integer id;

    @NotNull
    @Size(min = 2, message = "Name should have at least 2 characters")
    String name;
    Integer age;

    protected EmployeeDTO() { }

    public EmployeeDTO(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "\nEmployee ID =" + id +
                "\nName='" + name +
                "\nAge=" + age;
    }
}
