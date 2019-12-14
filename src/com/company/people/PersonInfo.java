package com.company.people;

import com.company.Room;

import java.io.Serializable;
import java.util.Date;

public class PersonInfo implements Serializable {
    public String name;
    public String secondName;
    public String lastName;
    public Date dob;
    public String note;
    public Date dateAdded;
    public Date dateModified;
    public Room location;
    public int id;
}
