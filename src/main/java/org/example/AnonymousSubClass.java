package org.example;
//匿名方法创造线程
public class AnonymousSubClass {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) System.out.println(Thread.currentThread().getName() + ":\t" + i);
                }
            }
        }).start();
    }

}
