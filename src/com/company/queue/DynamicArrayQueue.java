package com.company.queue;

/**
 * 用数组实现动态扩容队列
 */
public class DynamicArrayQueue<T> {


    private T[] array;

    private int capacity;

    private int tail;

    private int head;

    public DynamicArrayQueue(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    public boolean enqueue(T data){
        //数组满了
        if (tail == capacity){
            if (head == 0){
                return false;
            }

            //数据搬移
            for (int i = head; i < tail; i++) {
                array[i - head] = array[i];
            }
            tail = tail - head;
            head = 0;
        }

        array[tail] = data;
        tail = tail + 1;
        return true;
    }


    public T dequeue(){
        if (head == tail){
            return null;
        }

        T data = array[head];
        head++;
        return data;
    }

    public boolean isClean(){
        return head == tail;
    }


    public static void main(String[] args) {
        DynamicArrayQueue<Integer> arrayQueue = new DynamicArrayQueue<>(11);

        for (int i = 0; i < 11; i++) {
            arrayQueue.enqueue(i);
        }

        while (!arrayQueue.isClean()){
            System.out.println(arrayQueue.dequeue());
        }
    }
}
