package assignment4_f20;

import java.util.HashMap;

public class Cache_LFU implements Cache {
  
  HashMap<String, CacheFrame> map; 
    // allocate from java collections lib
    // do it this way so we all start with default size and 
    // default lambda and default hash function for string keys
  MinBinHeap heap; // your own heap code above
  int limit;       // max num elts the cache can hold
  int size;        // current number elts in the cache
  
  Cache_LFU (int maxElts) {
    this.map = new HashMap<String, CacheFrame>();
    this.heap = new MinBinHeap(maxElts);
    this.limit = maxElts;
    this.size = 0;
  }
  
  // dont change this we need it for grading
  public MinBinHeap getHeap() { return this.heap; }
  public HashMap getHashMap() { return this.map; }

@Override
public int size() {
	return this.limit;
}

@Override
// needs to be completed
public int numElts() {
	return heap.size();} // size of map = number key, value pairs present

@Override
public boolean isFull() {
	return (this.numElts() == this.size());
}

@Override
public boolean refer(String address) {
boolean inCache = false;
	if (map.containsKey(address) == true) {
		inCache = true;
		CacheFrame temp= map.get(address);
		heap.incElt(temp);
		return true;
	} 
		CacheFrame temp = new CacheFrame(address, 1);
	
	
	if(!inCache) { // need to add the frame
		temp = new CacheFrame (address, 1);
		if(!isFull()) { 
			heap.insert(temp); 
			map.put(address,  temp);
		} else if(isFull()) { // we need to make room for new frame by removing something
			map.remove(heap.getMin().getValue());
			heap.delMin();
			heap.insert(temp);
			map.put(address,  temp);
		}
	}
	return false;
}
  
  // =========================================================
  //
  // you fill in code for the other ops in the interface
  //
  //==========================================================
  
}
