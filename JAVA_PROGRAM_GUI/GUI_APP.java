//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//public class StudentMarksheet extends JFrame {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/school";
//    private static final String USER = "root";
//    private static final String PASSWORD = "Admin_!@1212";
//
//    private JTextField studentNameField, subject1Field, subject2Field, subject3Field, subject4Field, subject5Field;
//    private JTextArea outputArea;
//
//    public StudentMarksheet() {
//        setTitle("Student Marksheet App");
//        setSize(400, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new GridLayout(8, 2));
//
//        studentNameField = createField("Student Name:");
//        subject1Field = createField("Subject 1 Marks:");
//        subject2Field = createField("Subject 2 Marks:");
//        subject3Field = createField("Subject 3 Marks:");
//        subject4Field = createField("Subject 4 Marks:");
//        subject5Field = createField("Subject 5 Marks:");
//
//        JButton submitButton = new JButton("Submit");
//        JButton fetchButton = new JButton("Fetch Percentage");
//
//        outputArea = new JTextArea();
//        outputArea.setEditable(false);
//
//        add(submitButton);
//        add(fetchButton);
//        add(new JScrollPane(outputArea));
//
//        submitButton.addActionListener(e -> submitMarksheet());
//        fetchButton.addActionListener(e -> fetchPercentage());
//    }
//
//    private JTextField createField(String labelText) {
//        JLabel label = new JLabel(labelText);
//        JTextField field = new JTextField();
//        add(label);
//        add(field);
//        return field;
//    }
//
//    private void submitMarksheet() {
//        String studentName = studentNameField.getText();
//        int subject1 = Integer.parseInt(subject1Field.getText());
//        int subject2 = Integer.parseInt(subject2Field.getText());
//        int subject3 = Integer.parseInt(subject3Field.getText());
//        int subject4 = Integer.parseInt(subject4Field.getText());
//        int subject5 = Integer.parseInt(subject5Field.getText());
//
//        int totalMarks = subject1 + subject2 + subject3 + subject4 + subject5;
//        float percentage = totalMarks / 5.0f;
//
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                "INSERT INTO marksheet (student_name, subject1, subject2, subject3, subject4, subject5, total_marks, per) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
//
//        preparedStatement.setString(1, studentName);
//        preparedStatement.setInt(2, subject1);
//        preparedStatement.setInt(3, subject2);
//        preparedStatement.setInt(4, subject3);
//        preparedStatement.setInt(5, subject4);
//        preparedStatement.setInt(6, subject5);
//        preparedStatement.setInt(7, totalMarks);
//        preparedStatement.setFloat(8, percentage);
//
//        preparedStatement.executeUpdate();
//        outputArea.setText("Marksheet generated and saved successfully!");
//        preparedStatement.close();
//        connection.close();
//    }
//
//    private void fetchPercentage() {
//        String studentName = JOptionPane.showInputDialog(this, "Enter the name of the student to fetch the percentage:");
//        if (studentName != null) {
//            Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT per FROM marksheet WHERE student_name = ?");
//            preparedStatement.setString(1, studentName);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                float percentage = resultSet.getFloat("per");
//                outputArea.setText("Percentage of " + studentName + ": " + percentage);
//            } else {
//                outputArea.setText("Student " + studentName + " not found.");
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        }
//    }
//
//    private Connection getConnection() {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new StudentMarksheet().setVisible(true));
//    }
//}
