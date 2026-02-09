/**
 * @autor Andres Ramirez (Andres-cmk)
 */
package org.algoritmos.structures.nolinear.map;

public interface Map<K, V> {
    void put(K key, V value);
    int hash(K key);
    V get(K key);
    void remove(K key);
    void clear();
    int size();
    boolean isEmpty();
    boolean containsKey(K key);
    boolean containsValue(V value);
}
