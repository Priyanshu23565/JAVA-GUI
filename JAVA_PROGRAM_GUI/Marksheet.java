import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Marksheet {
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

        // Prepare the SELECT query
        String selectQuery = "SELECT * FROM marksheet";
        preparedStatement = connection.prepareStatement(selectQuery);
        resultSet = preparedStatement.executeQuery();

        // Fetch and display the data
        while (resultSet.next()) {
            String studentName = resultSet.getString("student_name");
            int subject1 = resultSet.getInt("subject1");
            int subject2 = resultSet.getInt("subject2");
            int subject3 = resultSet.getInt("subject3");
            int subject4 = resultSet.getInt("subject4");
            int subject5 = resultSet.getInt("subject5");
            int totalMarks = resultSet.getInt("total_marks");
            float percentage = resultSet.getFloat("per");

            System.out.println("Student Name: " + studentName);
            System.out.println("Subject 1: " + subject1);
            System.out.println("Subject 2: " + subject2);
            System.out.println("Subject 3: " + subject3);
            System.out.println("Subject 4: " + subject4);
            System.out.println("Subject 5: " + subject5);
            System.out.println("Total Marks: " + totalMarks);
            System.out.println("Percentage: " + percentage);
            System.out.println("-------------------------");
        }

        // Close resources
        if (resultSet != null) resultSet.close();
        if (preparedStatement != null) preparedStatement.close();
        if (connection != null) connection.close();
    }
}
