/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.structures.linear;
import org.algoritmos.structures.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class SingleLinkedList<T extends  Comparable<T>> implements Iterable<T>{

    private Node<T> head;

    // Puntero adicional para mejorar la eficiencia de pushBack
    private Node<T> tail;

    private int size;

    public SingleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return (current != null);
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T data = current.getValue();
                current = current.getNext();
                return data;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    // O(1)
    public boolean isEmpty() {
        return head == null;
    }

    // O(1)
    public int size() {
        return size;
    }

    // O(1)
    public void pushFront(T key) {
        Node<T> newNode = new Node<>(key);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    // O(1)
    public T topFront(){
        return head.getValue();
    }

    //O(1)
    public T popFront(){

        if (isEmpty()){
            throw new NoSuchElementException("List is empty");
        }

        if (head == tail){
            T temp = head.getValue();
            head = null;
            tail = null;
            size--;
            return temp;
        }

        T temp = head.getValue();
        head = head.getNext();
        size--;
        return temp;
    }

    // O(1)
    public void pushBack(T key){
        Node<T> newNode = new Node<>(key);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }


    // O(n)
    public T popBack() {
        if (isEmpty()){
            throw new NoSuchElementException("List is empty");
        }

        if (tail == head){
            T temp = tail.getValue();
            head = null;
            tail = null;
            size--;
            return temp;
        }

        Node<T> current = head;

        // Lineas responsables: Peor caso recorrer n elementos para eliminar el ultimo elemento
        while (current.getNext() != tail){
            current = current.getNext();
        }

        T temp = current.getNext().getValue();
        tail = current;
        tail.setNext(null);
        size--;
        return temp;
    }


    // O(n)
    public boolean findKey(T key){
        if (isEmpty()){
            throw new NoSuchElementException("List is empty");
        }

        if (head == tail){
            return key.equals(tail.getValue());
        }

        Node<T> current = head;
        while (current != null){
            if (current.getValue().equals(key)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    //O(n)
    public boolean removeKey(T key) {
        if (isEmpty()) throw new NoSuchElementException("List is empty");

        // 1. Manejar el caso si el HEAD es el que queremos borrar
        if (head.getValue().equals(key)) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            size--;
            return true;
        }

        // 2. usamos current y previous
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getValue().equals(key)) {
                // Encontrado: saltamos el nodo
                current.setNext(current.getNext().getNext());

                // Si borramos el que era TAIL, actualizamos el puntero tail
                if (current.getNext() == null) {
                    tail = current;
                }
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    // O(n) en una lista desordenada. O(1) en una lista ordenada simplemente enlazada
    // O(n)
    public T maxElement() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        // Asumimos que el primero es el máximo provisionalmente
        T max = head.getValue();
        Node<T> current = head.getNext(); // Empezamos a comparar desde el segundo

        while (current != null) {
            // Si current > max, actualizamos max
            if (current.getValue().compareTo(max) > 0) {
                max = current.getValue();
            }
            current = current.getNext();
        }
        return max;
    }

    // O(n)
    public T minElement() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        // Asumimos que el primero es el mínimo provisionalmente
        T min = head.getValue();
        Node<T> current = head.getNext();

        while (current != null) {
            // Si current < min, actualizamos min
            if (current.getValue().compareTo(min) < 0) {
                min = current.getValue();
            }
            current = current.getNext();
        }
        return min;
    }

    // O(1)
    // Igualamos ambos punteros a null y el recolector de basura de java lo elimina de la memoria.
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    //O(n)
    public boolean contains(T key) {
        return findKey(key);
    }

    // O(n)
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == this.size - 1) return tail.getValue();
        if (index == 0) return head.getValue();

        Node<T> node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node.getValue();

    }

}
