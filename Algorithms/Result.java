package Algorithms;

import java.util.List;

import Utils.Node;
/**
 * Class to store:
 * 		The number of nodes expanded.
 * 		The running time in milliseconds.
 * 		The path to the goal state.
 */
public class Result {
	private int nodesExpanded;
	private long time;
	private List<Node> path;

	public Result(int nodesExpanded, long time, List<Node> path) {
		this.nodesExpanded = nodesExpanded;
		this.time = time;
		this.path = path;
		
	}
	
	public List<Node> getPath() {
		return path;
	}

	public void setPath(List<Node> path) {
		this.path = path;
	}

	public Result() {
		
	}

	public int getCost() {
		return path.size() - 1;
	}


	public int getNodesExpanded() {
		return nodesExpanded;
	}

	public void setNodesExpanded(int nodesExpanded) {
		this.nodesExpanded = nodesExpanded;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
