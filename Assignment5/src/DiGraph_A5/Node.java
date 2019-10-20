package DiGraph_A5;


import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;


public class Node {

public long idNum;
public String label;
public Map<String, Edge> awayEdge; //Edge leaving and label of destination node 
public Map<String, Edge> intoEdge; //Edge coming in and label of source node
public HashSet<Long> intoEdgesSet; //Set of incoming edges ID's
public HashSet<Long> awayEdgesSet; //Set of leaving edges ID's
public long distance;
public boolean known;



	public Node(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
		
		intoEdge = new HashMap<String, Edge>();
		intoEdgesSet = new HashSet<Long>();
		awayEdge = new HashMap<String, Edge>();
		awayEdgesSet = new HashSet<Long>();
		distance = Integer.MAX_VALUE; //like saying infinity
		known = false;
	}
	
	
	
}
