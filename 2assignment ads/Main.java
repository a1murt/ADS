import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> history = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<String> accountRequests = new LinkedList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        accounts.add(new BankAccount("001", "Ali", 150000));
        accounts.add(new BankAccount("002", "Sara", 220000));
        accounts.add(new BankAccount("003", "Mura", 90000));

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");
            System.out.print("Choose: ");
            String ch = sc.nextLine();

            if (ch.equals("1")) bankMenu();
            else if (ch.equals("2")) atmMenu();
            else if (ch.equals("3")) adminMenu();
            else if (ch.equals("4")) {
                System.out.println("Bye!");
                break;
            } else System.out.println("Invalid input");
        }
    }
    static void bankMenu() {
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1 - Submit account opening request");
            System.out.println("2 - Deposit money");
            System.out.println("3 - Withdraw money");
            System.out.println("4 - Add bill payment");
            System.out.println("5 - View transaction history");
            System.out.println("6 - Undo last transaction");
            System.out.println("7 - Display all accounts");
            System.out.println("8 - Search account");
            System.out.println("9 - Back");
            System.out.print("Choose: ");
            String ch = sc.nextLine();
            if (ch.equals("1")) {
                System.out.print("Enter your name: ");
                String n = sc.nextLine();
                accountRequests.add(n);
                System.out.println("Request submitted for: " + n);
            } else if (ch.equals("2")) {
                System.out.print("Enter username: ");
                String u = sc.nextLine();
                BankAccount a = findAcc(u);
                if (a == null) { System.out.println("Not found"); continue; }
                System.out.print("Deposit: ");
                double amt = Double.parseDouble(sc.nextLine());
                a.balance += amt;
                String t = "Deposit " + amt + " to " + u;
                history.push(t);
                System.out.println(t);
                System.out.println("New balance: " + a.balance);
            } else if (ch.equals("3")) {
                System.out.print("Enter username: ");
                String u = sc.nextLine();
                BankAccount a = findAcc(u);
                if (a == null) { System.out.println("Not found"); continue; }
                System.out.print("Withdraw: ");
                double amt = Double.parseDouble(sc.nextLine());
                if (amt > a.balance) { System.out.println("Not enough funds"); continue; }
                a.balance -= amt;
                String t = "Withdraw " + amt + " from " + u;
                history.push(t);
                System.out.println(t);
                System.out.println("New balance: " + a.balance);
            } else if (ch.equals("4")) {
                System.out.print("Bill name: ");
                String b = sc.nextLine();
                billQueue.add(b);
                System.out.println("Added: " + b);
            } else if (ch.equals("5")) {
                if (history.isEmpty()) System.out.println("No transactions");
                else {
                    System.out.println("Last transaction: " + history.peek());
                    System.out.println("All history: " + history);
                }
            } else if (ch.equals("6")) {
                if (history.isEmpty()) System.out.println("Nothing to undo");
                else System.out.println("Undo -> " + history.pop() + " removed");
            } else if (ch.equals("7")) {
                displayAccounts();
            } else if (ch.equals("8")) {
                System.out.print("Enter username to search: ");
                String u = sc.nextLine();
                BankAccount a = findAcc(u);
                if (a != null) System.out.println("Found: " + a);
                else System.out.println("Not found");
            } else if (ch.equals("9")) break;
            else System.out.println("Invalid input");
        }
    }
    static void atmMenu() {
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1 - Balance enquiry");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Back");
            System.out.print("Choose: ");
            String ch = sc.nextLine();

            if (ch.equals("1")) {
                System.out.print("Enter username: ");
                String u = sc.nextLine();
                BankAccount a = findAcc(u);
                if (a != null) System.out.println("Balance: " + a.balance);
                else System.out.println("Not found");
            } else if (ch.equals("2")) {
                System.out.print("Enter username: ");
                String u = sc.nextLine();
                BankAccount a = findAcc(u);
                if (a == null) { System.out.println("Not found"); continue; }
                System.out.print("Amount: ");
                double amt = Double.parseDouble(sc.nextLine());
                if (amt > a.balance) { System.out.println("Not enough funds"); continue; }
                a.balance -= amt;
                history.push("ATM Withdraw " + amt + " from " + u);
                System.out.println("Withdrawn. New balance: " + a.balance);
            } else if (ch.equals("3")) break;
            else System.out.println("Invalid input");
        }
    }
    static void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1 - View account requests");
            System.out.println("2 - Process account request");
            System.out.println("3 - View bill queue");
            System.out.println("4 - Process bill payment");
            System.out.println("5 - Back");
            System.out.print("Choose: ");
            String ch = sc.nextLine();

            if (ch.equals("1")) {
                if (accountRequests.isEmpty()) System.out.println("No pending requests");
                else System.out.println("Pending: " + accountRequests);
            } else if (ch.equals("2")) {
                if (accountRequests.isEmpty()) { System.out.println("Queue empty"); continue; }
                String name = accountRequests.poll();
                String accNum = String.valueOf(accounts.size() + 1);
                accounts.add(new BankAccount(accNum, name, 0));
                System.out.println("Account created for: " + name);
            } else if (ch.equals("3")) {
                if (billQueue.isEmpty()) System.out.println("No bills");
                else System.out.println("Bills: " + billQueue);
            } else if (ch.equals("4")) {
                if (billQueue.isEmpty()) { System.out.println("No bills to process"); continue; }
                System.out.println("Processing: " + billQueue.poll());
                if (!billQueue.isEmpty()) System.out.println("Remaining: " + billQueue);
                else System.out.println("All bills processed");
            } else if (ch.equals("5")) break;
            else System.out.println("Invalid input");
        }
    }
    static BankAccount findAcc(String name) {
        for (BankAccount a : accounts) {
            if (a.username.equalsIgnoreCase(name)) return a;
        }
        return null;
    }
    static void displayAccounts() {
        if (accounts.isEmpty()) { System.out.println("No accounts"); return; }
        System.out.println("Accounts List:");
        int i = 1;
        for (BankAccount a : accounts) {
            System.out.println(i + ". " + a);
            i++;
        }
    }
}
