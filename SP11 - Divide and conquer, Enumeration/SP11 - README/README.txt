SP11 - DIVIDE AND CONQUER, ENUMERATION
Date: 11/18/2018

SP11 TEAM MEMBERS: TEJAS RAVI RAO
                   YASH AJAY MADANE

SP11 - Implement the expected O(n) algorithm for the k largest elements (select) 
of an array and compare its performance with the algorithm using priority queues
that we designed for the same problem on streams.
Use k=n/2 (median), and try large values of n: 16M, 32M, 64M, 128M, 256M.


IDE used Intellij IDEA 2018.2.2
- Create a new Java project.
- Inside src folder, include the package txr171830.
- Change value of size (n = 16M, 32M, 64M, 128M...) accordingly in main method of code.
- Run SP11.java
- The code outputs the time taken for O(n) select algorithm that uses randomized partition
  and O(nlogk) algorithm that uses priority queues.
- All outputs have been recorded in the report file.