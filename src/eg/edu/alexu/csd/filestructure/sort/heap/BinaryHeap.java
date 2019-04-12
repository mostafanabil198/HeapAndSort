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
		INode left = node.getLeftChild();
		INode right = node.getRightChild();
		INode max = node;
		if (left != null && left.getValue().compareTo(node.getValue()) > 0) {
			max = left;
		}
		if (right != null && right.getValue().compareTo(max.getValue()) > 0) {
			max = right;
		}
		if (!max.equals(node)) {
			swap(max, node);
			heapify(max);
		}

	}

	@Override
	public T extract() {
		if (numOfElements == 0) {
			return null;
		} else {
			INode root = heap.get(1);
			heap.set(1, heap.get(numOfElements));
			heap.remove(numOfElements);
			numOfElements--;
			heapify(heap.get(1));
			return (T) root;
		}
	}

	@Override
	public void insert(T element) {
		numOfElements++;
		Node node = new Node(numOfElements, element);
		heap.add(node);
		heapifyUp(heap.get(numOfElements));
	}

	@Override
	public void build(Collection<T> unordered) {
		for(int i=numOfElements/2;i>0;i--) {
			heapify(heap.get(i));
		}
	}
	public void heapifyUp(INode<T> node) {
		if(node.getParent()!=null&&node.getParent().getValue().compareTo(node.getValue())<0) {
			swap(node,node.getParent());
			heapifyUp(node.getParent());
		}
	}

	public void swap(INode<T> first, INode<T> second) {
		T temp = first.getValue();
		first.setValue(second.getValue());
		second.setValue(temp);
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
			if (index * 2 <= numOfElements) {
				return (INode<T>) heap.get(index * 2);
			} else {
				return null;
			}
		}

		@Override
		public INode<T> getRightChild() {
			if (index * 2 + 1 <= numOfElements) {
				return (INode<T>) heap.get(index * 2 + 1);
			} else {
				return null;
			}
		}

		@Override
		public INode<T> getParent() {
			if (index / 2 > 0 && numOfElements>0) {
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
