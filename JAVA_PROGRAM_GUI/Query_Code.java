import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Query_Code {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "Admin_!@1212";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Load MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establish connection
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        Scanner scanner = new Scanner(System.in);

        // Collect data from user
        System.out.println("Enter student name:");
        String studentName = scanner.nextLine();
        System.out.println("Enter marks for Subject 1:");
        int subject1 = scanner.nextInt();
        System.out.println("Enter marks for Subject 2:");
        int subject2 = scanner.nextInt();
        System.out.println("Enter marks for Subject 3:");
        int subject3 = scanner.nextInt();
        System.out.println("Enter marks for Subject 4:");
        int subject4 = scanner.nextInt();
        System.out.println("Enter marks for Subject 5:");
        int subject5 = scanner.nextInt();

        int totalMarks = subject1 + subject2 + subject3 + subject4 + subject5;
        float percentage = totalMarks / 5.0f;

        // Prepare the INSERT query
        String insertQuery = "INSERT INTO marksheet (student_name, subject1, subject2, subject3, subject4, subject5, total_marks, per) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertQuery);

        preparedStatement.setString(1, studentName);
        preparedStatement.setInt(2, subject1);
        preparedStatement.setInt(3, subject2);
        preparedStatement.setInt(4, subject3);
        preparedStatement.setInt(5, subject4);
        preparedStatement.setInt(6, subject5);
        preparedStatement.setInt(7, totalMarks);
        preparedStatement.setFloat(8, percentage);

        preparedStatement.executeUpdate();
        System.out.println("Marksheet generated and saved successfully!");

        // Prompt user for the student name to fetch the percentage
        System.out.println("Enter the name of the student to fetch the percentage:");
        scanner.nextLine(); // Consume the newline character left by nextInt()
        String queryStudentName = scanner.nextLine();

        // Fetch and display the percentage for the specified student name
        String selectQuery = "SELECT per FROM marksheet WHERE student_name = ?";
        preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, queryStudentName);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            float fetchedPercentage = resultSet.getFloat("per");
            System.out.println("Percentage of " + queryStudentName + ": " + fetchedPercentage);
        } else {
            System.out.println("Student " + queryStudentName + " not found.");
        }

        // Close resources
        if (resultSet != null) resultSet.close();
        if (preparedStatement != null) preparedStatement.close();
        if (connection != null) connection.close();
    }
}
