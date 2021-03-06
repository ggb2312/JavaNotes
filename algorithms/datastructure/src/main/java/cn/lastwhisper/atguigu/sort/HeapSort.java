package cn.lastwhisper.atguigu.sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author cn.lastwhisper
 */
public class HeapSort {
    public static void main(String[] args) {
        //int[] arr = {4, 6, 8, 5, 9, 58, -10};
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println("数组：" + Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {

        //adjustHeap(arr, 1, arr.length);
        //System.out.println("第1次" + Arrays.toString(arr)); // 4,9,8,5,6

        //adjustHeap(arr, 0, arr.length);
        //System.out.println("第2次" + Arrays.toString(arr)); // 9,6,8,5,4
        // 1. 满二叉树从右向左、从下往上构建大顶堆；完全二叉树从左向右、从下往上构建大顶堆；
        // i--意味着，从下到上，每一次都是[i,arr.length]的数组（二叉树）进行重构大顶堆
        // [0,arr.length]重构了数组（二叉树）头部几个元素的大顶堆，同时也会重构下面的二叉树
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 2. 交换堆顶元素与末尾元素，调整堆结构
        for (int j = arr.length - 1; j >= 0; j--) {
            // 交换
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }


    /**
     * 将一个数组(二叉树), 调整成一个大顶堆
     * 功能： 将以i对应的非叶子节点的树调整成大顶堆
     * 举例  int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用  adjustHeap 传入的是 i = 0 => {4, 9, 8, 5, 6} => {9, 6, 8, 5, 4}
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 当前i节点
        int temp = arr[i];
        // k是i节点的左子节点，k + 1是右子节点
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //  arr[k] < arr[k + 1]左节点小于右节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // k指向数值最大的节点
                k++;
            }
            if (arr[k] > temp) {//子节点大于父节点
                arr[i] = arr[k];//将子节点赋值到父节点
                i = k;//记录子节点的下标，为了最后 arr[i] = temp;
            } else {
                break;
            }
        }
        //i = k; arr[i]此时已经是子节点的位置了，前面父节点已经拿到了子节点的值了，这里要将子节点的值赋为父节点的值
        arr[i] = temp;
    }
}
