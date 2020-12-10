# CacheLFU
Implementation in Java using HashMap and Minimum Binary Heap

Cache data structure is implemented using a least frequently used (LFU) frame replacement policy. This is done with a cooperating pair of data structures: a HashMap (from Java collections) and a Minimum Binary Heap which I wrote. The HashMap makes the heap operations more efficient than a heap alone. To allow hasing access to heap elements, the heap array contains pointers to dynamically allocated cells. One of the fields in an object is a priority number used to structure the heap. The other fields are data for the application. 
