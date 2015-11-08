

public class BinaryTree<T> {

	class Node<T> implements Position<T>,  Iterator<T> {

		private T data;
		private Node<T> parentNode;
		private Node<T> leftChild;
		private Node<T> rightChild;

		public Node(T data, Node<T> parentNode, Node<T> leftChild, Node<T> rightChild){
			this.data = data; 
			this.parentNode = parentNode;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		public Node<T> getParent(){
			return parentNode;
		}

		public T getElement(){
			return data;
		}

		public Node<T> copyLeft() {

			Node<T> left = null;
			Node<T> right = null;
			Node<T> parent = null;

			if(this.leftChild != null){
				left = this.leftChild.copyLeft();
			}
			if (this.rightChild != null) {
				right = this.rightChild.copyLeft();
			}

			return new Node<T>(data,parent,left, right);
		}

		public Node<T> copyRight() {

			Node<T> left = null;
			Node<T> right = null;
			Node<T> parent = null;

			if(this.leftChild != null){
				left = this.leftChild.copyRight();
			}
			if (this.rightChild != null) {
				right = this.rightChild.copyRight();
			}

			return new Node<T>(data,parent,left, right);
		}



	}//End of inner class

	private Node<T> root = null; //Initially there is no root for the tree.
	private int size = 0;

	public BinaryTree(){}


	public void addRoot(T data){
		root = new Node<T>(data,null,null,null);
		size = 1;
	}

	public void addLeftChild(T data, Position<T> p){

		Node<T> parentNode = (Node<T>)p;
		Node<T> leftChild = new Node<T>(data,parentNode,null,null);
		parentNode.leftChild = leftChild;
		size++;
	}

	public void addRightChild(T data, Position<T> p){
		Node<T> parentNode = (Node<T>)p;
		Node<T> rightChild = new Node<T>(data,parentNode,null,null);
		parentNode.rightChild = rightChild;
		size++;
	}


	public Position<T> root(){
		return root;
	}


	public Position<T>parent(Position<T> p){
		Node<T> node = (Node<T>)p;
		return node.getParent();
	}

	public Iterable<Position<T>>children(Position <T> p){

		return null;//
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

	public int height(){

		return 0;
	}

	private Node<T> temp = root;

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
	
	
	public BinaryTree<T> clone(){


		BinaryTree<T> cloneTree = new BinaryTree();
		cloneTree.addRoot(root.getElement());
		cloneTree.root.leftChild = (root.copyLeft());
		cloneTree.root.rightChild = (root.copyRight());
		return cloneTree;
	}






}
