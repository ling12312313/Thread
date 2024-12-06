package org.example.multiThread;
//三个线程抢占资源
public class SafeTicketsWindow {
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

class WindowThread implements Runnable {
    private int tiketsNum = 100;
   @Override
    public void run() {
        while (true) {
            if (tiketsNum > 0) {
                try {
                    //手动让线程进入阻塞,增大错票概率
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":\t票号:" + tiketsNum);
                /*try {
                    //手动让线程进入阻塞,增大重票的概率
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                tiketsNum--;
            } else {
                break;
            }
        }
    }
}

