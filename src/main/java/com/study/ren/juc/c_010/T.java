package com.study.ren.juc.c_010;

import java.util.concurrent.TimeUnit;

/**
 * @author: renjiahui
 * @date: 2021-09-08 0:09
 * @description:
 */
public class T {

    synchronized void m() {
        System.out.println("m1 start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end...");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}

class TT extends T {

    @Override
    synchronized void m() {
        System.out.println("child m start...");
        super.m();
        System.out.println("child m end...");
    }
}
