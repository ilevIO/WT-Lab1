package com.company;

import java.util.Date;

public class Person implements IPerson {
    private String name;
    private String secondName;
    private String lastName;
    private Date dob;
    private String note;

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

    }
    static Person random() {
        Person person = new Person();
        person.setName("Some");
        person.setSecondName("Interesting");
        person.setSurname("Person");
        return person;
    }
}
