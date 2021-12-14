package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E> {
	protected BinaryNode<E> root; // Används också i BSTVisaulizer
	protected int size; // Används också i BSTVisaulizer
	private Comparator<E> comparator;

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this();
		this.comparator = comparator;
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(10);
		tree.add(20);
		tree.add(11);
		tree.add(3);
		tree.add(8);
		tree.add(93);
		tree.add(100);
		tree.add(52);
		tree.add(25);
		tree.add(36);
		tree.add(72);
		tree.rebuild();

		BSTVisualizer bstv = new BSTVisualizer("Int Tree", 1000, 800);
		bstv.drawTree(tree);

	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return add(root, x);
	}

	private boolean add(BinaryNode<E> n, E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
		} else {
			int i = compare(x, n.element);

			if (i == 0) {
				return false;
			} else if (i < 0) {
				if (n.left != null) {
					add(n.left, x);
				} else {
					n.left = new BinaryNode<E>(x);
					size++;
				}
			} else if (i > 0) {
				if (n.right != null) {
					add(n.right, x);
				} else {
					n.right = new BinaryNode<E>(x);
					size++;
				}
			}
		}

		return true;
	}

	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		} else if (e1 instanceof Comparable && e2 instanceof Comparable) {
			return ((Comparable) e1).compareTo((Comparable) e2);
		}

		return 0;
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> n) {
		if (n == null) {
			return 0;
		}

		return 1 + Math.max(height(n.left), height(n.right));
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> n) {
		if (n == null) {
			return;
		}

		printTree(n.left);
		System.out.println(n.element);
		printTree(n.right);
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> list = new ArrayList<E>();
		toArray(root, list);
		root = buildTree(list, 0, list.size());
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n == null) {
			return;
		}

		toArray(n.left, sorted);
		sorted.add(n.element);
		toArray(n.right, sorted);
	}

	/*
	 * Builds a complete tree from the elements from position first to last in the list sorted. Elements in the list a are assumed to be in ascending order. Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last) {
			return null;
		} else {
			int pos = (first + last) / 2;
			BinaryNode<E> n = new BinaryNode<E>(sorted.get(pos));
			n.left = buildTree(sorted, first, pos - 1);
			n.right = buildTree(sorted, pos + 1, last);

			return n;
		}
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
