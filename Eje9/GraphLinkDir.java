package Eje9;

import java.util.*;

public class GraphLinkDir<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLinkDir() {
        listVertex = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            listVertex.add(new Vertex<>(data));
        }
    }

    public void insertEdge(E from, E to) {
        Vertex<E> vFrom = getVertex(from);
        Vertex<E> vTo = getVertex(to);
        if (vFrom != null && vTo != null && !searchEdge(from, to)) {
            vFrom.listAdj.add(new Edge<>(vTo));
        }
    }

    public boolean searchVertex(E data) {
        return getVertex(data) != null;
    }

    public boolean searchEdge(E from, E to) {
        Vertex<E> vFrom = getVertex(from);
        Vertex<E> vTo = getVertex(to);
        if (vFrom != null && vTo != null) {
            return vFrom.listAdj.contains(new Edge<>(vTo));
        }
        return false;
    }

    public int getInDegree(E data) {
        Vertex<E> target = getVertex(data);
        if (target == null) return -1;
        int count = 0;
        for (Vertex<E> v : listVertex) {
            if (!v.equals(target)) {
                for (Edge<E> e : v.listAdj) {
                    if (e.refDest.equals(target)) count++;
                }
            }
        }
        return count;
    }

    public int getOutDegree(E data) {
        Vertex<E> v = getVertex(data);
        return (v != null) ? v.listAdj.size() : -1;
    }

    public boolean isDirectedCycle() {
        Set<Vertex<E>> visited = new HashSet<>();
        Set<Vertex<E>> recStack = new HashSet<>();
        for (Vertex<E> v : listVertex) {
            if (dfsCycle(v, visited, recStack)) return true;
        }
        return false;
    }

    private boolean dfsCycle(Vertex<E> v, Set<Vertex<E>> visited, Set<Vertex<E>> recStack) {
        if (recStack.contains(v)) return true;
        if (visited.contains(v)) return false;

        visited.add(v);
        recStack.add(v);
        for (Edge<E> e : v.listAdj) {
            if (dfsCycle(e.refDest, visited, recStack)) return true;
        }
        recStack.remove(v);
        return false;
    }

    public boolean isDirectedComplete() {
        int n = listVertex.size();
        for (Vertex<E> v : listVertex) {
            if (v.listAdj.size() != n - 1) return false;
        }
        return true;
    }

    public Vertex<E> getVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) return v;
        }
        return null;
    }

    public void printGraph() {
        for (Vertex<E> v : listVertex) {
            System.out.print(v.getData() + " -> ");
            for (Edge<E> e : v.listAdj) {
                System.out.print(e.refDest.getData() + " ");
            }
            System.out.println();
        }
    }
}
