/** LP2 - SkipLists
 * GROUP LP 17 - MEMBERS: OMKAR DIXIT      netID: ond170030
 *                        KARAN KANANI     netID: kyk170030
 *                        TEJAS RAVI RAO   netID: txr171830
 *                        SHAKTI SINGH     netID: sxs178130
 * */

package txr171830;

public interface SLIterator<T> {
    boolean hasNext();
    T next();
    void remove();
}