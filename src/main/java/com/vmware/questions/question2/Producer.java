package com.vmware.questions.question2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
    private ConditionBlockingQueue queue;

    public Producer(ConditionBlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            queue.put(new Data(ThreadLocalRandom.current().nextInt()));
            System.out.println(Thread.currentThread().getName() + " put data into queue ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
