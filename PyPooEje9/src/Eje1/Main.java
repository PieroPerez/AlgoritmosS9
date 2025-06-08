package Eje1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Crear grafo de tipo String
        GraphLink<String> graph = new GraphLink<>();

        // Insertar vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        // Insertar aristas (no dirigido)
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");
        graph.insertEdge("D", "E");

        // Mostrar grafo completo
        System.out.println("Grafo:");
        System.out.println(graph);

        // DFS desde A
        System.out.print("DFS desde A: ");
        graph.dfs("A");
        System.out.println();

        // BFS desde A
        System.out.print("BFS desde A: ");
        graph.bfs("A");
        System.out.println();

        // Buscar camino más corto de A a E
        System.out.println("Camino más corto de A a E:");
        ArrayList<String> path = graph.bfsPath("A", "E");
        if (path != null) {
            System.out.println(path); // Ejemplo: [A, B, D, E]
        } else {
            System.out.println("No hay camino de A a E.");
        }

        // Eliminar arista A-C
        graph.removeEdge("A", "C");
        System.out.println("Después de eliminar la arista A-C:");
        System.out.println(graph);

        // Eliminar vértice D
        graph.removeVertex("D");
        System.out.println("Después de eliminar el vértice D:");
        System.out.println(graph);

        // Nuevo BFS para ver cambios
        System.out.print("BFS desde A luego de cambios: ");
        graph.bfs("A");
    }
}
