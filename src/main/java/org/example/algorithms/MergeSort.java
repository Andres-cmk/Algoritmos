// Mande by Andres-cmk
package org.example.algorithms;

import org.example.data_structures.DSLineales.Array;

public class MergeSort {

    public void merge(Array A, int p, int q, int r) {

        int nL = q - p + 1;
        int nR = r - q;

        Array<Integer> L = new Array<>();
        Array<Integer> R = new Array<>();

        for (int i = 0; i < nL; i++) {
            L.add( (int) A.get(p + i));
        }

        for (int j = 0; j < nR; j++) {
            R.add( (int) A.get(q + j + 1));
        }

        int i = 0, j = 0, k = p;

        while (i < nL && j < nR) {
            if (L.get(i) <= R.get(j)) {
                A.set(k, L.get(i));
                i++;
            }else {
                A.set(k, R.get(j));
                j++;
            }
            k++;
        }

        while (i < nL) {
            A.set(k, L.get(i));
            i++;
            k++;
        }
        while (j < nR) {
            A.set(k, R.get(j));
            j++;
            k++;
        }

    }



    public void mergeSort(Array A, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(A, p, q);
            mergeSort(A, q+1, r);
            merge(A, p, q, r);
        }
    }

    public static void main(String[] args) {
        Array A = new Array();
        A.add(2);
        A.add(8);
        A.add(7);
        A.add(1);
        A.add(3);
        A.add(5);
        A.add(6);
        A.add(4);
        int p = 0;
        int r = A.size() - 1;
        MergeSort ms = new MergeSort();
        ms.mergeSort(A, p, r);
        A.print();
    }
}
