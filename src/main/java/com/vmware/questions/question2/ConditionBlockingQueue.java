package com.vmware.questions.question2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBlockingQueue<T> {
    private final Object[] items;
    private int putIndex;
    private int getIndex;
    private int count;

    private final Lock lock = new ReentrantLock();
    private final Condition allowPut = lock.newCondition();
    private final Condition allowGet = lock.newCondition();

    public ConditionBlockingQueue(){
        this(100);
    }
    public ConditionBlockingQueue(int size) {
        if(size < 0){
            throw new IllegalArgumentException("queue size can not be less than 0!");
        }
        this.items = new Object[size];
    }

    public void put(T data) throws InterruptedException {
        lock.lock();

        try {
            while (count == items.length) {
                allowPut.await();
            }
            items[putIndex] = data;
            count++;
            if(++putIndex == items.length){
                putIndex = 0;
            }
            allowGet.signal();
        } catch (InterruptedException e) {
            System.out.println("Interrupt error while put new data into queue");
            throw e;
        }
        finally {
            lock.unlock();
        }
    }

    public T get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                allowGet.await();
            }
            T data = (T) items[getIndex];
            count--;
            if(++getIndex == items.length){
                getIndex = 0;
            }
            allowPut.signal();
            return data;
        } catch (InterruptedException e) {
            System.out.println("Interrupt error while get data from queue");
            throw e;
        }
        finally {
            lock.unlock();
        }
    }
}