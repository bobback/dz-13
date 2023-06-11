package org.example;


public class Main {


    public static void main(String[] args) {
        DBTest.insertEmployee(1, "James Smith", 30, 5000.00);
        DBTest.insertEmployee(2, "Lily Brown", 45, 6000.00);
        DBTest.insertEmployee(3, "John Black", 24, 5500.00);

        DBTest.selectEmployeesWithAgeGreaterThan(25);

        DBTest.updateEmployeeSalary(1000.00, "Lily Brown");

        DBTest.deleteEmployeesWithAgeLessThan(26);
    }

}
