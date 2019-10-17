package com.company;

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
}
