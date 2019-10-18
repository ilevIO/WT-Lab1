package com.company;

public interface IHospital {
    IHospitalData getHospitalStructure();
    IPatient getPatientByName(String name);
    IEmployee getEmployeeByName(String name);
}
