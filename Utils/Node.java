package Utils;
import java.util.*;
public class Node {
	int state;
    Node parent;
    double Totalcost;
    int Cost=0;
    Node(){
        state=0;
        Cost=0;
    }
    public Node(int state){
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
		this.Cost=cost;
	}
    public int getCost() {
		return Cost;
	}
    public Node getParent(){
        return parent;
    }
    public void setParent(Node parent){
        this.parent=parent;
    }

    public double getTotalcost() {
    	return Totalcost;
    }
    public void setTotalcost(double total) {
    	this.Totalcost=total;
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
    	
    private int manhattanPoint(int currentPos, int destination) {
    	int x1 = currentPos / 3;
    	int x2 = destination / 3;
    	int y1 = currentPos % 3;
    	int y2 = destination % 3;
    		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
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
    
    private double euclideanPoint(int currentPos, int destination) {
    	int x1 = currentPos / 3;
    	int x2 = destination / 3;
    	int y1 = currentPos % 3;
    	int y2 = destination % 3;
    	return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    	
}
