import java.util.Scanner;
import java.util.Random;

// Class for storing Bank Account info
class BankAccount {
    private String accountHolderName;
    private int accountNumber;
    private double balance;

    // Constructor to initalize account 
    public BankAccount(String name, double balance) {
        this.accountHolderName = name;
        this.balance = balance;
        this.accountNumber = accountNumber(); // Calls method to create random account number
    }

    // Method to generate random 5-digit account number
    private int accountNumber() {
        Random rand = new Random();
        return 10000 + rand.nextInt(90000); // Number is generated from 10000 - 99999
    }

    // Method for depositing money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Balance has been updated. New Balance: $" + balance);
        } else {
            System.out.println("Invalid amount. Amount must be positive."); // Error if amount is negative
        }   
    }
    
    // Method for withdrawing money
    public void withdraw(double amount) {
        if (amount > 0 ) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Withdraw successful. Balance has been updated. New Balance: $" + balance); 
            } else { // Error if attempting to withdraw more from bank account than available
                System.out.println("You cannot withdraw more from your account than you have! Try again. Current Balance: $" + balance);
            }
        } else { // Error if   withdraw amount is negative
            System.out.println("Invalid amount. Amount must be positive.");
        }
    }
    
    // Getter method for balance
    public double getBalance() {
        return balance;
    }

    // Getter method for account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // Getter method for account name
    public String getAccountHolderName() {
        return accountHolderName;
    }
}

// Main class
public class SimpleBankingSystem {
    private static Scanner scanner = new Scanner(System.in); // Creates scanner to read user input
    private static BankAccount account = null; // Creates empty Account ready to store info
    public static void main(String[] args) {
        while (true) {
            // Creates menu for user in an infinite loop with 5 options navigated by pressing 1-5
            System.out.println("\nWelcome to Simple Bank System");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            // Error if a number 1-5 is not pressed
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number 1-5.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt(); // Reads choice

            // Switch method calling different methods depending on user's input
            switch (choice) {
                case 1:
                    createAccount(); // Calls account creation method 
                    break;
                case 2: // Calls deposit method
                    if (account != null) {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                    } else { // Checks if there is an account available to store info first
                        System.out.println("Please make an account first.");
                    }
                    break;
                case 3: // Calls withdraw method
                    if (account != null) {
                        System.out.print("Enter withdraw amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Please make an account first.");
                    }
                    break;
                case 4: // Calls balance method
                    if (account != null) {
                        System.out.print("Current Balance: $" + account.getBalance());
                    } else {
                        System.out.println("Please make an account first.");
                    }
                    break;
                case 5: // Exits system and prints "Thank you" message
                    System.out.println("Thank you for using Simple Banking System! Go make some more money!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Please pick a number 1-5.");
            }
        } 
    }
    
    // Method to create Bank Account
    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");

        // Checks for valid initial deposit 
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.next();
        }

        // Creates new account with name, initial deposit, and random account number
        double initialDeposit = scanner.nextDouble();
        account = new BankAccount(name, initialDeposit);
        System.out.println("Account has been created.");
        System.out.println("Account Holder: "+ account.getAccountHolderName());
        System.out.println("Account Number:" + account.getAccountNumber());
        System.out.println("Initial Balance: $" + account.getBalance());
    }
}
