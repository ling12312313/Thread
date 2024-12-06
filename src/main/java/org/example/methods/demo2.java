package org.example.methods;
//常用方法测试
public class demo2 {
    public static void main(String[] args) {
        //yield测试
        Thread t1 = new Thread(new MyRunnable("Thread1"));
        Thread t2 = new Thread(new MyRunnable("Thread2"));
//        t1.start();
//        t2.start();
        //join测试
        Thread t3 = new Thread(new MyThread());
        t3.setName("Thread1");
        t3.start();
    }
}
class MyRunnable implements Runnable{
    private String threadName;

    public MyRunnable(String threadName) {
        this.threadName = threadName;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            // 输出当前线程的信息
            System.out.println(threadName + " is running, iteration: " + i);

            // 当线程名是 "Thread1" 时，调用 yield()
            if ("Thread1".equals(threadName)) {
                // 让出CPU的控制权，提示调度器可能切换到同优先级的线程
                Thread.yield();
            }
        }
    }
}
class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i==5&&"Thread1".equals(Thread.currentThread().getName())){
                Thread thread2 = new Thread(new MyThread());
                try {
                    thread2.start();
                    thread2.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(i);
        }
    }
}
