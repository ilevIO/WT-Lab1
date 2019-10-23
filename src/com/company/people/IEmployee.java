package com.company.people;

import com.company.IDepartment;
import com.company.IPersonalData;

public interface IEmployee extends IPerson
{
    Comparable getSalary();
    void setSalary(int newSalary);
    IPersonalData getPersonalData();
    void setPersonalData(IPersonalData personalData);
    void assignDepartment(IDepartment department);
    IDepartment getDepartment();
    void setRank(String rank);
    String getRank();
    //void setCabinet(String cabinet);
    //String getCabinet();
}
