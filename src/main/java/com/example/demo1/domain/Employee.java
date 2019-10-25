package com.example.demo1.domain;

import lombok.Data;

/**
 * @author Xiewz
 */
@Data
public class Employee {
    private Integer age;
    private String status;
    private String name;
    private Double salary;

    public static String getStr(){
        return "123";
    }

    public Employee() {
    }

    public Employee(Integer age) {
        this.age = age;
    }

    public Employee(String name,Integer age,  Double salary,String status) {
        this.age = age;
        this.status = status;
        this.name = name;
        this.salary = salary;
    }

    public interface Status {
        String BUSY = "1";
        String VOCATION = "2";
        String FREE = "3";
    }
}
