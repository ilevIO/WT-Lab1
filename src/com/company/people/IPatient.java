package com.company.people;

import java.io.Serializable;
import java.util.List;

public interface IPatient extends Serializable, IPerson {
    void getHistory();
    void setDiagnosis(String diagnosis);
    String getDiagnosis();
    void assignDoctor(Employee doctor);
    void removeDoctor(Employee doctor);
    List getDoctors();
    void setDoctors(List doctors);

}
