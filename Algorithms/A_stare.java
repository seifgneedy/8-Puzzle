package Algorithms;

import java.util.PriorityQueue;

import Utils.Node;



public class A_stare  extends Algorithm {
	PriorityQueue<Node> frontier;
	Heuristic typeHeuristic;
	public A_stare () {
		super();
		typeHeuristic=Heuristic.H_Euclidean;
		frontier=new PriorityQueue<Node>(10,new PriorityComparator());
	}
	@Override
	boolean search(Node initialState) {
		// TODO Auto-generated method stub
		 frontier.add(initialState);
	        while(!frontier.isEmpty()){
	            Node state=frontier.poll();
	            if(explored.contains(state)) {
	            	continue;
	            }
	            explored.add(state);
	            if(goalTest(state)){
	                getGoalNode().setParent(state.getParent());
	                return true;
	            }
	            for(Node neighbor : state.neighbors()){
	                if(!explored.contains(neighbor)) {
	                	neighbor.setParent(state);
	                    neighbor.setCost(neighbor.getParent().getCost() +1);
	                    if (typeHeuristic == Heuristic.H_Manhattan) {
	                    	neighbor.setTotalcost(neighbor.getCost()+neighbor.manhattanCost());
	                    }else if(typeHeuristic == Heuristic.H_Euclidean) {
	                    	neighbor.setTotalcost(neighbor.getCost()+neighbor.euclideanCost());
						}
	                    frontier.add(neighbor);
	                }
	                	
	            }
	        }
		return false;
	}

   
}
