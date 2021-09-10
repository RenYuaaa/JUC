package com.study.ren.juc.c_20;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class T08_Phaser {

    static Random random = new Random();

    // static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        phaser.bulkRegister(7);

        for (int i = 0; i < 5; i++) {
            final int nameIndex = i;
            new Thread(new Person("p" + j)).start();
        }

        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }*/
}
