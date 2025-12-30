/**
 * @autor Andres Ramirez (Andres-cmk)
 */
package org.algoritmos.structures.linear;



import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * <p>
 *  Esta estructura se va implementar con una lista doblemente enlazada. A diferencia de las listas
 *  simple y doble, donde tail y head apunta a null en la doble, estos van a puntar, para el caso de
 *  head a tail y tail a head.
 *
 * </p>
 *
 * imagen de referencia: <a href="https://www.geeksforgeeks.org/dsa/applications-advantages-and-disadvantages-of-circular-doubly-linked-list/">link</a>
 */

public class CircularDoublyLinkedList<T extends Comparable<T>> implements Iterable<T>{

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
            int remaining = size; // recorrer exactamente `size` elementos

            @Override
            public boolean hasNext() {
                return remaining > 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                remaining--;
                return data;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    // Atributos principales de la estructura
    private Node<T> head, tail;
    private int size;


    public CircularDoublyLinkedList() {
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

    public void insertFirst(T key){


        // Nuevo nodo a insertar
        Node<T> newNode = new Node<>(key, null, null);

        if (isEmpty()) {
            // La lista esta vacia
            head = tail = newNode;

            head.next = newNode;
            head.prev = newNode;
        }
        else {
            // Lista ya tiene elementos

            // Configuracion de los punteros del nuevo nodo
            newNode.next = head;
            newNode.prev = tail;

            // Ajuste de los punteros de head y tail
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }

        size++;
    }

    public void insertLast(T key) {

        // Nuevo nodo a insertar
        Node<T> newNode = new Node<>(key, null, null);

        if (isEmpty()) {
            // La lista esta vacia
            head = tail = newNode;

            head.next = newNode;
            head.prev = newNode;

        } else {
            // Lista ya tiene elementos

            // Configuracion de los punteros del nuevo nodo
            newNode.next = head;
            newNode.prev = tail;

            // Ajuste de los punteros de head y tail
            tail.next = newNode;
            head.prev = newNode;
            tail = newNode;
        }

        size++;
    }


    public boolean insertAfter(T keyReference, T keyToInsert) {

        if (isEmpty()) throw new NoSuchElementException("List is empty");


        Node<T> current = head;

        do {
            // ¿Es este el nodo que buscamos?
            if (current.data.equals(keyReference)) {

                // Si es el último, debemos actualizar 'tail'
                if (current == tail) {
                    insertLast(keyToInsert);
                    return true;
                }

                // Lógica de inserción en el medio
                Node<T> newNode = new Node<>(keyToInsert, null, null);

                newNode.next = current.next;
                newNode.prev = current;

                current.next.prev = newNode;
                current.next = newNode;

                size++;
                return true;
            }

            current = current.next;

        } while (current != head); // 3. Revisamos hasta volver al principio

        // Si salimos del bucle, no lo encontramos
        throw new NoSuchElementException("Element " + keyReference + " not found");
    }

    public boolean insertBefore(T keyReference, T keyToInsert) {

        if (isEmpty()) throw new NoSuchElementException("List is empty");

        Node<T> current = head;

        do{

            if (current.data.equals(keyReference)) {
                if (current == head) {
                    insertFirst(keyToInsert);
                    return true;
                }

                // Logica de insercción
                Node<T> newNode = new Node<>(keyToInsert, null, null);

                newNode.next = current;
                newNode.prev = current.prev;

                current.prev.next = newNode;
                current.prev = newNode;

                size++;
                return true;

            }
            current = current.next;

        } while (current != head);

        throw new NoSuchElementException("Element " + keyReference + " not found");
    }


    public T peekFirst() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");

        // Guardamos el dato para retornarlo al final
        T data = head.data;

        if (head == tail) {

            // CASO 1: Solo hay un elemento
            head = tail = null;
        } else {

            // Caso 2: Hay mas elementos
            head = head.next;

            // RECONEXIÓN DEL CÍRCULO
            tail.next = head;
            head.prev = tail;
        }

        size--;
        return data;
    }

    public T removeLast() {

        if (isEmpty()) throw new NoSuchElementException("List is empty");

        T data = tail.data;

        if (tail == head) {

            head = tail = null;
        } else {

            tail = tail.prev;

            tail.next = head;
            head.prev = tail;
        }
        size--;
        return data;
    }

    // ------------------- Funcion de ayuda ----------------------//
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

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == this.size - 1) return tail.data;
        if (index == 0) return head.data;

        return getNode(index).data;
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

    public boolean contains(T key) {
        if (isEmpty()) throw new NoSuchElementException("List is empty");
        return indexOf(key) != -1;
    }

    public T maxElement() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");

        // Asumimos que el primero es el máximo provisionalmente
        T max = head.data;
        Node<T> current = head.next; // Empezamos a comparar desde el segundo

        while (current != null) {
            // Si current > max, actualizamos max
            if (current.data.compareTo(max) > 0) {
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }

    public T minElement() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");

        // Asumimos que el primero es el mínimo provisionalmente
        T min = head.data;
        Node<T> current = head.next;

        while (current != null) {
            // Si current < min, actualizamos min
            if (current.data.compareTo(min) < 0) {
                min = current.data;
            }
            current = current.next;
        }
        return min;
    }



    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        // recorrer exactamente `size` elementos para evitar bucle infinito
        for (int i = 0; i < size; i++) {
            sb.append(current.data);
            if (i != size - 1) {
                sb.append("->");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // ------------------------ Metodos de depurar ------------------//
    private T getNext(T key) {

        if (isEmpty()) {
            return null;
        }
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(key)) {
                return current.next.data;
            }
            current = current.next;
        }
        return null; // key not found

    }

    private T getPrev(T key) {

        if (isEmpty()) {
            return null;
        }
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(key)) {
                return current.prev.data;
            }
            current = current.next;
        }
        return null; // key not found

    }

}
