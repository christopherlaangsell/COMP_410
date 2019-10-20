package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
	public boolean containsNode(String s) {
		if (s.equals(data)) {
			return true;
		}
		else if(data.compareTo(s)>0) {
			if(this.left == null) {
				return false;
			}
			else return this.left.containsNode(s);
		}
		else {
			if(this.right == null) {
				return false;
			}
			else
				return this.right.containsNode(s);
		}
	}
  
  public boolean insertNode(String s){
	  if(data.equals(s)) {
		  return false;
	  }
	  else if(this.data.compareTo(s)>0) {
		  if(this.left == null) {
			  this.left = new BST_Node(s);
			  return true;
		  }
		  else return this.left.insertNode(s);
	  }
	  else
	  {
		  if(this.right == null) {
			  this.right = new BST_Node(s);
			  return true;
		  }
		  else return this.right.insertNode(s);
	  }
	  }
  
  public boolean removeNode(String s){
		if (s.compareTo(data) < 0) {
			if ( right == null && left == null) {
				return false;
			}
			if (left.data.equals(s)) {
				if (left.left == null && left.right != null) {
					left = left.left;
				} else if (left.right != null && left.left != null) {
					BST_Node tmp = left.right.findMin();
					left = tmp;
					return left.removeNode(tmp.data);
				} else {
					left = null;
					return true;
				}
			} else {
				return left.removeNode(s);
			}
		} else {
			if (left == null && right == null) {
				return false;
			}
			if (right.data.equals(s)) {
				if ( right.right != null && right.left == null ) {
					right = right.right;
					
					return true;
				} else if (right.right == null && right.left != null) {
					right = right.left;
					return true;
				} else if (right.right != null && right.left != null) {
					BST_Node tmp = right.right.findMin();
					right.data = tmp.data;
					return right.removeNode(tmp.data);
				} else {
					right = null;
					return true;
				}
			} else {
				return right.removeNode(s);
			}
		}
		return true;
	}

	public BST_Node findMin(){
	  if(left !=null) {
		  return left.findMin();
	  } else  
	  return this; 
	  }
  
  public BST_Node findMax(){
	  if(right != null) {
		  return right.findMax();
	  } else
	  return this;
	  }
  
  public int getHeight(){
	  int lfit = 0;
	  int rfit = 0;
	  
	  if(left == null && right == null) {
		  return 1;
	  }
	  
	  if(left != null) {
		  lfit = left.getHeight();
	  } else
		  return 0;
	  
	  
	  if(right != null) {
		  rfit = right.getHeight();
	  } else
		  return 0;
	  
	  if(rfit>lfit) {
		  return rfit + 1;
	  } else 
		  return lfit +1;
	  
	  }
 

  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}