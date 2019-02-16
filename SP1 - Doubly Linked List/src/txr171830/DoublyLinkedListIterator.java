package txr171830;

public interface DoublyLinkedListIterator<T>
{
    boolean hasNext();
    boolean hasPrevious();
    T next();
    T previous();
    void add(T x);
    void remove();
}
