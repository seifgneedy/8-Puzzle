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
    boolean search(Node initialState) {
        frontier.add(initialState);
        while(!frontier.isEmpty()){
            Node state=frontier.poll();
            explored.add(state);
            if(goalTest(state)){
                getGoalNode().setParent(state.getParent());
                return true;
            }
            for(Node neighbor : state.neighbors()){
                if(!frontier.contains(neighbor)&&!explored.contains(neighbor)) {
                	neighbor.setParent(state);
                    frontier.add(neighbor);
                }
            }
        }
        return false;
    }


}
