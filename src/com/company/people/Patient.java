package com.company.people;

import com.company.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends Person implements IPatient {
    private List doctors = new ArrayList();
    private Room ward = null;
    private String diagnosis;
    public Patient() {
    }
    public static Patient random() {
        Patient patient = new Patient();
        patient.setName("Some");
        patient.setSecondName("Interesting");
        patient.setSurname("Person");
        return patient;
    }
    @Override
    public void showDetail() {
        super.showDetail();
        System.out.println("Diagnosis: ");
    }

    @Override
    public void getHistory() {

    }

    @Override
    public void setDiagnosis(String diagnosis) {

        this.dateModified = new Date();
    }

    @Override
    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public void assignDoctor(Employee doctor) {
        doctors.add(doctor);
        this.dateModified = new Date();
    }

    @Override
    public void removeDoctor(Employee doctor) {
        int i = 0;
        for (; i < doctors.size() && doctors.get(i) != doctor; i++) {
        }
        doctors.remove(i);
        this.dateModified = new Date();
    }

    @Override
    public List getDoctors() {
        return this.doctors;
    }

    @Override
    public void setDoctors(List doctors) {
        this.doctors = doctors;
    }
}
