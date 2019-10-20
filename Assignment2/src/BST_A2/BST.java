package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ 
	  size=0; 
	  root=null; 
	  }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }


public boolean insert(String s) {
	if(s == null) {
		return false;
	}
	
	if(root == null) {
		root = new BST_Node(s);
		size ++;
		return true;
	} else if(root.insertNode(s)) {
		size ++;
		return true;
	} else
		return false;
}


public boolean remove(String s) {
	if(s == null) {
		return false;
	} else if(root == null) {
		return false;
	} else if(size == 1) {
		root = null;
		size--;
		return true;
	} else if(empty()) {
		return false;
	}
	else if(root.data.equals(s)) {
		String tmp = root.right.findMin().data;
		root.data = tmp;
		if(root.removeNode(tmp)) {
			size --;
			return true;
		} else
			return false;
	} 
	
	else if (root.removeNode(s)) {
		size --;
		return true;
	} else
	
	return false;
	
}


public String findMin() {
	if(size == 0) {
		return null;
	}
	return root.findMin().data;
}

public String findMax() {
	if(size == 0) {
		return null;
	}
	return root.findMax().data;
}


public boolean empty() {
	if(size == 0) {
		return true;
	} else
		return false;
}


public boolean contains(String s) {
	if(s == null) {
		return false;
	}
	return root.containsNode(s);
		
}


public int size() {
	
	return size;
}


public int height() {
	if(root == null) {
		return -1;
	} else
		return root.getHeight();
		
}

}
