package editor;

public class FastLinkedList<Item> {
	// sentinel.next points to the first element, sentinel.prev points to the
	// last element
	private final Node sentinel = new Node(null, null, null);
	private int size = 0;

	public class Node {
		public Node prev;
		public Item item;
		public Node next;

		public Node(Node p, Item i, Node n) {
			if (p == null && n == null) {
				// sentinel node
				prev = this;
				next = this;
			} else {
				if (i == null) {
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

	private Node currentNode = null;
	private int currentIndex = -1;

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(Item element) {
		if (sentinel.prev == sentinel) {
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
		if (currentIndex > 0) {
			if (currentIndex == index) {
				return currentNode.item;
			} else if (index > 0 && index == currentIndex - 1) {
				currentNode = currentNode.prev;
				currentIndex = index;
				return currentNode.item;
			} else if (index == currentIndex + 1) {
				currentNode = currentNode.next;
				currentIndex = index;
				return currentNode.item;
			}
		}
		Node node = getNode(index);
		if (node != null) {
			currentNode = node;
			currentIndex = index;
			return node.item;
		}
		return null;
	}

	public void add(int index, Item element) {
		//System.out.println("LinkedList add index - " + index);
		if (size == index) {
			add(element);
			return;
		} else if(currentIndex > 0){
			if (currentIndex == index) {
				Node prevNode = currentNode.prev;
				new Node(prevNode, element, currentNode);
				size++;
			} else if (index > 0 && index == currentIndex - 1) {
				currentNode = currentNode.prev;
				currentIndex = index;
				Node prevNode = currentNode.prev;
				new Node(prevNode, element, currentNode);
				size++;
			} else if (index == currentIndex + 1) {
				currentNode = currentNode.next;
				currentIndex = index;
				Node prevNode = currentNode.prev;
				new Node(prevNode, element, currentNode);
				size++;
			}
		} else if (index < size / 2) {
			int i = 0;
			Node node = sentinel.next;
			while (node != sentinel) {
				//System.out.println("time...");
				if (i == index) {
					// inserting newNode before node
					Node prevNode = node.prev;
					new Node(prevNode, element, node);
					size++;
					return;
				}
				node = node.next;
				i++;
			}
		} else {
			int i = size - 1;
			Node node = sentinel.prev;
			while (node != sentinel) {
				//System.out.println("time...");
				if (i == index) {
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
	
	private void removeNode(Node node){
		if(node != sentinel){
			Node prevNode = node.prev;
			Node nextNode = node.next;
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
			size--;
		}else{
			System.out.println("Remove sentinel node...");
			Thread.dumpStack();
		}
	}

	public Item remove(int index) {
		//System.out.println("FastLinkedList remove size - " + size+" index -"+index);
		if (currentIndex > 0) {
			//System.out.println("currentIndex defined...");
			if (currentIndex == index) {
				//System.out.println("remove current node...");
				Node node = currentNode;
				removeNode(node);
				currentNode = null;
				currentIndex = -1;
				return node.item;
			} else if (index > 0 && index == currentIndex - 1) {
				//System.out.println("remove prev node from current...");
				if(currentNode.prev != sentinel){
					Node node = currentNode.prev;
					removeNode(node);
					return node.item;
				}else{
					return null;
				}
			} else if (index == currentIndex + 1) {
				//System.out.println("remove next node from current...");
				if(currentNode.next != sentinel){
				Node node = currentNode.next;
					removeNode(node);
					return node.item;
				}else{
					return null;
				}
			}
		}
		if (index < size / 2) {
			int i = 0;
			Node node = sentinel.next;
			while (node != sentinel) {
				//System.out.println("time...");
				if (i == index) {
					removeNode(node);
					return node.item;
				}
				node = node.next;
				i++;
			}
		} else {
			int i = size - 1;
			Node node = sentinel.prev;
			while (node != sentinel) {
				//System.out.println("time...");
				if (i == index) {
					removeNode(node);
					return node.item;
				}
				node = node.prev;
				i--;
			}
		}
		return null;
	}

	public Node getNode(int index) {
		//System.out.println("LinkedList getNode index - " + index);
		if (index < size / 2) {
			int i = 0;
			Node node = sentinel.next;
			while (node != sentinel) {
				//System.out.println("time...");
				if (i == index) {
					return node;
				}
				node = node.next;
				i++;
			}
		} else {
			int i = size - 1;
			Node node = sentinel.prev;
			while (node != sentinel) {
				//System.out.println("time...");
				if (i == index) {
					return node;
				}
				node = node.prev;
				i--;
			}

		}
		return null;
	}

}