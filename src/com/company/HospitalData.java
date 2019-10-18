package com.company;

import java.util.ArrayList;
import java.util.Collection;

public class HospitalData implements IHospitalData {

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