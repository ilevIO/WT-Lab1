package com.company.people;

import com.company.Room;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlType(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements IPerson {
    @XmlElement(name="id", required = true)
    private int id;
    @XmlElement(name="name", required = true)
    private String name;
    @XmlElement(name="second_name", required = true)
    private String secondName;
    @XmlElement(name="last_name", required = true)
    private String lastName;
    @XmlElement(name="dob", required = true)
    private Date dob;
    private String note;
    private Date dateAdded;
    private Date dateModified;
    @XmlElement(name="location", required = true)
    private Room location;

    @Override
    public void setName(String name) {
        this.name = name;
    }
    /*@Override
    public PersonInfo getInfo(){
        return this;
    }*/
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setSecondName(String name) {
        this.secondName = name;
    }

    @Override
    public String getSecondName() {
        return this.secondName;
    }

    @Override
    public void setSurname(String name) {
        this.lastName = name;
    }

    @Override
    public String getSurname() {
        return this.lastName;
    }

    @Override
    public void setDOB(Date date) {
        this.dob = date;
    }

    @Override
    public Date getDOB() {
        return this.dob;
    }

    @Override
    public void addNote(String note) {
        this.note = note;
    }

    @Override
    public String getNote() {
        return this.note;
    }

    @Override
    public void showDetail() {
        System.out.printf("ID: %d\n", this.id);
        System.out.printf("Name: %s\n", this.getName());
        System.out.printf("Second name: %s\n", this.getSecondName());
        System.out.printf("Last name: %s\n", this.getSurname());
        if (this.location != null) {
            System.out.printf("Location: %s\n", this.location.getName());
        }
        if (this.dob != null) {
            System.out.print("DOB: ");
            var dateFormat = new SimpleDateFormat("dd.mm.yyyy");
            System.out.println(dateFormat.format(this.getDOB()));
        }
    }

    @Override
    public Date getAdditionDate() {
        return this.dateAdded;
    }

    @Override
    public Date getModificationDate() {
        return this.dateModified;
    }
    public Person () {
        this.dateAdded = new Date();
        this.dateModified = new Date();
    }

    public boolean fitsDescription(int id, String name, String secondName, String lastName, Date dob) {
        int level = 0;
        if (id == 0 || this.id == id) {
            return true;
        }
        if (name == null || this.name.equalsIgnoreCase(name)) {
            level++;
        }
        else {
            return false;
        }
        if (secondName == null || this.secondName.equalsIgnoreCase(secondName)) {
            level++;
        }
        else {
            return false;
        }
        if (lastName == null || this.lastName.equalsIgnoreCase(lastName)) {
            level++;
        }
        else {
            return false;
        }
        if (dob == null || this.dob == dob) {
            level++;
        }
        else {
            return false;
        }
        if (level > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void setLocation(Room room) {
        this.location = room;
    }

    @Override
    public Room getLocation() {
        return this.location;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    public Date getDateAdded() {
        return this.dateAdded;
    }
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public void create() {

    }

    @Override
    public IPerson retrieve() {
        return null;
    }

    @Override
    public void update() {
        this.setDateModified(new Date());
    }

    @Override
    public void delete() {

    }
}
