/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.algorithms.sorting;

import org.algoritmos.structures.linear.Array;

/**
 * Implementación del algoritmo de Ordenamiento por Inserción (Insertion Sort).
 *
 * <p>El <b>Insertion Sort</b> es un algoritmo de ordenamiento simple que construye
 * la lista ordenada final un elemento a la vez. Es muy eficiente para listas
 * pequeñas o para listas que ya están parcialmente ordenadas. Ademas, el algoritmo es estable, es decir,
 * se conserva el orden relativo de lo elementos que tienen la misma clave (key).</p>
 *
 * <h3>Análisis de Complejidad:</h3>
 * <ul>
 * <li><b>Peor Caso (O(n^2)):</b> Ocurre cuando el arreglo está ordenado inversamente.
 * Cada elemento debe ser comparado y desplazado hasta el inicio.</li>
 * <li><b>Mejor Caso (O(n)):</b> Ocurre cuando el arreglo ya está ordenado.
 * El algoritmo solo realiza una comparación por elemento sin hacer desplazamientos.</li>
 * <li><b>Promedio (O(n^2)):</b> En un arreglo aleatorio.</li>
 * <li><b>Espacio (O(1)):</b> Es un algoritmo "in-place", no requiere memoria extra significativa.</li>
 * </ul>
 *
 */

public class InsertionSort {

    /**
     * Ordena un arreglo de enteros utilizando el método de Inserción.
     *
     * <p>El algoritmo divide virtualmente el arreglo en una parte ordenada y otra desordenada.
     * Los valores de la parte desordenada se seleccionan y se colocan en la posición correcta
     * dentro de la parte ordenada.</p>
     *
     * @param A El arreglo dinámico de enteros a ordenar. Se modifica in-situ (in-place).
     * @return El mismo arreglo {@code A} pero con sus elementos ordenados de forma ascendente.
     */

    public static Array<Integer> insertionSort(Array<Integer> A) {

        //Se recorre todos los elementos del arreglo
        for (int i = 1; i < A.size(); i++) {

            // Toma un valor (key) del arreglo pero siempre una posicion adelante de j.
            // El iterador j nos ayuda a comparar cada elemento del arreglo a la izquierda del elemento A[i]
            int key = A.get(i);
            int j = i - 1;

            // bucle principal del algoritmo.
            while (j >= 0 && A.get(j) > key) {

                // Si la condición se cumple, entonces:
                // Coloca en la posicion j + 1 el elemento mayor.
                A.set(j+1, A.get(j));

                // Seguimos con los demas elementos del arreglo hacia la izquierda.
                j--;
            }
            // Cuando no se cumple.
            // Colocamos en la posicion correta (forma ordenada) el key.
            A.set(j+1, key);
        }

        return A;

    }

    public static void main(String[] args) {
        Array<Integer> arr = new Array<>(new Integer[]{44, 58, 48, 76, 58, 75});
        System.out.println(InsertionSort.insertionSort(arr));
    }
}
