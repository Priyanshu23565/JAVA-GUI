import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MARKSHEET_2 {
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

        // Fetch and display the data
        String selectQuery = "SELECT * FROM marksheet";
        preparedStatement = connection.prepareStatement(selectQuery);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String fetchedStudentName = resultSet.getString("student_name");
            int fetchedSubject1 = resultSet.getInt("subject1");
            int fetchedSubject2 = resultSet.getInt("subject2");
            int fetchedSubject3 = resultSet.getInt("subject3");
            int fetchedSubject4 = resultSet.getInt("subject4");
            int fetchedSubject5 = resultSet.getInt("subject5");
            int fetchedTotalMarks = resultSet.getInt("total_marks");
            float fetchedPercentage = resultSet.getFloat("per");

            System.out.println("Student Name: " + fetchedStudentName);
            System.out.println("Subject 1: " + fetchedSubject1);
            System.out.println("Subject 2: " + fetchedSubject2);
            System.out.println("Subject 3: " + fetchedSubject3);
            System.out.println("Subject 4: " + fetchedSubject4);
            System.out.println("Subject 5: " + fetchedSubject5);
            System.out.println("Total Marks: " + fetchedTotalMarks);
            System.out.println("Percentage: " + fetchedPercentage);
            System.out.println("-------------------------");
        }

        // Close resources
        if (resultSet != null) resultSet.close();
        if (preparedStatement != null) preparedStatement.close();
        if (connection != null) connection.close();
    }
}
