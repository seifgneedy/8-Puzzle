package Algorithms;

import java.util.Iterator;
import java.util.PriorityQueue;

import Utils.Node;



public class AStar  extends Algorithm {
	PriorityQueue<Node> frontier;
	Heuristic typeHeuristic;
	public AStar () {
		super();
		typeHeuristic=Heuristic.H_Euclidean;
		frontier=new PriorityQueue<Node>(10,new PriorityComparator());
	}
	@Override
	boolean search(Node initialState, Result result) {
		 frontier.add(initialState);
	        while(!frontier.isEmpty()){
	            Node state=frontier.poll();
	            if(explored.contains(state)) {
	            	continue;
	            }
	            explored.add(state);
	            if(goalTest(state)){
	                getGoalNode().setParent(state.getParent());
	                result.setNodesExpanded(explored.size());
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
						Iterator<Node> iter = frontier.iterator();
						Node iterNode;
						boolean addToFontier = true;
						while(iter.hasNext()){
							iterNode = iter.next();
							if(iterNode.equals(neighbor)){
								if(iterNode.getTotalcost() > neighbor.getTotalcost()){
									frontier.remove(iterNode);
								}else
									addToFontier = false;
								break;
							}
						}
						if(addToFontier)
							frontier.add(neighbor);
	                }
	                	
	            }
	        }
	    result.setNodesExpanded(explored.size());
		return false;
	}

   
}
