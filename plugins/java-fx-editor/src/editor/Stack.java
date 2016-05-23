package editor;

public class Stack<Item> extends LinkedList<Item> {
	private int sizeLimit;
	
	public Stack(int limit){
		sizeLimit = limit;
	}
	
	public void push(Item item){
		if(size() == sizeLimit){
			remove(size()-1);
		}
		add(0, item);
	}
	
	public Item pop(){
		if(!isEmpty()){
			Item item = get(0);
			remove(0);
			return item;
		}
		return null;
	}
}
