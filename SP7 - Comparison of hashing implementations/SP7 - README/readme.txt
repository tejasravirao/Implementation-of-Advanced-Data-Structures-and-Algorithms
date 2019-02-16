SP7 - HASHING

SP7 32 - MEMBERS: ESHA PUNJABI, NetId: ehp170000
		  TEJAS RAVI RAO, NetId: txr171830

Date - 10/21/2018


Hashing technique implemented : Robin Hood Hashing.


IDE used Intellij IDEA 2018.2.2

- Create a new Java project.
- Inside src folder, include the package txr171830.
- Include the file "lp2-in03.txt" in Program Arguments.
       Run -> Edit Configurations -> Configurations -> Program Arguments:
	   In Program Arguments: paste the path of the file "lp2-in03.txt" (...\src\txr171830\lp2-in03.txt)

- This file includes 1 million operations of add(), contains(), remove()
- Run HashingDriver.java
- please check details (HashingDriver.java details) below for console option inputs.


------------------------------------------------------------------------------------------------------------------------------------------

HashingDriver.java details - 

- The Hashing code can be run on a file with 1 million operations(of add(), contains(), remove()).
                                     (or)
- The Hashing code can be run with user input on console (to check performance of HashSet and Custom Hashing in finding number of Distinct Elements).

Run HashingDriver.java

- Initially Input option to either 1.Run on the file or
                                   2.Run with User Input  
   "Choose option: 1. Run file with million operations  2. User Input/Check performance between HashSet and Custom Hashing Implementation on Distinct elements"

-      - if(1) is chosen above
	     - Input option to 1.Run with Custom Hashing Implementation or 
                               2.Run with Java inBuilt HashSet
                "Choose option: 1.Using Custom Implementation  2.Using Java inBuilt HashSet"
       
       - else(2) is chosen above 
	     - Input option 1. add() - to add element into HashTable
                     option 2. conatins() - check if element is in table
                     option 3. remove()- remove element from table
                     option 4. Performance check to calculate number of Distinct elements in array of Large n.
                 "Choose options: 1.Add  2.Contains  3.Remove   4.Performance check between HashSet and Custom Hashing for Distinct elements (Large n)"
		 "Please enter required number after choosing option"

             - After choosing the option add, or contains, or remove - please enter required input(number) into hash table.

- Timer is used to check and compare time taken between Java inBuilt HashSet and Custom Hashing Implementation.

--------------------------------------------------------------------------------------------------------------------------------------------

Hashing.java code details - 

- Each Hash Table location contains the element T x 
                                  and    int status - 0 for free, 1 for inserted, 2 for deleted

- Initially all locations have element = null
                          and  status  = 0

- int find(T x) - Search for x and return index of x, If x is not found then return index where x can be added.

- boolean contains(T x) - If element exists in the table then return true, if not return false.

- T remove(T x) - Return the element to be deleted if it existsin the table, if not then it returns null.
                - Mark location where deleted as status = 2.

- boolean add(T x)- Add element in the table. Mark location where added as status = 1.

- getLoadFactor() - Calculates Load factor of the table
		  - Load factor used is 0.5
		  - Rehashing done if Load factor exceeds 0.5

- void rehash(T x) - Resize the hash table by a factor of 2 (len = len * 2)
                   - reinitialize the array
                   - rehash all the existing elements of the old Hash table into the new Hash table

- int hashFunction(T x)  - returns index forx in the Hash table.

- int displacement(T x , int loc) - Calculated displacement of x from its ideal location of h(x).

- void printTable() - prints in the following format
                     "index --- element --- status" 

- void generateRandomArray(object[] arrRandom, int n) -  Create an Object array of random numbers of size n (passed in HashingDriver.java)

- int distinctElementsCustom(T[ ] arr) - Returns the number of Distinct numbers the above (Random)array has using the Custom Hashing Implementation.

- int distinctElements(T[ ] arr) - Returns the number of Distinct numbers the above (Random)array has using inbuilt Java HashSet.

------------------------------------------------------------------------------------------------------------------------------------------------




                                                       















