import java.util.*;
import java.security.Key;

/** A data structure that uses a binary search tree to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Key operations are get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. */

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V> {
    private Node root;             // root of BST
    private Set<K> KeySet = new HashSet<>();

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /* If root is null, then return 0.
     * Else return the size of the BST.
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    @Override
    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {
            remove(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        keySet(root);
        return KeySet;
    }
    private Set<K> keySet(Node x) {
        if (x == null) {
            return null;
        }
        KeySet.add(x.key);
        keySet(x.left);
        keySet(x.right);
        return KeySet;
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    @Override
    public V remove(K key) {
        if (key == null) return null;
        V returnValue = get(key);
        root = remove(root, key);
        return returnValue;
    }

    public Node remove(Node x, K key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = remove(x.left,  key);
        else if (cmp > 0) x.right = remove(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public K min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    /**
     * Returns true if this binary search tree is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     * If value does not match, return the current value in the BST.
     */
    @Override
    public V remove(K key, V value) {
        if (key == null) return null;
        if (get(key) != value) {
            return get(key);
        }
        root = remove(root, key);
        return get(key);
    }

    /* Your BSTMap should also add an additional method printInOrder()
     * (not given in the Map61B interface) that prints out your BSTMap
     * in order of increasing Key. You will find this helpful for testing your implementation!
     */
    private void inOrder(Node x) {
        if (x == null) {
            return;
        }
        inOrder(x.left);
        System.out.println(x.key);
        inOrder(x.right);
    }

    public void printInOrder() {
        inOrder(root);
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {

        int i = 0;
        @Override
        public boolean hasNext() {
            return i < KeySet.size();
        }

        @Override
        public K next() {
            K[] keyArray = (K[]) KeySet.toArray();
            K returnKey = keyArray[i];
            i += 1;
            return returnKey;
        }
    }
}
