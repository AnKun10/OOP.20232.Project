package model;

public class BinaryTree extends Tree {
    private BinaryNode insertRecursive(BinaryNode cur, int value) {
        if (cur == null) {
            return new BinaryNode(value);
        }

        if (value < cur.getValue()) {
            cur.setLeft(insertRecursive(cur.getLeft(), value));
        } else if (value > cur.getValue()) {
            cur.setRight(insertRecursive(cur.getRight(), value));
        }
        // value already exists
        return cur;
    }
    @Override
    public void insert(int parentValue, int value) {
        // Binary tree insert logic
        this.root = insertRecursive()
    }

    @Override
    public void delete(int value) {
        // Binary tree delete logic
    }

    @Override
    public void update(int currentValue, int newValue) {
        // Binary tree update logic
    }

    @Override
    public void traverse(String algorithm) {
        // Binary tree traversal logic
    }

    @Override
    public void search(int value) {
        // Binary tree search logic
    }
}
