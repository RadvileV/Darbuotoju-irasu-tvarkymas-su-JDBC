package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Objektas skirtas irasu sukurimui
        /*
        Employee employee1 = new Employee("Greta", "Gretaite", 2000);
        EmployeeDAO.create(employee1);
        */

        //Spausdinami visi irasai esantys db
//        System.out.println(EmployeeDAO.printAll());

        //Paieska pagal darbuotojo varda
        /*
        List<Employee> employeeList = EmployeeDAO.searchByName("");
        if (employeeList.size() == 0)
            System.out.println("Tokio vardo nera");
        else
            System.out.println(employeeList);
        */

        //Paieska pagal darbuotojo id
        /*
        Employee employee2 = EmployeeDAO.searchById(2);
        if (employee2 == null)
            System.out.println("Tokio elemento sarase nera");
        else
            System.out.println(employee2);
        */

        //Irasu redagavimas
        /*
        Employee employee2 = new Employee(5, "Lina", "Linauskaite", 1520);
        EmployeeDAO.update(employee2);
        */

        //Irasu trynimas
//        EmployeeDAO.deleteById(1);
    }
}
