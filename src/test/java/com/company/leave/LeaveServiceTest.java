package com.company.leave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class LeaveServiceTest {

    @Test
    public void testApplyLeave() {
        LeaveService service = new LeaveService();
        Employee emp = new Employee(1, "John", 10);
        service.addEmployee(emp);

        LeaveRequest request = service.applyLeave(101, 1, 3);
        assertEquals(LeaveStatus.PENDING, request.getStatus());
    }

    @Test
    public void testApproveLeave() {
        LeaveService service = new LeaveService();
        Employee emp = new Employee(1, "John", 10);
        service.addEmployee(emp);

        service.applyLeave(101, 1, 2);
        service.approveLeave(101);
        assertEquals(8, service.getLeaveBalance(1));
    }

    @Test
    public void testRejectLeave() {
        LeaveService service = new LeaveService();
        Employee emp = new Employee(1, "John", 10);
        service.addEmployee(emp);

        service.applyLeave(101, 1, 2);
        service.rejectLeave(101);
        assertEquals(LeaveStatus.REJECTED, service.getLeaveStatus(101));
    }

    @Test
    public void testInsufficientLeaveBalance() {
        LeaveService service = new LeaveService();
        Employee emp = new Employee(1, "John", 2);
        service.addEmployee(emp);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.applyLeave(101, 1, 5);
        });
        assertNotNull(exception);
    }

    // Apply leave for non-existent employee
    @Test
    public void testApplyLeaveForNonExistentEmployee() {
        LeaveService service = new LeaveService();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.applyLeave(101, 999, 3);
        });
        assertNotNull(exception);
    }

    // Add null employee
    @Test
    public void testAddNullEmployee() {
        LeaveService service = new LeaveService();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.addEmployee(null);
        });
        assertNotNull(exception);
    }

    // Add duplicate employee
    @Test
    public void testAddDuplicateEmployee() {
        LeaveService service = new LeaveService();
        Employee emp1 = new Employee(1, "John", 10);
        Employee emp2 = new Employee(1, "John", 10);
        service.addEmployee(emp1);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.addEmployee(emp2);
        });
        assertNotNull(exception);
    }

    // Apply leave for zero days
    @Test
    public void testApplyLeaveZeroDays() {
        LeaveService service = new LeaveService();
        Employee emp = new Employee(1, "John", 10);
        service.addEmployee(emp);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.applyLeave(101, 1, 0);
        });
        assertNotNull(exception);
    }

    // Apply leave for negative days
    @Test
    public void testApplyLeaveNegativeDays() {
        LeaveService service = new LeaveService();
        Employee emp = new Employee(1, "John", 10);
        service.addEmployee(emp);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.applyLeave(101, 1, -3);
        });
        assertNotNull(exception);
    }

    // Employee with zero leave balance
    @Test
    public void testApplyLeaveWithZeroBalance() {
        LeaveService service = new LeaveService();
        Employee emp = new Employee(1, "John", 0);
        service.addEmployee(emp);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.applyLeave(101, 1, 1);
        });
        assertNotNull(exception);
    }

    // Approve already approved leave
    @Test
    public void testApproveAlreadyApprovedLeave() {
        LeaveService service = new LeaveService();
        Employee emp = new Employee(1, "John", 10);
        service.addEmployee(emp);
        service.applyLeave(101, 1, 2);
        service.approveLeave(101);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.approveLeave(101);
        });
        assertNotNull(exception);
    }

    // Approve already rejected leave
    @Test
    public void testApproveAlreadyRejectedLeave() {
        LeaveService service = new LeaveService();
        Employee emp = new Employee(1, "John", 10);
        service.addEmployee(emp);
        service.applyLeave(101, 1, 2);
        service.rejectLeave(101);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.approveLeave(101);
        });
        assertNotNull(exception);
    }

    // Duplicate leave request ID
    @Test
    public void testDuplicateLeaveRequestId() {
        LeaveService service = new LeaveService();
        Employee emp = new Employee(1, "John", 10);
        service.addEmployee(emp);
        service.applyLeave(101, 1, 2);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.applyLeave(101, 1, 3);
        });
        assertNotNull(exception);
    }
}