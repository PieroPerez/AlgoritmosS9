package Eje2;

import java.util.*;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            listVertex.add(new Vertex<>(data));
        }
    }

    public void insertEdge(E verOri, E verDes) {
        Vertex<E> vOri = getVertex(verOri);
        Vertex<E> vDes = getVertex(verDes);
        if (vOri != null && vDes != null && !searchEdge(verOri, verDes)) {
            vOri.listAdj.add(new Edge<>(vDes));
            vDes.listAdj.add(new Edge<>(vOri));
        }
    }

    public void insertEdgeWeight(E verOri, E verDes, int weight) {
        Vertex<E> vOri = getVertex(verOri);
        Vertex<E> vDes = getVertex(verDes);
        if (vOri != null && vDes != null && !searchEdge(verOri, verDes)) {
            vOri.listAdj.add(new Edge<>(vDes, weight));
            vDes.listAdj.add(new Edge<>(vOri, weight));
        }
    }

    public boolean searchVertex(E data) {
        return getVertex(data) != null;
    }

    public boolean searchEdge(E verOri, E verDes) {
        Vertex<E> vOri = getVertex(verOri);
        Vertex<E> vDes = getVertex(verDes);
        if (vOri != null && vDes != null) {
            return vOri.listAdj.contains(new Edge<>(vDes));
        }
        return false;
    }

    public void removeVertex(E data) {
        Vertex<E> v = getVertex(data);
        if (v != null) {
            listVertex.remove(v);
            for (Vertex<E> u : listVertex) {
                u.listAdj.remove(new Edge<>(v));
            }
        }
    }

    public void removeEdge(E verOri, E verDes) {
        Vertex<E> vOri = getVertex(verOri);
        Vertex<E> vDes = getVertex(verDes);
        if (vOri != null && vDes != null) {
            vOri.listAdj.remove(new Edge<>(vDes));
            vDes.listAdj.remove(new Edge<>(vOri));
        }
    }

    public void dfs(E start) {
        Vertex<E> startVertex = getVertex(start);
        if (startVertex == null) return;
        ListLinked<Vertex<E>> visited = new ListLinked<>();
        dfsRecursive(startVertex, visited);
    }

    private void dfsRecursive(Vertex<E> current, ListLinked<Vertex<E>> visited) {
        System.out.print(current.getData() + " ");
        visited.add(current);
        for (Edge<E> edge : current.listAdj) {
            Vertex<E> neighbor = edge.refDest;
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public void bfs(E start) {
        Vertex<E> startVertex = getVertex(start);
        if (startVertex == null) return;

        Queue<Vertex<E>> queue = new LinkedList<>();
        Set<Vertex<E>> visited = new HashSet<>();

        queue.offer(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            System.out.print(current.getData() + " ");
            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    public ArrayList<E> bfsPath(E start, E end) {
        Vertex<E> startVertex = getVertex(start);
        Vertex<E> endVertex = getVertex(end);
        if (startVertex == null || endVertex == null) return null;

        Queue<Vertex<E>> queue = new LinkedList<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        Set<Vertex<E>> visited = new HashSet<>();

        queue.offer(startVertex);
        visited.add(startVertex);
        prev.put(startVertex, null);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            if (current.equals(endVertex)) break;

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        ArrayList<E> path = new ArrayList<>();
        Vertex<E> current = endVertex;

        while (current != null) {
            path.add(0, current.getData());
            current = prev.get(current);
        }

        if (!path.isEmpty() && !path.get(0).equals(start)) return null;

        return path;
    }

    public ArrayList<E> shortPath(E start, E end) {
        Map<Vertex<E>, Integer> distances = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        Set<Vertex<E>> visited = new HashSet<>();
        PriorityQueue<Vertex<E>> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        Vertex<E> startV = getVertex(start);
        Vertex<E> endV = getVertex(end);
        if (startV == null || endV == null) return null;

        for (Vertex<E> v : listVertex) {
            distances.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
        }

        distances.put(startV, 0);
        queue.offer(startV);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            if (visited.contains(current)) continue;
            visited.add(current);

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                int newDist = distances.get(current) + edge.getWeight();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        ArrayList<E> path = new ArrayList<>();
        Vertex<E> current = endV;
        while (current != null) {
            path.add(0, current.getData());
            current = prev.get(current);
        }

        if (path.isEmpty() || !path.get(0).equals(start)) return null;

        return path;
    }

    public Stack<E> dijkstra(E start, E end) {
        Map<Vertex<E>, Integer> distances = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        Set<Vertex<E>> visited = new HashSet<>();
        PriorityQueue<Vertex<E>> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        Vertex<E> startV = getVertex(start);
        Vertex<E> endV = getVertex(end);
        if (startV == null || endV == null) return null;

        for (Vertex<E> v : listVertex) {
            distances.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
        }

        distances.put(startV, 0);
        queue.offer(startV);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            if (visited.contains(current)) continue;
            visited.add(current);

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                int newDist = distances.get(current) + edge.getWeight();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        Stack<E> stack = new Stack<>();
        Vertex<E> current = endV;
        while (current != null) {
            stack.push(current.getData());
            current = prev.get(current);
        }

        if (stack.isEmpty() || !stack.contains(start)) return null;

        return stack;
    }

    public boolean isConexo() {
        if (listVertex.size() == 0) return true;

        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();

        Vertex<E> start = listVertex.iterator().next();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return visited.size() == listVertex.size();
    }

    private Vertex<E> getVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) return v;
        }
        return null;
    }

    public String toString() {
        return listVertex.toString();
    }
}
