package DiGraph_A5;

public class Edge {

	
	
	public long idNum;
	public String sLabel;
	public String dLabel;
	public long weight;
	public String eLabel;
	
	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum;
		this.sLabel = sLabel;
		this.dLabel = dLabel;
		this.weight = weight;
		this.eLabel = eLabel;
	}
	/*
	public boolean samePath(Edge a, Edge b) {
		if(a.sLabel.equals(b.sLabel) && a.dLabel.equals(b.dLabel)) {
			return true;
		} else
			return false;
	}*/
	
}
