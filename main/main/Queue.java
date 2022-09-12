package main;

import java.util.ArrayList;

public class Queue<Key extends Comparable<Key>, Value>{
    
    private ArrayList<Node<Key, Value>> queue;

    public Queue() {
        this.queue = new ArrayList<>();
    }

    public void enqueue(Node<Key, Value> x) {
        queue.add(x);
    }
        
    public Node<Key, Value> dequeue() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        Node<Key, Value> saved = queue.get(0);
        queue.remove(queue.get(0));
        return saved;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}