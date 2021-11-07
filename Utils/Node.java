package Utils;
import java.util.*;
public class Node {
    // NOTE : the array must be 1D to be able to implement hashCode() so plz leave it --> NO
    int state;
    Node parent;
    Node(){
        state=0;
    }
    public Node(int state){
    	if(!isValidState(state))
    		System.out.println("Invalid State");
    		
        this.state=state;
    }
    public Node(Node parent){
        this();
        this.parent=parent;
    }

    public List<Node> neighbors(){
    	int n = 3;
    	int [] curr = new int[3*3];
    	int zeroIndex = -1;
    	ArrayList<Node>  neighbors = new ArrayList<Node>();
    	String st = Integer.toString(state);
    	if(st.length() != 9) {
    		st = '0' + st;
    	}
    	for (int i = 0; i < st.length(); i++){
    		curr[i] = st.charAt(i) - '0';
    		if(curr[i] == 0)
    			zeroIndex = i;
    	}
    	if(zeroIndex/n != 0) {		//Can Go UP
    		Node neighbor = new Node(state);
    		neighbor.swap(zeroIndex, zeroIndex-n);
    		neighbors.add(neighbor);
    	}
    	
    	if(zeroIndex/n != 2) {		//Can Go DOWN
    		Node neighbor = new Node(state);
    		neighbor.swap(zeroIndex, zeroIndex+n);
    		neighbors.add(neighbor);
    	}
    	
    	if(zeroIndex%n != 0) {		//Can Go Left
    		Node neighbor = new Node(state);
    		neighbor.swap(zeroIndex, zeroIndex-1);
    		neighbors.add(neighbor);
    	}
    	
    	if(zeroIndex%n != 2) {		//Can Go RIGHT
    		Node neighbor = new Node(state);
    		neighbor.swap(zeroIndex, zeroIndex+1);
    		neighbors.add(neighbor);
    	}
	
    	return neighbors;
    }
    

    public Node getParent(){
        return parent;
    }
    public void setParent(Node parent){
        this.parent=parent;
    }
    @Override
    public int hashCode(){
        return Integer.hashCode(state);
    }
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Node))
            return false;
        return this.state ==  ((Node) object).state;
    }
    
    public void print() {
    	String st = Integer.toString(state);
    	if(st.length() != 9) {
    		st = '0' + st;
    	}
    	for(int i=0;i<3;i++) {
    		for(int j=3*i;j<3*i+3;j++) {
    			System.out.print(st.charAt(j));
    			if(j%3 != 2)
        			System.out.print(" | ");
    			else
    				System.out.println();
    		}
    		System.out.println("---------");
    	}
		System.out.println("---------");
		System.out.println("---------");

    	    	
    	
    }
    
    private void swap (int i, int j) {
    	int [] curr = new int[3*3];
    	String st = Integer.toString(state);
    	if(st.length() != 9) {
    		st = '0' + st;
    	}
    	for (int k=0;k<st.length();k++){
    		curr[k] = st.charAt(k) - '0';
    	}
    	
		int t = curr[i];
		curr[i] = curr[j];
		curr[j] = t;
		
		
		int result = 0;
    	for (int k=0;k<st.length();k++){
		  result*=10;
		  result+=curr[k];
		}
		
		
		this.state =result;
    	
    }
    public boolean isValidState(int state) {
    	Set<Integer> curr = new HashSet<Integer>();
    	String st = Integer.toString(state);
    	if(st.length() == 8) {
    		st = '0' + st;
    	}
    	if(st.length() != 9)
    		return false;
    	for (int k=0;k<st.length();k++){
    		curr.add(st.charAt(k) - '0');
    	}
    	if(curr.size() != 9)
    		return false;
    	if(!curr.contains(0))
    		return false;
    	
    	return true;
    }
}
