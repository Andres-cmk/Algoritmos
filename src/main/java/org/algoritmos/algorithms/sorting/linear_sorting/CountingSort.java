/**
 * @autor Andres Ramirez (Andres-cmk)
 */

/**
 *
 * Implementación del algoritmo Counting Sort (Ordenamiento por Conteo).
 *
 * <p>El <b>Counting Sort</b> es un algoritmo de ordenamiento <b>no basado en comparaciones</b>.
 * En lugar de comparar elementos (mayor/menor), cuenta la frecuencia de aparición de cada valor
 * y utiliza esa información para colocarlos directamente en su posición final.</p>
 *
 * <h3>Características Principales:</h3>
 * <ul>
 * <li><b>Complejidad Temporal:</b> O(n + k), donde 'n' es el número de elementos y 'k' es el rango de los valores (max).</li>
 * <li><b>Complejidad Espacial:</b> O(n + k). Requiere arrays auxiliares para el conteo y la salida.</li>
 * <li><b>Restricción:</b> Solo funciona con números enteros no negativos (o mapeables a índices enteros).
 * Es ineficiente si 'k' es mucho mayor que 'n' (ej: ordenar [1, 1000000] gastaría mucha memoria).</li>
 * <li><b>Estabilidad:</b> Sí. Preserva el orden relativo de elementos iguales (gracias al recorrido inverso final).</li>
 * </ul>
 *
 */

package org.algoritmos.algorithms.sorting.linear_sorting;

import org.algoritmos.structures.linear.Array;

public class CountingSort {

    /**
     * Ordena un arreglo de enteros utilizando el método de Conteo.
     *
     * @param array El arreglo dinámico a ordenar.
     * @param k El valor máximo contenido en el arreglo (define el rango 0..k).
     */
    public static Array<Integer> countingSort(Array<Integer> array, int k) {

        // Array auxiliar para construir la solución
        Array<Integer> sortedArray = new Array<>(array.size());

        // Array de frecuencias de tamaño k + 1
        Array<Integer> counter = new Array<>( k + 1);

        // Dado la estructura de datos se inicializa en null, seteamos todos los campos en 0's.
        for (int i = 0; i < k + 1; i++) {
            counter.set(i, 0);
        }


        // Calcular frecuencias: cuenta cuantás veces aparece cada numero y lo guardamos en el counter.
        for (Integer j : array) {
            counter.set(j, counter.get(j) + 1);
        }

        // 3. Suma Acumulativa (Prefix Sum):
        // Determina la posición final del último elemento de cada valor.
        // counter[i] contendrá cuántos elementos son menores o iguales a i.
        for (int s = 1; s < k + 1; s++) {
            counter.set(s, counter.get(s) + counter.get(s - 1));
        }


        // 4. Construir el arreglo ordenado (Recorrido Inverso para Estabilidad)
        // Se recorre desde el final para mantener el orden de elementos repetidos.
        for (int i = array.size() - 1; i > -1; i--) {
            // Busamos en 'counter' dónde debe ir este valor
            int value = array.get(i);

            sortedArray.set(counter.get(value) - 1, value);

            // Decrementamos el contador para que el siguiente duplicado vaya una posición antes
            counter.set(value, counter.get(value) - 1);
        }
        return sortedArray;
    }

    /**
     * Método auxiliar para encontrar el valor máximo en el arreglo.
     * Necesario para definir el tamaño del array de conteo 'k'.
     *
     * @param array El arreglo a analizar.
     * @return El valor entero más grande encontrado.
     */
    public static int getMax(Array<Integer> array) {

        if (array.size() <=1) return array.get(0);

        int max = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
        }
        return max;
    }

    // Metodo principal.

    public static void main(String[] args) {

        Array<Integer> array = new Array<>(new Integer[] {2,0,21,2,4,6,7,8});
        int k = CountingSort.getMax(array);
        System.out.println(CountingSort.countingSort(array, k));

    }

}
