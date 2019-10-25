package com.example.demo1.testinterface;

/**
 * @author Xiewz
 */
@FunctionalInterface
public interface TestInterf<T> {
    static String X = "123";

    boolean test(T t);

    default boolean test2(T t){
        return false;
    }

    static String test3(Object obj){

        return "";
    }
}
