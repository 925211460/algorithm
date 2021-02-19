package com.company.queue;

/**
 * 基于链表的队列
 */
public class LinkedListQueue<T> {

    private Node<T> head;

    private Node<T> tail;



    public void enqueue(T data){
        Node newNode = new Node<>(data, null);
        if (tail == null){
            tail = newNode;
            head = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
    }


    public T dequeue(){
        if (head == null){
            return null;
        }

        T data = head.getData();
        head = head.next;
        if (head == null){
            tail = null;
        }
        return data;
    }

    public boolean isClean(){
        return head == null;
    }




    private static class Node<T> {
        private T data;
        private Node next;

        public T getData() {
            return data;
        }


        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        LinkedListQueue<Integer> arrayQueue = new LinkedListQueue<Integer>();

        for (int i = 0; i < 11; i++) {
            arrayQueue.enqueue(i);
        }

        while (!arrayQueue.isClean()){
            System.out.println(arrayQueue.dequeue());
        }
    }
}
