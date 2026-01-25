/**
 * @autor Andres Ramirez (Andres-cmk)
 */

package org.algoritmos.structures.linear;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


// Arreglo Dinamico
public class List<T> implements Iterable<T>, RandomAccess {

    private Object[] list;
    private int size;

    public List(){
        this(10);
    }

    public List(int capacity){
        this.list = new Object[capacity];
        this.size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return this.size;
    }

    public int getCapacity() {
        return this.list.length;
    }

    public void addArray(T e){
        this.add(this.size, e);
    }

    public void set(int index, T e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        this.list[index] = e;
    }


    @SafeVarargs // Suprime advertencias de seguridad de tipos con genéricos
    public static <T> List<T> of(T... elements) {
        // 1. Creamos la lista con la capacidad exacta para no desperdiciar memoria
        List<T> newList = new List<>(elements.length);

        // 2. Recorremos los elementos que nos pasaron y los agregamos
        for (T e : elements) {
            newList.addArray(e);
        }

        return newList;
    }

    public void add(int index,T e){

        if(this.size == this.list.length){
            this.grow(); // Para arreglo dinamico
        }

        // Desplazamiento a la derecha
        for (int i = this.size - 1; i >= index; i--) {
            this.list[i + 1] = this.list[i];
        }
        this.list[index] = e;
        size++;
    }


    private void grow(){
        int oldCapacity = this.list.length;

        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity < oldCapacity + 1) {
            newCapacity = oldCapacity + 10; // Crecimiento mínimo por defecto
        }

