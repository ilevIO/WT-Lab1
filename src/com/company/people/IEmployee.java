package com.company.people;

import com.company.IDepartment;
import com.company.IPersonalData;

import java.util.List;

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
    void addPatient(IPatient patient);
    void setPatients(List patients);
    List getPatients();
    void removePatient(IPatient patient);
    //void setCabinet(String cabinet);
    //String getCabinet();
}
