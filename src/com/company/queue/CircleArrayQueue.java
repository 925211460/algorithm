package com.company.queue;

/**
 * 用数组实现循环队列
 */
public class CircleArrayQueue<T> {


    private T[] array;

    private int capacity;

    private int tail;

    private int head;

    public CircleArrayQueue(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    /**
     * 队列满的条件： (tail + 1) % n = head
     * @param data
     * @return
     */
    public boolean enqueue(T data){
        if ((tail + 1) % capacity == head){
            return false;
        }

        array[tail] = data;
        tail = (tail + 1) % capacity;
        return true;
    }


    /**
     * 队列空的条件是 head==tail
     * @return
     */
    public T dequeue(){
        if (head == tail){
            return null;
        }

        T data = array[head];
        head = (head + 1) % capacity;
        return data;
    }

    public boolean isClean(){
        return head == tail;
    }


    public static void main(String[] args) {
        CircleArrayQueue<Integer> arrayQueue = new CircleArrayQueue<>(11);

        for (int i = 0; i < 11; i++) {
            arrayQueue.enqueue(i);
        }

        while (!arrayQueue.isClean()){
            System.out.println(arrayQueue.dequeue());
        }
    }
}