        Object[] temp = new Object[newCapacity];
        System.arraycopy(this.list, 0, temp, 0, this.size);
        this.list = temp;
    }

    public void clear(){
        this.list = new Object[10];
        this.size = 0;
    }

    public boolean contains(T e){
        return this.indexOf(e) != -1;
    }

    public int indexOf(T e){
        if(this.size == 0){
            return -1;
        }

        for (int i = 0; i < this.size; i++) {
            if(this.list[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public T get(int index){
        return (T)this.list[index];
    }

    public void remove(int index) {
        // Validación de índice recomendada
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // Desplazamiento a la izquierda
        for (int i = index + 1; i < this.size; i++) {
            this.list[i - 1] = this.list[i];
        }

        size--;             // 1. Primero reducimos el tamaño
        this.list[size] = null; // 2. Luego limpiamos esa posición que quedó "sobrando"
    }


    public boolean remove(T e){
        int indexElement = this.indexOf(e);
        if(indexElement != -1){
            this.remove(indexElement);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        int size = this.size;
        Object[] array = this.list;
        return new Iterator<>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }
            @Override
            public T next() {
                return (T) array[i++];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++){
            sb.append(list[i]);
            if (i != size - 1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // =================================================================
    //                 FUNCTIONAL API (ESTILO STREAM)
    // =================================================================


    /**
     * FILTER: Filtra elementos según una condición.
     * Equivalente a: Stream.filter()
     * @param p Condición que debe cumplir el elemento (devuelve true/false).
     * @return Una NUEVA lista solo con los elementos que pasaron la prueba.
     */
    public List<T> filter(Predicate<T> p){

        List<T> result = new List<T>();
        for (int i = 0; i < this.size; i++){
            T e = (T)list[i];
            if (p.test(e)){
                result.addArray(e);
            }
        }
        return result;
    }

    /**
     * MAP: Transforma cada elemento en otro tipo de dato.
     * Equivalente a: Stream.map()
     * * @param mapper Función que convierte de T (tipo actual) a R (nuevo tipo).
     * @param <R> El tipo de dato de la nueva lista.
     * @return Una NUEVA lista de tipo <R>.
     */
    public <R> List<R> map(Function<T,R> f){

        List<R> result = new List<R>();
        for (int i = 0; i < this.size; i++){
            R transformed = f.apply((T)list[i]);
            result.addArray(transformed);
        }
        return result;
    }


    /**
     * REDUCE: Combina todos los elementos en un solo resultado.
     * Equivalente a: Stream.reduce()
     * * @param identity Valor inicial (ej. 0 para sumas, "" para textos).
     * @param accumulator Función que combina dos valores (el acumulado y el actual).
     * @return El resultado final acumulado.
     */
    public T reduce(T i, BinaryOperator<T> accumulator){
        T result = i;
        for (int j = 0; j < this.size(); j++){
            result = accumulator.apply(result, (T) list[j]);
        }
        return result;
    }

    /**
     * Es una operación terminal que dado un conjunto de elemento cumpla con la condición dado.
     * @param p Condición que debe cumplir el elemento (devuelve true/false).
     * @return true si al menos un elemento cumple con la condición dada, en otro caso
     * retorna false.
     */
    public boolean anyMatch(Predicate<T> p){
        boolean  result = false;
        for (int i = 0; i < this.size; i++){
            result |= p.test((T)list[i]);
        }
        return  result;
    }

    /**
     * Metodo que evalua si todos los elementos del conjunto cumplen con la condición dada por el
     * predicate.
     *
     * @param p Condición que debe cumplir el elemento (devuelve true/false).
     * @return true si todos los elementos del conjunto cumplen con la condición, en otro caso
     * retorna false.
     */
    public boolean allMatch(Predicate<T> p){
        for (int i = 0; i < this.size; i++){
            if(!p.test((T)list[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * Devuelve una secuencia que consta de los elementos. El metodo trunca para que
     * los elementos no supere maxSize.
     * @param maxSize El numero de elementos a truncar.
     * @return Retorna el conjunto de elementos truncados. El caso donde el maxSize supere a this.size,
     * entonces devuelve todos los elementos del conjunto en cuestión.
     */

    public List<T> limit(int maxSize){
        int actualInt = (maxSize < this.size()) ? maxSize : this.size;
        List<T> newList = new List<>(actualInt);
        for (int i = 0; i < actualInt; i++){
            newList.addArray(this.get(i));
        }

        if (actualInt == maxSize) System.out.println("Se devolvio todos los elementos del arreglo.");

        return newList;
    }

    /**
     *
     * Metodo para eliminar elementos del conunto que se encuentran 2 o mas veces en el mismo conjunto.
     * @return Retorna un nuevo conjunto pero sin elemento duplicados.
     */
    public List<T> distinct (){
        List<T> uniqueList = new List<>();
        for (int i = 0; i < this.size; i++){
            T item =  (T) list[i];
            if (!uniqueList.contains(item)){
                uniqueList.addArray(item);
            }
        }
        return uniqueList;
    }


    /**
     * Retorna una nueva lista que contiene una porción de la original.
     * Funciona como el slicing de Python: [fromIndex, toIndex).
     *
     * @param fromIndex Índice inicial (inclusivo).
     * @param toIndex Índice final (exclusivo).
     * @return Una nueva lista con los elementos copiados.
     * @throws IndexOutOfBoundsException si los índices son inválidos.
     */

    public List<T> subList(int fromIndex, int toIndex){

        if (fromIndex < 0) throw new IndexOutOfBoundsException("From index: " + fromIndex);
        if (toIndex > this.size()) throw new IndexOutOfBoundsException("toIndex: " + toIndex + " es mayor al tamaño: " + this.size);
        if (fromIndex > toIndex) throw new IllegalArgumentException("fromIndex(" + fromIndex + ") no puede ser mayor que toIndex(" + toIndex + ")");

        int newSize = toIndex - fromIndex;
        if (newSize < 1) return new List<>(0);

        List<T> result = new List<>(newSize);
        for (int i = fromIndex; i < toIndex; i++){
            result.addArray((T) this.list[i]);
        }

        return result;
    }


    // Clase especifica para trabajar con matrices
    // Util cuando se va a trabajar con algunos algoritmos como Strassen.
    static class MatrixUtils {
        /**
         * Extrae una sub-matriz de una matriz dada.
         * @param matrix La matriz original (List de List).
         * @param r1 Fila inicial (inclusive)
         * @param r2 Fila final (exclusive)
         * @param c1 Columna inicial (inclusive)
         * @param c2 Columna final (exclusive)
         * @return Una nueva matriz recortada.
         */
        public static <T> List<List<T>> subMatrix(List<List<T>> matrix, int r1, int r2, int c1, int c2) {
            // 1. Primero obtenemos las filas deseadas
            List<List<T>> rows = matrix.subList(r1, r2);

            // 2. A cada fila, la recortamos para quedarnos con las columnas
            return rows.map(row -> row.subList(c1, c2));
        }
    }

}
