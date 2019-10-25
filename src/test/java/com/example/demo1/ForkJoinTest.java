package com.example.demo1;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import static org.junit.Assert.*;

public class ForkJoinTest {

    @Test
    public void compute() {
    }

    /**
     * 实现数的累加
     */
    @Test
    public void test1() {
        //开始时间
        Instant start = Instant.now();

        //这里需要一个线程池的支持
        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoin(0L, 10000000000L);
        // 没有返回值     pool.execute();
        // 有返回值
        long sum = pool.invoke(task);

        //结束时间
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).getSeconds());
    }

    /**
     * java8 并行流 parallel()
     */
    @Test
    public void test2() {
        //开始时间
        Instant start = Instant.now();

        // 并行流计算    累加求和
        LongStream.rangeClosed(0, 10000000000L).parallel()
                .reduce(0, Long :: sum);

        //结束时间
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).getSeconds());
    }

    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().forEach(System.out::print);

        list.parallelStream()
                .forEach(System.out::print);
    }

    @Test
    public void test4(){

    }
}