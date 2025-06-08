package Eje9;

public class Main {
    public static void main(String[] args) {
        GraphLink<String> grafo1 = new GraphLink<>();
        grafo1.insertVertex("A");
        grafo1.insertVertex("B");
        grafo1.insertVertex("C");
        grafo1.insertEdge("A", "B");
        grafo1.insertEdge("B", "C");

        GraphLink<String> grafo2 = new GraphLink<>();
        grafo2.insertVertex("X");
        grafo2.insertVertex("Y");
        grafo2.insertVertex("Z");
        grafo2.insertEdge("X", "Y");
        grafo2.insertEdge("Y", "Z");

        System.out.println("¿Grafo1 es conexo?: " + grafo1.isConexo());
        System.out.println("¿Grafo1 y Grafo2 son isomorfos?: " + grafo1.isIsomorfo(grafo2));
        System.out.println("¿Grafo1 es plano?: " + grafo1.isPlano());
        System.out.println("¿Grafo1 es auto-complementario?: " + grafo1.isAutoComplementario());
    }
}
