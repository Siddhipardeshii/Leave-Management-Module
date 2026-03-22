package com.company.leave;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeaveServiceTest {
//
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

        assertThrows(RuntimeException.class, () -> {
            service.applyLeave(101, 1, 5);
        });
    }

    //more testcases



    //  Apply leave for non-existent employee
@Test
public void testApplyLeaveForNonExistentEmployee() {
    LeaveService service = new LeaveService();
    assertThrows(RuntimeException.class, () -> {
        service.applyLeave(101, 999, 3); // empId 999 doesn't exist
    });
}
//  Add null employee
@Test
public void testAddNullEmployee() {
    LeaveService service = new LeaveService();
    assertThrows(RuntimeException.class, () -> {
        service.addEmployee(null);
    });
}


//  Add duplicate employee
@Test
public void testAddDuplicateEmployee() {
    LeaveService service = new LeaveService();
    Employee emp1 = new Employee(1, "John", 10);
    Employee emp2 = new Employee(1, "John", 10); // same ID
    service.addEmployee(emp1);
    assertThrows(RuntimeException.class, () -> {
        service.addEmployee(emp2);
    });
}

//  Apply leave for zero days
@Test
public void testApplyLeaveZeroDays() {
    LeaveService service = new LeaveService();
    Employee emp = new Employee(1, "John", 10);
    service.addEmployee(emp);
    assertThrows(RuntimeException.class, () -> {
        service.applyLeave(101, 1, 0); // 0 days doesn't make sense
    });
}

//  Apply leave for negative days
@Test
public void testApplyLeaveNegativeDays() {
    LeaveService service = new LeaveService();
    Employee emp = new Employee(1, "John", 10);
    service.addEmployee(emp);
    assertThrows(RuntimeException.class, () -> {
        service.applyLeave(101, 1, -3); // negative days invalid
    });
}

//  Employee with zero leave balance
@Test
public void testApplyLeaveWithZeroBalance() {
    LeaveService service = new LeaveService();
    Employee emp = new Employee(1, "John", 0);
    service.addEmployee(emp);
    assertThrows(RuntimeException.class, () -> {
        service.applyLeave(101, 1, 1);
    });
}

//  Approve already approved leave
@Test
public void testApproveAlreadyApprovedLeave() {
    LeaveService service = new LeaveService();
    Employee emp = new Employee(1, "John", 10);
    service.addEmployee(emp);
    service.applyLeave(101, 1, 2);
    service.approveLeave(101);
    // approving again should throw exception
    assertThrows(RuntimeException.class, () -> {
        service.approveLeave(101);
    });
}

//  Approve already rejected leave
@Test
public void testApproveAlreadyRejectedLeave() {
    LeaveService service = new LeaveService();
    Employee emp = new Employee(1, "John", 10);
    service.addEmployee(emp);
    service.applyLeave(101, 1, 2);
    service.rejectLeave(101);
    assertThrows(RuntimeException.class, () -> {
        service.approveLeave(101); // can't approve rejected leave
    });
}

//  Duplicate leave request ID
@Test
public void testDuplicateLeaveRequestId() {
    LeaveService service = new LeaveService();
    Employee emp = new Employee(1, "John", 10);
    service.addEmployee(emp);
    service.applyLeave(101, 1, 2);
    assertThrows(RuntimeException.class, () -> {
        service.applyLeave(101, 1, 3); // same leaveId 101 again
    });
}
}