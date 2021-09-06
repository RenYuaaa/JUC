package com.study.ren.juc.c_000;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: renjiahui
 * @date: 2021-09-07 0:05
 * @description:
 */
public class T02_HowToCreateThread {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun");
        }
    }

    static class MyCallable implements Callable {

        @Override
        public Object call() throws Exception {
            return "Hello MyCallable";
        }
    }

    public static void main(String[] args) {
        // 第一种方式：继承Thread
        new MyThread().start();

        // 第二种：实现Rannable接口
        new Thread(new MyRun()).start();

        // 第三种：通过线程池的方式创建（Executors.newCachedThread()），下面非此方式
        new Thread(() -> {
            System.out.println("Hello Lambda!");
        }).start();

        // 第四种：使用Callable和Future创建线程
        MyCallable callable = new MyCallable();
        FutureTask<String> task = new FutureTask<>(callable);
        new Thread(task).start();
        try {
            //get方法未执行前，后面的代码都得等着，与闭锁的功能相同
            String message = task.get();
            System.out.println("---------------------");
            System.out.println(message);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
