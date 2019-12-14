package com.company.people;

import com.company.IPersonalData;

import java.util.List;

public interface IEmployee extends IPerson
{
    int getSalary();
    void setSalary(int newSalary);
    IPersonalData getPersonalData();
    void setPersonalData(IPersonalData personalData);
    void setRank(String rank);
    String getRank();
    void addPatient(IPatient patient);
    void setPatients(List patients);
    List getPatients();
    void removePatient(IPatient patient);
    //void setCabinet(String cabinet);
    //String getCabinet();
}
