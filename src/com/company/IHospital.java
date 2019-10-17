package com.company;

public interface IHospital {
    IHospitalStructure getHospitalStructure();
    IPatient getPatientByName(String name);
    IEmployee getEmployeeByName(String name);
}
