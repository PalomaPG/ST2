package suffixtree;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import node.*;

public class SuffixTree {
	
	


	private NonLeaf root;
	private String text;
	private int remaining;
	private End end;
	private NonLeaf active_node;
	private Edge active_edge;
	private int active_length;
	private HashMap<Integer,SuffixLink> sl_register;
	
	public SuffixTree(String text){
		this.text = text;
		root = new NonLeaf();
		remaining = 0;
		active_node = root;
		active_edge = null;
		active_length = 0;
		end = new End(-1);
		sl_register = new HashMap<Integer, SuffixLink>();
	}

	
	public void ukkonen(){
		
		for(int i=0; i<text.length(); i++){
			end.incrementEnd();
			remaining++;
			extension(i);
		}
	}

	
	private void extension(int i){
		
		char c = text.charAt(i);

		if(i==0){
			root.addEntry(c, new Edge(i, end, new Leaf()));
		}
		
		else{ //i>0
			
			if(active_edge==null){/**/
				if(!root.getChildren().containsKey(c)){
					root.addEntry(c, new Edge(i, end, new Leaf()));
					remaining--;
				}
				else {
					
					active_edge = active_node.getChildren().get(text.charAt(i));
					active_length++;
				}
				
			}
			
			else{
				
				while(active_length>0){
				
					
					String s = active_edge.getLabel(text);
					
					//if(text.charAt(i)=='a') System.out.println(s);
					//System.out.println(active_length);
					/*Rule 3*/
					if(s.charAt(active_length)==text.charAt(i)){ break;}
					System.err.println(active_edge.getLabel(text));
					System.err.println(text.charAt(i-active_length));
					System.err.println(s);
					NonLeaf new_node = new NonLeaf(root);
					/**/
					Edge new_edge_1 = new Edge(active_edge.getStart()+active_length,end, new Leaf());
					Edge new_edge_2 = new Edge(i, end, new Leaf());

					new_node.addEntry(text.charAt(new_edge_1.getStart()), new_edge_1);
					new_node.addEntry(text.charAt(i), new_edge_2);
				
					active_edge.setChild(new_node);
					active_edge.setEnd(new End(active_edge.getStart()+active_length-1));
					active_node.addEntry(text.charAt(active_edge.getStart()), active_edge);
					remaining--;
					active_length--;
					
					active_edge = active_node.getChildren().get(text.charAt(i-active_length));
					
					
				}
				/*Rule 2*/
				if(!root.getChildren().containsKey(c)){
					root.addEntry(c, new Edge(i, end, new Leaf()));
					remaining--;
				}
				else {
					active_length++;
					
					
				}
			}
		}
	}
	/*private void extension(int i) {
		
		if(active_edge == null){
			
			if(active_node == root && active_length==0){
					if(!root.getChildren().containsKey(text.charAt(i))){
						root.addEntry(text.charAt(i), new Edge(i,end, new Leaf()));
						remaining--;
					}
					else {
						active_edge = root.getChildren().get(text.charAt(i));
						active_length++;
					}
			}

		}
		else {
			if(active_node == root){
				String s = active_edge.getKey(text);
				if(root.getChildren().get(s.charAt(0))!=null){
					if(s.charAt(active_length)==text.charAt(i)){
						active_length++;
						
					}
					else{
						NonLeaf inner_node = new NonLeaf();
						inner_node.setSLink(root);
						
						int active_edge_start =  active_edge.getStart();
						
						Edge new_edge_1 = new Edge(active_edge_start+active_length,end, new Leaf());
						Edge new_edge_2 = new Edge(i, end, new Leaf());
						
					
						inner_node.addEntry(text.charAt(new_edge_1.getStart()), new_edge_1);
						inner_node.addEntry(text.charAt(i), new_edge_2);
						active_edge.setChild(inner_node);
						active_edge.setEnd(new End(active_edge.getStart()+active_length-1));
						root.addEntry(text.charAt(active_edge.getStart()), active_edge);
						remaining--;
						
						if(!root.getChildren().containsKey(text.charAt(i))){
							root.addEntry(text.charAt(i), new Edge(i, end, new Leaf()));
							remaining--;
						}
						active_length--;
						
						if(active_length>0){
							active_edge = root.getChildren().get(text.charAt(i-active_length));
						}
					}	
				}
			}
		}
		return;
	}*/
	
	public void printST(Node n){
		
		if(n instanceof Leaf) return;
		
		else{
			HashMap<Character, Edge> children_= ((NonLeaf)n).getChildren();
			Iterator<Entry<Character, Edge>> it = children_.entrySet().iterator();
			
			//try{
			    //PrintWriter writer = new PrintWriter("output/tree-printing.txt", "UTF-8");
			    while(it.hasNext()){
			    	Entry<Character, Edge> pair = it.next();
			    	System.out.println("Key character:");
			    	System.out.println(pair.getKey());
			    	System.out.println("Edge label:");
			    	System.out.println(pair.getValue().getLabel(text));
			    	System.out.println("---------------------------------------------");
			    	printST(pair.getValue().getChild());
			    	
			    	//System.out.println(pair.getValue().getKey());
			    	}
			   // writer.close();
				//} catch (IOException e) {
				   // do something
				//}
		}
		
	}

	public Node getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	
}
