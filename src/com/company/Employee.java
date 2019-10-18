package com.company;

public class Employee implements IEmployee {
    private int salary;

    @Override
    public Comparable getSalary() {
        return this.salary;
    }

    @Override
    public void setSalary(int newSalary) {
        this.salary = newSalary;
    }

    @Override
    public IPersonalData getPersonalData() {
        return null;
    }

    @Override
    public void setPersonalData(IPersonalData personalData) {

    }

    @Override
    public void assignDepartment(IDepartment department) {

    }

    @Override
    public IDepartment getDepartment() {
        return null;
    }

    @Override
    public void setRank(String rank) {

    }

    @Override
    public String getRank() {
        return null;
    }
}
