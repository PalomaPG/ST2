package node;
import suffixtree.*;

public class Edge {
	
	private int start;
	private End end;
	private Node child;
	
	public Edge(int start, End end, Node child){
		
		this.start = start;
		this.end = end;
		this.child = child;
	}

	public End getEnd() {
		return end;
	}

	public void setEnd(End end) {

		this.end = end;

	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	

	public Node getChild() {
		return child;
	}

	public void setChild(Node child) {
		this.child = child;
	}


	public String getLabel(String text) {
		// TODO Auto-generated method stub
		return text.substring(start, end.getEnd()+1);
	}


}
