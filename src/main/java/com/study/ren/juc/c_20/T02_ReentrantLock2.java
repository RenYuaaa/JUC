package com.study.ren.juc.c_20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 * 由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 * <p>
 * 使用Reentrantlock可以完成同样的功能
 * 需要注意的是，必须要手动释放锁
 * 使用sync锁的时候，如果遇到一场，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 */
public class T02_ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1() {
        // 和synchronized(this)有同样作用
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    synchronized void m2() {
        lock.lock();
        try {
            System.out.println("m2.....");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T02_ReentrantLock2 tl = new T02_ReentrantLock2();
        new Thread(tl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(tl::m2).start();
    }
}
