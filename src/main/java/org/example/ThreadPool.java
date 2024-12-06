package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

//提前创建好多个线程，放入线程池中，使用时直接获取，
// 使用完放回池中。可以避免频繁创建销毁、实现重复利用。
public class ThreadPool {
    public static void main(String[] args) {
//        ExecutorService管理线程池的接口，创建固定大小的线程池
          ExecutorService service = Executors.newFixedThreadPool(10);
//        ExecutorService 是线程池的接口，它定义了一些管理和执行任务的方法，但并没有提供所有线程池内部细节的控制。
//        ThreadPoolExecutor 是 ExecutorService 接口的一个实现类，它提供了更多的控制选项，比如线程池的大小、线程的生命周期、队列类型等。
          ThreadPoolExecutor service1 = (ThreadPoolExecutor)service;
//        System.out.println(service.getClass());
//        输出线程池接口名称
//        service1.setCorePoolSize(15);
//        设置线程池的核心线程数为 15。核心线程数是线程池中始终保持活动的线程数。
//        如果线程池中的线程数低于核心线程数，即使没有任务，线程池也会保持这些线程。
//        service1.setKeepAliveTime();
//        设置线程在空闲时保持活动的最长时间。默认情况下，线程池中的非核心线程在空闲时会在指定时间后被销毁，
//        setKeepAliveTime() 用于配置这个空闲时间。
         service.execute(new NumberThread());//适合用于Runnable
//       service.submit(new NumberThread());//适合用于Callable
        service.shutdown();
//        关闭线程池管理接口
    }
}
class NumberThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":\t" + i);
            }
        }
    }
}