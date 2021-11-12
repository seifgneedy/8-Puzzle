package Algorithms;
import java.util.*;
import Utils.*;
public class BFS extends Algorithm {
    Queue<Node> frontier;
    public BFS(){
        super();
        frontier = new LinkedList<>();
    }

    @Override
    boolean search(Node initialState, Result result) {
        frontier.add(initialState);
        while(!frontier.isEmpty()){
            Node state=frontier.poll();
            explored.add(state);
            if(goalTest(state)){
                getGoalNode().setParent(state.getParent());
                result.setNodesExpanded(explored.size());
                return true;
            }
            for(Node neighbor : state.neighbors()){
                if(!explored.contains(neighbor) && !frontier.contains(neighbor)) {
                	neighbor.setParent(state);
                    frontier.add(neighbor);
                }
            }
        }
        result.setNodesExpanded(explored.size());
        return false;
    }


}
