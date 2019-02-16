/**
 * Esha Punjabi(EHP170000)
 * Tejas Ravi Rao(TXR171830)
 */

package txr171830;

import java.util.Random;


public class SP9 {
    public static Random random = new Random();
    public static int numTrials = 100;

    public static void main(String[] args) {
        //change n according to the requirements
        int n = 1000000;
        int choice;

        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            choice = Integer.parseInt(args[1]);
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        //choice 1: insertion sort
        //choice 2: Merge sort(Take1)
        //choice 3: Merge sort(Take2)
        //choice 4: Merge sort(Take3)
        choice = 3;
        Timer timer = new Timer();

        switch (choice) {
            case 1:
                //insertion sort
                Shuffle.shuffle(arr);
                numTrials = 1;
                insertionSort(arr);
                break;
            case 2:
                //Merge Sort(Take 1)
                for (int i = 0; i < numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort1(arr);

                }
                break;
            case 3:
                //Merge Sort(Take 2)
                for (int i = 0; i < numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort2(arr);

                }
                break;
            case 4:
                //Merge Sort(Take 3)
                for (int i = 0; i < numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort3(arr);

                }
                break;// etc
        }
        timer.end();

        timer.scale(numTrials);

        System.out.println("Choice: " + choice + "\n" + timer);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * Insertion Sort
     */
    public static void insertionSort(int[] arr) {
        insertionhelper(arr, 0, arr.length - 1);
    }

    public static void insertionhelper(int[] arr, int p, int r) {

        int i = 0;
        int j = 0;
        int key = 0;


        for (i = p + 1; i <= r; i++) {

            key = arr[i];
            j = i - 1;

            while (j >= p && key < arr[j]) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;

        }

    }

    //--------------------------------------------------------------------------------------------

    /**
     * Merge Sort Take 1
     */

    public static void mergeSort1(int[] arr) {

        mergeSort1(arr, 0, arr.length - 1);
    }

    public static void mergeSort1(int[] arr, int p, int r) {
        int q = 0;
        if (p < r) {
            q = (p + r) / 2;


            mergeSort1(arr, p, q);
            mergeSort1(arr, q + 1, r);
            merge1(arr, p, q, r);

        }
    }

    public static void merge1(int[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] left = new int[n1];
        int[] right = new int[n2];
        System.arraycopy(arr, p, left, 0, n1);
        System.arraycopy(arr, q + 1, right, 0, n2);
        int i = 0, j = 0;
        int k = p;
        for (k = p; k <= r; k++) {
            if ((j >= right.length) || ((i < left.length) && (left[i] <= right[j]))) {

                arr[k] = left[i++];

            } else {
                arr[k] = right[j++];
            }
        }

    }

    //--------------------------------------------------------------------------------------------

    /**
     * Merge Sort Take 2
     */

    public static void mergeSort2(int[] arr) {

        int[] arr2 = new int[arr.length];
        mergeSort2(arr, arr2, 0, arr.length);
    }

    public static void mergeSort2(int[] arr, int[] arr2, int left, int n) {


        // Considered T to be 17
        if (n < 17) {
            insertionhelper(arr, left, left + n - 1);
        } else {
            int Ln = n / 2;
            mergeSort2(arr, arr2, left, Ln);
            mergeSort2(arr, arr2, left + Ln, n - Ln);
            merge2(arr, arr2, left, left + Ln - 1, left + n - 1);

        }

    }

    public static void merge2(int[] arr, int[] arr2, int p, int q, int r) {
        int i = 0;
        int j = 0;
        System.arraycopy(arr, p, arr2, p, r - p + 1);
        i = p;
        j = q + 1;
        int k = 0;
        for (k = p; k <= r; k++) {
            if ((j > r) || ((i <= q) && (arr2[i] <= arr2[j]))) {

                arr[k] = arr2[i++];

            } else {
                arr[k] = arr2[j++];
            }
        }

    }

    //--------------------------------------------------------------------------------------------

    /**
     * Merge Sort Take 3
     */

    public static void mergeSort3(int[] arr) {
        int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        mergeSort3(arr, arr2, 0, arr.length);
    }

    public static void mergeSort3(int[] arr, int[] arr2, int left, int n) {

        // Considered T to be 17
        if (n < 17) {
            insertionhelper(arr, left, left + n - 1);
        } else {
            int Ln = n / 2;
            mergeSort3(arr2, arr, left, Ln);
            mergeSort3(arr2, arr, left + Ln, n - Ln);
            merge3(arr, arr2, left, left + Ln - 1, left + n - 1);
        }

    }

    public static void merge3(int[] arr, int[] arr2, int p, int q, int r) {

        int i = p;
        int j = q + 1;
        int k = p;

        while ((i <= q) && (j <= r)) {

            if (arr2[i] <= arr2[j]) {
                arr[k++] = arr2[i++];
            } else {
                arr[k++] = arr2[j++];
            }

        }

        while (i <= q) {
            arr[k++] = arr2[i++];

        }

        while (j <= r) {
            arr[k++] = arr2[j++];
        }
    }

    //--------------------------------------------------------------------------------------------


    /**
     * Timer class for roughly calculating running time of programs
     *
     * @author rbk
     * Usage:  Timer timer = new Timer();
     * timer.start();
     * timer.end();
     * System.out.println(timer);  // output statistics
     */

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() {
            if (!ready) {
                end();
            }
            return elapsedTime;
        }

        public long memory() {
            if (!ready) {
                end();
            }
            return memUsed;
        }

        public void scale(int num) {
            elapsedTime /= num;
        }

        public String toString() {
            if (!ready) {
                end();
            }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed / 1048576) + " MB / " + (memAvailable / 1048576) + " MB.";
        }
    }

    /**
     * @author rbk : based on algorithm described in a book
     */


    /* Shuffle the elements of an array arr[from..to] randomly */
    public static class Shuffle {

        public static void shuffle(int[] arr) {
            shuffle(arr, 0, arr.length - 1);
        }

        public static <T> void shuffle(T[] arr) {
            shuffle(arr, 0, arr.length - 1);
        }

        public static void shuffle(int[] arr, int from, int to) {
            int n = to - from + 1;
            for (int i = 1; i < n; i++) {
                int j = random.nextInt(i);
                swap(arr, i + from, j + from);
            }
        }

        public static <T> void shuffle(T[] arr, int from, int to) {
            int n = to - from + 1;
            Random random = new Random();
            for (int i = 1; i < n; i++) {
                int j = random.nextInt(i);
                swap(arr, i + from, j + from);
            }
        }

        static void swap(int[] arr, int x, int y) {
            int tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        static <T> void swap(T[] arr, int x, int y) {
            T tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        public static <T> void printArray(T[] arr, String message) {
            printArray(arr, 0, arr.length - 1, message);
        }

        public static <T> void printArray(T[] arr, int from, int to, String message) {
            System.out.print(message);
            for (int i = from; i <= to; i++) {
                System.out.print(" " + arr[i]);
            }
            System.out.println();
        }
    }
}
