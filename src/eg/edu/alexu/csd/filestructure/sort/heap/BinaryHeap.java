/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.edu.alexu.csd.filestructure.sort.heap;

import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;
import java.util.ArrayList;
import java.util.Collection;
import javax.management.RuntimeErrorException;

/**
 *
 * @author arabtech
 * @param <T>
 */
public class BinaryHeap<T extends Comparable<T>> implements IHeap<T> {

    private ArrayList<INode<T>> heap;
    private int numOfElements = 0;

    public BinaryHeap() {
        heap = new ArrayList<>();
        heap.add(new Node(0, null));
    }

    @Override
    public INode<T> getRoot() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(1);
    }

    @Override
    public int size() {
        return heap.size() - 1;
    }

    @Override
    public void heapify(INode<T> node) {

    }

    @Override
    public T extract() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(T element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void build(Collection<T> unordered) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void exchangeValues() {
        T temp = heap.get(1).getValue();
        heap.get(1).setValue(heap.get(numOfElements).getValue());
        heap.get(numOfElements).setValue(temp);
        numOfElements--;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        numOfElements = size();
        BinaryHeap<T> h = new BinaryHeap<>();
        for (int i = 0; i < this.heap.size(); i++) {
            h.heap.add(i, (INode<T>) ((Node<T>) this.heap.get(i)).clone());
        }
        h.numOfElements = numOfElements;
        return h;
    }

    class Node<T extends Comparable<T>> implements INode<T> {

        private int index;
        private T value;

        public Node(int i, T value) {
            this.index = i;
            this.value = value;
        }

        @Override
        public INode<T> getLeftChild() {
            if (index * 2 < heap.size()) {
                return (INode<T>) heap.get(index * 2);
            } else {
                return null;
            }
        }

        @Override
        public INode<T> getRightChild() {
            if (index * 2 + 1 < heap.size()) {
                return (INode<T>) heap.get(index * 2 + 1);
            } else {
                return null;
            }
        }

        @Override
        public INode<T> getParent() {
            if (index / 2 > 0 && heap.size() > 1) {
                return (INode<T>) heap.get(index / 2);
            } else {
                return null;
            }
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return new Node(index, value);
        }

    }

}
