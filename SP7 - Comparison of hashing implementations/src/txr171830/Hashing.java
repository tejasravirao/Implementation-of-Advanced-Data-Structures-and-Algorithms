/**SP7 - HASHING
 *
 * SP7 32 - MEMBERS: ESHA PUNJABI, NET ID: ehp170000
 *                   TEJAS RAVI RAO, NET ID: txr171830
 *
 * Please check readme.txt file
 *
 * Robin Hood Hashing
 * *
 * */



package txr171830;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Hashing<T> {

    Entry<T>[] table;
    int size;   //Number of elements in the Hash Table
    int len;    //Length of the Hash Table Array

    public class Entry<T> {
        T element;
        int status;//status can be 0-free, 1-inserted, 2-deleted


        public Entry(T x) {
            this.element = x;
            this.status = 0;// initially all Hash Table spaces are free
        }
    }

    // Constructor for Hashing
    public Hashing() {

        this.len=1024; // initial size is 1024. Rehashing causes size to increase by a factor of 2
        reinitialize();
    }

    /** Reinitialize - Initialize the new Hash Table (after resizing)
     * */
    public void reinitialize()
    {

        this.table = new Entry[len];

        this.size = 0;

        //Initially all elements to be null. Status of all locations to be free
        for(int i = 0; i<len ;i++){
            table[i] = new Entry<>(null);
        }
    }

    /** Helper Method.
     * Search for x and return index of x.
     * If x is not found, return index where x can be added.
     * */
    public int find(T x) {
        int k = 0, xspot = 0;

        int position=hashFunction(x);

        while (true) {
            if (position==len)
            {
                position=(position%len);
            }


            if (table[position].status == 0 || (table[position].element).equals(x)) {

                return position;
            }
            else if (table[position].status == 2) {
                break;
            }
            else {
                position++;
            }
        }

        xspot = position;
        while (true) {

            if (table[position].status == 0) {
                return xspot;
            }

            if (table[position].status == 1 && (table[position].element).equals(x)) {
                return position;
            }

            position++;
            if (position==len)
            {
                position=(position%len);
            }


        }

    }

    /** If element exists in the table then return true, if not then return false.
     * */
    public boolean contains(T x) {
        int loc = find(x);

        if ((table[loc].status == 1) && (table[loc].element).equals(x)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**return the element to be deleted if it exists in the table, if not then it returns null
     * */
    public T remove(T x) {
        int loc = find(x);
        T result;
        if (table[loc].status==1 && (table[loc].element).equals(x)) {
            result = table[loc].element; //return deleted element
            table[loc].status = 2; //mark location as deleted
            size--;
            return result;
        } else
            return null;


    }

    /** Return hash for the value.
     *
     * This function ensures that hashCodes that differ only by
     * constant multiples at each bit position have a bounded
     * number of collisions (approximately 8 at default load factor).
     *
     * */
    static int hash(int h) {

        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**Gives the index within  the Length of the table
     *
     * length = table.length is a power of 2
     * */
    static int indexFor(int h, int length)
    {
        return h & (length - 1);
    }

    /** Custom hash function
     * */
    public int hashFunction(T x) {
        return indexFor(hash(x.hashCode()), len);
    }

    /**Calculate displacement of x from its ideal location of h( x )
     * */
    public int displacement(T x, int loc) {
        return (loc >= hashFunction(x) ? loc - hashFunction(x) : len + loc - hashFunction(x));
    }

    /** Used to element with another element having lower displacement.
     * */
    public T swap(int loc, T x) {

        T y = table[loc].element;
        table[loc].element=x;


        return y;

    }

    /** Calculate Load Factor of the Hash Table
     * */
    public Double getLoadFactor()
    {
        return ((1.0)*size/len);
    }

    /** Rehash when load Factor crosses 50%. Size of the Hash Table is increased
     * by a factor of 2
     * */
    public void rehash(T x)
    {
        Object auxiliary[]=new Object[size];
        int j=0;


        //store all the elements of the table into temporary array
        for (int i=0;i<len;i++)
        {
            if(table[i].status==1)
            {
                auxiliary[j++]=table[i].element;

            }

        }

        //double the len of the table
        len*=2;
        reinitialize(); //reinitialize the resized Hash Table

        //add all the elements of temporary array to newly created hash table(len*2)
        for(int i=0;i<j;i++)
        {
            this.add((T)auxiliary[i]);
        }
        this.add(x);
    }

    /** Add the element in the table
     * */
    public boolean add(T x) {
        int loc = 0;
        int d = 0; //keep displacement value

        if (contains(x)) {
            return false;
        }

        loc = hashFunction(x);

        while (true) {

            if (table[loc].status == 0 || table[loc].status == 2) {

                size++;
                if(getLoadFactor()>0.5)
                {
                    //call rehash then add the element
                    rehash(x);
                }
                else {

                    table[loc].element = x;
                    table[loc].status = 1;

                }
                return true;


            }
            else if (displacement(table[loc].element, loc) >= d) {

                d = d + 1;
                loc = (loc + 1) % len;


            }
            else {
                x = swap(loc, x);
                loc = (loc + 1) % len;
                d = displacement(x, loc);

            }
        }
    }




    public void printTable() {

        if (size != 0) {
            for (int i = 0; i < len; i++) {
                //Print           index ---   element/value      ---     status
                System.out.println(i + "---" + table[i].element + "---" + table[i].status);

            }
        }

    }


    /** Calculate the number of distinct numbers. Used for Hash Set.
     * */
    static<T> int distinctElements(T[ ] arr) {

        // Creates an empty hashset
        HashSet<T> set = new HashSet<>();

        //keep track of number of distinct elements
        int distinctn=0;

        // Traverse the input array
        for (int i=0; i<arr.length; i++)
        {
            // If the number is not present, then put it in Hash Table and print it
            if (!set.contains(arr[i]))
            {
                set.add(arr[i]);
                distinctn++; // keep count of the number of distinct elements
            }
        }
        return distinctn;
    }
    
    /**Calculate the number of distinct numbers. Used for Team Hash Implementation.
     * */
    static<T> int distinctElementsCustom(T[ ] arr) {
        
        // Creates an empty HashSet
        Hashing set = new Hashing();
        
        //Keep track of number of distinct elements
        int distinctn=0;
        
        // Traverse the input array
        for (int i=0; i<arr.length; i++)
        {
            // If the number is not present, then put it in Hash Table and print it
            if (!set.contains((Integer)arr[i]))
            {
                set.add((Integer)arr[i]);
                distinctn++;  // keep count of the number of distinct elements
            }
        }

        return distinctn;
    }



    /** Generate an array of size n with random integers.
     * */
    public void generateRandomArray(Object[] arrRandom,int n){

        Random random = new Random();

        for (int i = 0; i < n; i++)
        {
            arrRandom[i]=(Integer)random.nextInt()%n;
        }

    }


}



















