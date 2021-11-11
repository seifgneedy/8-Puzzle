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

    public List<Node> solve(Node initialState){
        if(!search(initialState)){		// first make sure state is valid and solvable (Later after testing)
            System.out.println("Failed to solve");
            return null;
        }
        return getPath();
    }

    abstract boolean search(Node initialState);

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
