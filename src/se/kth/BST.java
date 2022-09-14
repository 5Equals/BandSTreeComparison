/**
 * Binary search tree implementation.
 * The Implementation is from "Algorithms, 4th Edition" site by Robert Sedgewick and Kevin Wayne.
 * The link for the material: "https://algs4.cs.princeton.edu/30searching/".
 * Modified by Gleano Malke.
 */

package se.kth;

import java.util.NoSuchElementException;

class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value value;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }
    
    public BST() {}

    /**
     * Check if the tree is empty or not.
     * @return true or false depending on the condition.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Gets the number of nodes present in the whole tree.
     * @return a number representing the size of the tree.
     */
    public int size() {
        return size(this.root);
    }

    private int size(Node node) {
        if(node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    /**
     * Check if the tree has key that is similar to the given.
     * @param key key that wanted to check if exists.
     * @return ture or false depending on the condition.
     * @throws IllegalArgumentException if the key is invalid.
     */
    public boolean contains(Key key) {
        if(key == null) {
            throw new IllegalArgumentException("The key is invalid.");
        }
        return get(key) != null;
    }

    /**
     * Get the value that is associated to the given key.
     * @param key the key that wanted to retrieve the value of.
     * @return the value associated with the given key.
     * @throws IllegalArgumentException if the key is invalid.
     */
    public Value get(Key key) {
        return get(this.root, key);
    }

    private Value get(Node node, Key key) {
        if(key == null) {
            throw new IllegalArgumentException("The key is invalid.");
        }
        if(node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return get(node.left, key);
        } else if (compare > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    /**
     * Enter the key and value to the tree.
     * @param key the key that wanted to but into the tree.
     * @param value the value associated to the key.
     * @throws IllegalArgumentException if the key is invalid.
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("The key is invalid.");
        }
        if (value == null) {
            delete(key);
            return;
        }
        this.root = put(this.root, key, value);
        assert check();
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = put(node.left, key, value);
        } else if (compare > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }


    /**
     * Remove the smallest key with its value.
     * @throws NoSuchElementException if the table is empty.
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is already empty.");
        }
        this.root = deleteMin(this.root);
        assert check();
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Remove the largetest key with its value.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is already empty.");
        }
        this.root = deleteMax(this.root);
        assert check();
    }

    private Node deleteMax(Node node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = deleteMax(node.right);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Remove the wanted key is it exists in the tree.
     * @param key the key wanted to be deleted.
     * @throws IllegalArgumentException if the key is invalid.
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is invalid.");
        }
        this.root = delete(this.root, key);
        assert check();
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = delete(node.left, key);
        } else if (compare > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }


    /**
     * Get the smallest key in the tree.
     * @return the smallest key in the tree
     * @throws NoSuchElementException if tree is empty.
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is empty.");
        }
        return min(this.root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        else return min(node.left);
    }

    /**
     * Get the largest key in the tree.
     * @return the largest key in tree.
     * @throws NoSuchElementException if the tree is empty.
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is empty.");
        }
        return max(this.root).key;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        else return max(node.right);
    }
    
    /**
     * Return the key in the symbol table of a given {@code rank}.
     * This key has the property that there are {@code rank} keys in
     * the symbol table that are smaller. In other words, this key is the
     * ({@code rank}+1)st smallest key in the symbol table.
     *
     * @param rank the rank which specifies the key. taking into consideration the order is from
     *             the smallest.
     * @return the key in the rank specified.
     * @throws IllegalArgumentException the rank is invalid.
     */
    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("The rank is invalid. " + rank);
        }
        return select(this.root, rank);
    }

    private Key select(Node node, int rank) {
        if (node == null) {
            return null;
        }
        int leftSize = size(node.left);
        if (leftSize > rank) {
            return select(node.left, rank);
        } else if (leftSize < rank) {
            return select(node.right, rank - leftSize - 1);
        } else {
            return node.key;
        }
    }

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     *
     * @param key the key wanted to know the rank of it.
     * @return the size of the tree or subtree.
     * @throws IllegalArgumentException if the key is invalid."
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("The key is invalid.");
        }
        return rank(key, this.root);
    }

    private int rank(Key key, Node node) {
        if (node == null) {
            return 0;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return rank(key, node.left);
        } else if (compare > 0) {
            return 1 + size(node.left) + rank(key, node.right);
        } else {
            return size(node.left);
        }
    }

    /**
     * Iterable that returns all the keys present in the tree.
     * @return queue with all the keys.
     */
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new Queue<Key>();
        }
        return keys(min(), max());
    }

    /**
     * Return all the keys in a given range.
     * @param lo start of the range.
     * @param hi the end of the range.
     * @return return a queue with all the keys in the specified range.
     * @throws IllegalArgumentException
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("The lo key is invalid.");
        }
        if (hi == null) {
            throw new IllegalArgumentException("The hi key is invalid.");
        }
        Queue<Key> queue = new Queue<Key>();
        keys(this.root, queue, lo, hi);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key lo, Key hi) {
        if (node == null) {
            return;
        }
        int compareLo = lo.compareTo(node.key);
        int compareHi = hi.compareTo(node.key);
        if (compareLo < 0) {
            keys(node.left, queue, lo, hi);
        }
        if (compareLo <= 0 && compareHi >= 0) {
            queue.enqueue(node.key);
        }
        if (compareHi > 0) {
            keys(node.right, queue, lo, hi);
        }
    }

    /**
     * Get the size of tree or subtree in a specific range.
     * @param lo minimum start point.
     * @param hi maximum end point.
     * @return the number of key the tree or subtree holds.
     * @throws IllegalArgumentException if any of the keys are invalid.
     */
    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("The lo key is invalid.");
        }
        if (hi == null) {
            throw new IllegalArgumentException("The hi key is invalid.");
        }
        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /*************************************************************************
     *  Check integrity of BST data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST()) System.out.println("Not in symmetric order");
        if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
        if (!isRankConsistent()) System.out.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(this.root, null, null);
    }

    // is the tree this.rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node node, Key min, Key max) {
        if (node == null) return true;
        if (min != null && node.key.compareTo(min) <= 0) return false;
        if (max != null && node.key.compareTo(max) >= 0) return false;
        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
        return isSizeConsistent(this.root);
    }

    private boolean isSizeConsistent(Node node) {
        if (node == null) return true;
        if (node.size != size(node.left) + size(node.right) + 1) return false;
        return isSizeConsistent(node.left) && isSizeConsistent(node.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }
}