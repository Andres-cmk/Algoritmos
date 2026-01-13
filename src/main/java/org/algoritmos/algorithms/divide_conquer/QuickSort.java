/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.algorithms.divide_conquer;

import org.algoritmos.structures.linear.Array;

/**
 * Implementación del algoritmo de ordenamiento Quick Sort (Ordenamiento Rápido).
 *
 * <p>El <b>QuickSort</b> es un algoritmo eficiente basado en la estrategia "Divide y Vencerás".
 * A diferencia del MergeSort, que divide el arreglo por la mitad física, QuickSort lo divide
 * basándose en los valores (particionamiento).</p>
 *
 * <h3>Mecánica:</h3>
 * Selecciona un elemento como "pivote" y reorganiza el arreglo de tal manera que todos los
 * elementos menores que el pivote queden a su izquierda y los mayores a su derecha.
 * Luego, ordena recursivamente los sub-arreglos.
 *
 * <h3>Análisis de Complejidad:</h3>
 * <ul>
 * <li><b>Tiempo Promedio (O(n log n)):</b> Es el caso más común y es extremadamente rápido.</li>
 * <li><b>Peor Caso (O(n^2)):</b> Ocurre cuando el arreglo ya está ordenado (o inversamente ordenado)
 * y elegimos siempre el último elemento como pivote. Esto genera particiones desbalanceadas (n-1 y 0).</li>
 * <li><b>Espacio (O(log n)):</b> Utiliza espacio de pila (stack) para la recursión. Es mejor que MergeSort
 * en términos de memoria porque no requiere arreglos auxiliares (es "in-place").</li>
 * <li><b>Estabilidad:</b> No. El intercambio de elementos lejanos rompe el orden relativo de iguales.</li>
 * </ul>
 *
 */



public class QuickSort {

    static int com = 0;

    /**
     * Método de Partición (Esquema Lomuto).
     *
     * <p>Toma el último elemento {@code arr[r]} como pivote y coloca:
     * <ul>
     * <li>Elementos menores o iguales al pivote -> a la izquierda.</li>
     * <li>Elementos mayores al pivote -> a la derecha.</li>
     * </ul>
     * Al final, coloca el pivote en su posición definitiva y correcta.</p>
     *
     * @param arr El arreglo a particionar.
     * @param p Índice inicial (low).
     * @param r Índice final (high) - Usado como Pivote.
     * @return El índice final donde quedó ubicado el pivote.
     */

    static int partition(Array<Integer> arr, int p, int r) {

        // Para este caso, elegimos el pivote como el ultimo elemento.
        int pivot = arr.get(r);
        int temp;

        // 'i' rastrea la frontera de los elementos menores que el pivote
        int i = p - 1;

        // Recorremos el arreglo desde p hasta r-1
        for (int j = p; j < r; j++) {

            // Si el elemento actual es menor o igual al pivote
            if (arr.get(j) <= pivot) {

                // Expandimos la frontera de los menores
                i++;

                // Swap: Intercambiamos arr[i] con arr[j]
                temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                com++;
            }
            com++; // Conteo de comparación
        }

        // Paso final: Colocar el pivote (que estaba en 'r') en su lugar correcto (i + 1)
        temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(r));
        arr.set(r, temp);
        // Retornamos la posición del pivote
        return i + 1;
    }



    /**
     * Método recursivo principal del QuickSort.
     *
     * @param arr El arreglo a ordenar.
     * @param p Índice inicial del sub-arreglo.
     * @param r Índice final del sub-arreglo.
     */
    static void quickSort(Array<Integer> arr, int p, int r){

        if (p < r){

            // 1. Obtener el índice de partición (el pivote ya queda ordenado aquí)
            int q = partition(arr, p, r);
            // 2. Ordenar recursivamente la sub-lista izquierda (antes del pivote)
            quickSort(arr, p, q - 1);
            // 3. Ordenar recursivamente la sub-lista derecha (después del pivote)
            quickSort(arr, q + 1, r);
        }
    }

    public static void main(String[] args) {
        Array<Integer> A = new Array<>(new Integer[] {2 , 3, 4, 2, -1, 0, 1, 3, 3, 4, 6, 7, 9});
        int p = 0;
        int r = A.size() - 1;
        QuickSort.quickSort(A, p, r);
        System.out.println(A);
        System.out.println("# de comparaciones del arreglo: " + com);
    }

}
