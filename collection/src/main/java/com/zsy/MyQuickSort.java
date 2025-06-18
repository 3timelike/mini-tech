package com.zsy;

public class MyQuickSort {

    
    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            // 获取分区点
            int pi = partition(arr, low, high);
            
            // 递归排序分区点左边的子数组
            sort(arr, low, pi - 1);
            // 递归排序分区点右边的子数组
            sort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        // 选择最右边的元素作为基准值
        int pivot = arr[high];

        //int i = (low - 1);
        //小于基准值的元素的最后位置
        int last = low - 1;
        // 遍历数组，将小于基准值的元素放到左边
        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                last++;
                swap(arr, last, i);
            }
        }
        
        // 将基准值放到正确的位置
        swap(arr, last + 1, high);
        return last + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
} 