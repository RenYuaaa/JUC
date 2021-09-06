package com.study.ren.juc.c_000;

import java.util.concurrent.TimeUnit;

/**
 * @author: renjiahui
 * @date: 2021-09-06 23:52
 * @description:
 */
public class T01_WhatIsThread {

    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        // 调用run方法实际只是方法调用的效果。
        // new T1().run();

        // 调用start方法，是开启了线程同时运行
        new T1().start();

        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}
