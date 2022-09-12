package br.unicap.ed1.atividade06;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeWalker<Key extends Comparable<Key>, Value> {
    
    private BinarySearchTree<Key, Value> tree;

    public BinaryTreeWalker(BinarySearchTree<Key, Value> tree){
        this.tree = tree;
    }

    public List<Key> inOrder(){
        List<Key> listInOrder = new ArrayList<>();
        this.inOrder(tree.getRoot(), listInOrder);
        return listInOrder;
    }

    private void inOrder(Node<Key, Value> root, List<Key> listInOrder){
        if (root == null) {
            return;
        }
        inOrder(root.getLeft(), listInOrder);
        listInOrder.add(root.getKey());
        inOrder(root.getRight(), listInOrder);
    }

    public List<Key> preorder(){
        List<Key> listPreorder = new ArrayList<>();
        this.preorder(tree.getRoot(), listPreorder);
        return listPreorder;
    }

    private void preorder(Node<Key, Value> root, List<Key> listPreorder){
        if (root == null) {
            return;
        }
        listPreorder.add(root.getKey());
        preorder(root.getLeft(), listPreorder);
        preorder(root.getRight(), listPreorder);
    }

    public List<Key> postorder(){
        List<Key> listPostorder = new ArrayList<>();
        this.postorder(tree.getRoot(), listPostorder);
        return listPostorder;
    }

    private void postorder(Node<Key, Value> root, List<Key> listPostorder){
        if (root == null) {
            return;
        }
        postorder(root.getLeft(), listPostorder);
        postorder(root.getRight(), listPostorder);
        listPostorder.add(root.getKey());
    }

    public List<Key> breadthFirst(){
        List<Key> listBreadthFirst = new ArrayList<>();
        Queue<Key, Value> q = new Queue<Key, Value>();
        q.enqueue(tree.getRoot());
        while (!q.isEmpty()) {
            Node<Key, Value> n = q.dequeue();
            listBreadthFirst.add(n.getKey());
            if (n.getLeft() != null) {
                q.enqueue(n.getLeft());
            }
            if (n.getRight() != null){
                q.enqueue(n.getRight());
            }
        }
        return listBreadthFirst; 
    }
}
