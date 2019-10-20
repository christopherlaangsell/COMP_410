package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.

  public EntryPair[] getHeap() { 
    return this.array;
  }


public void insert(EntryPair entry) {
	this.array[size+1] = entry;
	size ++;
	
	int index = size;
	
		while (array[index / 2].getPriority() > entry.getPriority()) {

			EntryPair temp = array[index/2];
			array[index / 2] = entry;
			array[index] = temp;
			index = (int) index / 2;
		}

	}



public void delMin() {
	if(array[1]==null) {
		return;
	}
	array[1] = array[size];
	array [size] = null;
	size --;
	bubbleDown(1);
	
}

public void bubbleDown(int hole) {
	int child;
	EntryPair tmp = array[hole];
	
	for( ; hole* 2 <= size; hole = child)
	{
		child = hole * 2;
		if( child != size && array[ child + 1].priority<array[ child ].priority ) {
			child ++;
		}
		
		if( array [child ].priority < tmp.priority) {
			array [hole] = array [child];
		} else
			break;
	}
	array [ hole] = tmp;
}


public EntryPair getMin() {
	if(array[1] == null) {
		return null;
	}
	return array[1];
}


public int size() {
	return size;
}


public void build(EntryPair[] entries) {
	size = entries.length;
	for(int i=0; i < size; i++) {
		array[i+1] = entries[i];
	}
	for( int i = size/2; i>0; i--) {
		bubbleDown(i);
	}
		
	
}
}