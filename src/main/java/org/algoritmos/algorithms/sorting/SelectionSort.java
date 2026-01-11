/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.algorithms.sorting;

/**
 * Implementación del algoritmo de Ordenamiento por Selección (Selection Sort).
 *
 * <p>El <b>Selection Sort</b> funciona dividiendo el arreglo en dos partes:
 * una sublista de elementos ya ordenados (al inicio) y una sublista de elementos
 * restantes por ordenar. En cada iteración, el algoritmo busca el elemento mínimo
 * (o máximo) de la sublista desordenada y lo intercambia con el primer elemento
 * desordenado.</p>
 *
 * <h3>Análisis de Complejidad:</h3>
 * <ul>
 * <li><b>Peor Caso (O(n^2)):</b> Ocurre siempre. Debemos recorrer todo el resto del arreglo para asegurar cuál es el mínimo.</li>
 * <li><b>Mejor Caso (O(n^2)):</b> A diferencia del Insertion Sort, este algoritmo <b>no mejora</b> aunque
 * el arreglo ya esté ordenado, pues de igual forma debe realizar todas las comparaciones.</li>
 * <li><b>Escrituras (Writes):</b> Realiza un máximo de O(n) intercambios, lo cual es útil si escribir en memoria es costoso.</li>
 * <li><b>Espacio (O(1)):</b> Es un algoritmo "in-place".</li>
 * </ul>
 */

import org.algoritmos.structures.linear.Array;

public class SelectionSort {

    /**
     * Ordena un arreglo de enteros utilizando el método de Selección.
     *
     * <p>Estrategia:</p>
     * <ol>
     * <li>Encuentra el elemento más pequeño en la parte no ordenada del arreglo.</li>
     * <li>Lo intercambia con el elemento al inicio de la parte no ordenada.</li>
     * <li>Avanza el límite de la parte ordenada una posición.</li>
     * </ol>
     *
     * @param A El arreglo dinámico de enteros a ordenar.
     * @return El mismo arreglo {@code A} con sus elementos ordenados de menor a mayor.
     */
    public static Array<Integer> selectionSort(Array<Integer> A){

        // Límite entre la parte ordenada (izquierda) y no ordenada (derecha)
        for (int i = 0; i < A.size(); i++){

            // Asumimos temporalmente que el primer elemento no ordenado es el mínimo
            int minIndex = i;

            // Buscamos el verdadero mínimo en el resto del arreglo
            for (int j = i+1; j < A.size(); j++){
                if (A.get(j) < A.get(minIndex)) minIndex = j; // Actualizamos el índice del nuevo mínimo encontrado
            }

            // Intercambio (Swap): Colocamos el mínimo encontrado en su posición correcta (i)
            // Solo intercambiamos si el mínimo no estaba ya en su lugar (opcional, pero ahorra una asignación)
            int temp = A.get(i);
            A.set(i, A.get(minIndex));
            A.set(minIndex, temp);
        }
        // Retornamos el arreglo
        return A;
    }


    public static void main(String[] args) {
        Array<Integer> A = new Array<>(new Integer [] {64, 25, 12, 22, 11});
        System.out.println(SelectionSort.selectionSort(A));
    }
}
