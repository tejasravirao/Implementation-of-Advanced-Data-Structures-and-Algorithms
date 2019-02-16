/* SP3 - BINARY HEAP
SG2 32 - TEAM MEMBERS: TEJAS RAVI RAO (netId: txr171830)
                       ARPITA UMESHKUMAR AGRAWAL  (netId: aua170030)
Date - 9/23/2018
*/


package txr171830;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BinaryHeap<T extends Comparable<? super T>> {
    T[] pq;
    Comparator<T> comp;
    int qsize;

    // Constructor for building an empty priority queue using natural ordering of T
    public BinaryHeap(T[] q) {
        // Use a lambda expression to create comparator from compareTo
        this(q, (T a, T b) -> a.compareTo(b));
        qsize = 0; //initially no elements in queue
    }

    // Constructor for building an empty priority queue with custom comparator
    public BinaryHeap(T[] q, Comparator<T> c) {
        pq = q;
        comp = c;
        qsize = 0;//initially no elements in queue

    }

    /**
     * Build a priority queue with a given array q, using q[0..n-1].
     * It is not necessary that n == q.length.
     * Extra space available can be used to add new elements.
     * Implement this if solving optional problem.  To be called from heap sort.
     */
//    private BinaryHeap(T[] q, Comparator<T> c, int n) {
//        pq = q;
//        comp = c;
//        // You need to add more code here to build queue
//
//    }
    public void add(T x) { /* throw exception if pq is full */
        if (qsize == pq.length) {
            throw new IllegalStateException("Queue is full!");
        } else {
            move(qsize, x);
            percolateUp(qsize);
            qsize++;
        }
    }


    public boolean offer(T x) { /* return false if pq is full */
        if (qsize == pq.length) {
            return false;
        } else {
            move(qsize, x);
            percolateUp(qsize);
            qsize++;
            return true;
        }
    }

    public T remove() { /* throw exception if pq is empty */
        T element = null;
        if (qsize == 0) {
            throw new NoSuchElementException("Queue is Empty!");
        }

        element = pq[0];
        move(0, pq[qsize - 1]);
        qsize--;
        percolateDown(0);

        return element;
    }


    public T poll() { /* return null if pq is empty */
        T element = null;
        if (qsize == 0) {
            return null;
        }

        element = pq[0];
        move(0, pq[qsize - 1]);
        qsize--;
        percolateDown(0);

        return element;
    }


    public T peek() { /* return null if pq is empty, otherwise return the root of the heap */
        if (qsize == 0) {
            return null;
        }
        return pq[0];
    }

    /**
     * pq[i] may violate heap order with parent
     */
    void percolateUp(int i) { /* percolate up operation */
        T element = null;
        element = pq[i];
        while (i > 0 && comp.compare(element, pq[parent(i)]) < 0) {
            move(i, pq[parent(i)]);
            i = parent(i);
        }
        move(i, element);

    }

    /**
     * pq[i] may violate heap order with children
     */
    void percolateDown(int i) { /* percolate down operation */
        T element = null;
        element = pq[i];
        int c = leftChild(i);

        while (c <= qsize - 1) {
            if (c < qsize - 1 && comp.compare(pq[c], pq[c + 1]) > 0) {
                c++;
            }
            if (comp.compare(element, pq[c]) <= 0) {
                break;
            }
            move(i, pq[c]);
            i = c;
            c = leftChild(i);

        }

        move(i, element);
    }


    // Assign x to pq[i].  Indexed heap will override this method
    void move(int i, T x) {
        pq[i] = x;
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    int leftChild(int i) {
        return 2 * i + 1;
    }

    //print the priority queue
    public void printQ() {
        if (qsize != 0) {
            for (int i = 0; i < qsize; i++)
                System.out.print(pq[i] + " ");
        }
        System.out.println();

    }

    // end of functions for team project

    public static void main(String[] args) {
        BinaryHeap<Integer> BHeap = new BinaryHeap<>(new Integer[8]);

        Scanner in = new Scanner(System.in);
        System.out.println("Options: 1.add  2.offer  3.remove  4.poll  5.peek ");
        whileloop:
        while (in.hasNext()) {
            int opt = in.nextInt();
            switch (opt) {
                case 1:// add - throws exception if queue is full
                    int x = in.nextInt();
                    BHeap.add(x);
                    BHeap.printQ();
                    ;
                    break;
                case 2:// offer - returns false if queue is full
                    x = in.nextInt();
                    if (BHeap.offer(x)) {
                        BHeap.printQ();
                    }
                    break;
                case 3://remove - throws exception if queue is empty
                    System.out.println(BHeap.remove());
                    BHeap.printQ();
                    break;
                case 4://poll - returns null if queue is empty
                    System.out.println(BHeap.poll());
                    BHeap.printQ();
                    break;
                case 5://peek
                    System.out.println(BHeap.peek());
                    BHeap.printQ();
                    break;
                default:
                    break whileloop;

            }
        }
    }









    // start of optional problem: heap sort (Q2)

    /** Create a heap.  Precondition: none.
     *  Implement this ifsolving optional problem
     */
    void buildHeap() {
    }

    /* sort array arr[].
       Sorted order depends on comparator used to buid heap.
       min heap ==> descending order
       max heap ==> ascending order
       Implement this for optional problem
     */
    public static<T extends Comparable<? super T>> void heapSort(T[] arr, Comparator<T> c) { /* to be implemented */
    }

    // Sort array using natural ordering
    public static<T extends Comparable<? super T>> void heapSort(T[] arr) {
        heapSort(arr, (T a, T b) -> a.compareTo(b));
    }
// end of optional problem: heap sort (Q2)



// start of optional problem: kth largest element (Q3)

    public void replace(T x) {
	/* TO DO.  Replaces root of binary heap by x if x has higher priority
	     (smaller) than root, and restore heap order.  Otherwise do nothing.
	   This operation is used in finding largest k elements in a stream.
	   Implement this if solving optional problem.
	 */
    }

    /** Return the kth largest element of stream using custom comparator.
     *  Assume that k is small enough to fit in memory, but the stream is arbitrarily large.
     *  If stream has less than k elements return null.
     */
    public static<T extends Comparable<? super T>> T kthLargest(Iterator<T> stream, int k, Comparator<T> c) {
        return null;
    }

    /** Return the kth largest element of stream using natural ordering.
     *  Assume that k is small enough to fit in memory, but the stream is arbitrarily large.
     *  If stream has less than k elements return null.
     */
    public static<T extends Comparable<? super T>> T kthLargest(Iterator<T> stream, int k) {
        return kthLargest(stream, k, (T a, T b) -> a.compareTo(b));
    }
 //end of optional problem: kth largest element (Q3)


}



