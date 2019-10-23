package com.company;

import com.company.people.IEmployee;
import com.company.people.IPatient;

import java.util.AbstractCollection;

public interface IHospital {
    IHospitalData getHospitalStructure();
    IPatient getPatientByName(String name);
    IEmployee getEmployeeByName(String name);
    AbstractCollection<IEmployee> getEmployees();

    void addPatient(IPatient patient);

    void addEmployee(IEmployee employee);

    AbstractCollection<IPatient> getPatients();

}
