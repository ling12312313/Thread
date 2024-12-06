package org.example;

public class RunnableDemo implements Runnable {
    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnableDemo());
        t1.start();
        Thread t2 = new Thread(new RunnableDemo());
        t2.start();
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) System.out.println(Thread.currentThread().getName() + ":\t" + i);
        }
    }
}
