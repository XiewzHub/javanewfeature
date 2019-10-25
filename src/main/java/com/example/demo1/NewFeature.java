package com.example.demo1;

import com.example.demo1.domain.Product;
import com.example.demo1.testinterface.ColorPredicate;
import com.example.demo1.testinterface.MyPredicate;
import com.example.demo1.testinterface.TestInterf;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Xiewz
 */
public class NewFeature {
    public static void main(String[] args) {
        testLambda();
    }

    public static void testLambda(){
        //匿名内部类
        Comparator<Integer> cpt = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> set = new TreeSet<>(cpt);
        set.add(1);
        set.add(1);
        System.out.println(set);

        System.out.println("=========================");

        //使用lambda表达式
        Comparator<Integer> cpt2 = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> set2 = new TreeSet<>(cpt2);
        set2.add(4);
        set2.add(3);
        set2.add(3);
        System.out.println(set2);
    }



    // 筛选颜色为红色
    public List<Product> filterProductByColor(List<Product> list){
        ColorPredicate colorPredicate = new ColorPredicate();
        return filterProduct(list,colorPredicate);
    }

    // 筛选价格小于8千的
    public  List<Product> filterProductByPrice(List<Product> list){

        return filterProduct(list,product -> product.getPrice() < 80);
    }

    public List<Product> filterProduct(List<Product> list, MyPredicate<Product> mp){
        List<Product> prods = new ArrayList<>();
        for (Product product : list){
            if (mp.test(product)){
                prods.add(product);
            }
        }
        return prods;
    }

}
