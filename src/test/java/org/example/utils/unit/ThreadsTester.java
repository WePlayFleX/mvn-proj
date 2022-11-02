package org.example.utils.unit;

import lombok.AllArgsConstructor;

public class ThreadsTester {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = Thread.currentThread();

        thread.setName("WePlaY_FleX");
        thread.setPriority(Thread.MAX_PRIORITY);
        long id = thread.getId();

//        thread.setDaemon(true);

//        System.out.println(thread + " " + id);

        System.out.println(thread + " is started...");

        //MAIN job
        Thread myThread = new Thread(new MyThread("MY THREAD #1"));
        myThread.start();
        myThread.join();

        Thread myThread2 = new Thread(new ExtraThread("MY THREAD #2"));
        myThread2.start();
        myThread2.join();

        for (int i = 0; i < 10; i++) {
            System.out.println("MAIN i = " + i);
            Thread.sleep(50);
        }

        System.out.println(thread + " is finished.");

    }
}
class MyThread implements Runnable {


    String label;
    public MyThread(String label) {
        this.label = label;
    }

    @Override
    public void run() {

        System.out.println(label + " is started...");

        //job
        for (int i = 0; i < 100; i++) {
            System.out.println(label + " i = " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("MyThread is Interrupted");
                throw new RuntimeException(e);
            }
        }


        System.out.println(label + " is finished.");
    }
}


@AllArgsConstructor
class ExtraThread implements Runnable {

    Thread joinTh;

    String label;
    public ExtraThread(String label) {
        this.label = label;
    }

    @Override
    public void run() {

        System.out.println(label + " is started...");

        //job
        for (int i = 0; i < 50; i++) {
            System.out.println(label + " i = " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("MyThread is Interrupted");
                throw new RuntimeException(e);
            }
        }


        System.out.println(label + " is finished.");
    }
}



