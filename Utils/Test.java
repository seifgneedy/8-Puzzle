package Utils;

import java.util.*;

import Algorithms.*;

public class Test {

	public static void main(String[] args) {
		Node node = new Node(125304678);
		if(!node.isValidState()) {
			System.out.println("Invalid Initial State");
			System.exit(-1);
		}
		Algorithm m = new BFS();
		List<Node> path = m.solve(node);
		System.out.printf("Solved in %d Moves\n", path.size());
		for(Node n : path)
			n.print();
	
	}
}

