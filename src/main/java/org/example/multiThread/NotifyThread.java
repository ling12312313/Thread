package org.example.multiThread;

class Store {
    private int stock = 0;

    // 消费者方法
    public synchronized void consume() throws InterruptedException {
        while (stock == 0) {
            wait();  // 等待，直到有货物
        }
        stock--;
        System.out.println("Consumed, remaining stock: " + stock);
        notify();  // 通知生产者生产货物
    }

    // 生产者方法
    public synchronized void produce() throws InterruptedException {
        while (stock >= 10) {
            wait();  // 等待，直到库存有空间
        }
        stock++;
        System.out.println("Produced, stock: " + stock);
        notify();  // 通知消费者消费
    }
}

public class NotifyThread {
    public static void main(String[] args) {
        Store store = new Store();

        // 生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    store.produce();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 消费者线程
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    store.consume();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
