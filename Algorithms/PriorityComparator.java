
package Algorithms;

import java.util.Comparator;

import Utils.Node;

public class PriorityComparator implements Comparator<Node> {
	 @Override
	    public int compare(Node x, Node y) {
	        if (x.getTotalcost() < y.getTotalcost()) {
	            return -1;
	        }
	        if (x.getTotalcost() > y.getTotalcost()) {
	            return 1;
	        }
	        return 0;
	    }
}
