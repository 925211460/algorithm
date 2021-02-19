package com.company.sort;

import java.util.ArrayList;

public class Sort {


    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] array){
        if (array == null || array.length < 1) return;

        for (int i = 0; i < array.length; i++) {
            //优化点，设置提前退出标记位
            boolean flag = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]){
                    flag = true;
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
            if (!flag){
                break;
            }
        }
    }


    /**
     * 插入排序，数据分成已排序区和未排序区，初始状态下已排序区只有一个数据。
     * 每次从未排序区拿一个数据，插入到已排序区
     * @param array
     */
    public static void insertionSort(int[] array){
        if (array == null || array.length < 1){
            return;
        }

        for (int i = 1; i < array.length; i++) {
            //查找插入位置
            int value = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (array[j] > value){
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = value;
        }
    }


    /**
     * 选择排序
     * @param array
     */
    public static void selectionSort(int[] array){
        if (array == null || array.length < 2){
            return;
        }

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]){
                    minIndex = j;
                }
            }

            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    /**
     * 希尔排序,按步长序列为{1....n/4,n/2}对数组分组进行插入排序，当步长为1时，排序完成
     * @param arr
     */
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 2){
            return;
        }

        int length = arr.length;
        //按不同的步长分组
        for (int gap = length/2; gap > 0 ; gap /=2) {
            //下面就是插入排序的思路，轮流对每组的元素进行插入排序，而不是一个组排完再排另外一个组
            for (int i = gap; i < length; i++) {
                //插入排序
                int inserted = arr[i];
                int j;
                for (j = i - gap; j >= 0; j-=gap) {
                    if (arr[j]  > inserted){
                        arr[j + gap] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + gap] = inserted;
            }
        }

    }

    /**
     * 归并排序，稳定排序算法，但不是原地排序算法，因为合并操作需要另外的数组
     * 递归公式：mergeSort(array, p, r) = merge(mergeSort(array, p, q), mergeSort(array, q + 1, r));
     * 终止条件： q >= r
     * 重点： merge函数的实现
     * @param array
     */
    public static void mergeSort(int[] array){
        mergeSortInternal(array, 0, array.length - 1);
    }

    private static void mergeSortInternal(int[] array, int p, int r) {
        if (p >= r){
            return;
        }

        //求分界的中间点, 注意计算过程中不能超过整型的最大值
        int q = p + (r - p)/2;
        mergeSortInternal(array, p, q);
        mergeSortInternal(array, q + 1, r);

        //合并两个有序子数组
        merge(array, p, q, r);
    }

    /**
     * 合并两个有序子数组， 利用哨兵机制简化代码
     * @param array
     * @param p
     * @param q
     * @param r
     */
    private static void merge(int[] array, int p, int q, int r) {
        int[] leftArray = new int[q - p + 2];
        int[] rightArray = new int[r - q + 1];

        for (int i = 0; i < q - p + 1; i++) {
            leftArray[i] = array[p + i];
        }
        //设置哨兵
        leftArray[q - p + 1] = Integer.MAX_VALUE;

        for (int i = 0; i < r - q; i++) {
            rightArray[i] = array[q + 1 + i];
        }
        //设置哨兵
        rightArray[r - q] = Integer.MAX_VALUE;

        //注意这里的循环终止条件是k=r， 而不是k<r
        int k = p, i = 0, j = 0;
        while (k <= r){
            if (leftArray[i] <= rightArray[j]){
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
    }

    /**
     * 快速排序，原地排序算法，不稳定的排序算法
     * 递归公式： quickSort(array, p, r) = quickSort(p, q - 1) + quickSort(q + 1, r)
     * 终止条件： p >= r
     * @param array
     */
    public static void quickSort(int[] array){
        quickSortInternal(array, 0, array.length - 1);
    }

    private static void quickSortInternal(int[] array, int p, int r) {
        if (p >= r){
            return;
        }
        //分区方法可以通过交换元素的技巧达到原地分区的效果
        int q = partition(array, p, r);
        quickSortInternal(array, p, q - 1);
        quickSortInternal(array, q + 1, r);
    }

    private static int partition(int[] array, int p, int r) {
        int pivot = array[r];

        //分区点，array[p, partition - 1]的都是小于pivot的，array[partition, r- 1]的都是大于pivot的
        int partition = p;
        for (int i = p; i < r; i++) {
            if (array[i] < pivot){
                //交换array[i] 和 array[partition]，然后partition后移一位
                if (i == partition){
                    partition++;
                } else {
                    int temp = array[partition];
                    array[partition] = array[i];
                    array[i] = temp;
                    partition++;
                }
            }
        }

        //把pivot放到partition的位置
        int temp = array[partition];
        array[partition] = pivot;
        array[r] = temp;

        //返回分区位置
        return partition;
    }

    /**
     * 求第K小的元素
     * @param arr
     * @param k
     * @return
     */
    public static int kthSmallest(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return -1;
        }

        int partition = partitionForKthSmallest(arr, 0, arr.length - 1);
        //如果 partition + 1 = k, 则满足条件
        //如果 partition + 1 > k, 则要求的位置在array[0, partition - 1]范围内
        //如果 partition + 1 < k， 则要求的位置在array[partition + 1, array.length -1]范围内
        while (partition + 1 != k) {
            if (partition + 1 < k) {
                partition = partitionForKthSmallest(arr, partition + 1, arr.length - 1);
            } else {
                partition = partitionForKthSmallest(arr, 0, partition - 1);
            }
        }

        return arr[partition];
    }

    private static int partitionForKthSmallest(int[] arr, int p, int r) {
        int pivot = arr[r];

        int i = p;
        for (int j = p; j < r; j++) {
            // 这里要是 <= ，不然会出现死循环，比如查找数组 [1,1,2] 的第二小的元素
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, r);

        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * 桶排序
     * @param array
     * @param bucketSize
     */
    public static void bucketSort(int[] array, int bucketSize){
        if (array == null || array.length < 2) {
            return;
        }

        //确定数组中的最大值和最小值
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] > max){
                max = array[i];
            } else if (array[i] < min){
                min = array[i];
            }
        }

        //确定桶的个数,初始化桶数组
        int bucketCount = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];

        //遍历原数组，将数据放入到每个桶中
        int[] bucketItemIndexArray = new int[bucketCount];
        for (int i = 0; i < array.length; i++) {
            int bucketIndex = (array[i] - min)/bucketSize;

            //确认buckets[bucketIndex]这个数组的容量是否足够，如果不够则扩容
            if (bucketItemIndexArray[bucketIndex] == bucketSize){
                ensureCapecity(buckets[bucketIndex], bucketSize);
            }

            buckets[bucketIndex][bucketItemIndexArray[bucketIndex]++] = array[i];
        }

        //对每个桶内的数据进行排序
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            quickSortInternal(buckets[i], 0, bucketItemIndexArray[i] - 1);

            //取出桶中的数据，放入原数组，完成排序
            for (int j = 0; j < bucketItemIndexArray[i]; j++) {
                array[k++] = buckets[i][j];
            }
        }
    }

    private static void ensureCapecity(int[] bucket, int bucketSize) {
        int[] newBucket = new int[2 * bucketSize];
        for (int i = 0; i < bucket.length; i++) {
            newBucket[i] = bucket[i];
        }
        bucket = newBucket;
    }

    /**
     * 计数排序
     * @param array
     */
    public static void countSort(int[] array){
        //计算数组的最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }
        }

        //初始化计数数组
        int[] c = new int[max + 1];


        //计算原数组中每个值的个数，并存到计数数组中
        for (int i = 0; i < array.length; i++) {
            c[array[i]]++;
        }

        //对计数数组顺序求和
        for (int i = 1; i < max + 1; i++) {
            c[i] = c[i - 1] + c[i];
        }


        //从尾部遍历原数组，根据计数数组，把原数组的值按顺序存储到新的数组中。从尾部遍历，是为了保持算法的稳定性
        int[] sortArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            //计算新的数组中的位置
            int index = c[array[i]] - 1;

            //赋值
            sortArray[index] = array[i];

            //计数数组中的对应值要减1
            c[array[i]]--;
        }

        //拷贝排序好的数组到原数组
        for (int i = 0; i < array.length; i++) {
            array[i] = sortArray[i];
        }
    }


    /**
     * 基数排序，时间复杂度为O(n)
     * 例如十万个11位的手机号排序的例子，因为数据范围太大不适合用桶排序和计数排序.
     * 手机号可以分割成一位一位的数据，并且高位大的时候就不用比较低位了，此时就非常适合使用基数排序
     * 基数排序就是从尾到头依次比较每一位，并且比较每一位的算法要是稳定性算法而且时间复杂度要是O(n)。比较K次时间复杂度就是O(kn)
     * 手机号是11位，所以时间复杂度就是O(11n),即O(n)
     * @param array
     */
    public static void radixSort(int[] array){
        //先求数组的最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }
        }

        for (int exp= 1; max / exp > 0; exp *= 10){
            //根据"位数"对数组进行计数排序
            radixCountSortInternal(array, exp);
        }
    }

    /**
     * 根据位数对数组进行排序，需要是稳定的排序，这里采用计数排序
     * @param array
     * @param exp
     */
    private static void radixCountSortInternal(int[] array, int exp) {
        int[] c = new int[10];

        for (int i = 0; i < array.length; i++) {
            c[array[i] / exp % 10]++;
        }

        //对c顺序求和
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i - 1] + c[i];
        }

        //倒叙遍历原数组，初始化一个新数组，根据c计数数组排序后存储到新数组中
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int index = c[array[i] / exp % 10] - 1;
            newArray[index] = array[i];
            c[array[i] / exp % 10]--;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }

    public static void main(String[] args) {
        int[] array = {11,12,14,15,16,13};

        radixSort(array);

        for (int item: array){
            System.out.println(item + " ");
        }
    }
}
