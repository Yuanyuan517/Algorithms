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
What to add is that in Hint provided by Coursera:  
> Hint: Use two stacks, one to store all of the items and a second stack to store the maximums.    
So we can use 2 stacks.
3. Java generics. Explain why Java prohibits generic array creation.  
A. In the book of [Algorithms](https://algs4.cs.princeton.edu/13stacks/ "Algorithms"), 1.3, it wrote: 
> The underlying cause is that arrays in Java are covariant, but generics are not. In other words, String[] is a subtype of Object[], but Stack<String> is not a subtype of Stack<Object>.   
But I am still not very clear, then i found [someone](https://stackoverflow.com/questions/2927391/whats-the-reason-i-cant-create-generic-array-types-in-java "someone") in stackoverflow wrote:
> The reason this is impossible is that Java implements its Generics purely on the compiler level, and there is only one class file generated for each class. This is called Type Erasure.  

At runtime, the compiled class needs to handle all of its uses with the same bytecode. So, new T[capacity] would have absolutely no idea what type needs to be instantiated.  
Another better and more clear [answer](https://stackoverflow.com/a/33072474 "answer") states: in Java type information for type parameters is discarded by the compiler after compilation of code is done; therefore the type info is not available at run time. The process is called** type erasure**. The important point here is that since at run-time there is no type information, there is no way to ensure that we are not committing heap pollution.  
