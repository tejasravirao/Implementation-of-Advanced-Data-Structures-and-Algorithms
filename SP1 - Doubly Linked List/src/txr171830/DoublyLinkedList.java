/* SP1
SG1 16 - TEAM MEMBERS: TEJAS RAVI RAO (netId: txr171830)
                       SHREYASH SANJAY MANE  (netId: ssm170730)
SP1 - DoublyLinkedList extends SinglyLinkedList
      methods hasPrevious(), Previous(), add(x) in DoublyLinkedList
Date - 2/9/2018
*/

package txr171830;

import java.util.*;

public class DoublyLinkedList<T> extends SinglyLinkedList<T> {
    static class Entry<E> extends SinglyLinkedList.Entry<E> {
        Entry<E> prev;

        Entry(E x, Entry<E> next, Entry<E> prev) {
            super(x, next);
            this.prev = prev;
        }
    }
    public DoublyLinkedList() {
        head = new Entry<T>(null, null, null);
        tail = head;
        size = 0;
    }
   public DoublyLinkedListIterator dlliterator () {
        return new DLLIterator();
    }

    protected class DLLIterator extends SLLIterator implements DoublyLinkedListIterator<T> {

        DLLIterator(){
            super();
        }

        public boolean hasPrevious() {

            return((Entry<T>)cursor).prev != null && ((Entry<T>)cursor).prev.prev != null;
            /* Handling case: If Previous() is called on first element of the list, code terminates with exit code 0,
            that is the cursor does not go to the dummy header.*/
        }

        public T previous() {

            ready = true;
            cursor = ((Entry<T>) cursor).prev;
            return ((Entry<T>) cursor).element;
        }

        public void add(T x){
            Entry<T> newnode = new Entry<T>(x,null,null);

            newnode.next = ((Entry<T>)cursor).next;
            newnode.prev = (Entry<T>)cursor;

            if ((Entry<T>)cursor.next != null)
            {
                ((Entry<T>)cursor.next).prev = newnode;
            }
            cursor.next = newnode;

            if (cursor == tail){
                tail = tail.next;
            }
            cursor = cursor.next;// Cursor is moved to the new element that is added.
            ready = false;
            size++;
        }

        public void remove() {
            if(!ready) {
                throw new NoSuchElementException();
            }
            ((Entry<T>)cursor).prev.next = cursor.next;

            if(((Entry<T>)cursor.next) != null)
                ((Entry<T>)cursor.next).prev = ((Entry<T>)cursor).prev;

            // Handle case when tail of a list is deleted
            if(cursor == tail) {
                tail = ((Entry<T>)tail).prev;
            }
            cursor = ((Entry<T>)cursor).prev;// Cursor is moved to the previous element after remove.

            ready = false;  // Calling remove again without calling next will result in exception thrown
            size--;
        }
        } //End of DLLIterator

    public void add (T x){
            add(new Entry<>(x, null, null));
        }

    public void add(Entry<T> ent) {
        ent.prev = (Entry<T>)tail;
        super.add(ent);
    }
    public static void main(String[] args) throws NoSuchElementException {
        int n = 10;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        DoublyLinkedList<Integer> lst = new DoublyLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(Integer.valueOf(i));
        }
        lst.printList();

        DoublyLinkedListIterator<Integer> it = lst.dlliterator();
        Scanner in = new Scanner(System.in);
        whileloop:
        while(in.hasNext()) {
            int com = in.nextInt();
            switch(com) {
                case 1:  // Move to next element and print it
                    if (it.hasNext()) {
                        System.out.println(it.next());
                    } else {
                        break whileloop;
                    }
                    break;
                case 2:  // Remove element
                    it.remove();
                    lst.printList();
                    break;
                case 3: //check and move to Previous element
                    if(it.hasPrevious())
                        System.out.println(it.previous());
                    else
                        break whileloop;
                    break;
               case 4:  //add element
                    int x = in.nextInt();
                    it.add(x);
                    lst.printList();
                    break;
                default:  // Exit loop
                    break whileloop;
            }
        }
        lst.printList();

    }


}
