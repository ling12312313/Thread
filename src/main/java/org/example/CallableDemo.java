package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//创建线程的方式三：实现Callable接口。 ---JDK5新特性
//  如何理解Callable比Runnable强大？
//  1.call()可以有返回值
//  2.call()可以抛出异常被外面的操作捕获
public class CallableDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i < 100; i++) {
            if(i%2==0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        //3.创建Callable接口实现类的对象
        CallableDemo callableDemo = new CallableDemo();
        //4.将此Callable接口实现类的对象作为参数传递到FutureTask构造器中，创建FutureTask对象
        FutureTask<Integer> futureTask = new FutureTask<>(callableDemo);
        //5.将FutureTask对象作为参数传递到Thread类的构造器中，创建Thread对象
        Thread thread = new Thread(futureTask);
        //6.通过Thread对象调用start()方法启动线程
        thread.start();
        //7.通过FutureTask对象调用get()方法获取线程执行的结果
        System.out.println(futureTask.get());
    }
}
