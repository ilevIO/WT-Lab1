package com.company;

import java.io.Serializable;
import java.util.Collection;

public interface IHospitalData extends Serializable {
    Collection<IPatient> getPatients();
    Collection<IEmployee> getEmployees();
    String serialize();
    void serializeToFile();
    //IHospitalStructure(Collection<IPatient>, Collection<IEmployee>);
}
