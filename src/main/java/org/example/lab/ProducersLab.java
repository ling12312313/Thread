package org.example.lab;

import java.util.concurrent.locks.ReentrantLock;

/*题目
生产者( Producers)将产品交给店员( Clerk),而消费者( (Customer)
 从店员处取走产品, 店员一次只能持有固定数量的产品(比如:20),
 如果生产者试图生产更多的产品,店员会叫生产者停一下,
 如果店中有空位放产品了再通知生产者继续生产; 如果店中没有产品了,
 店员会告诉消费者等一下,如果店中有产品了再通知消费者来取走产品。
 */
public class ProducersLab {
    public static void main(String[] args) {
         Producers producers = new Producers();
         Thread t1 = new Thread(producers);
         Thread t2 = new Thread(new Customer());
         t1.start();
         t2.start();
    }
}
class Clerk {
    protected static int productCount = 5;

}
class Producers extends Clerk implements Runnable{
    @Override
    public void run() {
     synchronized (this) {

         try {
             while (productCount < 20) {
                 produce();
             }
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
     }
    }
    public void produce() throws InterruptedException {
        while (Clerk.productCount >20) {
            wait();
        }
        productCount++;
        System.out.println("生产者生产1个产品,现在有" + productCount + "个产品");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }
}

class Customer extends Clerk implements Runnable{
    @Override
    public void run() {
        synchronized (this) {
            while (productCount >0) {
                try {
                    consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    public void consume() throws InterruptedException {

        while (Clerk.productCount <1) {
            wait();
        }
        productCount--;
        System.out.println("消费者消费1个产品,现在有" + productCount + "个产品");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }
}


