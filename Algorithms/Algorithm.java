package Algorithms;
import Utils.Node;

import java.util.*;

public abstract class Algorithm {
    HashSet<Node> explored;
    Node goalNode;
    Algorithm(){
        int goalState= 12345678;
        goalNode=new Node(goalState);
        explored=new HashSet<>();
    }

    public Result solve(Node initialState){
    	if(!initialState.isValidState())
    		System.out.println("Invalid Initial State");
    	
    	if(!initialState.isSolvableState())
    		System.out.println("Unsolvable Initial State");
    	
        Result result = new Result();

        long startTime = System.nanoTime();
        
        search(initialState, result);

		long endTime = System.nanoTime();
		long timeElapsed = (endTime - startTime);
		
        result.setTime(timeElapsed / 1000000);
        result.setPath(getPath());
        
        return result;
    }

    abstract boolean search(Node initialState,Result result);

    List<Node> getPath(){
        LinkedList<Node> path=new LinkedList<>();
        Node curr=goalNode;
        while(curr!=null){
            path.addFirst(curr);
            curr=curr.getParent();
        }
        return path;
    }
    
    
    public boolean goalTest(Node state){
        return state.equals(goalNode);
    }
    Node getGoalNode(){
        return goalNode;
    }
}
