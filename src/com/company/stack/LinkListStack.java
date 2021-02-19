package com.company.stack;

/**
 * 链表实现栈
 */
public class LinkListStack<T> {



    private Node<T> top = null;

    public void push(T data){
        Node<T> node = new Node<>(data, null);

        if (top == null){
            top = node;
        } else {
            node.next = top;
            top = node;
        }
    }

    public T pop(){
        if (top == null) return null;
        T data = top.getData();
        top = top.next;
        return data;
    }


    private static class Node<T> {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }
    }


    public static void main(String[] args) {
        LinkListStack<Integer> linkListStack = new LinkListStack<>();
        for (int i = 0; i < 10; i++) {
            linkListStack.push(i);
        }

        int i = 0;
        while (i < 10){
            System.out.println(linkListStack.pop());
            i++;
        }
    }

}
