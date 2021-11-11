package Algorithms;

import java.util.*;

import Utils.Node;

public class DFS extends Algorithm {
    Stack<Node> frontier;
    public DFS(){
        super();
        frontier=new Stack<>();
    }
    @Override
    boolean search(Node initialState) {
        frontier.push(initialState);

        while(!frontier.isEmpty()){
            Node state=frontier.pop();
            explored.add(state);
            if(goalTest(state)){
                getGoalNode().setParent(state.getParent());
                return true;
            }
            for(Node neighbor : state.neighbors()){
                if(!explored.contains(neighbor) && !frontier.contains(neighbor)) {
                	neighbor.setParent(state);
                    frontier.add(neighbor);
                	
                }
            }
        }
        return false;
    }
    
}
