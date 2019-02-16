/* SP2
SG2 32 - TEAM MEMBERS: TEJAS RAVI RAO (netId: txr171830)
                       ARPITA UMESHKUMAR AGRAWAL  (netId: aua170030)
Date - 9/9/2018
*/

package txr171830;
import java.util.*;

public class BoundedQueue<T> {
    Object[] arrayQ;
    int size;  //size of Array
    int qsize; //number of elements in Queue
    int front; //front of queue
    int rear;  //end of queue

public BoundedQueue( int size){

    this.arrayQ = new Object[size];
    this.size = size;
    qsize = 0;
    front = -1;
    rear = -1;
    }

    //add a new element at the end of the queue
    public boolean offer (T x) {
        if (size == qsize)
            return false;
        else {
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % size;
            arrayQ[rear] = x;
            qsize++;
            return true;
        }
    }

    //remove and return the element at the front of the queue
    public T poll(){
        if(isEmpty())
        return null;
        else{
             T polvalue = ((T)arrayQ[front]);
            front = (front+1)% size;
             qsize--;
            return polvalue;
            }
    }

    //return the front element of the queue
    public T peek()
    {
        if(!isEmpty())
            return ((T)arrayQ[front]);
        else return null;
    }

    //returns number of elements in queue
    public int size()
    {
        return qsize;
    }

    //check if the queue is empty
    boolean isEmpty(){
    if(qsize==0)
        return true;
    else return false;
    }

    //fill user supplied array with queue elements in order
    void toArray(T[] a)
    {
            if(qsize == 0)
            {
                System.out.println("Queue is empty. No elements in user defined Array.");
            }
        if (a.length >= qsize)
        {
            if (!isEmpty())
            {
                for (int i = 0; i < qsize; i++)
                    a[i] = ((T) arrayQ[(front + i) % size]);

            }
        }
            else System.out.println("User given Array is smaller than Queue size.");

    }


    //clear queue of elements
    void clear(){
    front = -1;
    rear = -1;
    qsize = 0;
    }

    //to print the queue
    public void printQ()
    {
        if (!isEmpty())
        {
            for (int i = 0; i < qsize; i++)
                System.out.print((T) arrayQ[(front + i) % size] + " ");
        }
            else System.out.println("Queue is Empty");
    System.out.println();
    }



    public static void main(String[] args)
    {
    BoundedQueue<Integer> BQueue = new BoundedQueue<>(7);
    Scanner in = new Scanner(System.in);
    System.out.println("Options: 1.offer  2.poll  3.peek  4.size  5.isEmpty  6.clear  7.toArray");

    whileloop:
        while(in.hasNext()){
            int opt = in.nextInt();
            switch(opt){
                case 1:// offer - add element at the end of queue
                    int x = in.nextInt();
                    if(BQueue.offer(x)){
                        BQueue.printQ();
                    }
                    break;
                case 2:// poll element from the front of the queue
                    System.out.println(BQueue.poll());
                    BQueue.printQ();
                    break;
                case 3://  peek element at front of the queue
                    System.out.println(BQueue.peek());
                    break;
                case 4:// check size of the queue
                    System.out.println("Size of the queue is " + BQueue.size());
                    break;
                case 5:// check if queue is empty
                    System.out.println(BQueue.isEmpty());
                    BQueue.printQ();
                    break;
                case 6:// clear queue
                    BQueue.clear();
                    BQueue.printQ();
                    break;
                case 7:// to array
                    x = in.nextInt();
                    Integer[] a = new Integer[x];
                    BQueue.toArray(a);
                    for(int i=0;i<a.length; i++)
                    {
                        if (a[i] != null)
                            System.out.print(a[i] + " ");
                    }
                    break;
                default:
                    break whileloop;

            }
        }
    }
}