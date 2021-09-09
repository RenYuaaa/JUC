package com.study.ren.juc.c_20;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁
 * synchronized是可重入锁的一种
 */
public class T01_ReentrantLock1 {

    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2() {
        System.out.println("m2.....");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 tl = new T01_ReentrantLock1();
        new Thread(tl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(tl::m2).start();
    }
}
