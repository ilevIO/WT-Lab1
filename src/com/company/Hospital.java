package com.company;

import java.util.ArrayList;
import java.util.Collection;

public class Hospital implements IHospital {
    private Collection<IPatient> patients;
    private Collection<IEmployee> employees;
    @Override
    public IHospitalStructure getHospitalStructure() {
        var patients = new ArrayList<IPatient>();
        var employees = new ArrayList<IEmployee>();
        return new HospitalStructure(patients, employees);
    }

    @Override
    public IPatient getPatientByName(String name) {
        return null;
    }

    @Override
    public IEmployee getEmployeeByName(String name) {
        return null;
    }
}
