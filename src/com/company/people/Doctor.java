package com.company.people;

import java.util.ArrayList;

class Doctordeprecated extends Employee {
    private ArrayList<IPatient> patients;
    public void assignPatient(IPatient patient) {

    }
    public void removePatient(IPatient patient) {

    }
    public ArrayList<IPatient> getPatients() {
        return this.patients;
    }
}
