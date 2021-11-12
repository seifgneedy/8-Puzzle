package Utils;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Algorithms.*;

public class Main {

	public static void main(String[] args) throws Exception {
		//125304678
		//432650781
		Node node = new Node(432650871);
		Algorithm m = new DFS();
		Result res  = m.solve(node);
		System.out.printf("Solved in %d Moves\n", res.getCost());
		System.out.printf("Solved in %d ms\n", res.getTime());
		System.out.println("Nodes Expanded: " + res.getNodesExpanded());
		System.out.println("Path Printed to file");


		String outputFile = "d:/test";
		File outputObj = new File(outputFile);
		outputObj.createNewFile();
		FileWriter output = new FileWriter(outputFile);
		
		for(Node n : res.getPath()) {
			output.write(n.toString());
		}
		output.close();
		
	}
}

