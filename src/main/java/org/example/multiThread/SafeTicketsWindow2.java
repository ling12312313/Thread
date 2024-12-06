package org.example.multiThread;
//同步代码块解决抢占资源
// 线程一得到锁后
public class SafeTicketsWindow2 {
    public static void main(String[] args) {
        WindowThread2 ticketsThread02 = new WindowThread2();
        Thread t1 = new Thread(ticketsThread02);
        Thread t2 = new Thread(ticketsThread02);
        Thread t3 = new Thread(ticketsThread02);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
class WindowThread2 implements Runnable {
//    三个线程共用一个类对象
//    每个线程都会尝试进入同步块，获取 object 锁，然后开始售票。
   private static final Object object = new Object();
    private int tiketsNum = 100;
    @Override
    public void run() {
        synchronized (object) {
        while (true) {
            if (tiketsNum > 0) {
                System.out.println(Thread.currentThread().getName() + ":\t票号:" + tiketsNum);
                tiketsNum--;
            } else {
                break;
            }
        }
    }
 }
}

