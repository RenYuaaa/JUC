package com.study.ren.juc.c_006;

/**
 * @author: renjiahui
 * @date: 2021-09-07 23:49
 * @description:
 */
public class T implements Runnable {

    private int count = 100;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        com.study.ren.juc.c_005.T t = new com.study.ren.juc.c_005.T();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
}
