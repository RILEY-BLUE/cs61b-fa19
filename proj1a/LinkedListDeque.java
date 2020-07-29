/** This is the "Double Sentinel" version of DLList.
 * @author YuruiDu
 * @param <Flipflop>
 */

public class LinkedListDeque<Flipflop> {
    public class StuffNode {
        public Flipflop item;
        public StuffNode prev;
        public StuffNode next;
        public StuffNode(Flipflop i, StuffNode m, StuffNode n) {
            item = i;
            prev = m;
            next = n;
        }
    }
    private int size;
    private StuffNode sentinel;
    private StuffNode last;

    /** Creates an empty list. */
    public LinkedListDeque() {
        sentinel = new StuffNode((Flipflop)null, null, null);
        last = new StuffNode((Flipflop)null, null, null);
        sentinel.next = last;
        last.prev = sentinel;
        size = 0;
    }

    /** Creates a deep copy of other. */
    public LinkedListDeque(LinkedListDeque<Flipflop> other) {
        sentinel = new StuffNode(null, null, null);
        last = new StuffNode(null, null, null);
        sentinel.next = last;
        last.prev = sentinel;
        size = 0;
        for(int i = 1; i <= other.size; i += 1) {
            addFirst(other.get(i));
        }
    }

    /** Determines whether a list is empty. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(Flipflop x) {
        StuffNode add = new StuffNode(x, sentinel, sentinel.next);
        sentinel.next.prev = add;
        sentinel.next = add;
        size += 1;
    }

    /** Retrieves the front item from the list. */
    public Flipflop getFirst() {
        return sentinel.next.item;
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public Flipflop removeFirst() {
        if(size == 0) {
            return null;
        }
        size -= 1;
        Flipflop remove = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return remove;
    }

    /** Adds an item to the end of the list. */
    public void addLast(Flipflop x) {
        /* Your Code Here! */
        StuffNode add = new StuffNode(x, last.prev, last);
        last.prev.next = add;
        last.prev = add;
        size += 1;
    }

    /** Removes and returns the item at the end of the deque.
     * If no such item exists, returns null. */
    public Flipflop removeLast() {
        if(size == 0) {
            return null;
        }
        size -= 1;
        Flipflop remove = last.prev.item;
        last.prev.prev.next = last;
        last.prev = last.prev.prev;
        return remove;
    }

    /** Returns the number of items in the list using recursion. */
    public int size() {
        /* Your Code Here! */
        return size;
    }

    /** Prints the items in the deque from first to last, separated by
     * a space. Once all the items have been printed, print out a new line. */
    public void printDeque() {
        StuffNode ptr = sentinel;
        while (ptr.next != null) {
            System.out.print(ptr.item);
            System.out.print(" ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    /** Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque! */
    public Flipflop get(int index) {
        if (index > size || index <= 0) {
            return null;
        }
        StuffNode ptr = sentinel;
        for (int i = 1; i <= index; i += 1) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    /** Same as get, but uses recursion. */
    public Flipflop getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    /** Helper method to achieve recursion. */
    public Flipflop getRecursiveHelper(int index, StuffNode ptr) {
        if (index > size || index <= 0) {
            return null;
        }
        if (index == 1) {
            return ptr.item;
        }
        return getRecursiveHelper(index - 1, ptr.next);
    }

}