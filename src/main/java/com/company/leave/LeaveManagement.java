package com.company.leave;

public interface LeaveManagement {
//
    LeaveRequest applyLeave(int leaveId, int empId, int days);

    void approveLeave(int leaveId);

    void rejectLeave(int leaveId);

    int getLeaveBalance(int empId);

    LeaveStatus getLeaveStatus(int leaveId);
}