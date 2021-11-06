package Utils;
import java.util.*;
public class Node {
    // NOTE : the array must be 2D to be able to implement hashCode() so plz leave it
    byte[] state;
    Node parent;
    private final int SIZE=9;
    Node(){
        state=new byte[SIZE];
    }
    public Node(byte[] state){
        this.state=state;
    }
    public Node(Node parent){
        this();
        this.parent=parent;
    }

    public List<Node> neighbors(){
        //TODO : Implement valid neighbors of this state
        return null;
    }

    public Node getParent(){
        return parent;
    }
    public void setParent(Node parent){
        this.parent=parent;
    }
    @Override
    public int hashCode(){
        return Arrays.hashCode(state);
    }
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Node))
            return false;
        return Arrays.equals(this.state, ((Node) object).state);
    }
}