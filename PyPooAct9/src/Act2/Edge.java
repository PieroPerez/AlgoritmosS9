package Act2;

public class Edge<E> { // Clase genérica para representar una arista
    Vertex<E> refDest; // Referencia al vértice destino
    private int weight; // Peso de la arista (por defecto -1 si no tiene)

    public Edge(Vertex<E> refDest) { // Constructor sin peso
        this(refDest, -1); // Llama al constructor con peso -1
    }

    public Edge(Vertex<E> refDest, int weight) { // Constructor con peso
        this.refDest = refDest; // Asigna vértice destino
        this.weight = weight; // Asigna peso
    }

    public boolean equals(Object o) { // Compara aristas por su vértice destino
        if (o instanceof Edge<?>) { // Verifica que sea arista
            Edge<E> e = (Edge<E>) o; // Castea
            return this.refDest.equals(e.refDest); // Compara vértices destino
        }
        return false; // No son iguales
    }

    public String toString() { // Representación textual de la arista
        if (this.weight > -1) // Si tiene peso
            return refDest.getData() + " [" + this.weight + "], "; // A [3],
        else
            return refDest.getData() + ", "; // A,
    }
}
