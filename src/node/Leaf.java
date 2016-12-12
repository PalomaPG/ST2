package node;

public class Leaf extends Node {

	private int position;

	public Leaf(int position){
		this.position = position;
	}
	
	public Leaf(){
		position = -1;
	}
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public void extension(char c) {
		// TODO Auto-generated method stub
		return;
	}
	
	
}
