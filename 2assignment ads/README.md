Alnur Kengesbay, SE-2511

Assignment 2 - Banking System (Physical & Logical Data Structures)

Part 1 - Logical Data Structures

Task 1 - Bank Account Storage (LinkedList)

Made BankAccount class with accountNumber, username, balance. Stored in LinkedList. Can add new account, display all, search by username.

![task1](screenshots/task1_accounts.png)

Task 2 - Deposit & Withdraw

Extended task 1, added deposit and withdraw. Balance updates directly in LinkedList.

![task2](screenshots/task2_deposit_withdraw.png)

Task 3 - Transaction History (Stack)

Used Stack to store all transactions. Can view last transaction with peek, undo with pop.

![task3](screenshots/task3_stack_history.png)

Task 4 - Bill Payment Queue

Queue using LinkedList for bills. Add bill to queue, process next one, display remaining.

![task4](screenshots/task4_bill_queue.png)

Task 5 - Account Opening Queue

Queue for account requests. User submits, admin processes and moves to main LinkedList.

![task5](screenshots/task5_account_queue.png)

Part 2 - Physical Data Structures

Task 6 - Array

3 predefined BankAccount objects loaded on startup into the LinkedList, demonstrates array-based initialization.

![task6](screenshots/task6_array.png)

Part 3 - Mini Banking Menu

Integrated all tasks into one menu: Bank (deposit, withdraw, bill payment, account request, history), ATM (balance check, withdraw), Admin (process account queue, process bill queue).

![atm](screenshots/atm_menu.png)

Work process

Started with BankAccount class, then built each task separately and combined into one Main class with menus. Had a problem with Scanner eating newlines after nextInt so switched to nextLine everywhere and parsed with Double.parseDouble. Used LinkedList for accounts because its easy to add/remove, Stack for history because last transaction should be undone first (LIFO), Queue for bills and requests because first come first served (FIFO).
