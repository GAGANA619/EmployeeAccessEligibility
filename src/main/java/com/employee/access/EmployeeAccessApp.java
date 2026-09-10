package com.employee.access;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeAccessApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AccessEligibility eligibility = new AccessEligibility();

        List<Employee> employees = new ArrayList<>();

        try {
            System.out.print("Enter number of employees: ");
            int count = Integer.parseInt(scanner.nextLine());

            if (count <= 0) {
                throw new IllegalArgumentException(
                        "Number of employees must be greater than zero.");
            }

            for (int i = 1; i <= count; i++) {

                System.out.println("\nEmployee " + i);

                System.out.print("Employee ID: ");
                String id = scanner.nextLine();

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Age: ");
                int age = Integer.parseInt(scanner.nextLine());

                System.out.print("Department: ");
                String department = scanner.nextLine();

                System.out.print("Employment Type: ");
                String employmentType = scanner.nextLine();

                System.out.print("Security Clearance (1-3): ");
                int clearance = Integer.parseInt(scanner.nextLine());

                System.out.print("Is ID Valid? (true/false): ");
                boolean idValid = Boolean.parseBoolean(scanner.nextLine());

                employees.add(new Employee(
                        id,
                        name,
                        age,
                        department,
                        employmentType,
                        clearance,
                        idValid
                ));
            }

            System.out.print("\nRequested Access Level (1-3): ");
            int requestedAccess = Integer.parseInt(scanner.nextLine());

            System.out.println("\n===== ACCESS ELIGIBILITY RESULTS =====");

            for (Employee employee : employees) {

                String result = eligibility.checkEligibility(
                        employee,
                        requestedAccess
                );

                System.out.println(
                        employee.getName() + " (" +
                        employee.getEmployeeId() + "): " +
                        result
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid input: Please enter numbers where required."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Input error: " + e.getMessage()
            );

        } finally {

            scanner.close();
        }
    }
}