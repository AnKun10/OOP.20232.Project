package model;

class BinaryNode {
    private int value;
    private BinaryNode left;
    private BinaryNode right;

    BinaryNode(int value) {
        this.value = value;
        right = null;
        left = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }
}
