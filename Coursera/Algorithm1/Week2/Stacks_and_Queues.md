### Interview Questions:
1. Queue with two stacks. Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.  
A: 2 methods:  
(1) costly in enqueue, less costly in dequeue  
	idea: keep the oldest element on the top of a stack, which guarantee when dequeue, the oldest element is firstly out.   
	Approach: stack1 is for storing elements, every time when a new element comes in, moves the original elements into stack 2, after putting the new element into stack 1, then moves the elements back from stack 2 to stack 1.    
	Analyze: moves all elements twice when enqueue, time complexity is O(n), dequeue is O(1).  
(2) less costly in enqueue, costly in dequeue   
	idea: always enqueue into stack1, dequeue from stack2   
	Approach: in enqueue, put the element at the top of stack1, when dequeue, if stack2 is empty, first pops all elements from stack1 to stack2, then moves the top element from stack2  
	Analyze; enqueue O(1), dequeue O(n)   
2. Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.  
A. Notice that it is to design a data structure. So the idea to use an auxilliary stack to store the max element at each element is not very appropriate here. Then one possible idea is to use linklist to design the stack with one additional pointer, which points to an older max number if the current push modifies the largest max number by now.  
There is a good [video](https://www.byte-by-byte.com/maxstack/ "video") talked about it from the view of the interviewer. Some points the speaker mentioned: before solving the problem, ask the data type to be handled(Integer, String or...); last in first out?  
