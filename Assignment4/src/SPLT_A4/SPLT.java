package SPLT_A4;

public class SPLT implements SPLT_Interface {
	private BST_Node root;
	private int size;

	public SPLT() {
		this.size = 0;
	}

	public BST_Node getRoot() { // please keep this in here! I need your root node to test your tree!
		return root;
	}

	@Override
	public void insert(String s) { //It was me
		// TODO Auto-generated method stub
		if (empty()) {
			root = new BST_Node(s);
			size += 1;
			 
		}
		
		
			root = root.insertNode(s);
			if (root.justMade) {
				size+= 1; 
			}
		
	}

	@Override
	public void remove(String s) { // DIO
		// TODO Auto-generated method stub
		
		root = root.containsNode(s);
		BST_Node tempRight = root.right;
		BST_Node tempLeft = root.left;
		
		if(root.data.equals(s)) {
			if(size ==1) {
				size -= 1;
				root = null;
				return;
			}
			size --;
			
			if(size == 1) {
				if( root.right != null) {
					root = root.right;
					root.left = null;
					root.right = null;
					root.par = null;
				}
				else {
					root = root.left;
					root.left = null;
					root.right = null;
					root.par = null;
				}
			}
			else {
				if (tempRight != null && tempLeft != null) {
					root = tempLeft.findMax();
					root.right = tempRight;
					root.par = null;
				}
				if (tempRight == null && tempLeft != null) {
					root = tempLeft.findMax();
					root.right = null;
					root.par = null;
				}
				if (tempLeft == null && tempRight != null) {
					root = tempRight.findMin();
					root.left = null;
					root.par = null;
				}
				
			}
			
			
			
			
		}
		

	}

	@Override
	public String findMin() {
		// TODO Auto-generated method stub
		if (empty()) {
			return null;
		} else {
			return root.findMin().data;
		}
	}

	@Override
	public String findMax() {
		// TODO Auto-generated method stub
		if (empty()) {
			return null;
		} else {
			return root.findMax().data;
		}
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		if (empty()) {
			return false;
		}

		root = root.containsNode(s);
		if(root.data.equals(s)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		if (empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}
}
