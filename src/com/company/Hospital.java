package com.company;

import com.company.people.IEmployee;
import com.company.people.IPatient;
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
    //@Override
    public IHospitalData getHospitalStructure() {
        var patients = new ArrayList<IPatient>();
        var employees = new ArrayList<IEmployee>();
        return new HospitalData(patients, employees);
    }
    static Hospital shared = new Hospital();
    //@Override
    public IPatient getPatientByName(String name) {
        return null;
    }

    //@Override
    public IEmployee getEmployeeByName(String name) {
        return null;
    }

    //@Override
    public List<IEmployee> getEmployees() {
        return this.employees;
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
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
        for (; i < wards.size() && wards.get(i).getName() != name; i++) {
        }
        if (i < wards.size()) {
            return wards.get(i);
        }
        return null;
    }
    public Room addCabinet(String cabinetName) {
        var newCabinet = new Room(cabinetName);
        this.cabinets.add(newCabinet);
        return newCabinet;
    }
    public Room addWard(String wardName) {
        var newWard = new Room(wardName);
        this.wards.add(newWard);
        return newWard;
    }
    public void addEmployee(IEmployee employee) {
        this.employees.add(employee);
        this.serialize();
    }
    //@Override
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
        /*Movie bourneIndentity=new Movie("The Bourne Identity",119,"Doug Liman",2002,"Matt Damon, Franka Potente");
        Movie bourneSupermacy=new Movie("The Bourne Supremacy",108,"Paul Greengrass",2004,"Matt Damon, Franka Potente, Joan Allen");
        Movie bourneUltimatum=new Movie("The Bourne Ultimatum",115,"Paul Greengrass",2007,"Matt Damon, Edgar Ramirez, Joan Allen");
        Movie bourneLegacy=new Movie("The Bourne Legacy",135,"Tony Gilroy",2012,"Jeremy Renner, Rachel Weisz, Edward Norton");

        List moviesList=new ArrayList();
        moviesList.add(bourneIndentity);
        moviesList.add(bourneSupermacy);
        moviesList.add(bourneUltimatum);
        moviesList.add(bourneLegacy);

        DVD bourneSeries=new DVD();
        bourneSeries.setMovies(moviesList);

        XMLEncoder encoder=null;
        try{
            encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Test.xml")));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File dvd.xml");
        }
        encoder.writeObject(bourneSeries);
        encoder.close();*/
    }
    public void deserialize() {
        XMLDecoder decoder=null;
        try {
            decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("Hospital.xml")));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File dvd.xml not found");
        }
        HospitalSerializable hospitalData = (HospitalSerializable) decoder.readObject();
        this.cabinets = hospitalData.getCabinets();
        this.patients = hospitalData.getPatients();
        this.wards = hospitalData.getWards();
        this.employees = hospitalData.getEmployees();
        //System.out.println(hospitalData);
    }
    private Hospital () {
        this.deserialize();
    }
}

