package Utils;

import java.util.*;

import Algorithms.*;

public class Test {

	public static void main(String[] args) {
		Node node = new Node(724506831);
		if(!node.isValidState()) {
			System.out.println("Invalid Initial State");
		}
		if(!node.isSolvableState()) {
			System.out.println("Unsolvable Initial State");
		}
		System.out.println(node.manhattanCost());
		
		Algorithm m = new BFS();
		List<Node> path = m.solve(node);
		System.out.printf("Solved in %d Moves\n", path.size() - 1);
		for(Node n : path)
			n.print();
	
	}
}

