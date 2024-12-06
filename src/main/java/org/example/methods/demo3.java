package org.example.methods;
// java调度优先级
//高优先级的线程要抢占低优先级线程cpu的执行权。但是只是从概率上讲。
public class demo3 {
    public static void main(String[] args) {
//        测试priority
        ThreadPriority t1 = new ThreadPriority();
        ThreadPriority t2 = new ThreadPriority();
        t1.setName("t1");
        t2.setName("t2");
        t1.setPriority(10);
        t2.setPriority(1);
        t1.start();
        t2.start();
//        默认priority为5
//        System.out.println(t1.getPriority());
    }
}

class ThreadPriority extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) System.out.println(Thread.currentThread().getName() + ":\t" + i);
        }
    }
}
