package Eje5;

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

    public int getDegree(E data) {
        Vertex<E> vertex = getVertex(data);
        if (vertex != null) {
            return vertex.listAdj.size();
        }
        return -1;
    }

    public boolean isCamino() {
        int countDeg1 = 0;
        int countDeg2 = 0;

        for (Vertex<E> v : listVertex) {
            int degree = v.listAdj.size();
            if (degree == 1) {
                countDeg1++;
            } else if (degree == 2) {
                countDeg2++;
            } else {
                return false;
            }
        }

        return countDeg1 == 2 && countDeg2 == listVertex.size() - 2;
    }

    public boolean isCiclo() {
        for (Vertex<E> v : listVertex) {
            if (v.listAdj.size() != 2) {
                return false;
            }
        }
        return true;
    }

    public boolean isRueda() {
        int n = listVertex.size();
        int centerCount = 0;
        int cycleCount = 0;

        for (Vertex<E> v : listVertex) {
            int deg = v.listAdj.size();
            if (deg == n - 1) {
                centerCount++;
            } else if (deg == 3) {
                cycleCount++;
            }
        }

        return centerCount == 1 && cycleCount == n - 1;
    }

    public boolean isCompleto() {
        int n = listVertex.size();
        for (Vertex<E> v : listVertex) {
            if (v.listAdj.size() != n - 1) {
                return false;
            }
        }
        return true;
    }

    private Vertex<E> getVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) return v;
        }
        return null;
    }

    public String toString() {
        return this.listVertex.toString();
    }
}
