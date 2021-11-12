
package Algorithms;

import java.util.Comparator;

import Utils.Node;

public class PriorityComparator implements Comparator<Node> {
	/*
	 * Overrided version of the compare method which
	 *  compares according to totalCost in the nodes.
	 */
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
