package org.example.multiThread;
//使用懒汉模式解决线程抢占同步问题（不是最终解决方法，感觉用处不大）
//懒汉模式的懒，指的是懒加载，即在需要的时候才去创建对象，使用相应的get方法
//懒汉模式的本质是单例模式+同步方法，单例模式是在需要的时候创建对象，使用双重验证，只需要实例化一个instance
// 其他线程使用第一个线程创建的instance。而同步方法，在方法声明中添加 synchronized，
// 意味着该方法在执行时会获取类级别的锁（对于静态方法来说是类的 Class 对象，对于实例方法来说是当前对象的锁）。
public class Singleton  {
    public static void main(String[] args) {
        SingletonRunnable singletonRunnable = new SingletonRunnable();
        Thread t1 = new Thread(singletonRunnable);
        Thread t2 = new Thread(singletonRunnable);
        Thread t3 = new Thread(singletonRunnable);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();

    }
}
class SingletonRunnable implements Runnable{


    @Override
    public void run() {
        Single single = Single.getInstance();
    }
}
  class Single {
      private  static int tiketsNum = 100;
     // 声明一个静态私有实例变量
     //静态的目的是让多个线程共享一个实例
     private static Single instance;

     // 私有构造函数，防止外部实例化
     private Single() {
     }

     // 提供一个公共的静态方法来获取实例
     public static  Single getInstance() {
         // 判断实例是否为null，如果为null,进入同步代码块
         if (instance == null) {
             synchronized (Single.class) {
                 printTicket();
                 if (instance == null) {
                     instance = new Single();
                 }
             }
         }
         return instance;
     }

     public static void printTicket() {
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