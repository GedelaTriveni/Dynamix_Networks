public class Transaction {
    String type;      // Income or Expense
    String category;  // Food, Rent, etc.
    double amount;
    String date;

    public Transaction(String type, String category, double amount, String date) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public void showTransaction() {
        System.out.println(type + " | " + category + " | " + amount + " | " + date);
    }
}
