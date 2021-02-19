package com.company.queue;

/**
 * 用数组实现队列
 */
public class ArrayQueue<T> {


    private T[] array;

    private int capacity;

    private int count;

    private int head;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    public boolean enqueue(T data){
        if (count == capacity){
            return false;
        }

        array[count] = data;
        count++;
        return true;
    }


    public T dequeue(){
        if (count == 0){
            return null;
        }

        T data = array[head];
        head++;
        count--;
        return data;
    }

    public boolean isClean(){
        return count == 0;
    }


    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(11);

        for (int i = 0; i < 11; i++) {
            arrayQueue.enqueue(i);
        }

        while (!arrayQueue.isClean()){
            System.out.println(arrayQueue.dequeue());
        }
    }
}
