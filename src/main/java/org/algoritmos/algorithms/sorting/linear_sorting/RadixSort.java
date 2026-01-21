/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.algorithms.sorting.linear_sorting;

import org.algoritmos.structures.linear.Array;


/**
 * Implementación de Radix Sort (Ordenamiento por Residuos) optimizada para bits.
 *
 * <p>Utiliza operaciones a nivel de bits (bitwise) para procesar los enteros,
 * lo cual es mucho más rápido que trabajar con base 10 (módulo y división).</p>
 *
 * <h3>Complejidad:</h3>
 * <ul>
 * <li><b>Tiempo:</b> O(d * (n + 2^r)), donde 'd' es el número de pasadas (b/r).</li>
 * <li><b>Espacio:</b> O(n + 2^r).</li>
 * </ul>
 *
 */

public class RadixSort {

    /**
     * Ordena el arreglo usando Radix Sort con manipulación de bits.
     *
     * @param A El arreglo a ordenar (solo enteros positivos).
     * @param r Cantidad de bits a procesar por pasada (ej: 4, 8, 16).
     * - r=4: base 16 (16 cubetas).
     * - r=8: base 256 (256 cubetas) -> Recomendado estándar.
     */

    public static void radixSort(Array<Integer> A, int r) {

        // Asumimos enteros de 32 bits (estándar en Java)
        final int b = 32;

        // Calculamos cuántas pasadas necesitamos: tech(b / r)
        // Ejemplo: Si b=32 y r=8, son 4 pasadas.
        int pasadas = (b + r - 1) / r;

        // Pre-calculamos la máscara para aislar los bits.
        // Ejemplo: Si r=2, (1 << 2) - 1 = 3 (Binario 11)
        int mask = (1 << r) - 1;

        for (int i = 0; i < pasadas; i++) {

            // Calculamos el desplazamiento (shift)
            int shift = r * i;

            // Llamamos a la sub-rutina de ordenamiento estable
            countingSort(A, shift, r, mask);
        }

    }


    public static void countingSort(Array<Integer> A, int shift, int r, int mask) {

        int n = A.size();

        int base = 1 << r;

        // 1. Array de frecuencia (Cubetas). Tamaño = 2^r
        Array<Integer> count = new Array<>(base);

        // Llenamos de 0's el objeto count.
        for (int i = 0; i < count.getCapacity(); i++) {
            count.set(i, 0);
        }

        // 2. Array de salida temporal
        Array<Integer> temp = new Array<>(n);

        // Llenamos de 0's al objeto temp.
        for (int i = 0; i < temp.getCapacity(); i++) {
            temp.set(i, 0);
        }


        // Contamos las frecuencias
        for (int i = 0; i < n; i++) {
            int value = A.get(i);
            int digit = (value >> shift) & mask;
            count.set(digit, count.get(digit) + 1);
        }

        // Suma acumulativa.
        for (int i = 1; i < base; i++) {
            count.set(i, count.get(i) + count.get(i - 1));
        }


        // PASO C: Construir el arreglo de salida (recorrido inverso para estabilidad)
        for (int i = n - 1; i >= 0; i--) {

            int val = A.get(i);
            int digit = (val >> shift) & mask;

            // Colocamos el valor en su posición correcta
            int pos = count.get(digit) - 1;
            temp.set(pos, val);
            count.set(digit, count.get(digit) - 1);
        }


        // Copiar de vuelta al arreglo original
        for (int i = 0; i < n; i++) {
            A.set(i, temp.get(i));
        }

    }



    public static void main(String[] args) {

        Array<Integer> A = new Array<>(new Integer[]{170, 45, 75, 90, 802, 24, 2, 66});
        RadixSort.radixSort(A, 8);
        System.out.println(A);

    }
}