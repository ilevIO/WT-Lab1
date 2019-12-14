package com.company.people;

import com.company.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee extends Person implements IEmployee {
    @XmlElement(name="salary", required = true)
    private int salary;
    @XmlElement(name="room", required = true)
    private Room cabinet;
    @XmlElement(name="rank", required = true)
    private String rank;
    @XmlList
    private List patients = new ArrayList<IPatient>();

    @Override
    public int getSalary() {
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
    final public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String getRank() {
        return this.rank;
    }

    @Override
    public void addPatient(IPatient patient) {
        this.patients.add(patient);
    }

    @Override
    public void setPatients(List patients) {
        this.patients = patients;
    }

    @Override
    public List getPatients() {
        return null;
    }

    @Override
    public void removePatient(IPatient patient) {
        int i = 0;
        for (; i < patients.size() && !patients.get(i).equals(patient); i++){}
        if (i < patients.size()) {
            patients.remove(i);
        }
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
        //System.out.println(toString());
        super.showDetail();
        System.out.printf("Salary: %d$\n", salary);
        System.out.printf("Rank: %s\n",this.rank);
        if (this.cabinet != null) {
            System.out.printf("Cabinet: %s\n", this.cabinet.getName());
        }
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
