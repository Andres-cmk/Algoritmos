/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.algorithms.divide_conquer;

import org.algoritmos.structures.linear.List;
import org.algoritmos.structures.linear.List.MatrixUtils;


public class Strassen {

    // Contenedor para devolver 4 matrices al tiempo
    public record Container <A, B, C, D> (A first, B second, C third, D fourth) {}

    // -------------------------------------------------------------------------
    // 1. MÉTODOS DE PADDING (La clave para que funcione con 3x3)
    // -------------------------------------------------------------------------


    // Calcula la siguiente potencia de 2 (ej: si n=3 devuelve 4)
    private static int nextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) {
            power = power << 1;
        }
        return power;
    }

    // Rellena la matriz con ceros hasta llegar al nuevo tamaño (newSize)
    private static List<List<Double>> padMatrix(List<List<Double>> matrix, int newSize) {

        List<List<Double>> padded = new List<>(newSize);
        int originalSize = matrix.size();

        for (int i = 0; i < newSize; i++) {
            List<Double> row = new List<>(newSize);
            for (int j = 0; j < newSize; j++) {
                if (i < originalSize && j < originalSize) {
                    row.addArray(matrix.get(i).get(j)); // Copiar valor original
                } else {
                    row.addArray(0.0); // Rellenar con cero
                }
            }
            padded.addArray(row);
        }
        return padded;
    }


    // -------------------------------------------------------------------------
    // 2. OPERACIONES BÁSICAS (Suma, Resta, Unión)
    // -------------------------------------------------------------------------

    private static List<List<Double>> sumMatrix(List<List<Double>> A,  List<List<Double>> B) {

        List<List<Double>> result = new List<>();

        int n = A.size();

        for (int i = 0; i < n; i++) {
            result.addArray(new List<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result.get(i).addArray(A.get(i).get(j) + B.get(i).get(j));
            }
        }

        return result;

    }

    private static List<List<Double>> subtractionMatrix(List<List<Double>> A,  List<List<Double>> B) {


        List<List<Double>> result = new List<>();

        int n = A.size();

        for (int i = 0; i < n; i++) {
            result.addArray(new List<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result.get(i).addArray(A.get(i).get(j) - B.get(i).get(j));
            }
        }

        return result;

    }


    private static Container< List<List<Double>>,List<List<Double>>, List<List<Double>>, List<List<Double>> > splitMatrix(List<List<Double>> matrix) {


        int n =  matrix.size();
        int mid = n / 2;

        List<List<Double>> A11 = MatrixUtils.subMatrix(matrix, 0, mid, 0, mid);
        List<List<Double>> A12 = MatrixUtils.subMatrix(matrix, 0, mid, mid, n);
        List<List<Double>> A21 = MatrixUtils.subMatrix(matrix, mid, n, 0, mid);
        List<List<Double>> A22 = MatrixUtils.subMatrix(matrix, mid, n, mid, n);

        return new Container<>(A11, A12, A21, A22);
    }


    public static List<List<Double>> unionMatrix(List<List<Double>> C11, List<List<Double>> C12, List<List<Double>> C21, List<List<Double>> C22) {
        int n = C11.size();
        List<List<Double>> result = new List<>(n * 2);

        // Top (C11 + C12)
        for (int i = 0; i < n; i++) {
            List<Double> row = new List<>();
            for(int k=0; k<C11.get(i).size(); k++) row.addArray(C11.get(i).get(k));
            for(int k=0; k<C12.get(i).size(); k++) row.addArray(C12.get(i).get(k));
            result.addArray(row);
        }
        // Bottom (C21 + C22)
        for (int i = 0; i < n; i++) {
            List<Double> row = new List<>();
            for(int k=0; k<C21.get(i).size(); k++) row.addArray(C21.get(i).get(k));
            for(int k=0; k<C22.get(i).size(); k++) row.addArray(C22.get(i).get(k));
            result.addArray(row);
        }
        return result;
    }

    // -------------------------------------------------------------------------
    // 3. ALGORITMO STRASSEN (Wrapper y Recursivo)
    // -------------------------------------------------------------------------

    public static List<List<Double>> strassenRecursive( List<List<Double>> A, List<List<Double>> B) {

        int n = A.size();

        if (n == 1) return List.of(List.of(A.get(0).get(0) * B.get(0).get(0)));

        var resultA = splitMatrix(A);
        var resultB = splitMatrix(B);

        List<List<Double>> A11 = resultA.first, A12 = resultA.second, A21 = resultA.third, A22 =  resultA.fourth;
        List<List<Double>> B11 = resultB.first, B12 = resultB.second, B21 = resultB.third, B22 = resultB.fourth;

        List<List<Double>> S1, S2, S3, S4, S5, S6, S7, S8, S9, S10;

        S1 = subtractionMatrix(B12, B22);
        S2 = sumMatrix(A11, A12);
        S3 = sumMatrix(A21, A22);
        S4 = subtractionMatrix(B21, B11);
        S5 = sumMatrix(A11, A22);
        S6 = sumMatrix(B11, B22);
        S7 = subtractionMatrix(A12, A22);
        S8 = sumMatrix(B21, B22);
        S9 = subtractionMatrix(A11, A21);
        S10 = sumMatrix(B11, B12);

        List<List<Double>> P1, P2, P3, P4, P5, P6, P7;

        P1 = strassenRecursive(A11, S1);
        P2 = strassenRecursive(S2, B22);
        P3 = strassenRecursive(S3, B11);
        P4 = strassenRecursive(A22, S4);
        P5 = strassenRecursive(S5, S6);
        P6 = strassenRecursive(S7, S8);
        P7 = strassenRecursive(S9, S10);

        List<List<Double>> C11, C12, C21, C22;

        assert P1 != null;
        assert P2 != null;
        assert P3 != null;
        assert P4 != null;
        assert P5 != null;
        assert P6 != null;
        assert P7 != null;

        C11 = sumMatrix(subtractionMatrix(sumMatrix(P5, P4), P2), P6);
        C12 = sumMatrix(P1,P2);
        C21 = sumMatrix(P3, P4);
        C22 = subtractionMatrix(subtractionMatrix(sumMatrix(P5, P1), P3), P7);

        return unionMatrix(C11, C12, C21, C22);
    }


    public static List<List<Double>> strassenCheck(List<List<Double>> A, List<List<Double>> B) {

        int rowsA = A.size(); int colsA = A.get(0).size();
        int rowsB = B.size(); int colsB = B.get(0).size();

        // revisamos el caso donde las matrices son nxn
        if ((rowsA == colsA) && (rowsB == colsB)) return strassenRecursive(A, B);

        // revisamos si las matrices cumplen las condiciones para la multiplicación
        if (colsA != rowsB) throw new IllegalArgumentException("Las dimensiones de las matrices no soportan multiplicación");

        // Si no se cumple de las otras condiciones.
        // ejecutamos el algoritmo.
        int maxSize = Math.max(Math.max(rowsA, colsA), Math.max(rowsB, colsB));
        int m = nextPowerOfTwo(maxSize);

        List<List<Double>> APadded = padMatrix(A, m);
        List<List<Double>> BPadded = padMatrix(B, m);

        List<List<Double>> C = strassenRecursive(APadded, BPadded);

        return MatrixUtils.subMatrix(C, 0, rowsA, 0, colsB);

    }

    public static void main(String[] args) {
        List<List<Double>> A = List.of(
                List.of(1.0, 1.0, 1.0, 2.3),
                List.of(2.0, 2.0, 2.0, 2.3),
                List.of(3.0, 3.0, 3.0, 3.1),
                List.of(4.0, 4.0, 4.0, 2.1)
        );

        List<List<Double>> B = List.of(
                List.of(1.0, 0.0, 0.0, 9.0),
                List.of(0.0, 1.0, 0.0, 8.1),
                List.of(0.0, 0.0, 1.0, 5.5),
                List.of(0.0, 0.0, 1.0, 1.2)
        );

        List<List<Double>> C = Strassen.strassenCheck(A, B);
        C.forEach(System.out::println);
    }
}
