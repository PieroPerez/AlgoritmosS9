package Act2;

public class Main {
    public static void main(String[] args) {

        // Crear el grafo de tipo String
        GraphLink<String> graph = new GraphLink<>();

        // Insertar vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        // Insertar aristas
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");
        graph.insertEdge("D", "E");

        // Mostrar el grafo
        System.out.println("Grafo:");
        System.out.println(graph);

        // Buscar vértices y aristas
        System.out.println("¿Existe vértice 'C'? " + graph.searchVertex("C"));  // true
        System.out.println("¿Existe arista entre 'A' y 'C'? " + graph.searchEdge("A", "C"));  // true
        System.out.println("¿Existe arista entre 'A' y 'E'? " + graph.searchEdge("A", "E"));  // false

        // Eliminar arista
        graph.removeEdge("A", "C");
        System.out.println("Después de eliminar arista A-C:");
        System.out.println(graph);

        // Eliminar vértice
        graph.removeVertex("D");
        System.out.println("Después de eliminar vértice D:");
        System.out.println(graph);

        // Recorrido en profundidad (DFS) desde A
        System.out.print("DFS desde A: ");
        graph.dfs("A");
    }
}
