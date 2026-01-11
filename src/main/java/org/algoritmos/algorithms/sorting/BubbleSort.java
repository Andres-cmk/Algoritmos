/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.algorithms.sorting;

import org.algoritmos.structures.linear.Array;

// Versión optimizada


/**
 * Implementación del algoritmo de Ordenamiento Burbuja (Bubble Sort).
 *
 * <p>El <b>Bubble Sort</b> es un algoritmo de ordenamiento sencillo que funciona revisando
 * repetidamente cada elemento de la lista que debe ordenarse, comparándolo con el siguiente,
 * e intercambiándolos de posición si están en el orden equivocado. Este proceso se repite
 * hasta que no se necesiten más intercambios.</p>
 *
 * <p>El nombre proviene de la forma en que los elementos más grandes "burbujean" hacia
 * el final de la lista con cada iteración.</p>
 *
 * <h3>Análisis de Complejidad:</h3>
 * <ul>
 * <li><b>Peor Caso (O(n^2)):</b> Ocurre si el arreglo está ordenado inversamente.</li>
 * <li><b>Promedio (O(n^2)):</b> Ocurre en arreglos con orden aleatorio.</li>
 * <li><b>Mejor Caso (O(n)):</b> Gracias a la optimización de la bandera {@code swapped},
 * si el arreglo ya está ordenado, el algoritmo solo hace una pasada y termina.
 * (Sin esta bandera, el mejor caso sería también O(n^2)).</li>
 * <li><b>Espacio (O(1)):</b> Es un algoritmo "in-place".</li>
 * </ul>
 *
 */
public class BubbleSort {


    /**
     * Ordena un arreglo de enteros utilizando el método de Burbuja Optimizado.
     *
     * <p>Utiliza una bandera {@code swapped} para detectar si en una iteración completa
     * no hubo intercambios. Si esto ocurre, significa que el arreglo ya está ordenado
     * y el algoritmo termina prematuramente para ahorrar tiempo.</p>
     *
     * @param A El arreglo dinámico de enteros a ordenar. Se modifica directamente.
     */
    public static void compare( Array<Integer> A){

        boolean swapped;

        // Bucle externo: Controla las pasadas.
        for (int i = 0; i < A.size(); i++) {

            swapped = false;

            // Bucle interno: Comparación e intercambio de adyacentes.
            // Se resta 'i' porque los últimos 'i' elementos ya están en su posición final (burbujearon).
            for (int j = 0; j < A.size() - i - 1; j++) {

                if(A.get(j) > A.get(j+1)) {

                    // Intercambio (Swap)
                    int temp = A.get(j);
                    A.set(j, A.get(j+1));
                    A.set(j+1, temp);
                    swapped = true; // Marcamos que hubo al menos un cambio
                }
            }

            // OPTIMIZACIÓN: Si no hubo intercambios en esta pasada, el arreglo ya está ordenado.
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Array<Integer> A = new Array<>(new Integer[]{5,2,67,9,2,1,-7});
        BubbleSort.compare(A);
        System.out.println(A);
    }
}
