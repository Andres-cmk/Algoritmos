/**
 * @autor Andres Ramirez (Andres-cmk)
 */
package org.algoritmos.structures.linear;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @param <T> Generico
 */
/**
 * <p>
 *  Esta estructura se va implementar con una lista doblemente enlazada. A diferencia de las listas
 *  simple y doble, donde tail y head apunta a null en la doble, estos van a puntar, para el caso de
 *  head a tail y tail a head.
 *
 * </p>
 *
 * imagen de referencia: <a href="https://www.geeksforgeeks.org/dsa/applications-advantages-and-disadvantages-of-circular-doubly-linked-list/">link</a>
 */
public class CircularDoublyLinkedList<T> implements Iterable<T>{


    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }
}
