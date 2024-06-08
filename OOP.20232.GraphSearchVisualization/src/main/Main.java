package main;

import model.Graph;
import gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6, 20);
        System.out.println(graph.getAdjMatrix());
        System.out.println(graph.getEdges());
        System.out.println(graph.getNodes());
    }
}
//adjmatrix: dict
//"A" = [F, T, F]
//"B" = [T, F, T]
//"C" = [T, T, F]
//
//nodes = [A, B, C]
//
//edges = [(A, B), (B,C)]