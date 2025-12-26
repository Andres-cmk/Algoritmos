/**
 * @autor Andres Ramirez (Andres-cmk)
 */
package org.algoritmos.structures;

public class Node<E> {
    Node<E> next;
    E value;
    long index;

    public Node(E value) {
        next = null;
        this.value = value;
    }

    public Node<E> getNext() {
        return next;
    }

    public E getValue() {
        return value;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
    public void setValue(E value) {
        this.value = value;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getIndex() {
        return index;
    }
}
