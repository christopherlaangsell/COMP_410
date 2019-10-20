/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel; // this will be the entry point to your linked list (the head)
	public int count = 0;
	public LinkedListImpl() {// this constructor is needed for testing purposes. Please don't modify!
		sentinel = new Node(0); // Note that the root's data is not a true part of your data set!
	}

	// implement all methods in interface, and include the getRoot method we made
	// for testing purposes. Feel free to implement private helper methods!

	public boolean insert(double elt, int index) {
		Node temp = sentinel.next;
		Node newNode = new Node(elt);
		if(index > count) {
			return false;
		}
		if(index<0) {
			return false;
		}
		if(count == 0) {
			sentinel.next = newNode;
			newNode.next = sentinel;
			newNode.prev = sentinel;
			sentinel.prev = newNode;
			count++;
			return true;
		}
		
		if(count == index) {
			newNode.next = sentinel;
			newNode.prev = sentinel.prev;
			sentinel.prev.next = newNode;
			sentinel.prev = newNode;
			count++;
			return true;
			
		}
		
		for (int i = 0; i < size(); i++) {
			if(index == i) {
				newNode.next = temp;
				newNode.prev = temp.prev;
				temp.prev.next = newNode;
				temp.prev = newNode;
				count++;
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	
		
		
		
		
				
		
		
	}

	public boolean remove(int index) {
		Node temp = sentinel.next;
		if(index > count) {
			return false;
		}
		if(index<0) {
			return false;
		}
		
		for (int i = 0; i < size(); i++) {
			if(index == i) {
				Node temp2 = temp.prev;
				temp2.next = temp.next;
				temp.next.prev = temp2;
				count--;
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	public double get(int index) {
		
		if(index>count) {
			return Double.NaN;
		}
		Node temp = sentinel.getNext();

		for (int i = 0; i < size(); i++) {
			if(index == i) {
				return temp.getData();
			}
			temp = temp.getNext();
		}
		return Double.NaN;

	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		if(size() == 0) {
			return true;
		}
		return false;
	}

	public void clear() {
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		count = 0;
	}

	public Node getRoot() { // leave this method as is, used by the grader to grab your linkedList easily.
		return sentinel;
	}

}
