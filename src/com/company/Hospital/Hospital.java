package com.company.Hospital;

import com.company.DBController.DBController;
import com.company.DBController.DBXmlMigrator;
import com.company.Room;
import com.company.XMLStuff.XSDValidator;
import com.company.people.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


class TestClass {
    @XmlElement(name = "x", required = true)
    int x;
    TestClass(int x) {
        this.x = x;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "test_classes", propOrder = {
        "testcs"
})
class TestClasses {
    @XmlElements({
            @XmlElement(name = "client", type = TestClass.class)
    })
    private
    List<TestClass> testcs = new ArrayList<TestClass>();

    TestClasses() {
        testcs.add(new TestClass(5));
    }
}

public class Hospital implements Serializable {
    private List patients = new ArrayList();
    private List employees = new ArrayList();
    private List<Room> cabinets = new ArrayList<Room>();
    private List<Room> wards = new ArrayList<Room>();
    public static Hospital shared = new Hospital();

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
        List patientsInfo = this.patients;/*new ArrayList<PersonInfo>();
        for (int i = 0; i < this.patients.size(); i++) {
            patientsInfo.add(((Patient) this.patients.get(i)).getInfo());
        }*/
        h.setPatients(patientsInfo);
        h.setCabinets(this.cabinets);
        h.setWards(this.wards);
        /*XMLEncoder encoder=null;
        try{
            encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Hospital.xml")));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("Error");
        }
        encoder.writeObject(h);
        encoder.close();*/
        HospitalSerializer hospitalSerializer = new HospitalSerializer();
        hospitalSerializer.serialize(h, "Hospital.xml");
        /*String file_path = "Hospital2.xml";
        try {
            File file = new File(file_path);
            if (!file.exists()) {
                if (!file.createNewFile())
                    throw new IOException();
            }
            TestClasses t = new TestClasses();
            JAXBContext context = JAXBContext.newInstance(TestClasses.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(h, new File(file_path));
        }
        catch (IOException e){
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }*/
    }
    public List<Room> getCabinets() {
        return this.cabinets;
    }
    public List<Room> getWards(){
        return this.wards;
    }
    public void deserialize() {
        /*XMLDecoder decoder=null;
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
        }*/
        //System.out.println(hospitalData);
        HospitalSerializer hospitalSerializer = new HospitalSerializer();
        HospitalSerializable hospitalData = hospitalSerializer.deserialize("Hospital.xml");
        if (hospitalData != null) {
            this.cabinets = hospitalData.getCabinets();
            this.patients = hospitalData.getPatients();
            this.wards = hospitalData.getWards();
            this.employees = hospitalData.getEmployees();
            assignLocations();
        }
    }
    public void migrate() {

        //XSDValidator xsdValidator = new XSDValidator();
        //xsdValidator.validate("Hospital.xsd", "Hospital.xml");
        DBController dbController = new DBController();
        Connection conn = dbController.connect();
        if (conn != null) {
            DBXmlMigrator xmlMigrator = new DBXmlMigrator(conn);
            xmlMigrator.migrate("Hospital.xml");
        } else {
            System.out.println("Could not connect to DataBase");
        }
    }
    private Hospital () {
        this.deserialize();
    }
}

