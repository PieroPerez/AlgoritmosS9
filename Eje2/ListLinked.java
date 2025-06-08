package Eje2;

import java.util.Iterator; // Importa la interfaz Iterator
import java.util.NoSuchElementException; // Para manejar excepciones si no hay más elementos

public class ListLinked<T> implements Iterable<T> { // Clase genérica de lista enlazada que implementa Iterable

    private Node<T> head; // Nodo principal o cabeza de la lista
    private int size; // Número de elementos en la lista

    public ListLinked() { // Constructor de la lista
        head = null; // Inicializa la lista vacía
        size = 0; // Tamaño inicial en 0
    }

    public void add(T data) { // Método para agregar un dato al final de la lista
        Node<T> newNode = new Node<>(data); // Crea nuevo nodo
        if (head == null) { // Si la lista está vacía
            head = newNode; // El nuevo nodo es la cabeza
        } else {
            Node<T> aux = head; // Nodo auxiliar para recorrer
            while (aux.next != null) { // Avanza hasta el final
                aux = aux.next;
            }
            aux.next = newNode; // Agrega el nuevo nodo al final
        }
        size++; // Incrementa el tamaño de la lista
    }

    public boolean remove(T data) { // Elimina la primera ocurrencia del dato
        if (head == null) return false; // Si está vacía, no hay nada que eliminar

        if (head.data.equals(data)) { // Si el dato está en la cabeza
            head = head.next; // Mueve la cabeza al siguiente nodo
            size--; // Disminuye el tamaño
            return true; // Eliminado con éxito
        }

        Node<T> current = head; // Nodo auxiliar
        while (current.next != null) { // Recorre la lista
            if (current.next.data.equals(data)) { // Si el siguiente es el que queremos eliminar
                current.next = current.next.next; // Salta el nodo
                size--; // Disminuye el tamaño
                return true; // Eliminado con éxito
            }
            current = current.next; // Avanza
        }

        return false; // No se encontró el dato
    }

    public boolean contains(T data) { // Verifica si un dato está en la lista
        for (T element : this) { // Recorre usando foreach
            if (element.equals(data)) { // Compara
                return true; // Lo encontró
            }
        }
        return false; // No está en la lista
    }

    public int size() { // Retorna el tamaño de la lista
        return size;
    }

    public String toString() { // Convierte la lista en String
        StringBuilder sb = new StringBuilder(); // Usa StringBuilder para eficiencia
        for (T element : this) { // Recorre todos los elementos
            sb.append(element.toString()); // Añade el texto de cada uno
        }
        return sb.toString(); // Retorna el string final
    }

    @Override
    public Iterator<T> iterator() { // Retorna un iterador para recorrer la lista
        return new Iterator<T>() {
            private Node<T> current = head; // Comienza desde la cabeza

            public boolean hasNext() { // ¿Hay un siguiente elemento?
                return current != null;
            }

            public T next() { // Retorna el siguiente elemento
                if (!hasNext()) throw new NoSuchElementException(); // Si no hay, lanza excepción
                T data = current.data; // Guarda dato actual
                current = current.next; // Avanza al siguiente nodo
                return data; // Retorna dato
            }
        };
    }

    private static class Node<T> { // Clase interna para los nodos
        T data; // Dato del nodo
        Node<T> next; // Enlace al siguiente nodo

        Node(T data) { // Constructor del nodo
            this.data = data;
            this.next = null;
        }
    }
}
