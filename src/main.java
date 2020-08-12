/**
 * 05/11/20
 * Justin Girgis
 *
 * This is a program to demonstrate the difference in speed between
 * heapsort and selection sort. Also, it was good practice for the
 * System class, specifically the nanoTime() function. 
 */


import java.util.HashSet;
import java.util.Scanner;
import java.util.Random;


public class main {

   static int [] array2; // HeapSort


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Please enter a number between 0 and 200: ");
        int num = scanner.nextInt();


        array2 = sortArray(num);

        for(int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + " ");
        }

        heap_sort(array2);


        System.out.println();

        for(int i = 0; i < array2.length; i++) {
            System.out.print(array2[i] + " ");
        }

        timeSelectionSort();
        timeHeapSort();

    }

    static void heap_sort(int [] array1) {
        int n = array1.length - 1;
        build_MaxHeap(n, array1);
        for(int i = n; i > -1; i--) {

            int temp = array1[0];
            array1[0] = array1[i];
            array1[i] = temp;
            max_heapify(0, i, array1);
        }

    }

    static void build_MaxHeap(int arraySize, int [] array1) {

        int n = arraySize;

        for(int i = (n/2); i >= 0; i--) {
            max_heapify(i, n, array1);
        }
    }

    static void max_heapify(int internalNode, int array2Size, int [] array1) {
        int left_index = (2*internalNode) + 1;
        int right_index = (2*internalNode) + 2;
        int max_index = internalNode;

        if(left_index < array2Size) {
            if(array1[left_index] > array1[internalNode])
                max_index = left_index;
        }

        if(right_index < array2Size) {
            if(array1[right_index] > array1[internalNode] && (array1[right_index] > array1[left_index]))
                max_index = right_index;
        }

        if(max_index != internalNode) {
            int temp = array1[internalNode];
            array1[internalNode] = array1[max_index];
            array1[max_index] = temp;
            max_heapify(max_index, array2Size, array1);
        }

    }

    static int [] sortArray(int size) {
        Random random = new Random();
        int smallest;
        int [] array = new int[size];
        HashSet<Integer> used = new HashSet<Integer>();

        for (int i = 0; i < array.length; i++) {
            int num = random.nextInt(2001) - 1000;
            while (used.contains(num)) {
                num = random.nextInt(2001) - 1000;
            }
            used.add(num);
            array[i] = num;
        }

        return array;
    }

    static void selectionSort(int [] array1) {
        for(int i = 0; i < array1.length - 1; ++i) {
            int min = i;

            int j;
            for(j = i + 1; j < array1.length; ++j) {
                if (array1[j] < array1[min]) {
                    min = j;
                }
            }

            j = array1[min];
            array1[min] = array1[i];
            array1[i] = j;
        }

    }

    static void timeSelectionSort() {
        int counter = 0;
        long start = System.nanoTime();
        int [] a;
        int repetitions = 100;

        while (counter < repetitions) {
            a = sortArray(1000);
            selectionSort(a);
            counter++;
        }


        long end = System.nanoTime();
        long total = end - start;

        System.out.println("\nAverage running time for 100 repetitions for selection sort is: " + total + " nanoseconds.");
    }

    static void timeHeapSort() {
        int counter = 0;
        long start = System.nanoTime();
        int [] a;
        int repetitions = 100;

        while (counter < repetitions) {
            a = sortArray(1000);
            heap_sort(a);
            counter++;
        }


        long end = System.nanoTime();
        long total = end - start;

        System.out.println("Average running time for 100 repetitions for heap sort is: " + total + " nanoseconds");
    }
}
