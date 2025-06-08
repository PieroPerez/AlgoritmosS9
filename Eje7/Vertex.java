package Eje7;

public class Vertex<E> { // Clase genérica para representar un vértice
    private E data; // Dato contenido en el vértice
    protected ListLinked<Edge<E>> listAdj; // Lista de adyacencias (aristas que salen de este vértice)

    public Vertex(E data) { // Constructor que recibe el dato del vértice
        this.data = data; // Asigna el dato
        listAdj = new ListLinked<Edge<E>>(); // Inicializa la lista de adyacencias vacía
    }

    public E getData() { // Devuelve el dato del vértice
        return data;
    }

    public boolean equals(Object o) { // Compara si dos vértices son iguales (por su dato)
        if (o instanceof Vertex<?>) { // Verifica que sea un objeto de tipo Vertex
            Vertex<E> v = (Vertex<E>) o; // Castea
            return this.data.equals(v.data); // Compara los datos
        }
        return false; // No son del mismo tipo
    }

    public String toString() { // Devuelve una representación textual del vértice y sus conexiones
        return this.data + " --> " + this.listAdj.toString() + "\n"; // Formato: A --> B, C
    }
}
