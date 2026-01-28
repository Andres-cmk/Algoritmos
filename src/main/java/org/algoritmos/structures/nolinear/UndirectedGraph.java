package org.algoritmos.structures.nolinear;

import org.algoritmos.structures.linear.List;

public class UndirectedGraph<T> {

    List<NodeUndirectedGraph<T>> graph;

    public UndirectedGraph() {
        this.graph = new List<>();
    }

    static class NodeUndirectedGraph<T>{
        private final T data;
        private final List<NodeUndirectedGraph<T>> neighbors;

        public NodeUndirectedGraph(T data) {
            this.data = data;
            this.neighbors = new List<>();

        }

        public T getData() {
            return data;
        }

        public List<NodeUndirectedGraph<T>> getNeighbors() {
            return neighbors;
        }

        public void addNeighbor(NodeUndirectedGraph<T> neighbor) {
            this.neighbors.addArray(neighbor);
        }

        @Override
        public String toString() { return data.toString(); }
    }

    // Metodos del grafo


    private NodeUndirectedGraph<T> findNode(T data) {

        for (NodeUndirectedGraph<T> node : this.graph) {
            if (node.getData().equals(data)) {
                return node;
            }
        }
        return null; // No encontrado
    }

    public NodeUndirectedGraph<T> addVertex(T data) {
        NodeUndirectedGraph<T> existingNode = findNode(data);
        if (existingNode != null) {
            return existingNode; // Ya existía, devolvemos el original
        }

        // Si no existe, creamos uno nuevo y lo metemos a la bolsa maestra
        NodeUndirectedGraph<T> newNode = new NodeUndirectedGraph<>(data);
        this.graph.addArray(newNode);
        return newNode;
    }

    public void addEdge(T u, T v) {

        NodeUndirectedGraph<T> sourceNode = addVertex(u);
        NodeUndirectedGraph<T> destNode = addVertex(v);

        // 2. Conectamos la flecha: Origen -> Destino
        sourceNode.addNeighbor(destNode);
        destNode.addNeighbor(sourceNode); // unico cambio con respecto a DirectedGraph
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < graph.size(); i++) {
            NodeUndirectedGraph<T> node = graph.get(i);
            sb.append(" Node: ").append(node.getData()).append("->");
            sb.append("[");
            List<NodeUndirectedGraph<T>> neighbors = node.getNeighbors();
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

        UndirectedGraph<Integer> grafo = new UndirectedGraph<>();

        grafo.addEdge(1, 2);

        // Al agregar (1, 3)
        grafo.addEdge(1, 3);

        grafo.addEdge(3, 5);

        System.out.println(grafo);

    }
}
