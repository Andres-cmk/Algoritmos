/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.structures.linear;

import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>>{

    private final SingleLinkedList<T> internalList;

    public Stack() {
        this.internalList = new SingleLinkedList<>();
    }

    public void push(T element) {
        this.internalList.pushFront(element);
    }

    public T pop() {
        return this.internalList.popFront();
    }

    public T peek() {
        if (this.internalList.isEmpty()) throw new NoSuchElementException("Stack is empty");
        return this.internalList.topFront();
    }

    public boolean isEmpty() {
        return this.internalList.isEmpty();
    }

    public int size() {
        return this.internalList.size();
    }

    public void clear() {
        this.internalList.clear();
    }

    @Override
    public String toString() {
        return this.internalList.toString();
    }


}
