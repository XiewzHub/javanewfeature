package com.example.demo1.domain;

import lombok.Data;

/**
 * @author Xiewz
 */
@Data
public class Product {
    public static final String RED="红色";
    public static final String GREEN="绿色";
    public static final String BLUE="蓝色";

    private  String color;
    private  Double price;
}
