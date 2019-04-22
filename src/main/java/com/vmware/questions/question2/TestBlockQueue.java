package com.vmware.questions.question2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestBlockQueue {
    public static void main(String[] args) {
        ExecutorService theadPool = Executors.newFixedThreadPool(10);
        ConditionBlockingQueue queue = new ConditionBlockingQueue(10);
        try {
            for (int i = 0; i < 100; i++) {
                theadPool.execute(new Producer(queue));
                theadPool.execute(new Consumer(queue));
            }
        }
        finally {
            theadPool.shutdown();
        }
    }
}
