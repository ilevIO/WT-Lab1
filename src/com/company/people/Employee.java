package com.company.people;

import com.company.*;


public class Employee extends Person implements IEmployee {
    private int salary;
    private Room cabinet;
    private String rank;
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
    final public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String getRank() {
        return this.rank;
    }

    public static Employee random() {
        Employee employee = new Employee();
        employee.setName("Some");
        employee.setSecondName("Interesting");
        employee.setSurname("Employee");
        employee.setSalary(1000);
        return employee;
    }
    @Override
    public void showDetail() {
        System.out.println(toString());
        /*super.showDetail();
        System.out.printf("Salary: %d$\n", salary);
        System.out.printf("Rank: %s\n",this.rank);
        if (this.cabinet != null) {
            System.out.printf("Cabinet: %s\n", this.cabinet.getName());
        }*/
    }
    @Override
    public String toString() {
        return "Employee{" +
                "Name=" + getName() +
                ", SecondName=" + getSecondName() +
                ", LastName=" + getSurname() +
                ", DOB=" + getDOB().toString() +
                ", Salary=" + getSalary() +
                ", Location=" + getLocation() +
                ", Rank=" + getRank() +
                '}';
    }

    @Override
    public void setLocation(Room room) {
        this.cabinet = room;
    }

    @Override
    public Room getLocation() {
        return this.cabinet;
    }
}
