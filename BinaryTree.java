import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;



public class BinaryTree<T> {

	class Node<T> implements Position<T> {

		private T data;
		private Node<T> parentNode;
		private Node<T> leftChild;
		private Node<T> rightChild;

		/**
		 * Parameterized constructor.
		 * @param data the specific parameter 
		 * @param parentNode the direct ancestor of the current node.
		 * @param leftChild the direct left descendant of the current node.
		 * @param rightChild the direct right descendant of the current node.
		 */
		public Node(T data, Node<T> parentNode, Node<T> leftChild, Node<T> rightChild){
			this.data = data; 
			this.parentNode = parentNode;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		/**
		 * Assessor method for the parent node.
		 * @return the parent node.
		 */
		public Node<T> getParent(){
			return parentNode;
		}

		/**
		 * Assessor method for the element held at the current node.
		 * @return a generic data type.
		 */
		public T getElement(){
			return data;
		}


	}//End of inner class

	private Node<T> root = null; //Initially there is no root for the tree.
	private int size = 0; //Initially size of the tree is 0.

	//Default constructor initializing the tree to null.
	public BinaryTree(){}

	public BinaryTree(BinaryTree<T> binaryTree) {
		// TODO Auto-generated constructor stub

		root = clone(binaryTree.root);
		size = binaryTree.size;
	}

	/**
	 * Adds a root node to a tree.
	 * @param data a generic data type that will be held in the newly created node.
	 */
	public void addRoot(T data){
		root = new Node<T>(data,null,null,null);
		size = 1; //Resets the size of the tree back to 1. 
	}

	/**
	 * This method adds a left node to a given node.
	 * @param data a generic data type that will be held in the newly created node.
	 * @param p the node to add the left child to.
	 */
	public void addLeftChild(T data, Position<T> p){

		Node<T> parentNode = (Node<T>)p;
		Node<T> leftChild = new Node<T>(data,parentNode,null,null);
		parentNode.leftChild = leftChild;
		size++; //Adding a child increases the size of the tree by 1.
	}

	/**
	 * This method adds a right node to a given node.
	 * @param data a generic data type that will be held in the newly created node.
	 * @param p the node to add the right child to.
	 */
	public void addRightChild(T data, Position<T> p){
		Node<T> parentNode = (Node<T>)p;
		Node<T> rightChild = new Node<T>(data,parentNode,null,null);
		parentNode.rightChild = rightChild;
		size++; //Adding a child increases the size of the tree by 1.
	}

	/**
	 * @return root of the tree as a generic position to avoid privacy leak.
	 */
	public Position<T> root(){
		return root;
	}

	/**
	 * @param p 
	 * @return
	 */
	public Position<T>parent(Position<T> p){
		Node<T> node = (Node<T>)p;
		return node.getParent();
	}

	public Iterable<Position<T>> children(Position <T> p){

		ArrayList<Position<T>> childList = new ArrayList<Position<T>>(2);
		if(left(p) != null)
			childList.add(left(p));

		if(right(p)!=null)
			childList.add(right(p));

		return childList;

	}

	public void printInorder(){
		printInorder(root);
		System.out.println();
	}
	public void printInorder(Node<T> p){

		if(p.leftChild != null)
			printInorder(p.leftChild);
		System.out.print(p.getElement() + " ");
		if(p.rightChild != null)
			printInorder(p.rightChild);
	}


	public Iterable<Position<T>> getPosIterator(){
		ArrayList<Position<T>> posList = new ArrayList<Position<T>>();
		positions(posList,root);
		System.out.println(); //For spacing issues omit this line
		return posList;

	}
	public void positions(ArrayList<Position<T>> arr,Node<T> p){
		//ArrayList<Position<T>> posList = new ArrayList<Position<T>>();

		if(p.leftChild != null)
			positions(arr,p.leftChild);

		arr.add(p);
		System.out.print(p.getElement() + " "); //Just to see the actual elements, omit this line
		if(p.rightChild != null)
			positions(arr,p.rightChild);

	}

	public int numChildren(Position <T> p){

		int count = 0;
		Node<T> node = (Node<T>)p;
		Node<T> leftChild = node.leftChild;
		Node<T> rightChild = node.rightChild;

		if (leftChild != null)
			count++;
		if (rightChild != null)
			count ++;
		return count;

	}
	public Position<T> left(Position<T> p){

		Node<T> parentNode = (Node<T>)p;
		Node<T> leftChild = parentNode.leftChild;
		return leftChild;
	}

	public Position<T> right(Position <T> p){
		Node<T> parentNode = (Node<T>)p;
		Node<T> rightChild = parentNode.rightChild;
		return rightChild;
	}

	public Position<T> siblings(Position<T> p){

		Node<T> node = (Node<T>)p;
		Node<T> parent = node.parentNode;
		Node<T> leftChild = parent.leftChild;
		Node<T> rightChild = parent.rightChild;


		if(leftChild == null || rightChild == null)
			System.out.println("No siblings.");

		if(leftChild == p){
			System.out.println("Same Left --  Returning Right sibling");
			return rightChild;
		}
		if(rightChild == p){
			System.out.println("Same right -- Returning Left sibling");
			return leftChild;
		}

		return null;
	}

	public boolean isInternal(Position<T> p){

		Node<T> node = (Node<T>)p;
		Node<T> leftChild = node.leftChild;
		Node<T> rightChild = node.rightChild;

		return leftChild != null || rightChild != null ;
	}

	public boolean isExternal(Position<T> p){
		return !(isInternal(p));
	}
	public boolean isRoot(Position<T> p){
		Node<T> node = (Node<T>)p; //Omit?
		return root == p; //Comparing actual memory addresses.
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return root == null;
	}

	/**
	 * Recursive method to find a height of the tree at a given position.
	 * @param p
	 * @return
	 */
	public int height( Position<T> p ){
		int height = 0; //Initially height 
		for(Position<T> c : children(p))
			height= Math.max(height,1+height(c));
		return height;
	}

	/**
	 * @return the height of the tree.
	 */
	public int height(){	
		return height(root);
	}



	public int numLeaf(Node<T> p){

		if (p==null)
			return 0;
		if(p.leftChild == null && p.rightChild == null)
			return 1;
		else
			return numLeaf(p.leftChild) + numLeaf(p.rightChild);
	}

	public int numLeaf(){
		return numLeaf(root);
	}



	public Node<T> clone(Position<T>p){
		Node<T> node = (Node<T>)p;

		Node<T> left = (Node<T>)p;
		Node<T> right = (Node<T>)p;

		if(node.leftChild != null){
			left = (Node<T>)clone(left(p));
			node.leftChild = left;
			left.parentNode = node;

		}
		if(node.rightChild != null){
			right = (Node<T>)clone(right(p));
			node.rightChild = right;
			right.parentNode = node;
		}

		return node; //Return the node which is a clone of all other nodes except for the root

	}
	public BinaryTree<T> clone(){

		return new BinaryTree<T>(this);
	}






}
