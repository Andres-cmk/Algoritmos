/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.algorithms.random_algorithms;

import org.algoritmos.structures.linear.Array;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Implementación del algoritmo Randomized QuickSort.
 * <p>
 * Esta variante del QuickSort utiliza una selección aleatoria del pivote para
 * garantizar un rendimiento esperado de O(n log n) y minimizar la probabilidad
 * de caer en el peor caso O(n^2), que ocurre típicamente en arreglos ya ordenados
 * con el QuickSort determinista.
 */
public class RandomizedQuickSort {

    /**
     * Realiza la partición del sub-arreglo utilizando el esquema de Lomuto.
     * Coloca el pivote en su posición correcta de modo que los elementos a su
     * izquierda sean menores o iguales y los de su derecha sean mayores.
     *
     * @param arr El arreglo que contiene los elementos.
     * @param p   Índice inicial del sub-arreglo (low).
     * @param r   Índice final del sub-arreglo (high/pivot).
     * @return El índice final donde quedó ubicado el pivote.
     */
    private static int partition(Array<Integer> arr, int p, int r) {

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

            }

        }

        // Paso final: Colocar el pivote (que estaba en 'r') en su lugar correcto (i + 1)
        temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(r));
        arr.set(r, temp);
        // Retornamos la posición del pivote
        return i + 1;
    }

    /**
     * Prepara la partición eligiendo un pivote aleatorio.
     * Intercambia un elemento al azar dentro del rango [p, r] con el elemento en 'r',
     * y luego delega el trabajo al método partition estándar.
     *
     * @param A Arreglo de enteros.
     * @param p Índice inferior (low).
     * @param r Índice superior (high).
     * @return El índice donde quedó el pivote después de particionar.
     */
    private static int randomizedPartition(Array<Integer> A, int p, int r){
        // Generamos un índice aleatorio entre p y r (inclusive).
        int randomPivot = ThreadLocalRandom.current().nextInt(p, r);

        // Intercambiamos el elemento en 'r' con el elemento aleatorio elegido
        int temp = A.get(r);
        A.set(r, A.get(randomPivot));
        A.set(randomPivot, temp);

        // Llamamos a la partición estándar de Lomuto
        return partition(A, p, r);
    }

    /**
     * Método principal recursivo para ordenar el arreglo.
     * Divide el problema en sub-problemas más pequeños alrededor de un pivote aleatorio.
     *
     * <p>Complejidad Esperada: O(n log n)</p>
     * <p>Complejidad Peor Caso: O(n^2) (Extremadamente improbable)</p>
     *
     * @param A Arreglo de enteros a ordenar.
     * @param p Índice inicial del rango a ordenar.
     * @param r Índice final del rango a ordenar.
     */
    static void randomizedQuickSort(Array<Integer> A, int p, int r){
        if (p < r){
            // Obtenemos el índice de partición (q) usando un pivote aleatorio
            int q = randomizedPartition(A, p, r);
            // Ordenamos recursivamente la sub-lista izquierda (elementos menores al pivote)
            randomizedQuickSort(A, p, q - 1);
            // Ordenamos recursivamente la sub-lista derecha (elementos mayores al pivote)
            randomizedQuickSort(A, q + 1, r);
        }
    }

    public static void main(String[] args) {
        Array<Integer> A = new Array<>(new Integer[] {2 , 3, 4, 2, -1, 0, 1, 3, 3, 4, 6, 7, 9});
        int p = 0;
        int r = A.size() - 1;
        RandomizedQuickSort.randomizedQuickSort(A, p, r);
        System.out.println(A);
    }

}
