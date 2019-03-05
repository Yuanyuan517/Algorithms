**Interview Questions**  
1. Social network connectivity. Given a social network containing nn members   
and a log file containing mm timestamps at which times pairs of members   
formed friendships, design an algorithm to determine the earliest time at   
which all members are connected (i.e., every member is a friend of a friend   
of a friend ... of a friend). Assume that the log file is sorted by timestamp  
and that friendship is an equivalence relation. The running time of your algorithm  
should be m \log nmlogn or better and use extra space proportional to nn.  
Answer:  
Keyword: Union-find  
Use union-find data structure to connect all components according to the log file  
(the file content is sorted by timestamps), when the number of components decreased  
to 1, it means all members are connected and the timestamp is the earliest time when  
all members are connected.  

2. Union-find with specific canonical element. Add a method \mathtt{find()}find() to  
the union-find data type so that \mathtt{find(i)}find(i) returns the largest element  
in the connected component containing ii. The operations, \mathtt{union()}union(),   
\mathtt{connected()}connected(), and \mathtt{find()}find() should all take logarithmic  
time or better.  
For example, if one of the connected components is \{1, 2, 6, 9\}{1,2,6,9}, then the   
\mathtt{find()}find() method should return 99 for each of the four elements in the   
connected components.  
Answer:  
hint: maintain an extra array to the weighted quick-union data structure that stores  
for each root \mathtt{i}i the large element in the connected component containing \mathtt{i}i  

