import java.util.*;

public class ExpenseTracker {

    static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== EXPENSE TRACKER =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View History");
            System.out.println("4. Daily Report");
            System.out.println("5. Monthly Report");
            System.out.println("6. Yearly Report");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            if (choice == 1) addTransaction("Income");
            else if (choice == 2) addTransaction("Expense");
            else if (choice == 3) viewHistory();
            else if (choice == 4) dailyReport();
            else if (choice == 5) monthlyReport();
            else if (choice == 6) yearlyReport();
            else break;
        }
    }

    // ---------- ADD INCOME / EXPENSE ----------
    static void addTransaction(String type) {

        System.out.print("Enter category (Food/Rent/Travel/Entertainment/Other): ");
        String category = sc.next();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        System.out.print("Enter date (DD-MM-YYYY): ");
        String date = sc.next();

        transactions.add(new Transaction(type, category, amount, date));
        System.out.println(type + " added successfully!");
    }

    // ---------- VIEW HISTORY ----------
    static void viewHistory() {

        System.out.println("\n--- Transaction History ---");

        if (transactions.isEmpty()) {
            System.out.println("No records found!");
            return;
        }

        for (Transaction t : transactions) {
            t.showTransaction();
        }
    }

    // ---------- DAILY REPORT ----------
    static void dailyReport() {

        System.out.print("Enter date (DD-MM-YYYY): ");
        String today = sc.next();

        double total = 0;

        for (Transaction t : transactions) {
            if (t.date.equals(today)) {
                total += t.amount;
            }
        }

        System.out.println("Total for " + today + " = " + total);
    }

    // ---------- MONTHLY REPORT ----------
    static void monthlyReport() {

        System.out.print("Enter month-year (MM-YYYY): ");
        String monthYear = sc.next();

        double total = 0;

        for (Transaction t : transactions) {
            if (t.date.substring(3).equals(monthYear)) {
                total += t.amount;
            }
        }

        System.out.println("Total for " + monthYear + " = " + total);
    }

    // ---------- YEARLY REPORT ----------
    static void yearlyReport() {

        System.out.print("Enter year (YYYY): ");
        String year = sc.next();

        double total = 0;

        for (Transaction t : transactions) {
            if (t.date.endsWith(year)) {
                total += t.amount;
            }
        }

        System.out.println("Total for year " + year + " = " + total);
    }
}
