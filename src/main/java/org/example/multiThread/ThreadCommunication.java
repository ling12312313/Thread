package org.example.multiThread;
//wait与sleep的异同
//相同点：两个方法一旦执行，都可以让线程进入阻塞状态。
//
//不同点：1) 两个方法声明的位置不同：Thread类中声明sleep(),Object类中声明wait()
//
//       2) 调用要求不同：sleep()可以在任何需要的场景下调用。wait()必须在同步代码块中调用。
//
//       3) 关于是否释放同步监视器：如果两个方法都使用在同步代码块或同步方法中，sleep不会释放锁，wait会释放锁。
public class ThreadCommunication {
    public static void main(String[] args) {
        CommunicationThread communicationThread = new CommunicationThread();
        Thread t1 = new Thread(communicationThread);
        Thread t2 = new Thread(communicationThread);
        Thread t3 = new Thread(communicationThread);

        t1.start();
        t2.start();
        t3.start();
    }
}
class CommunicationThread implements Runnable {
    int Num = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notifyAll();
                if (Num <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":\t" + Num);
                    Num++;

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }

        }
    }
}
