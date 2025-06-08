package Act2;

public class GraphLink<E> { // Clase para representar un grafo no dirigido
    protected ListLinked<Vertex<E>> listVertex; // Lista de todos los vértices

    public GraphLink() { // Constructor
        listVertex = new ListLinked<>(); // Inicializa lista vacía
    }

    public void insertVertex(E data) { // Inserta un nuevo vértice
        if (!searchVertex(data)) { // Solo si no existe ya
            listVertex.add(new Vertex<E>(data)); // Añade nuevo vértice
        }
    }

    public void insertEdge(E verOri, E verDes) { // Inserta arista entre dos vértices
        Vertex<E> vOri = getVertex(verOri); // Busca origen
        Vertex<E> vDes = getVertex(verDes); // Busca destino
        if (vOri != null && vDes != null && !searchEdge(verOri, verDes)) { // Si existen y no hay arista previa
            vOri.listAdj.add(new Edge<E>(vDes)); // Añade arista a origen
            vDes.listAdj.add(new Edge<E>(vOri)); // Añade arista inversa (grafo no dirigido)
        }
    }

    public boolean searchVertex(E data) { // Verifica si existe un vértice
        return getVertex(data) != null; // Si lo encuentra, retorna true
    }

    public boolean searchEdge(E verOri, E verDes) { // Verifica si existe una arista entre dos vértices
        Vertex<E> vOri = getVertex(verOri); // Origen
        Vertex<E> vDes = getVertex(verDes); // Destino
        if (vOri != null && vDes != null) { // Si ambos existen
            return vOri.listAdj.contains(new Edge<E>(vDes)); // Revisa si está en la lista
        }
        return false; // No existe
    }

    public void removeVertex(E data) { // Elimina un vértice
        Vertex<E> v = getVertex(data); // Busca vértice
        if (v != null) {
            listVertex.remove(v); // Elimina el vértice de la lista
            for (Vertex<E> u : listVertex) { // Recorre el resto de vértices
                u.listAdj.remove(new Edge<E>(v)); // Elimina referencias al vértice eliminado
            }
        }
    }

    public void removeEdge(E verOri, E verDes) { // Elimina una arista
        Vertex<E> vOri = getVertex(verOri); // Origen
        Vertex<E> vDes = getVertex(verDes); // Destino
        if (vOri != null && vDes != null) {
            vOri.listAdj.remove(new Edge<E>(vDes)); // Elimina de origen
            vDes.listAdj.remove(new Edge<E>(vOri)); // Elimina de destino (no dirigido)
        }
    }

    public void dfs(E start) { // Realiza recorrido DFS desde un nodo
        Vertex<E> startVertex = getVertex(start); // Busca vértice inicial
        if (startVertex == null) return; // Si no existe, termina
        ListLinked<Vertex<E>> visited = new ListLinked<>(); // Lista de visitados
        dfsRecursive(startVertex, visited); // Inicia DFS recursivo
    }

    private void dfsRecursive(Vertex<E> current, ListLinked<Vertex<E>> visited) { // DFS recursivo
        System.out.print(current.getData() + " "); // Muestra el vértice actual
        visited.add(current); // Marca como visitado
        for (Edge<E> edge : current.listAdj) { // Recorre sus vecinos
            Vertex<E> neighbor = edge.refDest; // Obtiene vecino
            if (!visited.contains(neighbor)) { // Si no fue visitado
                dfsRecursive(neighbor, visited); // Llama recursivamente
            }
        }
    }

    private Vertex<E> getVertex(E data) { // Busca un vértice por su dato
        for (Vertex<E> v : listVertex) { // Recorre todos los vértices
            if (v.getData().equals(data)) return v; // Retorna si encuentra
        }
        return null; // No encontrado
    }

    public String toString() { // Representación textual del grafo
        return this.listVertex.toString(); // Usa toString de la lista
    }
}
