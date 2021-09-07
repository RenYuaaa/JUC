package com.study.ren.juc.c_013;

import java.util.concurrent.TimeUnit;

/**
 * @author: renjiahui
 * @date: 2021-09-08 0:24
 * @description:
 */
public class T {

    /*volatile*/ boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {
            /*try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
