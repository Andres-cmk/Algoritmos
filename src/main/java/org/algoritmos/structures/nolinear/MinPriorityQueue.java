/**
 * @autor Andres Ramirez (Andres-cmk)
 *
 */
package org.algoritmos.structures.nolinear;

import org.algoritmos.structures.linear.List;

public class MinPriorityQueue<T extends Comparable<T>> extends Heap<T> {

    public MinPriorityQueue(List<T> list) {
        super(list);
        buildMinHeap();
    }

    public int sizeMinPriorityQueue() {
        return size();
    }

    public boolean isEmptyMinPriorityQueue() {
        return isEmpty();
    }

    @Override
    public T peek() {
        return super.peek();
    }

    public void insert(T item) {
       insertMinHeap(item);
    }

    public T removeMin() {
        return extractMinElement();
    }

    public T extractElement(T value) {
        return extracHeap(value);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
    public static void main(String[] args) {
        List<Integer> list = List.of(5,2,1,7,9,4,1);
        MinPriorityQueue<Integer> max = new MinPriorityQueue<>(list);
        System.out.println(max);
    }

}
