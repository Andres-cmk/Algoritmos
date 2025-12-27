/**
 * @autor Andres Ramirez (Andres-cmk)
 */
package org.algoritmos.structures.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class DoublyLinkedList<T extends Comparable<T>> implements Iterable<T> {

    /**
     * A diferencia de la simplemente enlazada, vamos a guardar en memoria un puntero que guarda el predecesor
     * del nodo en cuestión
     * Para este caso usaremos la clase Node con una modificacion de la clase Node del paquete org.algoritmos.structures.Node.java*/

    private static class Node<T> {
         T data;
         Node<T> next, prev;

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null){
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    // Primer elemento de la lista
    private Node<T> head;

    // Ultimo elemento de la lista
    private Node<T> tail;

    // Tamaño de la lista
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }
    public int size() {
        return size;
    }

    /**
     * @param key: Entero a insertar.
     *
     *Para la inserccion se tienen 3 casos:
     * 1. Se inserta al final de la lista
     * 2. Se inserta al pricinpio de la lista
     * 3 Se inserta en un indice especifico
     */
    public void insertLast(T key) {
        if (isEmpty()) {
            head = tail = new Node<>(key, null, null);
        } else {

            Node<T> newNode = new Node<>(key, null, tail);

            tail.next = newNode;

            tail = newNode;
        }
        size++;
    }

    public void insertFirst(T key) {
        if (isEmpty()) {
            head = tail = new Node<>(key, null, null);
        }else {
            Node<T> newNode = new Node<>(key, head, null);
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void insertAt(int index, T key) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == size) {
            insertLast(key);
            return;
        }

        if (index == 0) {
            insertFirst(key);
            return;
        }

        // Buscamos el nodo que actualmente ocupa esa posición
        Node<T> current = getNode(index);
        Node<T> previous = current.prev;

        // Next: current, Prev: previous
        Node<T> newNode = new Node<>(key, current, previous);
        // Reconectamos los vecinos
        previous.next = newNode;
        current.prev = newNode;

        size++;
    }

    // --------------------- FUNCION DE AYUDA -----------------------------//
    private Node<T> getNode(int index) {
        Node<T> node;
        if (index < (size >> 1)) { // Buscar desde el inicio
            node = head;
            for (int i = 0; i < index; i++) node = node.next;
        } else { // Buscar desde el final
            node = tail;
            for (int i = size - 1; i > index; i--) node = node.prev;
        }
        return node;
    }

    public void clear(){
        Node<T> current = head;
        while (current != null){
            Node<T> next = current.next;
            current.prev = current.next = null;
            current.data = null;
            current = next;
        }
        head = tail = null;
        size = 0;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }


    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }

        T data = head.data;
        head = head.next;
        size--;

        if (isEmpty()) tail = null;

        else head.prev = null;

        return data;

    }

    public T removeLast() {

        if (isEmpty()) throw new RuntimeException("List is empty");

        T data = tail.data;
        tail = tail.prev;
        size--;

        if (isEmpty()) head = null;

        else tail.next = null;

        return data;

    }


    private T removeNode(Node<T> node) {
        if (node.prev == null) return removeFirst();
        if  (node.next == null) return removeLast();

        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;

        node.data = null;
        node.prev = node.next = null;
        size--;

        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index: " + index + ", Size: " + size);
        }

        // Reusamos el helper optimizado
        Node<T> nodeToRemove = getNode(index);
        return removeNode(nodeToRemove);
    }

    public int indexOf(T key) {

        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }

        int i = 0;
        Node<T> node = head;

        while (node != null) {
            if (node.data.equals(key)) {
                return i;
            }
            node = node.next;
        }

        return -1;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null){
            sb.append(current.data);
            if (current.next != null){
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

}
