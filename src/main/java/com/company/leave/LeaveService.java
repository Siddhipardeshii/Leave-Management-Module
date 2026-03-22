package com.company.leave;

import java.util.HashMap;
import java.util.Map;

public class LeaveService implements LeaveManagement {
    
    private Map<Integer, Employee> employees = new HashMap<>();
    private Map<Integer, LeaveRequest> leaveRequests = new HashMap<>();


    // public void addEmployee(Employee emp) {
    //     employees.put(emp.getEmployeeId(), emp);
    // }
    // FIXED CODE for duplicate employee handling and null check
public void addEmployee(Employee emp) {
    if (emp == null) {
        throw new RuntimeException("Employee cannot be null");
    }
    if (employees.containsKey(emp.getEmployeeId())) {
        throw new RuntimeException("Employee already exists with ID: " 
                                    + emp.getEmployeeId());
    }
    employees.put(emp.getEmployeeId(), emp);
}

    @Override
    public LeaveRequest applyLeave(int leaveId, int empId, int days) {

        Employee emp = employees.get(empId);

        if (emp == null) {
            throw new RuntimeException("Employee not found");
        }

        if (emp.getLeaveBalance() < days) {
            throw new RuntimeException("Insufficient leave balance");
        }
        if(days <= 0) {
            throw new RuntimeException("Number of days must be greater than zero");
        }

        LeaveRequest request = new LeaveRequest(leaveId, empId, days);
        if(leaveRequests.containsKey(request.getLeaveId()))
        {
            throw new RuntimeException("Duplicate request,already request made");
        }
        leaveRequests.put(leaveId, request);

        return request;
    }

    @Override
    public void approveLeave(int leaveId) {

        LeaveRequest request = leaveRequests.get(leaveId);

        if (request == null) {
            throw new RuntimeException("Leave request not found");
        }
        if(request.getStatus()==LeaveStatus.APPROVED)
        {
            throw new RuntimeException("Leave request is already approved");
        }
        if(request.getStatus()==LeaveStatus.REJECTED)
        {
            throw new RuntimeException("Leave request is already rejected");
        }

        Employee emp = employees.get(request.getEmployeeId());

        emp.setLeaveBalance(
                emp.getLeaveBalance() - request.getNumberOfDays()
        );

        request.setStatus(LeaveStatus.APPROVED);
    }

    @Override
    public void rejectLeave(int leaveId) {

        LeaveRequest request = leaveRequests.get(leaveId);

        if (request == null) {
            throw new RuntimeException("Leave request not found");
        }

        request.setStatus(LeaveStatus.REJECTED);
    }

    @Override
    public int getLeaveBalance(int empId) {

        Employee emp = employees.get(empId);

        if (emp == null) {
            throw new RuntimeException("Employee not found");
        }

        return emp.getLeaveBalance();
    }

    @Override
    public LeaveStatus getLeaveStatus(int leaveId) {

        LeaveRequest request = leaveRequests.get(leaveId);

        if (request == null) {
            throw new RuntimeException("Leave request not found");
        }

        return request.getStatus();
    }
}