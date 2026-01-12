/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.algorithms.divide_conquer;

import org.algoritmos.structures.linear.Array;

/**
 * Implementación del algoritmo de ordenamiento Merge Sort (Ordenamiento por Mezcla).
 *
 * <p>El <b>Merge Sort</b> es un algoritmo clásico de la estrategia "Divide y Vencerás" (Divide & Conquer).
 * Funciona dividiendo recursivamente el arreglo en dos mitades hasta que cada sub-arreglo tiene un solo elemento,
 * y luego "mezcla" (merge) esos sub-arreglos en orden para reconstruir el arreglo completo ordenado.</p>
 *
 * <h3>Análisis de Complejidad:</h3>
 * <ul>
 * <li><b>Tiempo (Time Complexity):</b> O(n log n) en todos los casos (Peor, Promedio y Mejor).
 * Esto se debe a que siempre divide el arreglo a la mitad (log n niveles) y recorre todos los elementos
 * para mezclarlos (n operaciones por nivel).</li>
 * <li><b>Espacio (Space Complexity):</b> O(n). Requiere espacio auxiliar para crear los arreglos
 * temporales L (Left) y R (Right) durante la fase de mezcla.</li>
 * <li><b>Estabilidad:</b> Sí. Mantiene el orden relativo de elementos iguales.</li>
 * </ul>
 *
 */

public class MergeSort {

    /**
     * Contador estático para métricas de rendimiento (comparaciones/asignaciones).
     * Útil para análisis empírico de la complejidad.
     */
    static int com = 0;


    /**
     * El corazón del algoritmo: Mezcla dos sub-arreglos ordenados en uno solo.
     *
     * <p>Toma dos segmentos del arreglo A:
     * <ul>
     * <li>Primer segmento: desde {@code p} hasta {@code q}.</li>
     * <li>Segundo segmento: desde {@code q+1} hasta {@code r}.</li>
     * </ul>
     * Crea copias temporales (L y R) y sobreescribe en A los valores en orden ascendente.</p>
     *
     * @param A El arreglo original que contiene ambos segmentos.
     * @param p Índice de inicio del primer segmento (Left).
     * @param q Índice medio que divide los segmentos.
     * @param r Índice final del segundo segmento (Right).
     *
     * Complejidad de este método: O(n), donde n es el tamaño del rango (r - p + 1).
     */
    static void merge(Array<Integer> A, int p, int q, int r) {

        // 1. Calcular tamaños de los sub-arreglos
        int nL = q - p + 1;
        int nR = r - q;

        // 2. Crear arreglos temporales
        Array<Integer> L = new Array<>(nL);
        Array<Integer> R = new Array<>(nR);

        // 3. Copiar datos a los arreglos temporales
        for (int i = 0; i < nL; i++){
            L.addArray(A.get(p + i));
        }

        for (int j = 0; j < nR; j++){
            R.addArray(A.get(q + j + 1));
        }

        // 4. Mezclar L y R de vuelta en A[p..r]
        int i = 0, j = 0,
                k = p; // Índice inicial para el arreglo mezclado

        while (i < nL && j < nR) {

            // Comparamos los elementos al frente de L y R
            if (L.get(i) <= R.get(j)) {
                A.set(k, L.get(i));
                i++;
                com++;
            } else {
                A.set(k, R.get(j));
                j++;
                com++;
            }
            k++; // Registro de operación
        }

        // 5. Copiar elementos restantes de L (si quedan)
        while (i < nL) {
            A.set(k, L.get(i));
            i++;
            k++;
            com++;
        }

        // 6. Copiar elementos restantes de R (si quedan)
        while (j < nR) {
            A.set(k, R.get(j));
            j++;
            k++;
            com++;
        }

    }


    /**
     * Método recursivo principal que divide el problema.
     *
     * <p>Divide el arreglo en mitades recursivamente hasta llegar al caso base
     * (cuando el sub-arreglo tiene 1 o 0 elementos), y luego llama a {@link #merge}
     * para ordenar las soluciones.</p>
     *
     * @param Arr El arreglo dinámico a ordenar.
     * @param p Índice de inicio (normalmente 0 en la primera llamada).
     * @param r Índice final (normalmente size-1 en la primera llamada).
     */
    static void mergeSort(Array<Integer> Arr, int p, int r){

        // 1 Condición base: Si p < r, hay más de un elemento para dividir.
        if (p < r){

            // Encuentra el punto medio para dividir el arreglo en dos mitades
            int q = (p + r) / 2;

            // Llamada recursiva para la primera mitad
            mergeSort(Arr, p, q);
            // Llamada recursiva para la segunda mitad
            mergeSort(Arr, q+1, r);
            // Conquista: Mezcla las dos mitades ordenadas
            merge(Arr, p, q, r);
        }
    }


    public static void main(String[] args) {

        Array<Integer> A = new Array<>(new Integer[] {2 , 3, 4, 2, -1, 0, 1, 3, 3, 4, 6, 7, 9});
        int p = 0;
        int r = A.size() - 1;
        MergeSort.mergeSort(A, p, r);
        System.out.println(A);
        System.out.println("# de comparaciones del arreglo: " + com);

    }
}
