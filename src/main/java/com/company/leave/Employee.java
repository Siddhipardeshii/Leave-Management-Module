package com.company.leave;

public class Employee {

    private int employeeId;
    private String name;
    private int leaveBalance;
//constructor 
    public Employee(int employeeId, String name, int leaveBalance) {
        this.employeeId = employeeId;
        this.name = name;
        this.leaveBalance = leaveBalance;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }
}