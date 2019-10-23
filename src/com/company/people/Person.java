package com.company.people;

import com.company.Room;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements IPerson, Serializable {
    private String name;
    private String secondName;
    private String lastName;
    private Date dob;
    private String note;
    protected Date dateAdded;
    protected Date dateModified;
    private Room location;
    private int id;
    @Override
    public void setName(String name) {
        this.name = name;
    }

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

    static Person random() {
        Person person = new Person();
        person.setName("Some");
        person.setSecondName("Interesting");
        person.setSurname("Person");
        return person;
    }
    public Person () {
        this.dateAdded = new Date();
        this.dateModified = new Date();
    }

    @Override
    public int satisfies(String query) {
        var tags = query.split(" ");
        int fits = 0;
        for (int i = 0; i < tags.length; i++) {
            if (tags[i].equalsIgnoreCase(this.name)) {
                fits += 1;
            }
            if (tags[i].equalsIgnoreCase(this.secondName)) {

            }
            if (tags[i].equalsIgnoreCase(this.lastName)) {
                fits += 2;
            }
            var dateFormat = new SimpleDateFormat("dd.mm.yyyy");

            if (tags[i].equalsIgnoreCase(dateFormat.format(this.dob))) {
                fits += 2;
            }
        }
        return fits;
    }

    @Override
    public boolean reallySatisfies(String query) {
        var tags = query.split(" ");
        int fits = 0;
        for (int i = 0; i < tags.length; i++) {
            if (tags[i].equalsIgnoreCase(this.name)) {
                fits += 1;
            }
            if (tags[i].equalsIgnoreCase(this.secondName)) {

            }
            if (tags[i].equalsIgnoreCase(this.lastName)) {
                fits += 1;
            }
        }
        if (fits > 2) {
            return true;
        }
        return false;
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
}
