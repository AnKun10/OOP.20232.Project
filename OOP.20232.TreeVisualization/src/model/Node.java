package model;

import java.util.ArrayList;

public class Node {
    protected int value;

    protected ArrayList<Node> children;

    Node (int value) {
        this.value = value;
        this.children = null;
    }


}
