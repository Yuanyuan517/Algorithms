### Union Find   
Union-find algorithm is an algoríthm performing two operations-Union and Find on  a disjoint-set data structure:  
- **disjoint-set data structure**: a data structure that tracks a set of elements partitioned  into a number of disjoint (non-overlapping) subsets [from wiki](https://en.wikipedia.org/wiki/Disjoint-set_data_structure "wiki").
- **find**: determinie which subset the element is in. 
- **union**: join two subsets into a single subset [from geeksforgeeks](https://www.geeksforgeeks.org/union-find/ "from geeksforgeeks").

#### Implementations: 
All based on a site-indexed array [algs.cs.princeton](https://algs4.cs.princeton.edu/15uf/ "algs.cs.princeton").
- Quick-find: all sites in a same component have same value in id[]. So quick in find (1), but it needs to update the index of the whole subset everytime in uion, so slow in union (n).  
- Quick-union: the id[] entry for for each site will be the name of another site in the same component. To implement find() we start at the given site, follow its link to another site, so forth, following links till reaching a root, a site that has a link to itself. Both union and find need time complexity to tree height. 
- Weighted quick-union: we keep track of the size of each tree and always connect the smaller tree to the larger. Both union and find: lgN. 
- weighted quick-union with path compression: Make all the nodes that we do examine directly link to the root. Very nearly but not quite 1. The amortized time per union, find, and connected operation has inverse Ackermann complexity. 

#### Remaining Questions:  
- Don't understand the 2nd while in the following ([from algs4 1.12](https://algs4.cs.princeton.edu/15uf/QuickUnionPathCompressionUF.java.html "from algs4 1.12"))
`public int find(int p) {
        int root = p;
        while (root != id[root])
            root = id[root];
        while (p != root) {
            int newp = id[p];
            id[p] = root;
            p = newp;
        }
        return root;
    }`
- Check what is inverse Ackermann complexity[stackoverflow](https://stackoverflow.com/questions/44354922/why-is-the-inverse-ackermann-function-used-to-describe-complexity-of-kruskals-a).

#### Use Scenarios: 
- detect cycles: for each edge, make two subsets using both the vertices of the edge, If both the vertices are in the same subset, a cycle is found [to do and explain better from geeksforgeeks](https://www.geeksforgeeks.org/union-find/ "from geeksforgeeks").. 

### Interview Questions 
**1 Social network connectivity.**   
Given a social network containing nn members   
and a log file containing mm timestamps at which times pairs of members   
formed friendships, design an algorithm to determine the earliest time at   
which all members are connected (i.e., every member is a friend of a friend   
of a friend ... of a friend). Assume that the log file is sorted by timestamp  
and that friendship is an equivalence relation. The running time of your algorithm  
should be m \log nmlogn or better and use extra space proportional to nn.  

**Answer**  
Keyword: Union-find  
Use union-find data structure to connect all components according to the log file  
(the file content is sorted by timestamps), when the number of components decreased  
to 1, it means all members are connected and the timestamp is the earliest time when  
all members are connected.  


**2 Union-find with specific canonical element.**    
Add a method \mathtt{find()}find() to  
the union-find data type so that \mathtt{find(i)}find(i) returns the largest element  
in the connected component containing ii. The operations, \mathtt{union()}union(),   
\mathtt{connected()}connected(), and \mathtt{find()}find() should all take logarithmic  
time or better.  
For example, if one of the connected components is \{1, 2, 6, 9\}{1,2,6,9}, then the   
\mathtt{find()}find() method should return 99 for each of the four elements in the   
connected components.  

**Answer**   
hint: maintain an extra array to the weighted quick-union data structure that stores  
for each root \mathtt{i}i the large element in the connected component containing \mathtt{i}i  

add an array to contain the larger number when union 2 components, and we only store the value  
in the root node, every time, we just need to traverse to the root node, we will get the max   
number of components containing i.(lgN)  
[link](https://truongtx.me/2018/05/01/union-find-summary-part-5)  

**3 Successor with delete.** .  
Given a set of nn integers S = \{ 0, 1, ... , n-1 \}  
S={0,1,...,n−1} and a sequence of requests of the following form:  
Remove xx from SS
Find the successor of xx: the smallest yy in SS such that y \ge xy≥x.
design a data type so that all operations (except construction) take logarithmic time or   
better in the worst case.    

