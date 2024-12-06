package org.example;
//多个线程执行多个方法
public class ThreadExerDemo01 {
    public static void main(String[] args) {
        new Thread01().start();
        new Thread02().start();
    }
}
class Thread01 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) System.out.println(Thread.currentThread().getName() + ":\t" + i);
        }
    }
}

class Thread02 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) System.out.println(Thread.currentThread().getName() + ":\t" + i);
        }
    }
}
