import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Code {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "Admin_!@1212";

        // JDBC driver ko load karen
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connection Successful!");

        String sql = "INSERT INTO mytable1 (name, email,rollno) VALUES (?, ?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name:");
        String a = sc.next();
        System.out.println("Enter the Email ID:");
        String a1 = sc.next();
        int r=45;

        preparedStatement.setString(1, a);
        preparedStatement.setString(2, a1);
        preparedStatement.setInt(3,r);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new row was inserted successfully!");
        }

        preparedStatement.close();
        connection.close();
    }
}
