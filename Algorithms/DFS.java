package Algorithms;

import java.util.*;

import Utils.Node;

public class DFS extends Algorithm {
    Stack<Node> frontier;
    HashSet<Integer> frontierSet;
    public DFS(){
        super();
        frontier=new Stack<>();
        frontierSet = new HashSet<>();
    }
    /**
     * Search for the goal state using DFS algorithm.
     * @param initialState
     * starting state.
     * @param result
     * result object to store number of expanded 
     * nodes in it.
     * @return
     * true if goal state is reached.
     * false otherwise.
     */
    @Override
    boolean search(Node initialState, Result result) {
        frontier.push(initialState);
        frontierSet.add(initialState.getState());

        while(!frontier.isEmpty()){
            Node state=frontier.pop();
            frontierSet.remove(state.getState());
            explored.add(state.getState());
            if(goalTest(state)){
                getGoalNode().setParent(state.getParent());
                result.setNodesExpanded(explored.size());
                return true;
            }
            for(Node neighbor : state.neighbors()){
                if(!explored.contains(neighbor.getState()) && !frontierSet.contains(neighbor.getState())) {
                	neighbor.setParent(state);
                    frontier.add(neighbor);
                    frontierSet.add(neighbor.getState());
                }
            }
        }
        result.setNodesExpanded(explored.size());
        return false;
    }
    
}
