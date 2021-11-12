package Utils;
import java.util.*;
/**
 * Class to save minimal state, parent reference
 * totalCost and cost.
 * totalCost and cost are used in A*.
 */
public class Node {
	// State represented as sequence of digits
	int state;
    Node parent;
    double totalcost;
    int cost=0;
    Node(){
        state=0;
        cost=0;
    }
    public Node(int state){
        this.state=state;

    }
    public Node(Node parent){
        this();
        this.parent=parent;
    }
    /**
     * Create a list of all neighbors to this 
     * state by going up or down or left or right.
     * @return
     * Neighbors of this state.
     */
    public List<Node> neighbors(){
    	int n = 3;
    	int [] curr = new int[3*3];
    	int zeroIndex = -1;
    	LinkedList<Node>  neighbors = new LinkedList<Node>();
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
    
    public void setCost(int cost) {
		this.cost=cost;
	}
    public int getCost() {
		return cost;
	}
    
    public void setState(int state) {
		this.state=state;
	}
    public int getState() {
		return state;
	}
    
    public Node getParent(){
        return parent;
    }
    public void setParent(Node parent){
        this.parent=parent;
    }

    public double getTotalcost() {
    	return totalcost;
    }
    public void setTotalcost(double total) {
    	this.totalcost=total;
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
    @Override
    public String toString() {
    	String st = Integer.toString(state);
    	if(st.length() != 9) {
    		st = '0' + st;
    	}
    	String s = "";
    	for(int i=0;i<3;i++) {
    		for(int j=3*i;j<3*i+3;j++) {
    			s  += st.charAt(j) + " ";
    			if(j%3 != 2)
    				s+= "| ";
    			else
    				s+= "\n";
    		}
    	}
    	s += "---------\n";
		return s;
    }
    /**
     * Swap the values in the i and j index
     * in this state.
     * @param i
     * Index of the first element.
     * @param j
     * Index of the second element.
     */
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
    /**
     * Check if this state is solvable or not
     * using the number of inversions.
     * @return
     * true if solvable.
     * false otherwise.
     */
    public boolean isSolvableState() {
       	int [] curr = new int[3*3];
    	String st = Integer.toString(state);
    	if(st.length() != 9) {
    		st = '0' + st;
    	}
    	for (int i = 0; i < st.length(); i++){
    		curr[i] = st.charAt(i) - '0';
    	}
    	
        int inversionCount = 0;
        for (int i = 0; i < 9 - 1; i++)
            for (int j = i+1; j < 9; j++)
                 if (curr[i] > curr[j] && curr[i] > 0 && curr[j] > 0)
                	 inversionCount++;
    	return (inversionCount % 2 == 0);
    }
    /**
     * Check if this state is valid or not 
     * Checking for repeated digits or missing
     * digits.
     * @return
     * true if valid.
     * false otherwise.
     */
    public boolean isValidState() {
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
    /**
     * Calculate the heuristic Manhattan cost for this state
     * @return
     * The Manhattan cost.
     */
    public int manhattanCost() {
    	int cost = 0;
    	int [] curr = new int[3*3];
    	String st = Integer.toString(state);
    	if(st.length() != 9) {
    		st = '0' + st;
    	}
    	for (int i = 0; i < st.length(); i++){
    		curr[i] = st.charAt(i) - '0';
    		if(curr[i] != i && curr[i] != 0)
    			cost += manhattanPoint(i,curr[i]);
    	}
    	
    	return cost;
    }
    /**
     * Calculate the Manhattan cost for a single digit
     * @param currentPos
     * current position of digit.
     * @param destination
     * right position of the digit.
     * @return
     * Manhattan cost for the digit.
     */
    private int manhattanPoint(int currentPos, int destination) {
    	int x1 = currentPos / 3;
    	int x2 = destination / 3;
    	int y1 = currentPos % 3;
    	int y2 = destination % 3;
    		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    /**
     * Calculate the Euclidean cost for this state
     * @return
     * The Euclidean cost.
     */
    public double euclideanCost() {
    	double cost = 0;
    	int [] curr = new int[3*3];
    	String st = Integer.toString(state);
    	if(st.length() != 9) {
    		st = '0' + st;
    	}
    	for (int i = 0; i < st.length(); i++){
    		curr[i] = st.charAt(i) - '0';
    		if(curr[i] != i && curr[i] != 0)
    			cost += euclideanPoint(i,curr[i]);
    	}
    	
    	return cost;
    }
    /**
     * Calculate the Euclidean cost for a single digit
     * @param currentPos
     * current position of digit.
     * @param destination
     * right position of the digit.
     * @return
     * Euclidean cost for the digit.
     */
    private double euclideanPoint(int currentPos, int destination) {
    	int x1 = currentPos / 3;
    	int x2 = destination / 3;
    	int y1 = currentPos % 3;
    	int y2 = destination % 3;
    	return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    	
}
