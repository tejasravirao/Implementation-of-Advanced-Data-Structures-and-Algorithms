/* SP4 - BINARY SEARCH TREE
SG2 32 - TEAM MEMBERS: TEJAS RAVI RAO (netId: txr171830)
                       ARPITA UMESHKUMAR AGRAWAL  (netId: aua170030)
Date - 9/30/2018
*/

package txr171830;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
            this.left = left;
            this.right = right;
        }
    }

    Entry<T> root;
    int size;
    Stack<Entry<T>> s = new Stack<>();  // Stack to keep track of parent information

    public BinarySearchTree() {
        root = null;
        size = 0;


    }


    /** TO DO: Is x contained in tree?
     */
    public boolean contains(T x) {

        Entry<T> newNode = find(x);

        if (newNode == null || newNode.element.compareTo(x) != 0) {
            return false;
        }
        else {
            return true;
        }
    }

    /** TO DO: Is there an element that is equal to x in the tree?
     *  Element in tree that is equal to x is returned, null otherwise.
     */
    public T get(T x) {

        Entry<T> newNode = find(x);

        if(newNode==null || newNode.element.compareTo(x) != 0) {
            return null;
        }
        else{
            return newNode.element;
        }

    }

    /** Helper method. Find entry containing x. */
    private Entry<T> find(T x){
        s.push(null);

        Entry<T> helper = find(this.root,x);
        return helper;
    }

    /** Find x within subtree rooted at subroot.
    * */
    private Entry<T> find(Entry<T> subroot,T x){
        if(subroot == null || subroot.element.compareTo(x) == 0){
            return subroot;
        }
        while(true) {
            if (x.compareTo(subroot.element) < 0) {
                if (subroot.left == null) {
                    break;
                } else {
                    s.push(subroot);
                    subroot = subroot.left;
                }
            }
            else if (x.compareTo(subroot.element) == 0) {
                break;
            }
            else {
                if (subroot.right == null) {
                    break;
                } else {
                    s.push(subroot);
                    subroot = subroot.right;
                }
            }
        }
        return subroot;

    }


    /** TO DO: Add x to tree. 
     *  If tree contains a node with same key, replace element by x.
     *  Returns true if x is a new element added to tree.
     */
    public boolean add(T x) {
        if (size==0){
            root = new Entry<T>(x,null,null);
            size++;
            return true;
        }
        else {
            Entry<T> newNode = find(x);

            if(newNode.element.compareTo(x)==0){
                newNode.element = x;               //replace element by x
                return false;
            }

            else if(x.compareTo(newNode.element)<0){
                newNode.left = new Entry<T>(x,null,null);
            }
            else {
                newNode.right = new Entry<T>(x,null,null);
            }
            size++;
            return true;
        }

    }

    /** TO DO: Remove x from tree. 
     *  Return x if found, otherwise return null
     */
    public T remove(T x) {

        if(root == null){
            return null;
        }

        Entry<T> newNode = find(x);

        if (newNode.element.compareTo(x) != 0){
            return null;
        }

        T result = newNode.element;

        if(newNode.left == null || newNode.right ==  null){
            bypass(newNode);
        }

        else {           //newNode has 2 children.
            s.push(newNode);

            Entry<T> minRight = find(newNode.right,x);
            newNode.element = minRight.element;
            bypass(minRight);
        }
        size--;
        return result;
    }

    /**PreCondition - newNode has at most one child.
     *              - stack s has path from root to parent of newNode
     * */
    private void bypass(Entry<T> node){
        Entry<T> parent = s.peek();
        Entry<T> child = null;

        if(node.left == null){
            child = node.right;
        }
        else{
            child = node.left;
        }

        if(parent == null){
            root = child;
        }
        else{
            if(parent.left == node){
                parent.left = child;
            }
            else{
                parent.right = child;
            }
        }
    }

    /** Minimum value element of the tree
     * */
    public T min() {
        if(size==0){
            return null;
        }
        Entry<T> newNode = root;
        while(newNode.left != null){
            newNode=newNode.left;
        }
        return newNode.element;
    }

    /** Maximum value element of the tree
     * */
    public T max() {
        if(size==0){
            return null;
        }
        Entry<T> newNode = root;
        while(newNode.right != null){
            newNode=newNode.right;
        }
        return newNode.element;
    }

    // TODO: Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
        Comparable[] arr = new Comparable [size];
        /* write code to place elements in array here */
        int index = 0;
        inorderstore(root,arr,index);

        return arr;

    }

    /** Inorder Traversal of tree in array.
     * */
    private int inorderstore(Entry<T>node, Comparable[] arr, int index){

       if(node==null){
           return index;
       }
       index = inorderstore(node.left,arr,index);

       arr[index] = node.element;
       index = index+1;

       index = inorderstore(node.right,arr,index);


       return index;
    }

// Start of Optional problem 2

    /** Optional problem 2: Iterate elements in sorted order of keys
     Solve this problem without creating an array using in-order traversal (toArray()).
     */
    public Iterator<T> iterator() {
        return null;
    }

    // Optional problem 2.  Find largest key that is no bigger than x.  Return null if there is no such key.
    public T floor(T x) {
        return null;
    }

    // Optional problem 2.  Find smallest key that is no smaller than x.  Return null if there is no such key.
    public T ceiling(T x) {
        return null;
    }

    // Optional problem 2.  Find predecessor of x.  If x is not in the tree, return floor(x).  Return null if there is no such key.
    public T predecessor(T x) {
        return null;
    }

    // Optional problem 2.  Find successor of x.  If x is not in the tree, return ceiling(x).  Return null if there is no such key.
    public T successor(T x) {
        return null;
    }

// End of Optional problem 2

    public static void main(String[] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        Scanner in = new Scanner(System.in);

        while(in.hasNext()) {
            System.out.println(t.get(7));

            int x = in.nextInt();
            if(x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if(x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                System.out.print("Final: ");
                for(int i=0; i<t.size; i++) {
                    System.out.print(arr[i] + " ");

                }
                System.out.println();
                return;
            }

        }

    }

    public void printTree() {
        System.out.print("[" + size + "]");
        printTree(root);
        System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
        if(node != null) {
            printTree(node.left);
            System.out.print(" " + node.element);
            printTree(node.right);
        }
    }

}