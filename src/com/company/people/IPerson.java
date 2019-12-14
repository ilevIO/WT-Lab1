package com.company.people;

import com.company.ISearchable;
import com.company.Room;

import java.util.Date;

public interface IPerson {
    void setName(String name);
    String getName();
    void setSecondName(String name);
    String getSecondName();
    void setSurname(String name);
    String getSurname();
    void setDOB(Date date);
    Date getDOB();
    void addNote(String note);
    String getNote();
    void showDetail();
    Date getAdditionDate();
    Date getModificationDate();
    boolean fitsDescription(int id, String name, String secondName, String lastName, Date dob);
    void setLocation(Room room);
    Room getLocation();
    int getId();
    void setId(int id);
    void setDateAdded(Date dateAdded);
    Date getDateAdded();
    void setDateModified(Date dateModified);
    //PersonInfo getInfo();
    void create();
    IPerson retrieve();
    void update();
    void delete();
}
