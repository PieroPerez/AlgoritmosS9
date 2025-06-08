package Eje8;

import java.util.*;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            listVertex.add(new Vertex<>(data));
        }
    }

    public void insertEdge(E verOri, E verDes) {
        Vertex<E> vOri = getVertex(verOri);
        Vertex<E> vDes = getVertex(verDes);
        if (vOri != null && vDes != null && !searchEdge(verOri, verDes)) {
            vOri.listAdj.add(new Edge<>(vDes));
            vDes.listAdj.add(new Edge<>(vOri));
        }
    }

    public boolean searchVertex(E data) {
        return getVertex(data) != null;
    }

    public boolean searchEdge(E verOri, E verDes) {
        Vertex<E> vOri = getVertex(verOri);
        Vertex<E> vDes = getVertex(verDes);
        if (vOri != null && vDes != null) {
            return vOri.listAdj.contains(new Edge<>(vDes));
        }
        return false;
    }

    private Vertex<E> getVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) return v;
        }
        return null;
    }

    public void showFormalRepresentation() {
        System.out.println("Representación formal:");
        for (Vertex<E> v : listVertex) {
            for (Edge<E> e : v.listAdj) {
                if (v.getData().toString().compareTo(e.refDest.getData().toString()) < 0) {
                    System.out.println("(" + v.getData() + ", " + e.refDest.getData() + ")");
                }
            }
        }
    }

    public void showAdjacencyList() {
        System.out.println("Lista de adyacencia:");
        for (Vertex<E> v : listVertex) {
            System.out.print(v.getData() + " -> ");
            for (Edge<E> e : v.listAdj) {
                System.out.print(e.refDest.getData() + " ");
            }
            System.out.println();
        }
    }

    public void showAdjacencyMatrix() {
        System.out.println("Matriz de adyacencia:");
        int n = listVertex.size();
        Object[] names = new Object[n];
        int[][] matrix = new int[n][n];

        int i = 0;
        for (Vertex<E> v : listVertex) {
            names[i++] = v.getData();
        }

        for (int x = 0; x < n; x++) {
            for (Edge<E> e : getVertex((E) names[x]).listAdj) {
                int y = indexOfVertex((E) e.refDest.getData());
                matrix[x][y] = 1;
            }
        }

        System.out.print("   ");
        for (Object name : names) {
            System.out.print(name + " ");
        }
        System.out.println();

        for (int x = 0; x < n; x++) {
            System.out.print(names[x] + ": ");
            for (int y = 0; y < n; y++) {
                System.out.print(matrix[x][y] + " ");
            }
            System.out.println();
        }
    }

    private int indexOfVertex(E data) {
        int index = 0;
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) return index;
            index++;
        }
        return -1;
    }

    public String toString() {
        return listVertex.toString();
    }
}
