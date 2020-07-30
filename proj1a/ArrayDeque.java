/** Array based list.
 *  @author Yurui Du
 */

public class ArrayDeque<Flipflop> {
    private int size;
    private Flipflop[] items;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty list.
     * The indices for nextFirst & nextLast are arbitrarily assigned by me. */
    public ArrayDeque() {
        size = 0;
        items = (Flipflop[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    /** Returns the index "before" a given index. */
    // Example:
    // index 0 1 2 3 4 5 6 7
    // minus 7 0 1 2 3 4 5 6
    // plus  1 2 3 4 5 6 7 0
    public int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    /** Returns the index "after" a given index. */
    public int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    /** Returns the index "after a given index, but for Flipflop arrays with other lengths. */
    public int plusOne(int index, int length) {
        if (index == length - 1) {
            return 0;
        }
        return index + 1;
    }
    /** Inserts X at the front of the list. */
    public void addFirst(Flipflop x) {
        resize();
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Flipflop x) {
        resize();
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /** Returns the item at the front of the list. */
    public Flipflop getFirst() {
        return items[plusOne(nextFirst)];
    }

    /** Returns the item from the back of the list. */
    public Flipflop getLast() {
        return items[minusOne(nextLast)];
    }

    /** Gets the item at the given index i in the list. */
    public Flipflop get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Determines whether a list is empty. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Deletes item at the front of the list and
     * returns deleted item. */
    public Flipflop removeFirst() {
        Flipflop deletedItem = getFirst();
        nextFirst = plusOne(nextFirst);
        size -= 1;
        resize();
        return deletedItem;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Flipflop removeLast() {
        Flipflop deletedItem = getLast();
        nextLast = minusOne(nextLast);
        size -= 1;
        resize();
        return deletedItem;
    }

    /** Creates a deep copy of other. */
    public ArrayDeque(ArrayDeque other) {
        size = 0;
        items = (Flipflop[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        for (int i = plusOne(other.nextFirst); i != other.nextLast; i = plusOne(i)) {
            addLast((Flipflop)other.get(i)); // needs (Flipflop) to cast
        }
    }

    /** Resizes the list. */
    public void resize() {
        /** if size reaches items.length, expands it twice as large. */
        if(size == items.length) {
            resizeHelper(items.length * 2);
        }
        /** if usage factor drops under 25% when item.length is larger than 8, reduces list's by half. */
        if(size < items.length / 4 && items.length > 8) {
            resizeHelper(items.length / 2);
        }
    }

    /** Expands / reduces the list to the assigned capacity. */
    public void resizeHelper(int capacity) {
        int begin = plusOne(nextFirst);
        int end = nextLast;
        Flipflop[] temp = (Flipflop[]) new Object[capacity];
        nextFirst = 4;
        nextLast = 5;
        do {
            temp[nextLast] = items[begin];
            nextLast = plusOne(nextLast, temp.length);
            begin = plusOne(begin);
        }
        while (begin != end);
        items = (Flipflop[]) new Object[capacity];
        items = temp;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

    /** Simple testing. */
    public static void main (String args[]) {
        ArrayDeque test = new ArrayDeque();
        for(int i = 0; i < 8; i += 1) {
            test.addFirst(i);
        }
        test.addLast(8);
        test.addFirst(9);
        for(int i = 0; i < 7; i += 1) {
            test.removeLast();
        }
        test.printDeque();
        //ArrayDeque copy = new ArrayDeque(test);
        //copy.printDeque();
    }
}