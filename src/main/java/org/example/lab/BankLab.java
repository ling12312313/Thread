package org.example.lab;
//题目
//银行有一个账户。
//有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。

import java.util.concurrent.locks.ReentrantLock;

public class BankLab {
    public static void main(String[] args) {
          Account account = new Account();
          Thread t1 = new Thread(account);
          Thread t2 = new Thread(account);
          t1.start();
          t2.start();
    }
}

class Account implements Runnable{
    int balance = 0;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        balance+=1000;
        System.out.println(Thread.currentThread().getName()+"存入1000"+"现余额"+balance);

        lock.unlock();
    }
}
