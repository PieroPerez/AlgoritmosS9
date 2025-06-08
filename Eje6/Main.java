package Eje6;

public class Main {
	public static void main(String[] args) {
	    GraphLink<String> grafo = new GraphLink<>();

	    grafo.insertVertex("A");
	    grafo.insertVertex("B");
	    grafo.insertVertex("C");
	    grafo.insertVertex("D");

	    grafo.insertEdge("A", "B");
	    grafo.insertEdge("A", "C");
	    grafo.insertEdge("B", "D");

	    System.out.println("=== Representación Formal ===");
	    grafo.printFormal();

	    System.out.println("\n=== Lista de Adyacencia ===");
	    grafo.printAdjacencyList();

	    System.out.println("\n=== Matriz de Adyacencia ===");
	    grafo.printAdjacencyMatrix();
	}
}