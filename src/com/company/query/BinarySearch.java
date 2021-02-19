package com.company.query;

/**
 * 二分查找
 */
public class BinarySearch {


    /**
     * 二分查找,二分查找的基本写法很简单，主要是掌握二分查找的变种
     * 变体一：查找第一个值等于给定值的元素
     * 变体二：查找最后一个值等于给定值的元素
     * 变体三：查找第一个大于等于给定值的元素
     * 变体四：查找最后一个小于等于给定值的元素
     */
    public static int binarySearch(int[] array, int value){
        int low = 0;
        int high = array.length - 1;
        while (low <= high){
            int mid = low + (high - low)/2;
            if (array[mid] > value){
                high = mid - 1;
            } else if (array[mid] < value){
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {11,12,13,14,15,16};

        int index = binarySearch(array, 17);
        System.out.println(index);


    }

}
