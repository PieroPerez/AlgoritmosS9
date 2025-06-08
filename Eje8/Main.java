package Eje8;

public class Main {
    public static void main(String[] args) {
        // Crear instancia del grafo
        GraphLink<String> g = new GraphLink<>();

        // Insertar vértices
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");

        // Insertar aristas (no dirigido)
        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");
        g.insertEdge("C", "D");

        // Mostrar representaciones
        System.out.println("=== Representación Formal ===");
        g.showFormalRepresentation();

        System.out.println("\n=== Lista de Adyacencia ===");
        g.showAdjacencyList();

        System.out.println("\n=== Matriz de Adyacencia ===");
        g.showAdjacencyMatrix();
    }
}
