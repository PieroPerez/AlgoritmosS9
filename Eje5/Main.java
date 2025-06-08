package Eje5;

public class Main {
    public static void main(String[] args) {
        // -------------------- EJEMPLO 1: Grafo tipo CAMINO P4 --------------------
        System.out.println("==== GRAFO CAMINO P4 ====");
        GraphLink<String> camino = new GraphLink<>();
        camino.insertVertex("A");
        camino.insertVertex("B");
        camino.insertVertex("C");
        camino.insertVertex("D");

        camino.insertEdge("A", "B");
        camino.insertEdge("B", "C");
        camino.insertEdge("C", "D");

        System.out.println(camino);
        System.out.println("Grado de A: " + camino.getDegree("A"));
        if (camino.isCamino()) System.out.println("Es un camino.");
        System.out.println();

        // -------------------- EJEMPLO 2: Grafo tipo CICLO C5 --------------------
        System.out.println("==== GRAFO CICLO C5 ====");
        GraphLink<String> ciclo = new GraphLink<>();
        ciclo.insertVertex("A");
        ciclo.insertVertex("B");
        ciclo.insertVertex("C");
        ciclo.insertVertex("D");
        ciclo.insertVertex("E");

        ciclo.insertEdge("A", "B");
        ciclo.insertEdge("B", "C");
        ciclo.insertEdge("C", "D");
        ciclo.insertEdge("D", "E");
        ciclo.insertEdge("E", "A");

        System.out.println(ciclo);
        System.out.println("Grado de A: " + ciclo.getDegree("A"));
        if (ciclo.isCiclo()) System.out.println("Es un ciclo.");
        System.out.println();

        // -------------------- EJEMPLO 3: Grafo tipo RUEDA W5 --------------------
        System.out.println("==== GRAFO RUEDA W5 ====");
        GraphLink<String> rueda = new GraphLink<>();
        rueda.insertVertex("A");
        rueda.insertVertex("B");
        rueda.insertVertex("C");
        rueda.insertVertex("D");
        rueda.insertVertex("E");

        rueda.insertEdge("A", "B");
        rueda.insertEdge("B", "C");
        rueda.insertEdge("C", "D");
        rueda.insertEdge("D", "A");
        rueda.insertEdge("A", "E");
        rueda.insertEdge("B", "E");
        rueda.insertEdge("C", "E");
        rueda.insertEdge("D", "E");

        System.out.println(rueda);
        System.out.println("Grado de E: " + rueda.getDegree("E"));
        if (rueda.isRueda()) System.out.println("Es una rueda.");
        System.out.println();

        // -------------------- EJEMPLO 4: Grafo COMPLETO K4 --------------------
        System.out.println("==== GRAFO COMPLETO K4 ====");
        GraphLink<String> completo = new GraphLink<>();
        completo.insertVertex("A");
        completo.insertVertex("B");
        completo.insertVertex("C");
        completo.insertVertex("D");

        completo.insertEdge("A", "B");
        completo.insertEdge("A", "C");
        completo.insertEdge("A", "D");
        completo.insertEdge("B", "C");
        completo.insertEdge("B", "D");
        completo.insertEdge("C", "D");

        System.out.println(completo);
        System.out.println("Grado de A: " + completo.getDegree("A"));
        if (completo.isCompleto()) System.out.println("Es un grafo completo.");
        System.out.println();

        // -------------------- EJEMPLO 5: Grado de un nodo específico --------------------
        System.out.println("==== SOLO GRADO DE UN NODO ====");
        GraphLink<String> grado = new GraphLink<>();
        grado.insertVertex("A");
        grado.insertVertex("B");
        grado.insertVertex("C");

        grado.insertEdge("A", "B");
        grado.insertEdge("A", "C");

        System.out.println(grado);
        System.out.println("Grado de A: " + grado.getDegree("A"));
        System.out.println();
    }
}
