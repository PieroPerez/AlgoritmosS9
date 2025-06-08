package Eje9;

import java.util.*;

public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    // a) Insertar vértice si no existe
    public void insertVertex(V info) {
        if (!searchVertex(info)) {
            secVertex.add(new VertexObj<>(info, secVertex.size()));
        }
    }

    // b) Insertar arista si no existe
    public void insertEdge(V v1, V v2) {
        VertexObj<V, E> vert1 = getVertex(v1);
        VertexObj<V, E> vert2 = getVertex(v2);

        if (vert1 != null && vert2 != null && !searchEdge(v1, v2)) {
            secEdge.add(new EdgeObj<>(vert1, vert2, null, secEdge.size()));
        }
    }

    // c) Buscar vértice
    public boolean searchVertex(V info) {
        return getVertex(info) != null;
    }

    // d) Buscar arista entre dos vértices
    public boolean searchEdge(V v1, V v2) {
        for (EdgeObj<V, E> edge : secEdge) {
            if ((edge.endVertex1.info.equals(v1) && edge.endVertex2.info.equals(v2)) ||
                (edge.endVertex1.info.equals(v2) && edge.endVertex2.info.equals(v1))) {
                return true;
            }
        }
        return false;
    }

    // e) Recorrido en anchura (BFS)
    public void bfs(V start) {
        VertexObj<V, E> startVertex = getVertex(start);
        if (startVertex == null) return;

        Set<VertexObj<V, E>> visited = new HashSet<>();
        Queue<VertexObj<V, E>> queue = new LinkedList<>();

        queue.offer(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.poll();
            System.out.print(current.info + " ");
            for (EdgeObj<V, E> edge : secEdge) {
                VertexObj<V, E> neighbor = null;

                if (edge.endVertex1.equals(current) && !visited.contains(edge.endVertex2)) {
                    neighbor = edge.endVertex2;
                } else if (edge.endVertex2.equals(current) && !visited.contains(edge.endVertex1)) {
                    neighbor = edge.endVertex1;
                }

                if (neighbor != null) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    // Método auxiliar para obtener vértice por dato
    private VertexObj<V, E> getVertex(V info) {
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.info.equals(info)) return vertex;
        }
        return null;
    }
}
