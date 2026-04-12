public class BankAccount {
    String accountNumber;
    String username;
    double balance;

    BankAccount(String accNum, String name, double bal) {
        this.accountNumber = accNum;
        this.username = name;
        this.balance = bal;
    }

    public String toString() {
        return username + " - Balance: " + balance;
    }
}
