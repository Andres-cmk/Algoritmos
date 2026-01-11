# ☕ Java Data Structures & Algorithms

> **Implementación profunda de Estructuras de Datos y Algoritmos en Java.**

Este repositorio contiene implementaciones manuales desde cero de las estructuras de datos y algoritmos más fundamentales en las Ciencias de la Computación.
El objetivo es comprender la **gestión de memoria**, los **punteros**, la **recursividad** y la **complejidad algorítmica** ($O(n)$, $O(\log n)$, etc.) sin depender de las cajas negras de `java.util`.

---

## 🧠 Filosofía del Proyecto

* **Sin Cajas Negras:** Todo se construye byte a byte (Nodos, Referencias, Índices).
* **Genéricos:** Uso extensivo de Java Generics `<T>` para máxima reutilización.
* **Clean Code:** Arquitectura modular separando Estructuras (`structures`) de Algoritmos (`algorithms`).

---

## 📦 1. Estructuras de Datos (Data Structures)

Ubicación: `src/main/java/org/algoritmos/structures`

| Categoría | Estructura | Descripción |
| :--- | :--- | :--- |
| **Lineales** | `Array` | Arreglo dinámico redimensionable. |
| | `SingleLinkedList` | Lista enlazada simple con puntero `tail`. |
| | `DoublyLinkedList` | Lista doblemente enlazada (prev/next). |
| | `CircularDoublyLinkedList` | Lista circular donde el último conecta con el primero. |
| | `Stack` (Pila) | LIFO. Implementada vía composición. |
| | `Queue` (Cola) | FIFO. Optimizada para inserción $O(1)$. |
| **Árboles** | `GenericTree` | Árbol genérico n-ario. |
| | `CompleteBinaryTree` | Árbol binario completo. |
| | `FullBinaryTree` | Árbol binario lleno. |
| | `TernaryTree` | Árbol donde cada nodo tiene hasta 3 hijos. |
| **Mapas** | `HashTable` | Tabla hash con manejo de colisiones. |
| **Otros** | `Graph` | Estructura de grafos (Adjacency List/Matrix). |
| | `Heap` | Montículo binario (Min/Max). |
| | `PriorityQueue` | Colas de prioridad (Min y Max). |

---

## ⚡ 2. Algoritmos (Algorithms)

Ubicación: `src/main/java/org/algoritmos/algorithms`

| Categoría | Algoritmo | Descripción |
| :--- | :--- | :--- |
| **Sorting** | `Bubble`, `Insertion`, `Selection` | Algoritmos de ordenamiento elementales. |
| | `MergeSort`, `QuickSort` | Ordenamiento eficiente ($O(n \log n)$). |
| | `HeapSort` | Ordenamiento basado en estructura Heap. |
| | `Linear Sorting` | BucketSort, CountingSort, RadixSort. |
| **Búsqueda** | `BinarySearchTree` | Búsqueda binaria en estructuras de árbol. |
| **Divide & Conquer** | `MaximalSubArray` | Problema del subarreglo máximo. |
| | `MatrixMultiplication` | Multiplicación de matrices clásica. |
| | `Strassen` | Multiplicación optimizada de matrices. |
| **Dynamic Prog.** | **Shortest Path (All Pairs)** | Floyd-Warshall, Johnson. |
| | **Shortest Path (Single)** | Dijkstra, Bellman-Ford. |
| | **MST** | Kruskal, Prim (Minimum Spanning Tree). |
| | **Graph Traversal** | BFS (Anchura), DFS (Profundidad). |

---

## 📂 Estructura del Proyecto

```text
src/
├── main/
│   └── java/org/algoritmos/
│       ├── algorithms/
│       │   ├── binary_search/       # BST y búsquedas
│       │   ├── divide_conquer/      # MergeSort, QuickSort, Strassen...
│       │   ├── dynamic_programming/
│       │   │   ├── all_pairs_shortest_path/  # FloydWarshall, Johnson
│       │   │   ├── minium_spanning_tree/     # Kruskal, Prim
│       │   │   └── single_source_shortest_path/ # Dijkstra, BellmanFord
│       │   ├── random_algorithms/   # RandomQuickSort
│       │   └── sorting/
│       │       ├── linear_sorting/  # Bucket, Counting, Radix
│       │       └── ...              # HeapSort, BubbleSort, etc.
│       └── structures/
│           ├── linear/              # Listas, Pilas, Colas, Arrays
│           └── nolinear/
│               ├── map/             # HashTable
│               └── trees/           # Binarios, Genéricos, Terna, Heap...
└── test/
    └── java/                        # Tests unitarios (JUnit)
