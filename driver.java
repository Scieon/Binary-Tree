
public class driver {

	public static void main(String[] args) {
		
		int counter = 1;
		
		BinaryTree b1 = new BinaryTree();
		
		b1.addRoot('+');
		
		Position p1 = b1.root();
		System.out.println("Root(" + counter++ + "): \t\t" + p1.getElement());
		
		
		b1.addLeftChild('*',p1);
		Position p2 = b1.left(p1);
		System.out.println("Left Child(" + counter++ + "): \t\t" + p2.getElement());
		
		b1.addRightChild('*', p1);
		Position p3 = b1.right(p1);
		System.out.println("Right Child(" + counter++ + "): \t" + p3.getElement());
		//System.out.println("Right Child(" + counter++ + "): " + p3.getElement());
		

		//--------------------------------------------------------------
		b1.addLeftChild(2,p2);
		b1.addRightChild('-',p2);
		
		Position p4 = b1.left(p2);
		Position p5 = b1.right(p2);
		
		System.out.println("Left Child(" + counter++ + "): \t\t" + p4.getElement());
		System.out.println("Right Child(" + counter++ + "): \t" + p5.getElement());
		
		//--------------------------------------------------------------
		b1.addLeftChild(3,p3);
		b1.addRightChild('b',p3);
		
		Position p6 = b1.left(p3);
		Position p7 = b1.right(p3);
		
		System.out.println("Left Child(" + counter++ + "): \t\t" + p6.getElement());
		System.out.println("Right Child(" + counter++ + "): \t" + p7.getElement());
		
		//--------------------------------------------------------------
		b1.addLeftChild('a',p5);
		b1.addRightChild(1,p5);
		
		Position p8 = b1.left(p5);
		Position p9 = b1.right(p5);
		
		System.out.println("Left Child(" + counter++ + "): \t\t" + p8.getElement());
		System.out.println("Right Child(" + counter++ + "): \t" + p9.getElement());
		
		
		//-- This is an extra position below p8
		b1.addLeftChild('X',p8);
		Position p10 = b1.left(p8);
		
		System.out.println("\n\nExtra: ");
		System.out.println(b1.numChildren(p8));
		
		
		//--------------------------------------------------------------
		System.out.println("\n-----------------------------------------------------");
		System.out.println(b1.siblings(p7));
		System.out.println(p6);
		System.out.println("-----------------------------------------------------");
		
		
		//--------------------------------------------------------------
		System.out.println("\n-----------------------------------------------------");
		System.out.println(b1.isInternal(p5));
		System.out.println(b1.isExternal(p5));
		System.out.println("Number of Children: " + b1.numChildren(p2));
		System.out.println("Size: " + b1.size());
		System.out.println(b1.numLeaf());
		System.out.println("-----------------------------------------------------");

		//--------------------------------------------------------------
		
	
		
		BinaryTree b3 = new BinaryTree();
		b3.addRoot("999");
		
		Position cc1 = b3.root();
		b3.addLeftChild("Redemption",cc1);
		
		Position leftc = b3.left(cc1);
		System.out.println(cc1.getElement());
		System.out.println(leftc.getElement());
		System.out.println("Below is clone");
		
		BinaryTree b2 = b3.clone();
		Position c1 = b2.root();
		Position c2 = b2.left(c1);
		
		b2.addRightChild("222",c1);
		Position c3 = b2.right(c1);
		
		System.out.println(c1.getElement());
		System.out.println(c2.getElement());
	
		
		
	}
	

}
