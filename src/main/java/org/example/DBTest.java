package org.example;

import java.math.BigDecimal;
import java.sql.*;

public class DBTest {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER_NAME = "postgres";
    private static final String USER_PASSWORD = "postgres";

    private static final String INSERT_QUERY = "INSERT INTO employees (id, name, age, salary) VALUES (?,?,?,?)";
    private static final String SELECT_QUERY = "SELECT * FROM employees WHERE age > ?";
    private static final String UPDATE_QUERY = "UPDATE employees SET salary = ? WHERE name = ?";
    private static final String DELETE_QUERY = "DELETE FROM employees WHERE age < ?";


    public static void insertEmployee(int id, String name, int age, double salary) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement insertStatement = connection.prepareStatement(INSERT_QUERY);
            insertStatement.setInt(1, id);
            insertStatement.setString(2, name);
            insertStatement.setInt(3, age);
            insertStatement.setDouble(4, salary);
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("The database doesn't exist/please, check connection string" + URL);
        }
    }


    public static void selectEmployeesWithAgeGreaterThan (int age) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement selectStatement = connection.prepareStatement(SELECT_QUERY);
            selectStatement.setInt(1, age);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int employeeAge = resultSet.getInt("age");
                    double salary = resultSet.getDouble("salary");
                    System.out.println("id: " + id + " name: " + name + ", age " + employeeAge + " salary " + salary);
                }
                }
            } catch (SQLException e) {
                throw new RuntimeException("The database doesn't exist/please, check connection string" + URL);
            }
        }
    public static void updateEmployeeSalary(double salary, String name){
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement updateStatement = connection.prepareStatement(UPDATE_QUERY);
            updateStatement.setDouble(1, salary);
            updateStatement.setString(2, name);
            System.out.println("You have changed " + name + " salary to " + salary);
            updateStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("The database doesn't exist/please, check connection string" + URL);
        }
    }

    public static void deleteEmployeesWithAgeLessThan (int age) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement deleteStatement = connection.prepareStatement(DELETE_QUERY);
            deleteStatement.setInt(1, age);
            deleteStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("The database doesn't exist/please, check connection string" + URL);
        }
    }
    }
