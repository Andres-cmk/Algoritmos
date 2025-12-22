package org.example.data_structures.Trees;

public class BinarySearchTree {

    static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    TreeNode root;

    // camino para imprimir el arbol ordenado
    public void inorderTreeWalk(TreeNode x){
        if (x != null){
            inorderTreeWalk(x.left);
            System.out.print(x.data + " ");
            inorderTreeWalk(x.right);
        }
    }

    public void preorderTreeWalk(TreeNode x){
        if (x != null){
            System.out.print(x.data + " ");
            preorderTreeWalk(x.left);
            preorderTreeWalk(x.right);
        }
    }

    public TreeNode treeSearch(TreeNode x, int k){
        if (x == null || k == x.data){
            return x;
        }
        if (k < x.data){
            return treeSearch(x.left, k);
        }
        else return treeSearch(x.right, k);
    }


    public TreeNode treeMinimum(TreeNode x){
        while (x.left != null){
            x = x.left;
        }
        return x;
    }

    public TreeNode treeMaximum(TreeNode x){
        while (x.right != null){
            x = x.right;
        }
        return x;
    }

    // sucesor y predesesor en el arbol

    /*
    Para este metodo se tiene 2 casos
    1. Si el Node x tiene hijos derechos, continuamos recorriendo el sub-arbol y retirnar el data el menor numero mayor que el Node x
    2. Si el Node x no tiene hijo derecho, entonces el sucesor sera el Nodo y mas grande catalogado como padre.
     */

    
    public TreeNode treeSuccessor(TreeNode x){

        if (x.right != null){
            return treeMinimum(x.right);
        }
        TreeNode y = x.parent;
        while (y != null && x == y.right){
            x = y;
            y = y.parent;
        }
        return y;
    }

    public TreeNode treePredecessor(TreeNode x){
        if (x.left != null){
            return treeMaximum(x.left);
        }

        TreeNode y = x.parent;
        while (y != null && x == y.left){
            x = y;
            y = y.parent;
        }
        return y;
    }

    public void treeInsertion(BinarySearchTree T, TreeNode z){

        TreeNode x = T.root;
        TreeNode y = null;

        while (x != null){
            y = x;
            if (z.data < x.data) x = x.left;
            else x = x.right;
        }

        z.parent = y;
        if (y == null) T.root = z;
        else if (z.data < y.data) y.left = z;
        else y.right = z;
    }
    // Delete
    /*
    Existen 3 casos:
    1. Si el nodo a eliminar no tiene hijos, simplemente se remueve.
    2. Si el nodo a eliminar tiene un hijo, entonces ese hijo tomara la posicion de z.
    3. Si el nodo a eliminar tiene 2 hijos, Buscamos un sucesor del nodo a eliminar
     */

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.treeInsertion(bst, new TreeNode(18));
        bst.treeInsertion(bst, new TreeNode(12));
        bst.treeInsertion(bst, new TreeNode(19));
        bst.treeInsertion(bst, new TreeNode(15));
        bst.treeInsertion(bst, new TreeNode(13));
        bst.treeInsertion(bst, new TreeNode(17));
        bst.treeInsertion(bst, new TreeNode(5));
        bst.treeInsertion(bst, new TreeNode(2));
        bst.treeInsertion(bst, new TreeNode(9));

        bst.inorderTreeWalk(bst.root);

        TreeNode node_15 = bst.treeSearch(bst.root, 15);
        System.out.println();
        System.out.println("Sucesor de 15 es: " + bst.treeSuccessor(node_15).data);
        System.out.println("Predecesor de 15 es: " + bst.treePredecessor(node_15).data);
    }
}
