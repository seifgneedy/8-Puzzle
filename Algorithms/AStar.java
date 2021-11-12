package Algorithms;

import java.util.HashMap;
import java.util.PriorityQueue;

import Utils.Node;



public class AStar  extends Algorithm {
	PriorityQueue<Node> frontier;
	HashMap<Integer, Double> costs;
	public static final int Manhattan = 0;
	public static final int Euclidean = 1;
	int heuristic;

	public AStar(int heuristic) {
		super();
		this.heuristic = heuristic;
		frontier=new PriorityQueue<Node>(10,new PriorityComparator());
		costs = new HashMap<Integer, Double>();
	}
	@Override
	boolean search(Node initialState, Result res) {
		frontier.add(initialState);
		costs.put(initialState.getState(), 0.0);
		while(!frontier.isEmpty()){
			Node state=frontier.poll();
			costs.remove(state.getState());
			if(explored.contains(state.getState())) {
				continue;
			}
			explored.add(state.getState());
			if(goalTest(state)){
				getGoalNode().setParent(state.getParent());
				res.setNodesExpanded(explored.size());
				return true;
			}
			for(Node neighbor : state.neighbors()){
				if(!explored.contains(neighbor.getState())) {
					neighbor.setParent(state);
					neighbor.setCost(neighbor.getParent().getCost() +1);
					if (heuristic == Manhattan) {
						neighbor.setTotalcost(neighbor.getCost()+neighbor.manhattanCost());
					}else if(heuristic == Euclidean) {
						neighbor.setTotalcost(neighbor.getCost()+neighbor.euclideanCost());
					}
					if(costs.containsKey(neighbor.getState())) {
						if(neighbor.getTotalcost() < costs.get(neighbor.getState())) {
							costs.put(neighbor.getState(), neighbor.getTotalcost());
							//frontier.remove(neighbor);
						}else {
							continue;
						}
					}
					frontier.add(neighbor);
				}

			}
		}
		res.setNodesExpanded(explored.size());
		return false;
	}


}
