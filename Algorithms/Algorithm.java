package Algorithms;
import Utils.Node;

import java.util.*;

public abstract class Algorithm {
    HashSet<Integer> explored;
    Node goalNode;
    Algorithm(){
        int goalState= 12345678;
        goalNode=new Node(goalState);
        explored=new HashSet<>();
    }
    /**
     * Check the state for being valid and solvable
     * if so then start to solve it and calculate the 
     * running time then save it in a Result object
     * with the rest of data
     * @param initialState
     * starting state
     * @return
     * A Result object. 
     */
    public Result solve(Node initialState){
    	if(!initialState.isValidState()) {
    		System.out.println("Invalid Initial State\nExiting");
    		System.exit(-1);
    	}
    	
    	if(!initialState.isSolvableState()) {
    		System.out.println("Unsolvable Initial State\nExiting");
			System.exit(-1);
    	}

    	
        Result result = new Result();
        // Calculate the running time
        long startTime = System.nanoTime();
        search(initialState, result);
		long endTime = System.nanoTime();
		long timeElapsed = (endTime - startTime);
        result.setTime(timeElapsed / 1000000);
        // Save the solution path
        result.setPath(getPath());
        
        return result;
    }
    /**
     * Search for the goal state.
     * @param initialState
     * starting state.
     * @param result
     * result object to store number of expanded 
     * nodes in it.
     * @return
     * true if goal state is reached.
     * false otherwise.
     */
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
    /**
     * Test if this state is the goal state.
     * @param state
     * state to be tested.
     * @return
     * true if this is the goal state.
     * false otherwise.
     */
    public boolean goalTest(Node state){
        return state.equals(goalNode);
    }
    Node getGoalNode(){
        return goalNode;
    }
}
