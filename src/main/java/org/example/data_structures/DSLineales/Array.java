// Mande by Andres-cmk
package org.example.data_structures.DSLineales;

import org.example.data_structures.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Array<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                if(!hasNext()) throw new NoSuchElementException();
                T element = get(index);
                index++;
                return element;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    private Node<T> firts;
    private Node<T> last;
    private int size;

    public Array() {
        firts = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return firts == null;
    }

    public int size() {
        return size;
    }

    private void updateIndex() {
        Node<T> current = firts;
        int idx = 0;
        while (current != null) {
            current.setIndex(idx);
            idx++;
            current = current.getNext();
        }
    }

    public void add(T a){
        Node<T> temp = new Node<>(a);
        temp.setIndex(size);
        if (isEmpty()){
            firts = temp;
            last = firts;
        }else {
            last.setNext(temp);
            last = temp;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        Node<T> current = firts;
        int currentIndex = 0;

        while (currentIndex < index) {
            current = current.getNext();
            currentIndex++;
        }

        return current.getValue();
    }

    public void set(int index, T value) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        Node<T> current = firts;
        int currentIndex = 0;

        while (currentIndex < index) {
            current = current.getNext();
            currentIndex++;
        }

        current.setValue(value);
    }


    public int remove(T a){

        if (isEmpty()) return -1;

        Node<T> temp = firts;

        if (firts == last){
            firts = last.getNext();
            last.setNext(null);
            return 1;
        }

        while (temp.getNext() != null){

            if(temp.getNext().getValue().equals(a)){
                temp.setNext(temp.getNext().getNext());
                return 1;
            }
            temp = temp.getNext();
        }
        updateIndex();
        return -1;
    }

    public void print(){
        StringBuilder s = new StringBuilder();
        if (isEmpty()){
            s.append("[]");
            System.out.println(s);
            return;
        }
        s.append("[");
        Node<T> temp = firts;
        while (temp.getNext() != null){
            s.append(temp.getValue()).append(",");
            temp = temp.getNext();
        }
        s.append(temp.getValue()).append("]");
        System.out.println(s);
    }

}
