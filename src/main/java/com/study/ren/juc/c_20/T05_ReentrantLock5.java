package com.study.ren.juc.c_20;

import java.util.concurrent.locks.ReentrantLock;

/**
 * RentrantLock也是一个公平锁，需要使用参数开启
 * Reentrantlock默认为非公平锁
 * synchronized只是非公平锁
 */
public class T05_ReentrantLock5 extends Thread{

    /**
     * 参数为true表示为公平锁
     */
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 rl = new T05_ReentrantLock5();
        Thread t1 = new Thread(rl);
        Thread t2 = new Thread(rl);
        t1.start();
        t2.start();
    }
}
