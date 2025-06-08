package org.ejemplo.grafo;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class GrafoCiudades {
    public static void main(String[] args) {
        Graph<String, DefaultWeightedEdge> grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        grafo.addVertex("Arequipa");
        grafo.addVertex("Lima");
        grafo.addVertex("Cusco");
        grafo.addVertex("Puno");

        grafo.setEdgeWeight(grafo.addEdge("Arequipa", "Lima"), 1010);
        grafo.setEdgeWeight(grafo.addEdge("Arequipa", "Cusco"), 500);
        grafo.setEdgeWeight(grafo.addEdge("Cusco", "Puno"), 320);
        grafo.setEdgeWeight(grafo.addEdge("Lima", "Puno"), 1300);

        System.out.println("Ciudades: " + grafo.vertexSet());
        System.out.println("Rutas:");
        for (DefaultWeightedEdge e : grafo.edgeSet()) {
            System.out.println(grafo.getEdgeSource(e) + " → " + grafo.getEdgeTarget(e) +
                               " = " + grafo.getEdgeWeight(e) + " km");
        }

        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo);
        GraphPath<String, DefaultWeightedEdge> camino = dijkstra.getPath("Arequipa", "Puno");

        if (camino != null) {
            System.out.println("\nRuta más corta de Arequipa a Puno:");
            System.out.println("→ " + camino.getVertexList());
            System.out.println("Distancia total: " + camino.getWeight() + " km");
        } else {
            System.out.println("No hay ruta entre Arequipa y Puno");
        }
    }
}
