import java.util.List;
import java.util.ArrayList;


/**
 * @author Anhkhoi Vu-Nguyen - 27501072
 */
public class BinaryTree<T> {

	@SuppressWarnings("hiding")
	/**
	 * Node is an inner class of the binary tree that implments the Position interface.
	 */
	class Node<T> implements Position<T> {

		private T data; //A generic element that will be held by every node of the tree.
		private Node<T> parentNode; //The direct first ancestor of a node.
		private Node<T> leftChild; //The direct left first descendant of a node.
		private Node<T> rightChild; //The direct right first descendant of a node.

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
		 * Copy Constructor used to make a deep copy of a node object.
		 * @param node the node to create a deep copy of.
		 */
		public Node(Node<T> node){
			this.data = node.data; 
			this.parentNode = node.parentNode;
			this.leftChild = node.leftChild;
			this.rightChild = node.rightChild;
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
	 * @param p the position of the current node object.
	 * @return position of the parent of position p.
	 */
	public Position<T>parent(Position<T> p){
		Node<T> node = (Node<T>)p; //Casting postion object to node object.
		return node.getParent();
	}

	/**
	 * @param p the position of the current node object.
	 * @return an iterable collection containing the children of position p.
	 */
	public Iterable<Position<T>> children(Position <T> p){

		ArrayList<Position<T>> childList = new ArrayList<Position<T>>(2);
		if(left(p) != null)
			childList.add(left(p));

		if(right(p)!=null)
			childList.add(right(p));

		return childList; //Returning the list of children ranging from 0 to 2.
	}

	/**
	 * @param p the position of the current node object.
	 * @return the number of children of position p.
	 */
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
	/**
	 * @param p the position of the current node object.
	 * @return the left child of p.
	 */
	public Position<T> left(Position<T> p){
		Node<T> parentNode = (Node<T>)p;
		Node<T> leftChild = parentNode.leftChild;
		return leftChild;
	}
	
	/**
	 * @param p the position of the current node object.
	 * @return the right child of p.
	 */

	public Position<T> right(Position <T> p){
		Node<T> parentNode = (Node<T>)p;
		Node<T> rightChild = parentNode.rightChild;
		return rightChild;
	}

	/**
	 * @param p the position of the current node object.
	 * @return either the left or right sibling if they are not null.
	 */
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
		
		return null; //At this point there is no sibling so return null.
	}

	/**
	 * @param p the position of the current node object.
	 * @return true if the position is an internal node otherwise false.
	 */
	public boolean isInternal(Position<T> p){

		Node<T> node = (Node<T>)p;
		Node<T> leftChild = node.leftChild;
		Node<T> rightChild = node.rightChild;

		return leftChild != null || rightChild != null ;
	}

	/**
	 * @param p the position of the current node object.
	 * @return true if the position is an external node otherwise false.
	 */
	public boolean isExternal(Position<T> p){
		return !(isInternal(p));
	}
	
	/**
	 * @param p the position of the current node object.
	 * @return true if the position is a root otherwise return false.
	 */
	public boolean isRoot(Position<T> p){
		Node<T> node = (Node<T>)p; 
		return root == node; //Comparing actual memory addresses.
	}

	/**
	 * @return the size of the binary tree which is the total number of nodes.
	 */
	public int size(){
		return size;
	}

	/**
	 * @return a boolean where true denotes that the binary tree is empty.
	 */
	public boolean isEmpty(){
		return root == null; //Alternatively we can check for the size == 0 but if there is no root than the tree is also empty.
	}

	/**
	 * Recursive method to find a height of the tree at a given position.
	 * @param p the position of the current node object.
	 * @return the height of the tree which is of type int.
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

	/**
	 * @param p the position of the current node object.
	 * @return the number of leaves at the current position through recursion.
	 */
	public int numLeaf(Node<T> p){

		if (p==null)
			return 0;
		if(p.leftChild == null && p.rightChild == null)
			return 1;
		else
			return numLeaf(p.leftChild) + numLeaf(p.rightChild); //Recursive Call
	}

	/**
	 * @return the number of leaves in the binary tree.
	 */
	public int numLeaf(){
		return numLeaf(root);
	}

	/**
	 * 
	 * @param p the position of the current node object.
	 * @return a deep copy of the current node object.
	 */
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

		return new Node<T>(node); //Return the node which is a clone of all other nodes except for the root

	}
	/**
	 * @return the a deep copy of the root using the overloaded clone method.
	 */
	public BinaryTree<T> clone(){

		return new BinaryTree<T>(this);
	}

	/**
	 * Recursive InOrder traversal method.
	 * @param p the initial position to begin the InOrder traversal.
	 */
	public void printInorder(Node<T> p){

		if(p.leftChild != null)
			printInorder(p.leftChild);
		System.out.print(p.getElement() + " ");
		if(p.rightChild != null)
			printInorder(p.rightChild);
	}
	
	/**
	 * Performs the InOrder traversal of the entire binary tree by starting at the root.
	 */
	public void printInorder(){
		printInorder(root);
		System.out.println();
	}
	
	/**
	 * @return an Iterable collection of all positions in the tree.
	 */
	public Iterable<Position<T>> positions(){
		ArrayList<Position<T>> posList = new ArrayList<Position<T>>(); 
		Position(posList,root);
		return posList;

	}
	
	/**
	 * @param arr a list object to hold all given positions in the tree.
	 * @param p the initial position.
	 */
	public void Position(ArrayList<Position<T>> arr,Node<T> p){
		if(p.leftChild != null)
			Position(arr,p.leftChild);
		
		arr.add(p); //Add the position into the list.
		
		if(p.rightChild != null)
			Position(arr,p.rightChild);
	}
	
	/**
	 * @return an iterator for all elements in the binary tree.
	 */
	public List<T> iterator(){
		ArrayList<T> iterList = new ArrayList<T>();
		generateIterator(iterList,root);
		return iterList;
	}
	
	/**
	 * 
	 * @param arr a list object to hold all given elements in the tree.
	 * @param p the initial position.
	 */
	public void generateIterator(ArrayList<T> arr,Node<T> p){
		
		if(p.leftChild != null)
			generateIterator(arr,p.leftChild);
		
		arr.add(p.getElement());
		
		if(p.rightChild != null)
			generateIterator(arr,p.rightChild);
		
	}


}
