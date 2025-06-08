package Eje9;

import java.util.*;

public class GraphLink<E> {
    protected Map<E, List<E>> adjacencyList;

    public GraphLink() {
        adjacencyList = new HashMap<>();
    }

    public void insertVertex(E vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void insertEdge(E source, E destination) {
        insertVertex(source);
        insertVertex(destination);
        adjacencyList.get(source).add(destination); // Grafo dirigido
    }

    public boolean isConexo() {
        for (E v : adjacencyList.keySet()) {
            Set<E> visited = new HashSet<>();
            dfsVisit(v, visited);
            if (visited.size() != adjacencyList.size()) return false;
        }
        return true;
    }

    private void dfsVisit(E v, Set<E> visited) {
        visited.add(v);
        for (E neighbor : adjacencyList.get(v)) {
            if (!visited.contains(neighbor)) {
                dfsVisit(neighbor, visited);
            }
        }
    }

    public GraphLink<E> getComplement() {
        GraphLink<E> complement = new GraphLink<>();
        for (E v : adjacencyList.keySet()) {
            complement.insertVertex(v);
        }
        for (E v : adjacencyList.keySet()) {
            for (E u : adjacencyList.keySet()) {
                if (!v.equals(u) && !adjacencyList.get(v).contains(u)) {
                    complement.insertEdge(v, u);
                }
            }
        }
        return complement;
    }

    public boolean isIsomorfo(GraphLink<E> other) {
        if (adjacencyList.size() != other.adjacencyList.size()) return false;
        if (getEdgeCount() != other.getEdgeCount()) return false;
        return true; // Implementación básica, no exhaustiva
    }

    public boolean isPlano() {
        int n = adjacencyList.size();
        int m = getEdgeCount();
        return m <= 3 * n - 6; // Teorema de planaridad para grafos dirigidos simples
    }

    public boolean isAutoComplementario() {
        GraphLink<E> complement = getComplement();
        return this.isIsomorfo(complement);
    }

    public int getEdgeCount() {
        int count = 0;
        for (E v : adjacencyList.keySet()) {
            count += adjacencyList.get(v).size();
        }
        return count;
    }

    public void printGraph() {
        for (E v : adjacencyList.keySet()) {
            System.out.println(v + " -> " + adjacencyList.get(v));
        }
    }
}
