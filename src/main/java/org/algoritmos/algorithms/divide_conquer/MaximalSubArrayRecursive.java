/**
 * @autor Andres Ramirez (Andres-cmk)
 *
 */

package org.algoritmos.algorithms.divide_conquer;

import org.algoritmos.structures.linear.Array;

// Hallar el la maxima suma de un subarreglo por dividir y conquistar.
public class MaximalSubArrayRecursive {


    public record Result(int low, int high, int sum){}

    // Su rol es encontrar la maxima suma en el centro donde se partio el arreglo.
    private static Result findMaxCrossingSubarray(Array<Integer> Arr, int low, int mid, int high){

        // suma desde el centro hacia la izquierda.
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = mid;


        for (int i = mid; i >= low; i--){
            sum += Arr.get(i);
            if (sum > leftSum){
                leftSum = sum;
                maxLeft = i; // Guardamos el indice donde logramos la mejor suma.
            }
        }

        // suma desde el centro hacia la derecha
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = mid + 1;
        for (int i = mid + 1; i <= high; i++){
            sum += Arr.get(i);
            if (sum > rightSum){
                rightSum = sum;
                maxRight = i; // guardamos el indice donde logramos la mejor suma.
            }
        }

        // Retornamos (índice inicio, índice fin, suma total combinada)
        return new Result(maxLeft, maxRight, leftSum + rightSum);
    }

    // Algoritmo principal
    public static Result maxSubArray(Array<Integer> A, int low, int high) {

            // caso base: si solo hay un elemento, ese es el maximo que encontramos
            if (low == high) return new Result(low, high, A.get(low));

            else {

                // Partimos el arreglo en 2
                int mid = (low + high) / 2;

                // Conquista Izquierda
                Result left = maxSubArray(A, low, mid);

                // Conquista derecha
                Result right = maxSubArray(A, mid + 1, high);

                // Conquista centro usando la función findMaxCrossingSubarray
                Result crossResult = findMaxCrossingSubarray(A, low, mid,  high);

                // 4. Comparar y retornar el ganador
                if (left.sum >= right.sum && left.sum >= crossResult.sum) return left;
                else if (right.sum >= left.sum && right.sum >= crossResult.sum) return crossResult;
                else return crossResult;
            }

    }

    public static void main(String[] args) {

        Array<Integer> A = new Array<>( new Integer[] {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7});
        Result ans = maxSubArray(A, 0, A.size() - 1);
        System.out.println("Suma resultante: " + ans.sum);

        Array<Integer> res = new Array<>(ans.high - ans.low + 1);

        for (int i = ans.low; i <= ans.high; i++){
            res.addArray(A.get(i));

        }

        System.out.println("Subarreglo: " + res);

    }
}
