package Eje1;

import java.util.*; // Importa utilidades: List, Queue, Set, Map, etc.

public class GraphLink<E> { // Clase que representa un grafo no dirigido genérico
    protected ListLinked<Vertex<E>> listVertex; // Lista de vértices que forman el grafo

    public GraphLink() { // Constructor
        listVertex = new ListLinked<>(); // Inicializa la lista enlazada de vértices
    }

    public void insertVertex(E data) { // Inserta un vértice al grafo
        if (!searchVertex(data)) { // Solo lo agrega si no existe
            listVertex.add(new Vertex<E>(data)); // Agrega un nuevo objeto Vertex
        }
    }

    public void insertEdge(E verOri, E verDes) { // Inserta una arista no dirigida entre dos vértices
        Vertex<E> vOri = getVertex(verOri); // Busca vértice origen
        Vertex<E> vDes = getVertex(verDes); // Busca vértice destino
        if (vOri != null && vDes != null && !searchEdge(verOri, verDes)) { // Si existen y no hay arista
            vOri.listAdj.add(new Edge<E>(vDes)); // Agrega arista desde origen
            vDes.listAdj.add(new Edge<E>(vOri)); // Agrega arista inversa (no dirigido)
        }
    }

    public boolean searchVertex(E data) { // Verifica si existe un vértice con ese dato
        return getVertex(data) != null; // Retorna true si lo encuentra
    }

    public boolean searchEdge(E verOri, E verDes) { // Verifica si hay una arista entre dos vértices
        Vertex<E> vOri = getVertex(verOri); // Busca vértice origen
        Vertex<E> vDes = getVertex(verDes); // Busca vértice destino
        if (vOri != null && vDes != null) { // Si ambos existen
            return vOri.listAdj.contains(new Edge<E>(vDes)); // Verifica si la arista está en la lista
        }
        return false; // Si no existen o no se encontró
    }

    public void removeVertex(E data) { // Elimina un vértice y todas sus conexiones
        Vertex<E> v = getVertex(data); // Busca el vértice
        if (v != null) {
            listVertex.remove(v); // Elimina el vértice de la lista principal
            for (Vertex<E> u : listVertex) { // Recorre todos los vértices restantes
                u.listAdj.remove(new Edge<E>(v)); // Elimina cualquier arista que apunte al vértice eliminado
            }
        }
    }

    public void removeEdge(E verOri, E verDes) { // Elimina una arista entre dos vértices
        Vertex<E> vOri = getVertex(verOri); // Busca vértice origen
        Vertex<E> vDes = getVertex(verDes); // Busca vértice destino
        if (vOri != null && vDes != null) {
            vOri.listAdj.remove(new Edge<E>(vDes)); // Elimina arista desde origen
            vDes.listAdj.remove(new Edge<E>(vOri)); // Elimina arista inversa
        }
    }

    public void dfs(E start) { // Recorrido en profundidad (Depth-First Search)
        Vertex<E> startVertex = getVertex(start); // Obtiene el vértice inicial
        if (startVertex == null) return; // Si no existe, termina
        ListLinked<Vertex<E>> visited = new ListLinked<>(); // Lista de vértices visitados
        dfsRecursive(startVertex, visited); // Inicia DFS recursivo
    }

    private void dfsRecursive(Vertex<E> current, ListLinked<Vertex<E>> visited) { // Función auxiliar recursiva
        System.out.print(current.getData() + " "); // Imprime el dato del vértice actual
        visited.add(current); // Marca como visitado
        for (Edge<E> edge : current.listAdj) { // Recorre sus vecinos
            Vertex<E> neighbor = edge.refDest; // Obtiene el vértice vecino
            if (!visited.contains(neighbor)) { // Si no ha sido visitado
                dfsRecursive(neighbor, visited); // Llama recursivamente
            }
        }
    }

    public void bfs(E start) { // Recorrido en anchura (Breadth-First Search)
        Vertex<E> startVertex = getVertex(start); // Obtiene vértice inicial
        if (startVertex == null) return; // Termina si no existe

        Queue<Vertex<E>> queue = new LinkedList<>(); // Cola para BFS
        Set<Vertex<E>> visited = new HashSet<>(); // Conjunto para guardar los vértices visitados

        queue.offer(startVertex); // Encola el vértice inicial
        visited.add(startVertex); // Marca como visitado

        while (!queue.isEmpty()) { // Mientras haya vértices por procesar
            Vertex<E> current = queue.poll(); // Toma el siguiente vértice
            System.out.print(current.getData() + " "); // Imprime el dato

            for (Edge<E> edge : current.listAdj) { // Recorre sus vecinos
                Vertex<E> neighbor = edge.refDest;
                if (!visited.contains(neighbor)) { // Si no fue visitado
                    visited.add(neighbor); // Marca como visitado
                    queue.offer(neighbor); // Encola para visitar después
                }
            }
        }
    }

    public ArrayList<E> bfsPath(E start, E end) { // Encuentra el camino más corto entre dos vértices
        Vertex<E> startVertex = getVertex(start); // Vértice de inicio
        Vertex<E> endVertex = getVertex(end); // Vértice de destino
        if (startVertex == null || endVertex == null) return null; // Si alguno no existe, no hay camino

        Queue<Vertex<E>> queue = new LinkedList<>(); // Cola para BFS
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>(); // Mapa de predecesores
        Set<Vertex<E>> visited = new HashSet<>(); // Conjunto de visitados

        queue.offer(startVertex); // Encola el inicio
        visited.add(startVertex); // Marca como visitado
        prev.put(startVertex, null); // El inicio no tiene predecesor

        while (!queue.isEmpty()) { // Mientras haya vértices
            Vertex<E> current = queue.poll(); // Toma el siguiente
            if (current.equals(endVertex)) break; // Si llegamos al destino, termina

            for (Edge<E> edge : current.listAdj) { // Recorre vecinos
                Vertex<E> neighbor = edge.refDest;
                if (!visited.contains(neighbor)) { // Si no fue visitado
                    visited.add(neighbor); // Marca
                    prev.put(neighbor, current); // Registra predecesor
                    queue.offer(neighbor); // Encola
                }
            }
        }

        ArrayList<E> path = new ArrayList<>(); // Lista para reconstruir el camino
        Vertex<E> current = endVertex; // Comienza desde el destino

        while (current != null) { // Retrocede por los predecesores
            path.add(0, current.getData()); // Inserta al inicio del camino
            current = prev.get(current); // Avanza al predecesor
        }

        if (!path.isEmpty() && !path.get(0).equals(start)) {
            return null; // Si el inicio no coincide, no hubo camino real
        }

        return path; // Devuelve el camino desde 'start' hasta 'end'
    }

    private Vertex<E> getVertex(E data) { // Busca un vértice por su dato
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) return v; // Retorna si encuentra
        }
        return null; // No lo encontró
    }

    public String toString() { // Representación en texto del grafo
        return this.listVertex.toString(); // Usa toString de la lista enlazada
    }
}
