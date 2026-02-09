package org.algoritmos.structures.nolinear.map;

import java.util.Objects;

public class HashNode<K, V> {

    private final K key;
    private V value;
    private HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    // --- Getters y Setters ---

    public K getKey() {return key;}
    public V getValue() {return value;}
    public void setValue(V value) {this.value = value;}
    public HashNode<K, V> getNext() {return next;}
    public void setNext(HashNode<K, V> next) {this.next = next;}


    @Override
    public String toString() {
        return key + " = " + value;
    }
}