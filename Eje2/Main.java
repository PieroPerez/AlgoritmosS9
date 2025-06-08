package Eje2;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Crear el grafo ponderado
        GraphLink<String> graph = new GraphLink<>();

        // Insertar vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        // Insertar aristas con pesos
        graph.insertEdgeWeight("A", "B", 4);
        graph.insertEdgeWeight("A", "C", 2);
        graph.insertEdgeWeight("B", "C", 1);
        graph.insertEdgeWeight("B", "D", 5);
        graph.insertEdgeWeight("C", "D", 8);
        graph.insertEdgeWeight("C", "E", 10);
        graph.insertEdgeWeight("D", "E", 2);

        // Mostrar el grafo
        System.out.println("Grafo:");
        System.out.println(graph);

        // Verificar si es conexo
        System.out.println("¿El grafo es conexo?: " + graph.isConexo());

        // Encontrar el camino más corto (por peso) de A a E usando shortPath
        ArrayList<String> shortestPath = graph.shortPath("A", "E");
        System.out.println("Camino más corto de A a E (shortPath): " + shortestPath);

        // Encontrar el camino más corto de A a E usando Dijkstra que retorna un Stack
        Stack<String> pathStack = graph.dijkstra("A", "E");
        System.out.print("Camino más corto de A a E (Dijkstra - Stack): ");
        while (!pathStack.isEmpty()) {
            System.out.print(pathStack.pop() + (pathStack.isEmpty() ? "\n" : " -> "));
        }

        // Probar el recorrido en anchura (BFS)
        System.out.print("Recorrido BFS desde A: ");
        graph.bfs("A");
        System.out.println();

        // Probar camino más corto en número de pasos (no por peso)
        ArrayList<String> bfsPath = graph.bfsPath("A", "E");
        System.out.println("Camino más corto de A a E (en número de pasos): " + bfsPath);
    }
}
