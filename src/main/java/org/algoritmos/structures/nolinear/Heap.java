/**
 * @autor Andres Ramirez (Andres-cmk)
 *
 */

package org.algoritmos.structures.nolinear;

import org.algoritmos.structures.linear.List;

// Demostración de como se implementa el Heap.

/** <p>Esta estructura de datos es la base de:
 *
 *  <ul>
 *      <li>La cola de prioridad menor</li>
 *      <li>Cola de prioridad mayor</li>
 *      <li>Heap Sort</li>
 *  </ul>
 *
 * Esta clase se desarrollo con el fin de hacer la abstracción de ambas estructuras anteriormente
 * mencionadas para luego en las otras clase que se encuentran en esta carpeta solo se necesite llamar
 * a los metodos en el cual les corresponde.
 * </p>
 */
public class Heap<T extends Comparable<T>> {

    protected final List<T> heap;

    public Heap(List<T> list) {
        heap = list;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private void swap(int i, int j) {
        T tmp = this.heap.get(i);
        this.heap.set(i, heap.get(j));
        this.heap.set(j, tmp);
    }

    private void maxHeapify(int i){

        int largest = i;
        int left = getLeftChildIndex(i);
        int right = getRightChildIndex(i);

        if (left < size() && heap.get(left).compareTo(heap.get(i)) > 0){
            largest = left;
        }

        if (right < size() && heap.get(right).compareTo(heap.get(largest)) > 0){
            largest = right;
        }

        if  (largest != i) {
            swap(largest, i);
            maxHeapify(largest);
        }
    }

    private void minHeapify(int i){

        int minimum = i;

        int left = getLeftChildIndex(i);
        int right = getRightChildIndex(i);

        if (left < size() && heap.get(left).compareTo(heap.get(minimum)) < 0){
            minimum = left;
        }

        if (right < size() && heap.get(right).compareTo(heap.get(minimum)) < 0){
            minimum = right;
        }

        if (minimum != i) {
            swap(minimum, i);
            minHeapify(minimum);
        }

    }

    public void buildMaxHeap(){
        for (int i = this.heap.size()/2 - 1; i >= 0; i--) {
            maxHeapify(i);
        }
    }
    public void buildMinHeap(){
        for (int i = this.heap.size()/2 - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    public T peek(){
        if (isEmpty()) return null;
        return this.heap.get(0);
    }

    public T extracHeap(T value){

        if (this.heap.isEmpty()) throw new IllegalStateException("heap is empty");


        int index = -1;
        for (int i = this.heap.size() - 1; i >= 0; i--) {
            if (this.heap.get(i).compareTo(value) == 0) {
                index = i;
            }
        }

        if (index != -1) {
            return null;
        }

        T temp = this.heap.get(index);
        this.heap.set(index, this.heap.get(size() - 1));
        this.heap.remove(size() - 1);
        minHeapify(index);
        return temp;
    }

    public T extractMinElement() {
        T temp;

        if (this.heap.isEmpty()) throw new IllegalStateException("Heap is empty");
        if (this.heap.size() == 1) {
            temp = this.heap.get(0);
            this.heap.remove(0);
            return temp;
        }

        temp  = this.heap.get(0);
        this.heap.set(0, this.heap.get(size() - 1));
        this.heap.remove(size() - 1);
        minHeapify(0);
        return temp;
    }

    public T extractMaxElement() {
        T temp;
        if (this.heap.isEmpty()) throw new IllegalStateException("Heap is empty");
        if (this.heap.size() == 1) {
            temp = this.heap.get(0);
            this.heap.remove(0);
            return temp;
        }
        temp  = this.heap.get(0);
        this.heap.set(0, this.heap.get(size() - 1));
        this.heap.remove(size() - 1);
        maxHeapify(0);
        return temp;
    }

    public void insertMaxHeap( T o){
        this.heap.addArray(o);

        int index = this.heap.size() - 1;
        while (index > 0){
            int parent_index = getParentIndex(index);

            if (o.compareTo(this.heap.get(parent_index)) > 0){
                swap(index, parent_index);
                index = parent_index;
            } else {
                break;
            }
        }
    }

    public void insertMinHeap( T o){
        this.heap.addArray(o);
        int index = this.heap.size() - 1;
        while (index > 0){
            int parent_index = getParentIndex(index);
            if (o.compareTo(this.heap.get(parent_index)) < 0){
                swap(index, parent_index);
                index = parent_index;
            } else {
                break;
            }
        }
    }
}
