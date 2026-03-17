package com.company.leave;

public class LeaveRequest {

    private int leaveId;
    private int employeeId;
    private int numberOfDays;
    private LeaveStatus status;

    public LeaveRequest(int leaveId, int employeeId, int numberOfDays) {
        this.leaveId = leaveId;
        this.employeeId = employeeId;
        this.numberOfDays = numberOfDays;
        this.status = LeaveStatus.PENDING;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }
}