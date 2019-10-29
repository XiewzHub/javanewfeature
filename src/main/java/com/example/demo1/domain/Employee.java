package com.example.demo1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Xiewz
 */
@Data
@AllArgsConstructor
public class Employee {
    private Integer age;
    private String status;
    private String name;
    private Double salary;

    public static String getStr(Employee emp){
        String res = "";
        String name = emp.getName();
        res = name + ":" + emp.getSalary();
        System.out.println(res);
        return res;
    }

    public Employee() {
    }

    public Employee(Integer age) {
        this.age = age;
    }

    public interface Status {
        String BUSY = "1";
        String VOCATION = "2";
        String FREE = "3";
    }
}
