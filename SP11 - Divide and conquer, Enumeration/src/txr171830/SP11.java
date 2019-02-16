/**SP11 - DIVIDE AND CONQUER, ENUMERATION
 * GROUP 16: TEJAS RAVI RAO
 *           YASH AJAY MADANE
 *
 * NOV 18TH 2018
 *
 * */



package txr171830;

import java.util.Random;

import java.util.PriorityQueue;

public class SP11 {

    //Threshold set at 20
    private static int T = 20;


    /**Returns Kth largest element.
     * */
    public static int select(int arr[], int k) {

        return select(arr, 0, arr.length, k);


    }


    /**Helper method to find Kth largest element.Performs select algorithm.
     * */
    public static int select(int arr[], int p, int n, int k) {

        //if size is less than threshold then use insertion sort
        if (arr.length < T) {
            insertionSort(arr, p, p + n - 1);
            return arr[n - k];

        }
        else {
            int q = RandomisedPartition(arr, p, p + n - 1);
            int left = q - p;
            int right = n - left - 1;
            if (right >= k) {
                return select(arr, q + 1, right, k);
            }
            else if (right + 1 == k) {
                return arr[q];
            }
            else {
                return select(arr, p, left, k - right - 1);
            }
        }

    }

    /**
     * Returns index of pivot chosen at random
     *
     * */

    public static int RandomisedPartition(int arr[], int p, int r) {
        Random rand = new Random();
        int i = p + rand.nextInt(r - p + 1);
        Exchange(arr, i, r);
        return partition(arr, p, r);
    }

    /**
     *
     * Helper method. Single pivot partition algorithm.
     *
     * */
    public static int partition(int arr[], int p, int r) {

        int x = arr[r];
        int i = p - 1;
        int j;
        for (j = p; j <= r - 1; j++) {
            if (arr[j] <= x) {
                i = i + 1;
                Exchange(arr, i, j);
            }
        }
        Exchange(arr, i + 1, r);
        return i + 1;
    }

    /**
     * Exchange elements between two locations of array - index a and index b.
     *
     * */
    public static void Exchange(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    /** Method to do insertion sort if size is less than threshold.
     * */
    public static void insertionSort(int[] arr, int p, int r) {
        for (int i = p + 1; i <= r; i++) {
            int temp = arr[i];
            int j = i - 1;

            while (j >= p && temp < arr[j]) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = temp;

        }
    }

    /**Method to generate random array of integers of size n.
     * */
    public static void generateRandomArray(int[] arr, int n) {

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = (Integer) random.nextInt() % n;
        }

    }

    /**Returns Kth largest element. Makes use of in-built Priority Queue.
     * */
    public static int findKthLargest(int[] arr, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(k);
        for (int i : arr) {
            q.offer(i);

            if (q.size() > k) {
                q.poll();
            }
        }

        return q.peek();
    }





    public static void main(String args[]) {


        Timer timer = new Timer();

        int n = 16000000;
        System.out.println("value of n: " + n);
        int arr[] = new int[n];


        generateRandomArray(arr, n);


        //Implementation of Select using Randomized Partition
        timer.start();
        int res = select(arr, n / 2);
        timer.end();
        System.out.println("Using O(n) select:");
        System.out.println(timer);
        //System.out.println("Kth largest element using O(n) select: " + res);

        //Using Java Priority Queue
        timer.start();
        int res2 = findKthLargest(arr, n / 2);
        timer.end();
        System.out.println("Using O(nlogk) algorithm with Java Priority Queue:");
        System.out.println(timer);
        //System.out.println("Kth largest element using O(nlogk) algorithm (Priority Queues): " + res2);


    }

}

