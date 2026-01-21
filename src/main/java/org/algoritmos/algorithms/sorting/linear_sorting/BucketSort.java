/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.algorithms.sorting.linear_sorting;

import org.algoritmos.structures.linear.Array;
import org.algoritmos.structures.linear.List;

/**
 * Implementación del algoritmo Bucket Sort (Ordenamiento por Cubetas).
 *
 * <p>Bucket Sort es un algoritmo de ordenamiento por distribución que divide
 * los elementos en varias "cubetas" (buckets) para luego ordenar cada cubeta
 * individualmente, generalmente usando otro algoritmo (en este caso, Insertion Sort).</p>
 *
 * <h3>Requisitos de Entrada:</h3>
 * <ul>
 * <li>Funciona de manera óptima cuando los elementos están distribuidos uniformemente.</li>
 * <li>Esta implementación asume valores en el rango <b>[0, 1)</b> (tipo Double).</li>
 * </ul>
 *
 * <h3>Complejidad:</h3>
 * <ul>
 * <li><b>Promedio:</b> O(n + k), donde n es el número de elementos y k el de cubetas.</li>
 * <li><b>Peor caso:</b> O(n^2), si todos los elementos caen en la misma cubeta.</li>
 * </ul>
 */

public class BucketSort {

    /**
     * Ordena una lista individual (cubeta) utilizando Insertion Sort.
     * <p>Se utiliza Insertion Sort porque es eficiente para conjuntos de datos pequeños,
     * lo cual es ideal para las cubetas individuales.</p>
     *
     * @param A Lista de números Double a ordenar.
     */

    private static void insertionSort(List<Double> A){

        if (A.isEmpty()) return;

        for (int i = 0; i < A.size(); i++) {

            double key = A.get(i);
            int j = i - 1;

            while (j >= 0 && A.get(j) > key) {

                A.set(j + 1, A.get(j));
                j--;
            }

            A.set(j + 1, key);
        }
    }

    /**
     * Método principal de ordenamiento Bucket Sort.
     *
     * @param A La lista de entrada con números decimales entre 0.0 y 1.0.
     * Nota: La lista original será modificada y quedará ordenada.
     */

    public static void bucketSort(List<Double> A){

        int n = A.size();


        // 1. CREACIÓN DE CUBETAS
        // Creamos un arreglo de N listas. Usamos 'Array' como contenedor fijo
        // y 'List' adentro porque las cubetas crecen dinámicamente.
        Array<List<Double>> buckets = new Array<>(n);

        for (int i = 0; i < n; i++){
            buckets.addArray(new List<>());
        }

        // 2. SCATTER (Distribución)
        // Repartimos los elementos en las cubetas según su valor.
        // Fórmula: index = valor * n
        for (Double d : A){
            int bi = (int)(d*n);
            buckets.get(bi).addArray(d);
        }

        // 3. ORDENAMIENTO INTERNO
        // Ordenamos cada cubeta individualmente con Insertion Sort
        for (List<Double> list : buckets){
            insertionSort(list);
        }

        // Limpiamos la lista original y concatenamos las cubetas ordenadas
        A.clear();

        for (List<Double> list : buckets){
            for (double value : list){
                A.addArray(value); // Agregamos al final (append)
            }
        }


    }

    public static void main(String[] args) {
        List<Double> list = List.of(0.78, 0.17, 0.18, 0.19, 0.20, 0.21, 0.1);

        BucketSort.bucketSort(list);
        System.out.println(list);

    }
}
