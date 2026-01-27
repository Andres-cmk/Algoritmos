/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.algorithms.divide_conquer;

// Multiplicación de matrices pero sin algoritmo de Strassen.
// Esto utiliza la filosofia de dividir y conquistar.
import org.algoritmos.structures.linear.List;
import static org.algoritmos.structures.linear.List.MatrixUtils;


public class MaxtrixMultiplication {

    private static int nextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) {
            power = power << 1;
        }
        return power;
    }

    private static List<List<Double>> paddMatrix(List<List<Double>> matrix, int newSize) {

        List<List<Double>> paddedMatrix = new List<>();
        int sizeOld =  matrix.size();

        for (int i = 0; i < newSize; i++) {
            List<Double> row = new List<>();
            for (int j = 0; j < newSize; j++) {
                if ( i < sizeOld && j <  sizeOld) {
                    row.addArray(matrix.get(i).get(j));
                } else {
                    row.addArray(0.0);
                }

            }
            paddedMatrix.addArray(row);
        }

        return paddedMatrix;

    }

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

    private static List<List<Double>> unionMatrix(List<List<Double>> C11, List<List<Double>> C12, List<List<Double>> C21, List<List<Double>> C22) {
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

    public static List<List<Double>> checkMatrix(List<List<Double>> A, List<List<Double>> B) {
        int rowsA = A.size(); int colsA = A.get(0).size();
        int rowsB = B.size(); int colsB = B.get(0).size();


        // Revisamos el caso donde las matrices son nxn
        if ((rowsA == colsA) && (rowsB == colsB)) return matrixMultiplyRecursive(A, B);


        // Revisamos las dimensiones para multiplicaciones.
        if (colsA != rowsB) throw new IllegalArgumentException("Las dimensiones de las matrices no soportan multiplicación");

        // Si no se cumple de las otras condiciones.
        // ejecutamos el algoritmo.

        int maxSize = Math.max(Math.max(rowsA, colsA), Math.max(rowsB, colsB));
        int m = nextPowerOfTwo(maxSize);

        List<List<Double>> APadded = paddMatrix(A, m);
        List<List<Double>> BPadded = paddMatrix(B, m);

        List<List<Double>> C = matrixMultiplyRecursive(APadded, BPadded);

        return MatrixUtils.subMatrix(C, 0, rowsA, 0, colsB);
    }


    public static List<List<Double>> matrixMultiplyRecursive(List<List<Double>> A, List<List<Double>> B) {

        int n = A.size();

        if (n == 1) return List.of(List.of(A.get(0).get(0) * B.get(0).get(0)));

        int mid = n / 2;

        List<List<Double>> A11 = MatrixUtils.subMatrix(A, 0, mid, 0, mid);
        List<List<Double>> A12 = MatrixUtils.subMatrix(A, 0, mid, mid, n);
        List<List<Double>> A21 = MatrixUtils.subMatrix(A, mid, n, 0, mid);
        List<List<Double>> A22 = MatrixUtils.subMatrix(A, mid, n, mid, n);

        List<List<Double>> B11 = MatrixUtils.subMatrix(B, 0, mid, 0, mid);
        List<List<Double>> B12 = MatrixUtils.subMatrix(B, 0, mid, mid, n);
        List<List<Double>> B21 = MatrixUtils.subMatrix(B, mid, n, 0, mid);
        List<List<Double>> B22 = MatrixUtils.subMatrix(B, mid, n, mid, n);


        // Para C11
        List<List<Double>> m1 = matrixMultiplyRecursive(A11, B11);
        List<List<Double>> m2 = matrixMultiplyRecursive(A12, B21);
        List<List<Double>> C11 = sumMatrix(m1, m2);

        // Para C12
        List<List<Double>> m3 = matrixMultiplyRecursive(A11, B12);
        List<List<Double>> m4 = matrixMultiplyRecursive(A12, B22);
        List<List<Double>> C12 = sumMatrix(m3, m4);

        // Para C21
        List<List<Double>> m5 = matrixMultiplyRecursive(A21, B11);
        List<List<Double>> m6 = matrixMultiplyRecursive(A22, B21);
        List<List<Double>> C21 = sumMatrix(m5, m6);

        // Para C22
        List<List<Double>> m7 = matrixMultiplyRecursive(A21, B12);
        List<List<Double>> m8 = matrixMultiplyRecursive(A22, B22);
        List<List<Double>> C22 = sumMatrix(m7, m8);


        return unionMatrix(C11, C12, C21, C22);


    }

    public static void main(String[] args) {

        // 3 x 4
        List<List<Double>> A = List.of(
                List.of(1.0, 1.0, 1.0, 0.5),
                List.of(2.0, 2.0, 2.0, 3.2),
                List.of(3.0, 3.0, 3.0, 3.1)
        );

        // 4 x 4
        List<List<Double>> B = List.of(
                List.of(1.0, 0.0, 0.0, 9.0),
                List.of(0.0, 1.0, 0.0, 8.1),
                List.of(0.0, 0.0, 1.0, 5.5),
                List.of(0.0, 0.0, 1.0, 1.2)
        );



        List<List<Double>> C = checkMatrix(A, B);
        C.forEach(System.out::println);

    }

}
