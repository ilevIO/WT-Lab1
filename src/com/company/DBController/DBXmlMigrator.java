package com.company.DBController;

import com.company.Hospital.HospitalSerializable;
import com.company.Hospital.HospitalSerializer;
import com.company.XMLStuff.XSDValidator;
import com.company.people.Employee;
import com.company.people.Patient;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBXmlMigrator {
    private Connection connection;

    private static String insertPatientQuery =
            "insert into Patients (p_id, name, second_name, last_name, dob, diagnosis, location) values(?,?,?,?,?,?,?);";
    private static String insertEmployeeQuery =
            "insert into Employees (p_id, name, second_name, last_name, dob, rank, salary, location) values(?,?,?,?,?,?,?,?);";


    public DBXmlMigrator(Connection connection){
        this.connection = connection;
    }
    public boolean migrate(String xmlFilePath){
        HospitalSerializer deserializer = new HospitalSerializer();
        HospitalSerializable hospitalData = null;
        hospitalData = deserializer.deserialize(xmlFilePath);
        migrateHospital(hospitalData);
        return false;
    }
    private boolean migrateHospital(HospitalSerializable hospitalData) {
        List<Patient> patients = hospitalData.getPatients();
        for (int i = 0; i < patients.size(); i++) {
            migratePatient(patients.get(i));
        }
        List<Employee> employees = hospitalData.getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            migrateEmployee(employees.get(i));
        }
        return true;
    }
    private boolean migrateEmployee(Employee employee){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertEmployeeQuery);
            preparedStatement.setInt(1,employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getSecondName());
            preparedStatement.setString(4, employee.getSurname());
            preparedStatement.setDate(5, new java.sql.Date(employee.getDOB().getTime()));
            preparedStatement.setString(6, employee.getRank());
            preparedStatement.setInt(7, employee.getSalary());
            String location = "unassigned";
            if (employee.getLocation() != null) {
                location = employee.getLocation().getName();
            }
            preparedStatement.setString(8, location);
            System.out.println(preparedStatement.toString());
            if(preparedStatement.executeUpdate() != 1){
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    private boolean migratePatient(Patient patient){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertPatientQuery);
            preparedStatement.setInt(1,patient.getId());
            preparedStatement.setString(2, patient.getName());
            preparedStatement.setString(3, patient.getSecondName());
            preparedStatement.setString(4, patient.getSurname());
            preparedStatement.setDate(5, new java.sql.Date(patient.getDOB().getTime()));
            preparedStatement.setString(6, patient.getDiagnosis());
            String location = "unassigned";
            if (patient.getLocation() != null) {
                location = patient.getLocation().getName();
            }
            preparedStatement.setString(7, location);;
            System.out.println(preparedStatement.toString());
            if(preparedStatement.executeUpdate() != 1){
                return false;
            }
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
}