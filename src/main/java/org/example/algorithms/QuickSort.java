// Mande by Andres-cmk
package org.example.algorithms;

import org.example.data_structures.DSLineales.Array;


public class QuickSort {

    public int partition(Array A, int p, int r){
        int x = (int) A.get(r);
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if ((int)A.get(j) <= x) {
                i++;
                int temp = (int) A.get(i);
                A.set(i, A.get(j));
                A.set(j, temp);
            }
        }
        int temp = (int)A.get(i + 1);
        A.set(i + 1, A.get(r));
        A.set(r, temp);
        return i + 1;
    }

    public void quickSort(Array A, int p, int r){
        if (p < r){
            int q = partition(A, p, r);
            quickSort(A, p, q-1);
            quickSort(A, q+1, r);
        }
    }

    public static void main(String[] args) {
        Array<Integer> A = new Array<>();
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
        QuickSort qs = new QuickSort();
        qs.quickSort(A, p, r);
        A.print();
    }
}
