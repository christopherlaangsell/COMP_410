package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
  }
 
  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  /*
  public BST_Node containsNode(String s){ return false; } //note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
  public BST_Node insertNode(String s){ return false; } //Really same logic as above note
  public boolean removeNode(String s){ return false; } //I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST
  public BST_Node findMin(){ return left; } 
  public BST_Node findMax(){ return right; }
  public int getHeight(){ return 0; }

  private void splay(BST_Node toSplay) { return false; } //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
  */

  // --- end example methods --------------------------------------

  
  

  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
 
  BST_Node Parent;
  BST_Node GParent;
  
  
  
	public BST_Node containsNode(String s) { // it was me
		if (data.equals(s)) {
			splay(this);
			return this;
		}
		if (data.compareTo(s) > 0) {// s lexiconically less than data
			if (left == null) {
				splay(this);
				return this; 
				}
			return left.containsNode(s);
		}
		if (data.compareTo(s) < 0) {
			if (right == null)
			{
				splay(this);
				return this;
			}
			return right.containsNode(s);
		}
		return this;
	}

	public BST_Node insertNode(String s) {
		if (data.compareTo(s) > 0) {
			if (left == null) {
				left = new BST_Node(s);
				BST_Node temp = left;
				left.par = this;
				splay(left);
				return temp;
			}
			return left.insertNode(s);
		}
		if (data.compareTo(s) < 0) {
			if (right == null) {
				right = new BST_Node(s);
				BST_Node temp = right;
				right.par = this;
				splay(right);
				return temp;
			}
			return right.insertNode(s);
		} else 
		{
			justMade = false;
			
			splay(this);
			return this;
		}
		
	}

	public boolean removeNode(String s) { // DIO
		BST_Node temp = containsNode(s);
		if(temp == null) {
			return false;
		}
		
		temp.data = left.findMax().data;
		left.findMax().par.right = null;
		return true;
	} 

	public BST_Node findMin() {
		if (left != null)
			return left.findMin();
		splay(this);
		return this;
	}

	public BST_Node findMax() {
		if (right != null)
			return right.findMax();
		splay(this);
		return this;
	}

	public int getHeight() {
		int l = 0;
		int r = 0;
		if (left != null)
			l += left.getHeight() + 1;
		if (right != null)
			r += right.getHeight() + 1;
		return Integer.max(l, r);
	}

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}
	
	private void splay(BST_Node node) {
		while(node.par != null ) {
			while(node.par.par != null) {
			Parent = node.par;
			GParent = node.par.par;
			
			//3 scenarios
			
			
			// zig zig R
			if(node == Parent.right && Parent == GParent.right) {
					rotateRight(Parent);
					rotateRight(node);
			}
			
			// zig zig L
			if(node == Parent.left && Parent == GParent.left) {
					rotateLeft(Parent);
					rotateLeft(node);
			}
			
			// zig zag L
			if(node == Parent.right && Parent == GParent.left) {
				rotateRight(node);
				rotateLeft(node);
			}
			
			if(node == Parent.left && Parent == GParent.right) {
				rotateLeft(node);
				rotateRight(node);
			}
			break;
		}
			// zig
			if (node.par != null) {
				if (node == node.par.left) {
					rotateLeft(node);
				} else {
					rotateRight(node);
				}
			}
		}

	}

	private void rotateRight(BST_Node node) {
		BST_Node Parent = node.par; 
		
		
		
		if(node.left != null) {
		 Parent.right = node.left;
		 Parent.right.par = Parent;
		} else {
			Parent.right = null;
		}
		
		if(Parent.par != null) {
			BST_Node GParent = Parent.par;
			if(GParent.left == Parent) {
				 GParent.left = node;
			 } else {
				 GParent.right = node;
			 } 
			node.par = GParent;
		}
		else {
			node.par = null;
		}
			
			node.left = Parent;
			Parent.par = node;
		}
		 
		
		 
		 
	

	private void rotateLeft(BST_Node node) {
		BST_Node Parent = node.par; 
		
		
		
		if(node.right != null) {
		 Parent.left = node.right;
		 Parent.left.par = Parent;
		} else {
			Parent.left = null;
		}
		
		if(Parent.par != null) {
			BST_Node GParent = Parent.par;
			if(GParent.right == Parent) {
				 GParent.right = node;
			 } else {
				 GParent.left = node;
			 } 
			node.par = GParent;
		}
		else {
			node.par = null;
		}
			
			node.right = Parent;
			Parent.par = node;
		}
	
}
