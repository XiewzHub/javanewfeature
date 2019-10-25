package com.example.demo1;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SampleClassTest {

    @Test
    public void test1() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();

        String test = proxy.test();//拦截test，输出Hello cglib
        System.out.println(test);

        System.out.println(proxy.toString());
        System.out.println(proxy.getClass());
        System.out.println(proxy.hashCode());

    }

    @Test
    public void test2(){
        String[] t = new String[]{"123", "435","33","wer"};
        List<String> stringList = Arrays.asList(t);
        ArrayList<String> list = new ArrayList<>(stringList);

        System.out.println(stringList.size());
        System.out.println(list.size());


        List<Integer> list2 = Arrays.asList(1,2,3);

        // java.lang.UnsupportedOperationException
        list2.add(5);

        // 这样才能使用add方法
        list2 = new ArrayList<>(list2);
        list2.add(5);

        System.out.print(list2.toString());

    }
}