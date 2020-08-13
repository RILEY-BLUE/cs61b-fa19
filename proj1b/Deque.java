/** This interface contains all the methods in both
 * ArrayDeque.java and LinkedListDeque.java. */

public interface Deque<Flipflop> {
    public void addFirst(Flipflop item);
    public void addLast(Flipflop item);
    default boolean isEmpty() {
        return (size() == 0);
    }
    public int size();
    public void printDeque();
    public Flipflop removeFirst();
    public Flipflop removeLast();
    public Flipflop get(int index);
}