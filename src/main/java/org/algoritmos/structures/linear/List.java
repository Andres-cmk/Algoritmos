/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.structures.linear;

import java.util.Iterator;
import java.util.function.Consumer;

// Arreglo estatico
public class List<T> implements Iterable<T> {

    private Object[] list;
    private int size;

    public List(){
        this(10);
    }

    public List(int capacity){
        this.list = new Object[capacity];
        this.size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return this.size;
    }

    public int getCapacity() {
        return this.list.length;
    }

    public void addArray(T e){
        this.add(this.size, e);
    }

    public void set(int index, T e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        this.list[index] = e;
    }


    @SafeVarargs // Suprime advertencias de seguridad de tipos con genéricos
    public static <T> List<T> of(T... elements) {
        // 1. Creamos la lista con la capacidad exacta para no desperdiciar memoria
        List<T> newList = new List<>(elements.length);

        // 2. Recorremos los elementos que nos pasaron y los agregamos
        for (T e : elements) {
            newList.addArray(e);
        }

        return newList;
    }

    public void add(int index,T e){

        if(this.size == this.list.length){
            this.grow(); // Para arreglo dinamico
        }

        // Desplazamiento a la derecha
        for (int i = this.size - 1; i >= index; i--) {
            this.list[i + 1] = this.list[i];
        }
        this.list[index] = e;
        size++;
    }


    private void grow(){
        int oldCapacity = this.list.length;

        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity < oldCapacity + 1) {
            newCapacity = oldCapacity + 10; // Crecimiento mínimo por defecto
        }

        Object[] temp = new Object[newCapacity];
        System.arraycopy(this.list, 0, temp, 0, this.size);
        this.list = temp;
    }

    public void clear(){
        this.list = new Object[10];
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
            if(this.list[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public T get(int index){
        return (T)this.list[index];
    }

    public void remove(int index) {
        // Validación de índice recomendada
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // Desplazamiento a la izquierda
        for (int i = index + 1; i < this.size; i++) {
            this.list[i - 1] = this.list[i];
        }

        size--;             // 1. Primero reducimos el tamaño
        this.list[size] = null; // 2. Luego limpiamos esa posición que quedó "sobrando"
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
        Object[] array = this.list;
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
            sb.append(list[i]);
            if (i != size - 1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
