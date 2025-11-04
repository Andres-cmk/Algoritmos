package org.example.data_structures;

public class Node<E> {
    Node<E> next;
    E value;
    int index;

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

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
