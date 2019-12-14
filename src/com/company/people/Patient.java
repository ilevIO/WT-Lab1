package com.company.people;

import com.company.Room;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlType(name="patient")
@XmlAccessorType(XmlAccessType.FIELD)
public class Patient extends Person implements IPatient {
    @XmlList
    private List doctors = new ArrayList();
    @XmlElement(name="room")
    private Room ward = null;
    @XmlElement(name="diagnosis")
    private String diagnosis;

    public Patient() {
    }
    /*public static Patient random() {
        Patient patient = new Patient();
        patient.setName("Some");
        patient.setSecondName("Interesting");
        patient.setSurname("Person");
        return patient;
    }*/
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
        this.update();
    }

    @Override
    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public void assignDoctor(IEmployee doctor) {
        doctors.add(doctor);
        this.update();
    }

    @Override
    public void removeDoctor(IEmployee doctor) {
        int i = 0;
        for (; i < doctors.size() && !doctors.get(i).equals(doctor); i++) {
        }
        doctors.remove(i);
        this.update();
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
