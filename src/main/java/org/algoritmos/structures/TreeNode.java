package org.algoritmos.structures;


public class TreeNode<T> {
    T key;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode<T> parent;

    public TreeNode(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public TreeNode(T key, TreeNode<T> left, TreeNode<T> right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = null;
    }

}
