package org.example.methods;

import java.util.concurrent.*;
//Thread常用方法以及Thread的创建方式

public class demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //start getName
        Thread t1 = new Thread(new Thread1());
        t1.start();
         // setName
        Thread t2 = new Thread(new Thread2());
        t2.start();
         ExecutorService pool =  Executors.newFixedThreadPool(3);
//       创造了一个线程池并生成3个空闲的线程，将t1任务提交给线程池，t1线程本身并不会加入线程池
        ThreadPoolExecutor pool1 = (ThreadPoolExecutor)pool;
        pool1.execute(t1);
        System.out.println(pool1.submit(new Callable2(pool1)).get());
    }

}

class Thread1 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().isAlive());
        System.out.println("current thread name is "+Thread.currentThread().getName());
    }
}
class Thread2 implements Runnable{

    @Override
    public void run() {
        Thread.currentThread().setName("thread2");
        System.out.println("set current thread name is "+Thread.currentThread().getName());
    }
}
class Callable1 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i < 100; i++) {
            if(i%2==0){
                System.out.println(i+" "+Thread.currentThread().getName());
                sum += i;
            }
            if (i==50){
                System.out.println(Thread.currentThread().getName()+"sleep");
            }
        }
        return sum;
    }
}
class Callable2 implements Callable<Integer>{
    private final ExecutorService pool1; // 线程池，用于提交 Callable1
    public Callable2(ExecutorService pool1) {
        this.pool1 = pool1;
    }
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i < 100; i++) {
            if(i%2==0){
                System.out.println(i+" "+Thread.currentThread().getName());
                sum += i;
            }
            if (i==50){
                System.out.println("执行call1");
                System.out.println(pool1.submit(new Callable1()).get());
            }
        }
        return sum;
    }
}