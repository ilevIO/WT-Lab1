package com.company.Hospital;

import com.company.Room;
import com.company.people.Employee;
import com.company.people.Patient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "hospital", propOrder = {
        "patients"
})
public class HospitalSerializable {
    @XmlElements({
            @XmlElement(name = "patient", type = Patient.class)
    })
    private List patients = new ArrayList();
    /*
    @XmlElements({
            @XmlElement(name = "employee", type = Employee.class)
    })*/
    private List employees = new ArrayList();
    /*
    @XmlElements({
            @XmlElement(name = "cabinet", type = Room.class)
    })*/
    private List cabinets = new ArrayList();
/*
    @XmlElements({
            @XmlElement(name = "ward", type = Room.class)
    })*/
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
