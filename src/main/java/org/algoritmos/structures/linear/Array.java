/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.structures.linear;

import java.util.Iterator;
import java.util.RandomAccess;
import java.util.function.Consumer;

// Arreglo estatico
public class Array<T> implements Iterable<T>, RandomAccess {

    private Object[] array;
    private int size;

    public Array(){
        this(10);
    }

    public Array(Object[] array){
        this.array = array;
        this.size = this.array.length;
    }

    public Array(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return this.size;
    }

    public int getCapacity() {
        return this.array.length;
    }

    public void addArray(T e){
        this.addArray(this.size, e);
    }

    public void addArray(int index,T e){

        if(this.size == this.array.length){
            throw new IllegalStateException("Array is full! Cannot add more elements.");
        }

        // Desplazamiento a la derecha
        for (int i = this.size - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }
        this.array[index] = e;
        size++;
    }


    private void grow(){
        int oldCapacity = this.array.length;

        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity < oldCapacity + 1) {
            newCapacity = oldCapacity + 10; // Crecimiento mínimo por defecto
        }

        Object[] temp = new Object[newCapacity];
        System.arraycopy(this.array, 0, temp, 0, this.size);
        this.array = temp;
    }

    public void clear(){
        this.array = new Object[10];
        this.size = 0;
    }

    public boolean contains(T e){
        return this.indexOf(e) != -1;
    }

    public int indexOf(T e){
        if(this.size == 0){
            return -1;
        }

        for (int i = 0; i < this.size; i++) {
            if(this.array[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public T get(int index){
        return (T)this.array[index];
    }

    public void set(int index,T e){
        this.array[index] = e;
    }

    public void remove(int index) {
        // Validación de índice recomendada
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // Desplazamiento a la izquierda
        for (int i = index + 1; i < this.size; i++) {
            this.array[i - 1] = this.array[i];
        }

        size--;             // 1. Primero reducimos el tamaño
        this.array[size] = null; // 2. Luego limpiamos esa posición que quedó "sobrando"
    }


    public boolean remove(T e){
        int indexElement = this.indexOf(e);
        if(indexElement != -1){
            this.remove(indexElement);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        int size = this.size;
        Object[] array = this.array;
        return new Iterator<>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }
            @Override
            public T next() {
                return (T) array[i++];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++){
            sb.append(array[i]);
            if (i != size - 1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
