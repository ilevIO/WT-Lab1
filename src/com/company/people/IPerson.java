package com.company.people;

import com.company.ISearchable;
import com.company.Room;

import java.util.Date;

public interface IPerson extends ISearchable {
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
    boolean fitsDescription(String name, String secondName, String lastName, Date dob);
    void setLocation(Room room);
    Room getLocation();
}
