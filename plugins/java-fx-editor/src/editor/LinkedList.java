package editor;

public class LinkedList<Item>{
	// sentinel.next points to the first element, sentinel.prev points to the last element
    private final Node sentinel = new Node(null, null, null);
    private int size = 0;

     public class Node {
        public Node prev;
        public Item item;
        public Node next;

        public Node(Node p, Item i, Node n) {
        	if(p == null && n == null){
        		// sentinel node
        		prev = this;
        		next = this;
        	}else{
        		if(i == null){
        			System.out.println("TEXT is NULL");
        			Thread.dumpStack();
        		}
	            prev = p;
	            prev.next = this;
	            item = i;
	            next = n;
	            next.prev = this;
        	}
        }
    }
     
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Item element) {
        if(sentinel.prev == sentinel) {
        	new Node(sentinel, element, sentinel);
        } else {
        	new Node(sentinel.prev, element, sentinel);
        }
        size++;
    }

    public void clear() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public Item get(int index) {
        Node node = getNode(index);
        if (node != null) {
            return node.item;
        } 
        return null;
    }

    public void add(int index, Item element) {
        if(size == index){
            add(element);
            return;
        } else if(index < size/2){
	        int i = 0;
	        Node node = sentinel.next;
	        while(node != sentinel) {
	            if(i == index) {
	                // inserting newNode before node 
	                Node prevNode = node.prev;
	                new Node(prevNode, element, node);
	                size++;
	                return;
	            }
	            node = node.next;
	            i++;
	        }
        }else{
	        int i = size-1;
	        Node node = sentinel.prev;
	        while(node != sentinel) {
	            if(i == index) {
	                // inserting newNode before node 
	                Node prevNode = node.prev;
	                new Node(prevNode, element, node);
	                size++;
	                return;
	            }
	            node = node.prev;
	            i--;
	        }
        }
    }

    public Item remove(int index) {
    	if(index < size/2){
	        int i = 0;
	        Node node = sentinel.next;
	        while(node != sentinel) {
	            if(i == index){
	                Node prevNode = node.prev;
	                Node nextNode = node.next;
	                prevNode.next = nextNode;
	                nextNode.prev = prevNode;
	                size--;
	                return node.item;
	            }
	            node = node.next;
	            i++;
	        }
    	}else{
	        int i = size-1;
	        Node node = sentinel.prev;
	        while(node != sentinel) {
	            if(i == index){
	                Node prevNode = node.prev;
	                Node nextNode = node.next;
	                prevNode.next = nextNode;
	                nextNode.prev = prevNode;
	                size--;
	                return node.item;
	            }
	            node = node.prev;
	            i--;
	        }
    	}
        return null;
    }

    public Node getNode(int index) {
        if(index < size/2){
        	int i = 0;
        	Node node = sentinel.next;
        	while(node != sentinel) {
        		if(i == index) {
        			return node;
        		}
        		node = node.next;
        		i++;
        	}
        }else{
        	int i = size-1;
        	Node node = sentinel.prev;
        	while(node != sentinel) {
        		if(i == index) {
        			return node;
        		}
        		node = node.prev;
        		i--;
        	}
        	
        }
        return null;
    }
   
}