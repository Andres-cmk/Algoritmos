    /**
     * @autor Andres Ramirez (Andres-cmk)
     *
     */
    package org.algoritmos.structures.nolinear;

    import org.algoritmos.structures.linear.List;

    public class MaxPriorityQueue<T extends Comparable<T>> extends Heap<T> {

        public MaxPriorityQueue(List<T> list) {
            super(list);
            buildMaxHeap();
        }

        public int sizeMaxPriorityQueue() {
            return size();
        }

        public boolean isEmptyMaxPriorityQueue() {
            return isEmpty();
        }

        @Override
        public T peek() {
            return super.peek();
        }

        public void insert(T item) {
            insertMaxHeap(item);
        }

        public T removeMax() {
            return extractMaxElement();
        }

        public T extractElement(T key){
            return extractMaxElement();
        }


        @Override
        public String toString() {
            return heap.toString();
        }


        public static void main(String[] args) {
            List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
            MaxPriorityQueue<Integer> max = new MaxPriorityQueue<>(list);
            System.out.println(max);

        }

    }
