package com.company;

import java.sql.*;
import java.util.*;

public class EmployeeDAO {

    //IRASU SUKURIMAS
    public static void create(Employee employee) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "INSERT INTO `employee`(`name`, `surname`, `salary`) VALUES (?, ?, ?)";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Sekmingai sukurtas naujas irasas");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ivyko klaida kuriant nauja irasa");
        }
    }

    //VISU IRASU SPAUSDINIMAS
    public static List<Employee> printAll() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "SELECT * FROM `employee`";
        List<Employee> list = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getDouble("salary")
                ));
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko atspausdinti irasus.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ivyko klaida atspausdinant irasus");
        }
        return list;
    }

    //IRASU PAIESKA PAGAL DARBUOTOJO VARDA
    public static List<Employee> searchByName(String name) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "SELECT * FROM `employee` WHERE `name` = ?";
        List<Employee> list = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getDouble("salary")
                ));
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko atlikti paieska pagal varda.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ivyko klaida vykdant paieska pagal varda.");
        }
        return list;
    }

    //IRASU REDAGAVIMAS
    public static void update(Employee employee) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "UPDATE `employee` SET `name`= ?,`surname`= ?,`salary`= ? WHERE `id` = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl,"root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setInt(4, employee.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Sekmingai atnaujintas irasas.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ivyko klaida atnaujinant irasa.");
        }
    }

    //IRASU TRYNIMAS PAGAL ID
    public static void deleteById(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String query = "DELETE FROM `employee` WHERE `id` = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Irasas sekmingai istrintas.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ivyko klaida bandant istrinti irasa.");
        }
    }

    //IRASU PAIESKA PAGAL DARBUOTOJO ID
    public static Employee searchById(int id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String querry = "SELECT * FROM `employee` WHERE `id` = ?";
        ArrayList<Employee> list = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setInt(1, id);
            //Tik įrašu paieskai executeQuery()
            // ResultSet yra RAW neapdirbti duomenys. Stulpelio pav atitinka rakta, o konkreti iraso reiksme
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //Kol turime sarase elementus
                list.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getDouble("salary")
                ));
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko atlikti paieška pagal id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko atlikti paieskos pagal id: " + id);
        }
        try {
            // Ieskome pagal pirma sarase esanti id, nes id unikalus
            return list.get(0);
        }
        // Nepavyko rasti pagal id
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
