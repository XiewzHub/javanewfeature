package com.example.demo1;

import java.util.concurrent.RecursiveTask;

/**
 * 要想使用Fark—Join，类必须继承
 * RecursiveAction（无返回值）
 * Or
 * RecursiveTask（有返回值）
 *
 */
public class ForkJoin extends RecursiveTask<Long> {

    /**
     * 要想使用Fark—Join，类必须继承RecursiveAction（无返回值） 或者
     * RecursiveTask（有返回值）
     *
     * @author Wuyouxin
     */
    private static final long serialVersionUID = 23423422L;

    private long start;
    private long end;

    public ForkJoin() {
    }

    public ForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    // 定义阙值
    private static final long THRESHOLD = 10000L;

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (end - start) / 2;
            ForkJoin left = new ForkJoin(start, middle);
            //拆分子任务，压入线程队列
            left.fork();
            ForkJoin right = new ForkJoin(middle + 1, end);
            right.fork();

            //合并并返回
            return left.join() + right.join();
        }
    }

}
