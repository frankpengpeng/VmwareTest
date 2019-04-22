package com.vmware.questions.question2;

public class Consumer implements Runnable {
    private ConditionBlockingQueue queue;
    public Consumer(ConditionBlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            Data data = (Data) queue.get();
            System.out.println(Thread.currentThread().getName() + " got data" + data.getValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
