package com.company;

import java.io.Serializable;

public interface IEmployee
{
    Comparable getSalary();
    void setSalary(int newSalary);
    IPersonalData getPersonalData();
    void setPersonalData(IPersonalData personalData);
    void assignDepartment(IDepartment department);
    IDepartment getDepartment();
    void setRank(String rank);
    String getRank();
}
