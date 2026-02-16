import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ExpenseTrackerGUI {

    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Expense Tracker");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Type selection
        JRadioButton incomeBtn = new JRadioButton("Income");
        JRadioButton expenseBtn = new JRadioButton("Expense");
        ButtonGroup group = new ButtonGroup();
        group.add(incomeBtn);
        group.add(expenseBtn);

        // Category dropdown
        String[] categories = {"Food", "Rent", "Travel", "Entertainment", "Other"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);

        // Amount field
        JTextField amountField = new JTextField(10);

        // Date field
        JTextField dateField = new JTextField(10);

        // Buttons
        JButton addBtn = new JButton("Add Transaction");
        JButton dailyBtn = new JButton("Daily Report");
        JButton monthlyBtn = new JButton("Monthly Report");
        JButton yearlyBtn = new JButton("Yearly Report");

        // Table for history
        String[] columns = {"Type", "Category", "Amount", "Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        // Add components to frame
        frame.add(new JLabel("Type:"));
        frame.add(incomeBtn);
        frame.add(expenseBtn);

        frame.add(new JLabel("Category:"));
        frame.add(categoryBox);

        frame.add(new JLabel("Amount:"));
        frame.add(amountField);

        frame.add(new JLabel("Date (DD-MM-YYYY):"));
        frame.add(dateField);

        frame.add(addBtn);
        frame.add(dailyBtn);
        frame.add(monthlyBtn);
        frame.add(yearlyBtn);
        frame.add(scroll);

        // ---- ADD TRANSACTION ACTION ----
        addBtn.addActionListener(e -> {
            String type = incomeBtn.isSelected() ? "Income" : "Expense";
            String category = (String) categoryBox.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());
            String date = dateField.getText();

            Transaction t = new Transaction(type, category, amount, date);
            transactions.add(t);

            model.addRow(new Object[]{type, category, amount, date});

            amountField.setText("");
            dateField.setText("");
        });

        // ---- DAILY REPORT ----
        dailyBtn.addActionListener(e -> {
            String d = JOptionPane.showInputDialog("Enter date (DD-MM-YYYY):");
            double total = 0;

            for (Transaction t : transactions) {
                if (t.date.equals(d)) total += t.amount;
            }

            JOptionPane.showMessageDialog(frame,
                "Total for " + d + " = " + total);
        });

        // ---- MONTHLY REPORT ----
        monthlyBtn.addActionListener(e -> {
            String m = JOptionPane.showInputDialog("Enter month-year (MM-YYYY):");
            double total = 0;

            for (Transaction t : transactions) {
                if (t.date.substring(3).equals(m)) total += t.amount;
            }

            JOptionPane.showMessageDialog(frame,
                "Total for " + m + " = " + total);
        });

        // ---- YEARLY REPORT ----
        yearlyBtn.addActionListener(e -> {
            String y = JOptionPane.showInputDialog("Enter year (YYYY):");
            double total = 0;

            for (Transaction t : transactions) {
                if (t.date.endsWith(y)) total += t.amount;
            }

            JOptionPane.showMessageDialog(frame,
                "Total for year " + y + " = " + total);
        });

        frame.setVisible(true);
    }
}




 