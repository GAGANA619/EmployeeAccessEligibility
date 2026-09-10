package com.employee.access;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AccessEligibilityTest {

    private final AccessEligibility eligibility = new AccessEligibility();

    @Test
    void testEligibleEmployee() {
        Employee employee = new Employee(
                "EMP001", "Ravi", 25,
                "IT", "Active", 3, true);

        assertEquals("Eligible",
                eligibility.checkEligibility(employee, 2));
    }

    @Test
    void testMinimumAgeBoundary() {
        Employee employee = new Employee(
                "EMP002", "Priya", 21,
                "HR", "Active", 2, true);

        assertEquals("Eligible",
                eligibility.checkEligibility(employee, 2));
    }

    @Test
    void testBelowMinimumAge() {
        Employee employee = new Employee(
                "EMP003", "Anu", 20,
                "IT", "Active", 3, true);

        String result = eligibility.checkEligibility(employee, 2);

        assertTrue(result.contains("at least 21"));
    }

    @Test
    void testUnauthorizedDepartment() {
        Employee employee = new Employee(
                "EMP004", "Kiran", 25,
                "Sales", "Active", 3, true);

        String result = eligibility.checkEligibility(employee, 2);

        assertTrue(result.contains("unauthorized department"));
    }

    @Test
    void testInactiveEmployee() {
        Employee employee = new Employee(
                "EMP005", "Arun", 25,
                "Finance", "Inactive", 3, true);

        String result = eligibility.checkEligibility(employee, 2);

        assertTrue(result.contains("active employment"));
    }

    @Test
    void testInvalidEmployeeId() {
        Employee employee = new Employee(
                "EMP006", "Sneha", 25,
                "HR", "Active", 3, false);

        String result = eligibility.checkEligibility(employee, 2);

        assertTrue(result.contains("invalid"));
    }

    @Test
    void testConditionalEligibility() {
        Employee employee = new Employee(
                "EMP007", "Rahul", 25,
                "IT", "Active", 1, true);

        String result = eligibility.checkEligibility(employee, 2);

        assertTrue(result.startsWith("Conditionally Eligible"));
    }

    @Test
    void testExactClearanceBoundary() {
        Employee employee = new Employee(
                "EMP008", "Meena", 30,
                "Finance", "Active", 2, true);

        assertEquals("Eligible",
                eligibility.checkEligibility(employee, 2));
    }

    @Test
    void testHighestClearance() {
        Employee employee = new Employee(
                "EMP009", "Vijay", 35,
                "Administration", "Active", 3, true);

        assertEquals("Eligible",
                eligibility.checkEligibility(employee, 3));
    }

    @Test
    void testMultipleFailures() {
        Employee employee = new Employee(
                "EMP010", "Sita", 18,
                "Sales", "Inactive", 1, false);

        String result = eligibility.checkEligibility(employee, 3);

        assertTrue(result.contains("at least 21"));
        assertTrue(result.contains("unauthorized department"));
        assertTrue(result.contains("active employment"));
        assertTrue(result.contains("invalid"));
        assertTrue(result.contains("insufficient"));
    }

    @Test
    void testNullEmployee() {
        assertThrows(
                IllegalArgumentException.class,
                () -> eligibility.checkEligibility(null, 2));
    }

    @Test
    void testEmptyEmployeeId() {
        Employee employee = new Employee(
                "", "Ravi", 25,
                "IT", "Active", 2, true);

        assertThrows(
                IllegalArgumentException.class,
                () -> eligibility.checkEligibility(employee, 2));
    }

    @Test
    void testNegativeAge() {
        Employee employee = new Employee(
                "EMP013", "Ravi", -5,
                "IT", "Active", 2, true);

        assertThrows(
                IllegalArgumentException.class,
                () -> eligibility.checkEligibility(employee, 2));
    }

    @Test
    void testInvalidClearance() {
        Employee employee = new Employee(
                "EMP014", "Ravi", 25,
                "IT", "Active", 5, true);

        assertThrows(
                IllegalArgumentException.class,
                () -> eligibility.checkEligibility(employee, 2));
    }

    @Test
    void testInvalidRequestedAccess() {
        Employee employee = new Employee(
                "EMP015", "Ravi", 25,
                "IT", "Active", 3, true);

        assertThrows(
                IllegalArgumentException.class,
                () -> eligibility.checkEligibility(employee, 4));
    }

    @Test
    void testActiveEmploymentCaseInsensitive() {
        Employee employee = new Employee(
                "EMP016", "Riya", 25,
                "IT", "active", 3, true);

        assertEquals("Eligible",
                eligibility.checkEligibility(employee, 2));
    }

    @Test
    void testHRDepartment() {
        Employee employee = new Employee(
                "EMP017", "Nisha", 22,
                "HR", "Active", 2, true);

        assertEquals("Eligible",
                eligibility.checkEligibility(employee, 1));
    }

    @Test
    void testFinanceDepartment() {
        Employee employee = new Employee(
                "EMP018", "Asha", 28,
                "Finance", "Active", 3, true);

        assertEquals("Eligible",
                eligibility.checkEligibility(employee, 3));
    }

    @Test
    void testAdministrationDepartment() {
        Employee employee = new Employee(
                "EMP019", "Varun", 24,
                "Administration", "Active", 2, true);

        assertEquals("Eligible",
                eligibility.checkEligibility(employee, 1));
    }

    @Test
    void testLowestClearanceBoundary() {
        Employee employee = new Employee(
                "EMP020", "Neha", 21,
                "IT", "Active", 1, true);

        assertEquals("Eligible",
                eligibility.checkEligibility(employee, 1));
    }
}