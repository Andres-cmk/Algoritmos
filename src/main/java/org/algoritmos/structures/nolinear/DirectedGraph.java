package org.algoritmos.structures.nolinear;

import org.algoritmos.structures.linear.List;

public class DirectedGraph<T> {

    private final List<NodeDirectedGraph<T>> graph;

    public DirectedGraph() {
        this.graph = new List<>();
    }


    static class NodeDirectedGraph<T> {
        private final T data;
        private final List<NodeDirectedGraph<T>> neighbors;

        public NodeDirectedGraph(T data) {
            this.data = data;
            this.neighbors = new List<>();
        }

        public T getData() { return data; }

        public List<NodeDirectedGraph<T>> getNeighbors() { return neighbors; }

        // Método helper para conectar este nodo con otro
        public void addNeighbor(NodeDirectedGraph<T> neighbor) {
            this.neighbors.addArray(neighbor); // Usando tu método addArray
        }

        @Override
        public String toString() { return data.toString(); }
    }

    // --- MÉTODOS DEL GRAFO ---

    // Paso 1: Método auxiliar para buscar si un nodo ya existe en la bolsa
    private NodeDirectedGraph<T> findNode(T data) {

        for (NodeDirectedGraph<T> node : this.graph) {
            if (node.getData().equals(data)) {
                return node;
            }
        }
        return null; // No encontrado
    }

    // Paso 2: El método que asegura que el nodo exista. Si no, lo crea.
    public NodeDirectedGraph<T> addVertex(T data) {
        NodeDirectedGraph<T> existingNode = findNode(data);
        if (existingNode != null) {
            return existingNode; // Ya existía, devolvemos el original
        }

        // Si no existe, creamos uno nuevo y lo metemos a la bolsa maestra
        NodeDirectedGraph<T> newNode = new NodeDirectedGraph<>(data);
        this.graph.addArray(newNode);
        return newNode;
    }

    // Paso 3: Agregar la arista (La magia ocurre aquí)
    public void addEdge(T u, T v) {
        // 1. Recuperamos (o creamos) los nodos.
        // Esto garantiza que si A ya existe, usamos EL MISMO objeto A.
        NodeDirectedGraph<T> sourceNode = addVertex(u);
        NodeDirectedGraph<T> destNode = addVertex(v);

        // 2. Conectamos la flecha: Origen -> Destino
        sourceNode.addNeighbor(destNode);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < graph.size(); i++) {
            NodeDirectedGraph<T> node = graph.get(i);
            sb.append(" Node: ").append(node.getData()).append("->");
            sb.append("[");
            List<NodeDirectedGraph<T>> neighbors = node.getNeighbors();
            for (int j = 0; j < neighbors.size(); j++) {
                sb.append(neighbors.get(j).getData());

                if (j < neighbors.size() - 1) {
                    sb.append(", ");
                }

            }
            sb.append("]");
            if (i != graph.size() - 1) sb.append(",");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DirectedGraph<Integer> grafo = new DirectedGraph<>();

        // Al agregar (1, 2) crea los nodos 1 y 2
        grafo.addEdge(1, 2);

        // Al agregar (1, 3)
        grafo.addEdge(1, 3);

        grafo.addEdge(3, 5);

        System.out.println(grafo);
    }
}
