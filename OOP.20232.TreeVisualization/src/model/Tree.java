package model;

public abstract class Tree {
    BinaryNode root;
    public void create() {
        this.root = null;
    }
    public void create(int rootValue) {
        this.root = new BinaryNode(rootValue);
    };
    protected abstract void insert(int parentValue, int value);
    protected abstract void delete(int value);
    protected abstract void update(int currentValue, int newValue);
    protected abstract void traverse(String algorithm);
    protected abstract void search(int value);
}
