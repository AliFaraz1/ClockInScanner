package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) {
     //Declaring my credentials here:

                String jdbcUrl = "jdbc:mysql://localhost:3306/employeeData";
                String username = "root";
                String password = "sibi@soft";

     //JDBC established here
                try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                    System.out.println("Time Sheet!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }


        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter Employee ID: ");
            int employeeID = scanner.nextInt();
            String query = "SELECT employeeID, ClockInDate, ClockOutDate , clockInTime , clockOutTime FROM employeeData WHERE employeeID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, employeeID);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int retrievedEmployeeID = resultSet.getInt("employeeID");
                    LocalDateTime ClockInDate = resultSet.getTimestamp("ClockInDate").toLocalDateTime();
                    LocalDateTime ClockOutDate = resultSet.getTimestamp("ClockOutDate").toLocalDateTime();
                    LocalDateTime clockInTime = resultSet.getTimestamp("clockInTime").toLocalDateTime();
                    LocalDateTime clockOutTime = resultSet.getTimestamp("clockOutTime").toLocalDateTime();

                    LocalDate clockindate = ClockInDate.toLocalDate();
                    LocalTime clockintime = clockInTime.toLocalTime();
                    LocalDate clockoutdate = ClockOutDate.toLocalDate();
                    LocalTime clockouttime = clockOutTime.toLocalTime();

                    System.out.println("Employee ID: " + retrievedEmployeeID);
                    System.out.println("Clock In Date: " + clockindate);
                    System.out.println("Clock In Time: " + clockintime);
                    System.out.println("Clock Out Date: " + clockoutdate);
                    System.out.println("Clock Out Time: " + clockouttime);


                }
            }

        } catch (SQLException | java.util.InputMismatchException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }



            }

        }




