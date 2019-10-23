package com.company;

import com.company.people.IEmployee;
import com.company.people.IPatient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
public class HospitalData implements IHospitalData, Serializable {
    @Override
    public Collection<IPatient> getPatients() {
        return new ArrayList<IPatient>();
    }

    @Override
    public Collection<IEmployee> getEmployees() {
        return new ArrayList<IEmployee>();
    }

    @Override
    public String serialize() {
        return null;
    }

    @Override
    public void serializeToFile() {

    }
    public HospitalData(Collection<IPatient> patients, Collection<IEmployee> employees) {

    }
}