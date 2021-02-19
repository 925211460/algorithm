package com.company.stack;

/**
 * 数组实现栈
 */
public class ArrayStack<T> {

    private T[] array;

    /**
     * 栈中元素的个数
     */
    private int count;

    /**
     * 栈的大小
     */
    private int capicity;

    public ArrayStack(int n) {
        array = (T[]) new Object[n];
        capicity = n;
    }

    public boolean add(T value){
        if (value == null){
            return false;
        }

        if (count > capicity){
            return false;
        }

        array[count] = value;
        count++;
        return true;
    }


    public T pop(){
       if (count == 0){
           return null;
       }
       count--;
       return array[count];
    }


    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(10);

        for (int i = 0; i < 10; i++) {
            arrayStack.add(i);
        }

        int i = 0;
        while (i < 10){
            System.out.println(arrayStack.pop());
            i++;
        }
    }

}
