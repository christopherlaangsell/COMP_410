package DiGraph_A5;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Comparator;

public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
	
	//public int numNodes;
	//public int numEdges;
	public Map<Long, Node> nodeIDs = new HashMap<Long, Node>();
	public Map<String, Node> nodeNames = new HashMap<String, Node>();
	public Map<Long, Edge> edgeIDs = new HashMap<Long, Edge>();
	public Map<String, Edge> edgeLabels = new HashMap<String, Edge>();
	
	
	
	
  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
  }

@Override
public boolean addNode(long idNum, String label) {
	if(idNum<0) {
		return false;
	} 
	if(nodeIDs.containsKey(idNum)) {
		return false;
	}
	if(label == null) {
		return false;
	}
	if(nodeNames.containsKey(label)) {
		return false;
	}
	
	
	Node node = new Node(idNum, label);
	nodeIDs.put(idNum, node);
	nodeNames.put(label, node);
	//numNodes++;
	return true;
	
	
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	if(idNum<0) {
		return false;
	}
	if(sLabel == null || dLabel == null) {
		return false;
	}
	if(weight<1) {
		weight = 1;
	}
	if(edgeIDs.containsKey(idNum)) {
		return false;
	}
	if(!nodeNames.containsKey(sLabel)) {
		return false;
	}
	if(!nodeNames.containsKey(dLabel)) {
		return false;
	}
	
	Node source = nodeNames.get(sLabel);
	Node destination = nodeNames.get(dLabel);
	
	if(source.awayEdge.containsKey(destination.label)) {
		return false;
	}
	
	Edge edge = new Edge(idNum, sLabel, dLabel, weight, eLabel);
	edgeIDs.put(idNum, edge);
	edgeLabels.put(eLabel, edge);
	//Add in this edge as one of the incoming edges for the source node.
	source.awayEdgesSet.add(idNum);
	//source.intoEdgesSet.add(idNum);
	//Add in this edge as one of the outgoing edges for the destination node.
	destination.intoEdgesSet.add(idNum);
	//destination.awayEdgesSet.add(idNum);
	//Mark leaving edge with destination label and edge name
	source.awayEdge.put(dLabel,  edge);
	//Mark incoming edge with source label and edge name
	destination.intoEdge.put(sLabel, edge);
	//numEdges++;
	return true;
}

@Override
public boolean delNode(String label) {
	if(!nodeNames.containsKey(label)) {
		return false;
	}
	if(label == null) {
		return false;
	}
	//Most efficient way to remove edges is using set and remove all method.
	Node temp = nodeNames.get(label);
	
//	nodeIDs.remove(temp.idNum);
//	temp.intoEdge.remove(temp.label);
//	temp.awayEdge.remove(temp.label);
	
	edgeIDs.keySet().removeAll(nodeNames.get(label).awayEdgesSet);
	edgeIDs.keySet().removeAll(nodeNames.get(label).intoEdgesSet);
	
	nodeNames.get(temp.label).awayEdge.clear();
	nodeNames.get(temp.label).intoEdge.clear();
	nodeIDs.remove(temp.idNum);
	nodeNames.remove(temp.label);
	//numNodes--;
	return true;
	
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	if(!nodeNames.containsKey(sLabel)) {
		return false;
	}
	if(!nodeNames.containsKey(dLabel)) {
		return false;
	}
	//these edges don't exist
	if(!nodeNames.get(sLabel).awayEdge.containsKey(dLabel)) {
		return false;
	}
	if(!nodeNames.get(dLabel).intoEdge.containsKey(sLabel)) {
		return false;
	}
	
	Node tempSource = nodeNames.get(sLabel);
	Node tempEnd = nodeNames.get(dLabel);
	
	long temp = nodeNames.get(tempSource.label).awayEdge.get(tempEnd.label).idNum;
	
	String tempLabel = edgeIDs.get(temp).eLabel;
	edgeIDs.remove(temp);
	edgeLabels.remove(tempLabel);
	//taking care of connections
	nodeNames.get(sLabel).awayEdge.remove(dLabel);
	nodeNames.get(dLabel).intoEdge.remove(sLabel);
	//clear sets of edge
	nodeNames.get(sLabel).awayEdgesSet.remove(temp);
	nodeNames.get(dLabel).awayEdgesSet.remove(temp);
	return true;
	
}

@Override
public long numNodes() {
	return nodeIDs.size();
}

@Override
public long numEdges() {
	return edgeIDs.size();
}

public void print() {
	System.out.println(Arrays.asList(nodeNames));
}
public class  CompareOrder implements Comparator<Node> {
	public int compare(Node v1, Node v2) {
		return (int)(v1.distance-v2.distance);
	}
	
	public CompareOrder() {
		
	}
}


@Override
public ShortestPathInfo[] shortestPath(String label) {
	
	
	
	//-------------------------------------
	
	if(!nodeNames.containsKey(label)) {
		return null;
	}
	if(label == null) {
		return null;
	}
	Node start = nodeNames.get(label);
	CompareOrder comp = new CompareOrder();
	PriorityQueue<Node> PQ = new PriorityQueue<Node>(nodeNames.size(),comp);
	start.distance = 0;
	PQ.add(start);
	while(PQ.size()!=0) {
		Node n = PQ.peek();
		long d = n.distance;
		PQ.remove();
		if(n.known == false) {
			n.known = true;
			for(int i = 0; i< n.awayEdge.size(); i++ ) {
				Node a = nodeNames.get(n.awayEdge.keySet().toArray()[i]);
				if(a.known == false) {
					if(a.distance > (d+n.awayEdge.get(a.label).weight)) {
						a.distance = d+n.awayEdge.get(a.label).weight;
						PQ.add(a);
					}
				}
			}
		
		}
		
		
		
	}
	
	ShortestPathInfo[] shortestPaths = new ShortestPathInfo[nodeIDs.size()];
	Node[] difpaths = new Node[nodeIDs.size()];
	nodeNames.values().toArray(difpaths);
	
	for (int i = 0; i< difpaths.length; i++) {
		if( difpaths[i].distance == Integer.MAX_VALUE) {
			difpaths[i].distance = -1;
		}
		shortestPaths[i] = new ShortestPathInfo(difpaths[i].label, difpaths[i].distance);
	}
	
	return shortestPaths;
	
}
  
  // rest of your code to implement the various operations
}