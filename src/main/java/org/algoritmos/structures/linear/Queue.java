/**
 * @autor Andres Ramirez (Andres-cmk)
 */
package org.algoritmos.structures.linear;


public class Queue<T extends Comparable<T>> {

    private final SingleLinkedList<T> internalList;

    public Queue() {
        this.internalList = new SingleLinkedList<>();
    }


    public void enqueue(T element) {
        this.internalList.pushBack(element);
    }

    public T dequeue() {
        return this.internalList.popFront();
    }

    public T peek() {
        return this.internalList.topFront();
    }

    public int size() {
        return this.internalList.size();
    }

    public boolean isEmpty() {
        return this.internalList.isEmpty();
    }

    @Override
    public String toString() {
        return this.internalList.toString();
    }


}
