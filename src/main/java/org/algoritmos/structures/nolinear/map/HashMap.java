/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.structures.nolinear.map;

import org.algoritmos.structures.linear.Array;

public class HashMap<K,V> implements Map<K,V>{

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int capacity;
    private final float loadFactor;
    private Array<HashNode<K, V>> table;
    private int size;

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new Array<>(capacity);
        this.size = 0;
    }

    public void resize(){

        if (this.size >= this.capacity * loadFactor) {

            int newCapacity = this.capacity * 2;


            Array<HashNode<K, V>> oldTable = this.table;


            this.capacity = newCapacity;


            this.table = new Array<>(newCapacity);

            this.size = 0;


            for (int i = 0; i < oldTable.getCapacity(); i++) {
                HashNode<K, V> current = oldTable.get(i);
                while (current != null) {

                    this.put(current.getKey(), current.getValue());
                    current = current.getNext();
                }
            }
        }
    }

    @Override
    public void put(K key, V value) {

        int index = Math.abs(this.hash(key));

        HashNode<K, V> current = this.table.get(index);

        HashNode<K, V> head = this.table.get(index);

        while (current != null) {
            if (current.getKey().equals(key)) {
                current.setValue(value);
                return;
            }
            current = current.getNext();
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.setNext(head);
        this.table.set(index, newNode);
        this.size++;
        resize();
    }

    @Override
    public int hash(K key) {
        return key.hashCode() % capacity;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.capacity; i++) {
            sb.append("bucket ").append(i).append(": ");

            HashNode<K, V> current = this.table.get(i);

            // CORRECCIÓN 3: Lógica del toString arreglada
            if (current == null) {
                sb.append("null");
            } else {
                while (current != null) {
                    sb.append("[").append(current.getKey()).append("=").append(current.getValue()).append("]");
                    if (current.getNext() != null) {
                        sb.append(" -> ");
                    }
                    current = current.getNext();
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        map.put("key6", "value6");
        map.put("key56", "value7");
        System.out.println(map);
    }
}
