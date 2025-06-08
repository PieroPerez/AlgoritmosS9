package Eje6;

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

    public int getDegree(E data) {
        Vertex<E> v = getVertex(data);
        if (v != null) return v.listAdj.size();
        return -1;
    }

    public boolean isPath() {
        int countDegree1 = 0;
        for (Vertex<E> v : listVertex) {
            int deg = v.listAdj.size();
            if (deg == 1) countDegree1++;
            else if (deg != 2) return false;
        }
        return countDegree1 == 2;
    }

    public boolean isCycle() {
        for (Vertex<E> v : listVertex) {
            if (v.listAdj.size() != 2) return false;
        }
        return true;
    }

    public boolean isWheel() {
        int centerCount = 0;
        int n = listVertex.size();
        for (Vertex<E> v : listVertex) {
            int deg = v.listAdj.size();
            if (deg == n - 1) centerCount++;
            else if (deg != 3) return false;
        }
        return centerCount == 1;
    }

    public boolean isComplete() {
        int n = listVertex.size();
        for (Vertex<E> v : listVertex) {
            if (v.listAdj.size() != n - 1) return false;
        }
        return true;
    }

    public void printFormal() {
        System.out.print("V = {");
        for (Vertex<E> v : listVertex) {
            System.out.print(v.getData() + ", ");
        }
        System.out.println("}");
        System.out.print("E = {");
        Set<String> printed = new HashSet<>();
        for (Vertex<E> v : listVertex) {
            for (Edge<E> e : v.listAdj) {
                String a = v.getData() + "-" + e.refDest.getData();
                String b = e.refDest.getData() + "-" + v.getData();
                if (!printed.contains(b)) {
                    System.out.print(a + ", ");
                    printed.add(a);
                }
            }
        }
        System.out.println("}");
    }

    public void printAdjacencyList() {
        for (Vertex<E> v : listVertex) {
            System.out.print(v.getData() + " -> ");
            for (Edge<E> e : v.listAdj) {
                System.out.print(e.refDest.getData() + " ");
            }
            System.out.println();
        }
    }

    public void printAdjacencyMatrix() {
        int n = listVertex.size();
        Object[] nodes = new Object[n];
        int[][] matrix = new int[n][n];

        int i = 0;
        for (Vertex<E> v : listVertex) {
            nodes[i++] = v.getData();
        }

        for (i = 0; i < n; i++) {
            Vertex<E> v = getVertex((E) nodes[i]);
            for (Edge<E> e : v.listAdj) {
                int j = indexOf(nodes, e.refDest.getData());
                matrix[i][j] = 1;
            }
        }

        System.out.print("   ");
        for (int k = 0; k < n; k++) {
            System.out.print(nodes[k] + " ");
        }
        System.out.println();

        for (i = 0; i < n; i++) {
            System.out.print(nodes[i] + ": ");
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private int indexOf(Object[] array, Object value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) return i;
        }
        return -1;
    }

    private Vertex<E> getVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) return v;
        }
        return null;
    }

    public String toString() {
        return listVertex.toString();
    }
}
