import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PROJEC_T extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "Admin_!@1212";

    private JTextField studentNameField, subject1Field, subject2Field, subject3Field, subject4Field, subject5Field;
    private JTextArea outputArea;

    public PROJEC_T() {
        setTitle("Student Marksheet App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        studentNameField = addField("Student Name:");
        subject1Field = addField("Subject 1 Marks:");
        subject2Field = addField("Subject 2 Marks:");
        subject3Field = addField("Subject 3 Marks:");
        subject4Field = addField("Subject 4 Marks:");
        subject5Field = addField("Subject 5 Marks:");

        JButton submitButton = new JButton("Submit");
        JButton fetchButton = new JButton("Fetch Percentage");

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(submitButton);
        add(fetchButton);
        add(new JScrollPane(outputArea));

        submitButton.addActionListener(this::submitMarksheet);
        fetchButton.addActionListener(this::fetchPercentage);
    }

    private JTextField addField(String labelText) {
        add(new JLabel(labelText));
        JTextField field = new JTextField();
        add(field);
        return field;
    }

    private void submitMarksheet(ActionEvent e) {
        String studentName = studentNameField.getText();
        int subject1 = Integer.parseInt(subject1Field.getText());
        int subject2 = Integer.parseInt(subject2Field.getText());
        int subject3 = Integer.parseInt(subject3Field.getText());
        int subject4 = Integer.parseInt(subject4Field.getText());
        int subject5 = Integer.parseInt(subject5Field.getText());

        int totalMarks = subject1 + subject2 + subject3 + subject4 + subject5;
        float percentage = totalMarks / 5.0f;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO marksheet (student_name, subject1, subject2, subject3, subject4, subject5, total_marks, per) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, studentName);
            preparedStatement.setInt(2, subject1);
            preparedStatement.setInt(3, subject2);
            preparedStatement.setInt(4, subject3);
            preparedStatement.setInt(5, subject4);
            preparedStatement.setInt(6, subject5);
            preparedStatement.setInt(7, totalMarks);
            preparedStatement.setFloat(8, percentage);

            preparedStatement.executeUpdate();
            outputArea.setText("Marksheet generated and saved successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            outputArea.setText("Error: " + ex.getMessage());
        }
    }

    private void fetchPercentage(ActionEvent e) {
        String studentName = JOptionPane.showInputDialog(this, "Enter the name of the student to fetch the percentage:");
        if (studentName != null) {
            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT per FROM marksheet WHERE student_name = ?")) {

                preparedStatement.setString(1, studentName);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    float percentage = resultSet.getFloat("per");
                    outputArea.setText("Percentage of " + studentName + ": " + percentage);
                } else {
                    outputArea.setText("Student " + studentName + " not found.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                outputArea.setText("Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PROJEC_T().setVisible(true));
    }
}
