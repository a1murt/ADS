Alnur Kengesbay, SE-2511

Assignment 2 - Banking System (Physical & Logical Data Structures)

Part 1 - Logical Data Structures

Task 1 - Bank Account Storage (LinkedList)

Made BankAccount class with accountNumber, username, balance. Stored in LinkedList. Can add new account, display all, search by username.



Task 2 - Deposit & Withdraw

Extended task 1, added deposit and withdraw. Balance updates directly in LinkedList.



Task 3 - Transaction History (Stack)

Used Stack to store all transactions. Can view last transaction with peek, undo with pop.



Task 4 - Bill Payment Queue

Queue using LinkedList for bills. Add bill to queue, process next one, display remaining.



Task 5 - Account Opening Queue

Queue for account requests. User submits, admin processes and moves to main LinkedList.



Part 2 - Physical Data Structures

Task 6 - Array

3 predefined BankAccount objects loaded on startup into the LinkedList, demonstrates array-based initialization.


Part 3 - Mini Banking Menu

Integrated all tasks into one menu: Bank (deposit, withdraw, bill payment, account request, history), ATM (balance check, withdraw), Admin (process account queue, process bill queue).


Work process

Started with BankAccount class, then built each task separately and combined into one Main class with menus. Used LinkedList for accounts because its easy to add/remove, Stack for history because last transaction should be undone first (LIFO), Queue for bills and requests because first come first served (FIFO).

![1a7a0107-f54d-43f7-a03e-6c6c5096150a](https://github.com/user-attachments/assets/49151490-dcc1-4915-9e59-b4cdf989b5e3)
![c7e642c9-e3e7-4e3a-9f81-52cf3002387f](https://github.com/user-attachments/assets/5a857637-c5bb-4966-9c5c-fd684bb41d2e)
![4a8fe79d-8181-48c6-a6ca-cf65736e11c5](https://github.com/user-attachments/assets/191fe1a3-b0b8-4f62-9f86-d6a40962fec5)
![30ae5e50-180d-410f-ba7d-f293314f0c1f](https://github.com/user-attachments/assets/1d27f0e4-e46c-4970-8971-cfd384188c79)
![538dc290-d5f8-48f3-9526-1a11e3d3df39](https://github.com/user-attachments/assets/12c1de0a-6d13-42dd-905f-1590697675d8)
![e844557c-284a-4826-9056-b8a8bde38cdd](https://github.com/user-attachments/assets/26232726-97a0-435b-848c-b69d2cef62f0)

