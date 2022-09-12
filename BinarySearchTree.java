package br.unicap.ed1.atividade06;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node<Key, Value> root;
    
    public Node<Key, Value> getRoot() {
        return root;
    }

    public void setRoot(Node<Key, Value> root) {
        this.root = root;
    }

    public void put(Key key, Value value){
        this.setRoot(put(key, value, this.getRoot()));
    }

    private Node<Key, Value> put(Key key, Value value, Node<Key, Value> x){
        if (x == null) {
            return new Node<Key, Value>(key, value, 1);
        } else {
            int cmp = key.compareTo(x.getKey());
            if(cmp < 0){
                Node<Key, Value> leftSubTree = this.put(key, value, x.getLeft());
                x.setLeft(leftSubTree);
            }else if(cmp > 0){
                Node<Key, Value> rigthtSubTree = this.put(key, value, x.getRight());
                x.setRight(rigthtSubTree);
            }else{
                x.setValue(value);
            }   
            //Update size
            int newSize = 1 + size(x.getLeft()) + size(x.getRight());
            x.setSize(newSize);
            return x;
        }
    }

    public Value get(Key key){
        return this.get(key, this.getRoot());
    }

    private Value get(Key key, Node<Key, Value> x){
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.getKey());
        if(cmp < 0){
            return this.get(key, x.getLeft());
        }else if(cmp > 0){
            return this.get(key, x.getRight());
        }else{
            return x.getValue();
        }
    }

    public int size( Node<Key, Value> x) {
        if (x == null) {
          return 0;
        }
        return x.getSize();
    }

    public int size() {
        return this.size(this.getRoot());
    }

    public Key min() {
        Node<Key, Value> min = this.min(this.getRoot());
        if (min == null) {
            return null;
        }
        return min.getKey();
    }

    public Key max() {
        Node<Key, Value> max = this.max(this.getRoot());
        if (max == null) {
            return null;
        }
        return max.getKey();
    }

    private Node<Key, Value> min(Node<Key, Value> x) {
        if (x == null) {
            return null;
        }
        if (x.getLeft() == null) {
            return x;
        }else {
            return this.min(x.getLeft());
        }
    }

    private Node<Key, Value> max(Node<Key, Value> x) {
        if (x == null) {
            return null;
        }
        if (x.getRight() == null) {
            return x;
        } else {
            return this.max(x.getRight());
        }
    }

    public void delete(Key key) {
        this.setRoot(delete(this.getRoot(), key));
    }

    private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.getKey());
        if (cmp < 0){
        x.setLeft(delete(x.getLeft(), key)); 
        } else if (cmp > 0){
        x.setRight(delete(x.getRight(), key));
        } else {
            if(x.getLeft() == null) {
                int newSize = 1 + size(x.getLeft()) + size(x.getRight());
                x.setSize(newSize);
                return x.getRight();
            }
            if (x.getRight() == null){
                int newSize = 1 + size(x.getLeft()) + size(x.getRight());
                x.setSize(newSize);
                return x.getLeft();
            }
        Node<Key, Value> nodeToDelete = x;
        x = min(nodeToDelete.getRight());
        x.setRight(delete(nodeToDelete.getRight(), x.getKey()));
        x.setLeft(nodeToDelete.getLeft());
        }

        int newSize = 1 + size(x.getLeft()) + size(x.getRight());
        x.setSize(newSize);
        return x;
    }
    
    public Key select(int i) {
        BinaryTreeWalker<Key, Value> walker = new BinaryTreeWalker<>(this);
        List<Key> selectList = new ArrayList<>();
        selectList = walker.inOrder();
        if (i == selectList.size()) {
            return null;
        }
        return selectList.get(i);
    }
}