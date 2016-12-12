package node;

import java.util.HashMap;

public class NonLeaf extends Node {
	
	
	private SuffixLink slink;
	private HashMap<Character, Edge> children;
	
	public NonLeaf(){

		children = new HashMap<Character, Edge>();
		slink = null;
	}
	
	public NonLeaf(NonLeaf sl_node){
		
		children = new HashMap<Character, Edge>();
		slink = new SuffixLink(sl_node);
	}
	
	public HashMap<Character, Edge> getChildren(){
		return children;
	}
	
	public void addEntry(char c, Edge edge){
		if(children.get(c)== null)
			children.put(c, edge);
	}
	
	public void setSLink(NonLeaf node){
		slink = new SuffixLink(node);
	}
	
	public SuffixLink getSLink(){
		return slink;
	}

	@Override
	public void extension(char c) {
		// TODO Auto-generated method stub
		
	}
	
}
