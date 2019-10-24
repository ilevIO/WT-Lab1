package com.company;

import com.company.people.IEmployee;
import com.company.people.IPatient;
import com.company.people.IPerson;
import com.company.people.Patient;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Hospital implements Serializable {
    private List patients = new ArrayList();
    private List employees = new ArrayList();
    private List<Room> cabinets = new ArrayList<Room>();
    private List<Room> wards = new ArrayList<Room>();
    static Hospital shared = new Hospital();

    public List<IEmployee> getEmployees() {
        return this.employees;
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
        this.serialize();
    }

    public Room getCabinet(String name) {
        int i = 0;
        for (; i < cabinets.size() && !cabinets.get(i).getName().equalsIgnoreCase(name); i++) {
        }
        if (i < cabinets.size()) {
            return cabinets.get(i);
        }
        return null;
    }
    public Room getWard(String name) {
        int i = 0;
        for (; i < wards.size() && !wards.get(i).getName().equalsIgnoreCase(name); i++) {
        }
        if (i < wards.size()) {
            return wards.get(i);
        }
        return null;
    }
    public Room addCabinet(String cabinetName) {
        var newCabinet = new Room(cabinetName);
        this.cabinets.add(newCabinet);
        this.serialize();
        return newCabinet;
    }
    public Room addWard(String wardName) {
        var newWard = new Room(wardName);
        this.wards.add(newWard);
        this.serialize();
        return newWard;
    }
    public void addEmployee(IEmployee employee) {
        this.employees.add(employee);
        this.serialize();
    }
    /**
     * Aligns deserialised pointers
     */
    private void assignLocations() {
        this.cabinets = new ArrayList<>();
        var employees = (ArrayList<IPerson>)this.employees;
        for (int i = 0; i < employees.size(); i++) {
            var cabinet = employees.get(i).getLocation();
            ArrayList<IPerson> occupant = new ArrayList<IPerson>();
            occupant.add(employees.get(i));
            if (cabinet != null) {
                //cabinet.addOccupants(occupant);
                cabinets.add(cabinet);
            }
        }

        this.wards = new ArrayList<>();
        var patients = (ArrayList<IPerson>)this.patients;
        for (int i = 0; i < patients.size(); i++) {
            var ward = patients.get(i).getLocation();
            ArrayList<IPerson> occupant = new ArrayList<IPerson>();
            occupant.add(patients.get(i));
            if (ward != null) {
                //ward.addOccupants(occupant);
                wards.add(ward);
            }
        }
    }
    public List<IPatient> getPatients() {
        return this.patients;
    }
    public void serialize() {
        HospitalSerializable h = new HospitalSerializable();
        h.setEmployees(this.employees);
        h.setPatients(this.patients);
        h.setCabinets(this.cabinets);
        h.setWards(this.wards);
        XMLEncoder encoder=null;
        try{
            encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Hospital.xml")));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("Error");
        }
        encoder.writeObject(h);
        encoder.close();
    }
    public List<Room> getCabinets() {
        return this.cabinets;
    }
    public List<Room> getWards(){
        return this.wards;
    }
    public void deserialize() {
        XMLDecoder decoder=null;
        try {
            decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("Hospital.xml")));
            HospitalSerializable hospitalData = (HospitalSerializable) decoder.readObject();
            this.cabinets = hospitalData.getCabinets();
            this.patients = hospitalData.getPatients();
            this.wards = hospitalData.getWards();
            this.employees = hospitalData.getEmployees();
            assignLocations();
        } catch (FileNotFoundException e) {
            //System.out.println("File not found");
        }
        //System.out.println(hospitalData);
    }
    private Hospital () {
        this.deserialize();
    }
}

