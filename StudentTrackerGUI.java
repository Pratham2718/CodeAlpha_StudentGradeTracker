import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

public class StudentTrackerGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField nameField, mathField, scienceField, englishField;
    private JTextField searchField, deleteField;
    private String[] subjects;
    private String filename = "students.txt";

    public StudentTrackerGUI() {
        setTitle("Student Grade Tracker");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        List<Student> students = GradeTracker.loadStudents(filename);
        subjects = GradeTracker.subjects;

        String[] allColumns = concat(new String[]{"Name"}, subjects);
        model = new DefaultTableModel(allColumns, 0);
        for (Student s : students) {
            model.addRow(s.toRow());
        }

        table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                saveTableToFile();
            }
        });

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(10);
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Math:"), gbc);
        gbc.gridx = 1;
        mathField = new JTextField(10);
        formPanel.add(mathField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Science:"), gbc);
        gbc.gridx = 1;
        scienceField = new JTextField(10);
        formPanel.add(scienceField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("English:"), gbc);
        gbc.gridx = 1;
        englishField = new JTextField(10);
        formPanel.add(englishField, gbc);

        JButton addButton = createStyledButton("Add Student", Color.decode("#4CAF50"));
        JButton searchButton = createStyledButton("Show Stats", Color.decode("#2196F3"));
        JButton deleteButton = createStyledButton("Delete Student", Color.decode("#F44336"));
        JButton exportButton = createStyledButton("Export to CSV", Color.decode("#9C27B0"));
        JButton refreshButton = createStyledButton("Refresh Table", Color.decode("#795548"));

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(addButton, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Search Name:"), gbc);
        gbc.gridx = 1;
        searchField = new JTextField(10);
        formPanel.add(searchField, gbc);
        gbc.gridx = 2;
        formPanel.add(searchButton, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Delete Name:"), gbc);
        gbc.gridx = 1;
        deleteField = new JTextField(10);
        formPanel.add(deleteField, gbc);
        gbc.gridx = 2;
        formPanel.add(deleteButton, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(exportButton, gbc);
        gbc.gridx = 1;
        formPanel.add(refreshButton, gbc);

        add(formPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addStudent());
        searchButton.addActionListener(e -> searchStudentStats());
        deleteButton.addActionListener(e -> deleteStudent());
        exportButton.addActionListener(e -> exportToCSV());
        refreshButton.addActionListener(e -> refreshTable());
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(160, 40));
        return button;
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String math = mathField.getText().trim();
        String science = scienceField.getText().trim();
        String english = englishField.getText().trim();

        if (name.isEmpty() || math.isEmpty() || science.isEmpty() || english.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        List<Integer> grades = new ArrayList<>();
        try {
            grades.add(Integer.parseInt(math));
            grades.add(Integer.parseInt(science));
            grades.add(Integer.parseInt(english));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for all grades.");
            return;
        }

        Student student = new Student(name, grades);
        model.addRow(student.toRow());
        GradeTracker.saveStudent(filename, student);
        JOptionPane.showMessageDialog(this, "Student added successfully!");

        nameField.setText("");
        mathField.setText("");
        scienceField.setText("");
        englishField.setText("");
    }

    private void searchStudentStats() {
        String searchName = searchField.getText().trim();
        if (searchName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a student name.");
            return;
        }

        boolean found = false;
        for (int row = 0; row < model.getRowCount(); row++) {
            String studentName = model.getValueAt(row, 0).toString();
            if (studentName.equalsIgnoreCase(searchName)) {
                List<Integer> grades = new ArrayList<>();
                for (int col = 1; col <= subjects.length; col++) {
                    grades.add(Integer.parseInt(model.getValueAt(row, col).toString()));
                }
                Student student = new Student(studentName, grades);

                String message = student.name + " Stats:\n"
                        + "Average: " + String.format("%.2f", student.getAverage()) + "\n"
                        + "Highest: " + student.getHighest() + "\n"
                        + "Lowest: " + student.getLowest();

                JOptionPane.showMessageDialog(this, message);
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Student not found.");
        }
    }

    private void deleteStudent() {
        String deleteName = deleteField.getText().trim();
        if (deleteName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name to delete.");
            return;
        }

        boolean found = false;

        for (int row = model.getRowCount() - 1; row >= 0; row--) {
            String studentName = model.getValueAt(row, 0).toString();
            if (studentName.equalsIgnoreCase(deleteName)) {
                model.removeRow(row);
                found = true;
                break;
            }
        }

        if (found) {
            saveTableToFile();
            JOptionPane.showMessageDialog(this, "Student '" + deleteName + "' deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Student not found.");
        }

        deleteField.setText("");
    }

    private void exportToCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("Exported_Student_Report.csv"))) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                pw.print(model.getColumnName(col));
                if (col < model.getColumnCount() - 1) pw.print(",");
            }
            pw.println();

            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    pw.print(model.getValueAt(row, col));
                    if (col < model.getColumnCount() - 1) pw.print(",");
                }
                pw.println();
            }

            JOptionPane.showMessageDialog(this, "Exported to Exported_Student_Report.csv successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error exporting file.");
        }
    }

    private void refreshTable() {
        List<Student> students = GradeTracker.loadStudents(filename);
        model.setRowCount(0);
        for (Student s : students) {
            model.addRow(s.toRow());
        }
        JOptionPane.showMessageDialog(this, "Table refreshed successfully.");
    }

    private void saveTableToFile() {
        List<Student> students = new ArrayList<>();
        for (int row = 0; row < model.getRowCount(); row++) {
            String name = model.getValueAt(row, 0).toString();
            List<Integer> grades = new ArrayList<>();
            for (int col = 1; col <= subjects.length; col++) {
                grades.add(Integer.parseInt(model.getValueAt(row, col).toString()));
            }
            students.add(new Student(name, grades));
        }
        GradeTracker.saveAllStudents(filename, students);
    }

    private String[] concat(String[] a, String[] b) {
        String[] result = new String[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentTrackerGUI().setVisible(true));
    }
}
