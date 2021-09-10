package com.study.ren.juc.c_20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 * 由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 *
 * 使用Reentrantlock可以完成同样的功能
 * 需要注意的是，必须要手动释放锁
 * 使用sync锁的时候，如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 */
public class T03_ReentrantLock3 {
    Lock lock = new ReentrantLock();

    void m1() {
        // 和synchronized(this)有同样作用
        lock.lock();
        try {
            // 如果循环5次以下，tryLock就可以拿到锁了，因为他等待的时间为5秒种，超过5秒则不继续尝试获取锁了
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据tryLock的返回值来判定是否锁定
     * 也可以制定tryLock的时间。由于tryLock(time)抛出异常，所以要注意unclock
     */
    synchronized void m2() {
        /*boolean locked = lock.tryLock();
        System.out.println("m2..." + locked);
        if (locked) {
            lock.unlock();
        }*/

        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T03_ReentrantLock3 tl = new T03_ReentrantLock3();
        new Thread(tl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(tl::m2).start();
    }
}
