package org.example.multiThread;
//同步方法解决抢占问题
public class SafeTicketsWindow3 {
    public static void main(String[] args) {
        Window02Thread ticketsThread02 = new Window02Thread();
        Thread t1 = new Thread(ticketsThread02);
        Thread t2 = new Thread(ticketsThread02);
        Thread t3 = new Thread(ticketsThread02);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.setPriority(Thread.MIN_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}
class  Window02Thread implements Runnable{

    private int tiketsNum = 100;
    @Override
    public void run() {
          printTicket();
    }
    private synchronized void printTicket() {
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
