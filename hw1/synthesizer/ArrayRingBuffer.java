package synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last += 1;
        if (last == rb.length) {
            last = 0;
        }
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T removedItem = rb[first];
        rb[first] = null;
        fillCount -= 1;
        first += 1;
        if (first == rb.length) {
            first = 0;
        }

        return removedItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    private class ArrayBufferIterator implements Iterator<T> {
        private int wizPos;
        private int nextCount;

        public ArrayBufferIterator() {
            wizPos = first;
            nextCount = 0;
        }

        public boolean hasNext() {
            return nextCount < fillCount;
        }

        public T next() {
            T returnItem = rb[wizPos];
            wizPos += 1;
            nextCount += 1;
            if (wizPos == rb.length) {
                wizPos = 0;
            }
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayBufferIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        boolean equal = true;
        if (other.fillCount() != this.fillCount()) {
            return false;
        }
        for (int i = 0; i< this.fillCount(); i++) {
            T item1 = this.dequeue();
            T item2 = other.dequeue();
            if (item1 != item2) {
                equal = false;
            }
            this.enqueue(item1);
            other.enqueue(item2);
        }
        return equal;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
