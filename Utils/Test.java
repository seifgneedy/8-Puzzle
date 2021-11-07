package Utils;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		Node invalid = new Node(123456789);	//should print invalid
		

		Node node = new Node(12345678);		// 2 childern (0 to left and 0 down)
		ArrayList<Node> nigh = new ArrayList<Node>();
		nigh = (ArrayList<Node>) node.neighbors();
		for(Node n : nigh)
			n.print();
	
	
	}
}

