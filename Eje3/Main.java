package Eje3;

public class Main {
    public static void main(String[] args) {
        GraphListEdge<String, String> graph = new GraphListEdge<>();

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");

        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");

        System.out.println("¿Existe vértice C? " + graph.searchVertex("C"));
        System.out.println("¿Existe arista A-B? " + graph.searchEdge("A", "B"));
        System.out.println("¿Existe arista C-D? " + graph.searchEdge("C", "D"));

        System.out.print("Recorrido BFS desde A: ");
        graph.bfs("A");
    }
}
