package com.company;

import java.util.ArrayList;
import java.util.List;

public class HospitalSerializable {
    private List patients = new ArrayList();
    private List employees = new ArrayList();
    private List cabinets = new ArrayList();
    private List wards = new ArrayList();
    public void setPatients(List patients) {
        this.patients = patients;
    }
    public List getPatients() {
        return this.patients;
    }
    public void setEmployees(List employees) {
        this.employees = employees;
    }
    public List getEmployees() {
        return this.employees;
    }
    public void setCabinets(List cabinets) {
        this.cabinets = cabinets;
    }
    public List getCabinets() {
        return this.cabinets;
    }
    public void setWards(List wards) {
        this.cabinets = cabinets;
    }
    public List getWards() {
        return this.wards;
    }
    public HospitalSerializable() {
    }
}
