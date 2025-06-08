package Eje7;

public class Main {
    public static void main(String[] args) {
        GraphLinkDir<String> g = new GraphLinkDir<>();

        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");

        g.insertEdge("A", "B");
        g.insertEdge("B", "C");
        g.insertEdge("C", "D");
        g.insertEdge("D", "A");

        System.out.println("Grafo dirigido:");
        g.printGraph();

        System.out.println("\nGrado de entrada de B: " + g.getInDegree("B"));
        System.out.println("Grado de salida de B: " + g.getOutDegree("B"));

        System.out.println("\n¿Es ciclo dirigido?: " + g.isDirectedCycle());
        System.out.println("¿Es grafo dirigido completo?: " + g.isDirectedComplete());
    }
}
