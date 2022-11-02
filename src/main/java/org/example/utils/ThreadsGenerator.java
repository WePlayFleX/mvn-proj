package org.example.utils;


import lombok.AllArgsConstructor;

public class ThreadsGenerator {

    public static void main(String[] args) {

        Thread thisThread = Thread.currentThread();
        System.out.println(thisThread + " is started...");

        //run generator
        Thread file = new Thread(new FileGeneratorThread());
        file.start();

        //run sender
        Thread mail = new Thread(new MailSenderThread());
        mail.start();

        System.out.println(thisThread + " is finished.");

    }

}

@AllArgsConstructor
class FileGeneratorThread implements Runnable{

    @Override
    public void run(){

    }
}

@AllArgsConstructor
class MailSenderThread implements Runnable{

    private Thread wait;
    private long timeout;

//    public void setWait(Thread wait) {
//        this.wait = wait;
//    }

    @Override
    public void run(){

        System.out.println("MailSenderThread is started...");

        try{
            wait.join(timeout);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        MailUtils.send(AppConstants.EMAIL_ACC, );



        System.out.println("MailSenderThread is finished.");
    }
}