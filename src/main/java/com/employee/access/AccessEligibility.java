package com.employee.access;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AccessEligibility {

    private static final Set<String> AUTHORIZED_DEPARTMENTS =
            Set.of("IT", "HR", "Finance", "Administration");

    public String checkEligibility(Employee employee, int requestedAccessLevel) {

        validateInput(employee, requestedAccessLevel);

        List<String> reasons = new ArrayList<>();

        if (employee.getAge() < 21) {
            reasons.add("Employee must be at least 21 years old.");
        }

        if (!AUTHORIZED_DEPARTMENTS.contains(employee.getDepartment())) {
            reasons.add("Employee belongs to an unauthorized department.");
        }

        if (!employee.getEmploymentType().equalsIgnoreCase("Active")) {
            reasons.add("Employee does not have active employment status.");
        }

        if (!employee.isIdValid()) {
            reasons.add("Employee ID is invalid.");
        }

        if (employee.getSecurityClearanceLevel() < requestedAccessLevel) {
            reasons.add("Security clearance is insufficient for requested access.");
        }

        if (reasons.isEmpty()) {
            return "Eligible";
        }

        if (reasons.size() == 1 &&
                reasons.get(0).contains("Security clearance")) {
            return "Conditionally Eligible: " + reasons.get(0);
        }

        return "Not Eligible: " + String.join(" ", reasons);
    }

    private void validateInput(Employee employee, int requestedAccessLevel) {

        if (employee == null) {
            throw new IllegalArgumentException(
                    "Employee details cannot be null.");
        }

        if (employee.getEmployeeId() == null ||
                employee.getEmployeeId().isBlank()) {
            throw new IllegalArgumentException(
                    "Employee ID cannot be empty.");
        }

        if (employee.getName() == null ||
                employee.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Employee name cannot be empty.");
        }

        if (employee.getAge() < 0) {
            throw new IllegalArgumentException(
                    "Age cannot be negative.");
        }

        if (employee.getDepartment() == null ||
                employee.getDepartment().isBlank()) {
            throw new IllegalArgumentException(
                    "Department cannot be empty.");
        }

        if (employee.getEmploymentType() == null ||
                employee.getEmploymentType().isBlank()) {
            throw new IllegalArgumentException(
                    "Employment type cannot be empty.");
        }

        if (employee.getSecurityClearanceLevel() < 1 ||
                employee.getSecurityClearanceLevel() > 3) {
            throw new IllegalArgumentException(
                    "Security clearance level must be between 1 and 3.");
        }

        if (requestedAccessLevel < 1 ||
                requestedAccessLevel > 3) {
            throw new IllegalArgumentException(
                    "Requested access level must be between 1 and 3.");
        }
    }
}