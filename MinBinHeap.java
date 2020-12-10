package assignment4_f20;

public class MinBinHeap implements Heap {
	  private CacheFrame[] array; // load this array
	  private int size;      // how many items currently in the heap
	  private int arraySize; // Everything in the array will initially
	                         // be null. This is ok! Just build out
	                         // from array[1]
	  public MinBinHeap(int nelts) {
	    this.array = new CacheFrame[nelts+1];  // remember we dont use slot 0
	    this.arraySize = nelts+1;
	    this.size = 0;
	    this.array[0] = new CacheFrame(null, 0); // 0 not used, so this is arbitrary
	  }
	  public CacheFrame[] getHeap() { return this.array; }

	public void insert(CacheFrame elt) {
		array[size() + 1] = elt;
		array[size() + 1].setSlot(size()+1);
		if(size () > 0) {
			bubbleup(elt.getSlot());
		}
		size++;
	}

	public void delMin() {
			array[1] = array[size()]; // replace root with the last element
			array[1].setSlot(1);  // set the slot 
			array[size()] = null;
			size--; // size goes down
			bubbledown(1);
		}

	public CacheFrame getMin() {
		return array[1];
	}

	public int size() {
		return size;
	}

	@Override
	public void incElt(CacheFrame elt) {
		if(elt == null) {
			return;
		} 
		elt.setPriority(elt.getPriority() + 1);
		bubbledown(elt.getSlot()); 
		
	}
	@Override
	public void decElt(CacheFrame elt) {
		if(elt == null) {
			return;
		}
		if (elt.getPriority() > 1) {
			elt.setPriority(elt.getPriority() - 1);
			bubbleup(elt.getSlot());
		} else {
			return;
		}
		
	}
	
	public int getParent(int i) {
		if ( i == 0 || i == 1) { return 0; }

		return (int)Math.floor(i/2);
	}
	public int getLC (int i ) {
		return (2*i);
	}
	public int getRC (int i) {
		return (2*i + 1);
	}
	
	public void bubbledown (int slot) {
		int LC = getLC(slot);
		int RC = getRC(slot);
		int minIndex;
		CacheFrame temp;
		if(RC >= size) {
			if(LC >= size) 
				return;
			else 
				minIndex = LC;
		}  else {
			if(array[LC].getPriority() <= array[RC].getPriority())
				minIndex = LC;
			else 
				minIndex = RC;
		}
		if(array[slot].getPriority() > array[minIndex].getPriority()) {
			temp = array[minIndex];
			array[minIndex] = array[slot];
			array[minIndex].setSlot(slot);
			array[slot] = temp;
			array[slot].setSlot(minIndex);
			bubbledown(minIndex);
		}
		
	}
	public void bubbleup(int slot) {
		if(slot != 0) {
			int parentIndex = getParent(slot);
			if(array[parentIndex].getPriority() > array[slot].getPriority() ) {
				CacheFrame temp = array[parentIndex];
				array[parentIndex] = array[slot];
				array[slot] = temp;
				array[parentIndex].setSlot(slot); 
				array[slot].setSlot(parentIndex);
				bubbleup(parentIndex);
			}
		}
		
		
	}
	

}
