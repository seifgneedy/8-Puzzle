package Utils;

import java.io.FileWriter;
import java.util.*;

import Algorithms.*;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter initial state space or comma separated: ");
		String s = in.nextLine();
		s = s.replaceAll(",", "");
		s = s.replaceAll(" ", "");
		Node node = new Node(Integer.parseInt(s));
		System.out.println("Choose algorithm to Solve : ");
		System.out.println("1 --> DFS\n2 --> BFS\n3 --> A* Manhattan Distances\n4 --> A* Euclidean Distances");
		int alg = in.nextInt();
		in.close();
		Algorithm m = null;
		switch (alg) {
		case 1:
			m = new DFS();
			break;
		case 2:
			m = new BFS();
			break;
		case 3:
			m = new AStar(AStar.Manhattan);
			break;
		case 4:
			m = new AStar(AStar.Euclidean);
			break;
		default:
			System.out.println("Wrong Selection\nExiting");
			System.exit(-1);
		}
		
		Result res  = m.solve(node);
		System.out.printf("Solved in %d Moves\n", res.getCost());
		System.out.printf("Solved in %d ms\n", res.getTime());
		System.out.println("Nodes Expanded: " + res.getNodesExpanded());
		System.out.println("Path Printed to path.txt");
		
		
		String steps = "";
		
		for(Node n : res.getPath()) {
			steps += n.toString();
		}
		FileWriter output = new FileWriter("path.txt");
		output.write(steps);
		output.flush();
		output.close();
	}
}

