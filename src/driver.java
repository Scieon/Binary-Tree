
public class driver {

	public static void main(String[] args) {


		
		int counter = 1;

		BinaryTree b1 = new BinaryTree();

		//-- Populating Binary Tree
		System.out.println("\n----------------Populating Tree-------------------------------------");
		b1.addRoot('+');
		Position p1 = b1.root();
		System.out.println("Root(" + counter++ + "): \t\t" + p1.getElement());


		b1.addLeftChild('*',p1);
		Position p2 = b1.left(p1);
		System.out.println("Left Child(" + counter++ + "): \t\t" + p2.getElement());

		b1.addRightChild('*', p1);
		Position p3 = b1.right(p1);
		System.out.println("Right Child(" + counter++ + "): \t" + p3.getElement());

		b1.addLeftChild(2,p2);
		b1.addRightChild('-',p2);
		Position p4 = b1.left(p2);
		Position p5 = b1.right(p2);
		System.out.println("Left Child(" + counter++ + "): \t\t" + p4.getElement());
		System.out.println("Right Child(" + counter++ + "): \t" + p5.getElement());


		b1.addLeftChild(3,p3);
		b1.addRightChild('b',p3);
		Position p6 = b1.left(p3);
		Position p7 = b1.right(p3);
		System.out.println("Left Child(" + counter++ + "): \t\t" + p6.getElement());
		System.out.println("Right Child(" + counter++ + "): \t" + p7.getElement());

		b1.addLeftChild('a',p5);
		b1.addRightChild(1,p5);
		Position p8 = b1.left(p5);
		Position p9 = b1.right(p5);
		System.out.println("Left Child(" + counter++ + "): \t\t" + p8.getElement());
		System.out.println("Right Child(" + counter++ + "): \t" + p9.getElement());




		//------------------- Testing Methods -------------------------------------------
		System.out.println("\n----------------Testing Methods-------------------------------------");
		System.out.println("Number of children at root / point(1): " + b1.numChildren(p1));
		System.out.println("Iterable collection of children at point(1): " + b1.children(p1));
		System.out.println("Is point(1) the root: " + b1.isRoot(p1));
		System.out.println("Is point(1) internal: " + b1.isInternal(p1));
		System.out.println("Is point(1) external: " + b1.isExternal(p1));
		System.out.println();
		System.out.println("Size of binary Tree: " + b1.size());
		System.out.println("Height of Tree: " + b1.height());
		System.out.println("Number of leaves of Tree: " + b1.numLeaf());
		System.out.println("Is Tree empty: " + b1.isEmpty());
		System.out.println();
		System.out.println("Iterator for all elements: " + b1.iterator());
		System.out.println("Iterable collection for all positions: " + b1.positions());
		System.out.println();
		System.out.println("Performing clone...\n");
		BinaryTree b2 = b1.clone();
		System.out.print("Inorder traversal of cloned tree: ");
		b2.printInorder();
		System.out.println("\n-----------------------------------------------------------------");



	}


}
