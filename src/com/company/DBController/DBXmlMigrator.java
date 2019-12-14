package com.company.DBController;

import com.company.Hospital.HospitalSerializable;
import com.company.Hospital.HospitalSerializer;
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
    private String dbName;
    private Connection connection;

    private static final String insertPatientQuery =
            "insert into patients (p_id, name, second_name, last_name, dob, diagnosis, location) values(?,?,?,?,?,?,?);";
    private static final String insertMedSpecQuery =
            "insert into employees (p_id, name, second_name, last_name, dob, rank, salary, location) values(?,?,?,?,?,?,?,?);";
    private static final String xsdDirectoryName = "xsd";
    private static final String xmlDirectoryName = "xml";

    private String patientsTableName;
    private String employeesTableName;

    public DBXmlMigrator(Connection connection, String dbName, String patientsTableName, String employeesTableName){
        this.connection = connection;
        this.dbName = dbName;
        this.patientsTableName = patientsTableName;
        this.employeesTableName = employeesTableName;
    }
    public boolean migrate(String filePath){
        HospitalSerializer deserializer = new HospitalSerializer();
        Object hospitalData = null;
        hospitalData = deserializer.deserialize(filePath);
        return false;
    }
    private boolean migrateHospital(String tableName, HospitalSerializable hospitalData) {
        List<Employee> employees = hospitalData.getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            migrateEmployee(employeesTableName, employees.get(i));
        }
        List<Patient> patients = hospitalData.getPatients();
        for (int i = 0; i < patients.size(); i++) {
            migratePatient(patientsTableName, patients.get(i));
        }
        return true;
    }
    private boolean migrateEmployee(String tableName, Employee employee){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertPatientQuery);
            preparedStatement.setInt(1,employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getSecondName());
            preparedStatement.setString(4, employee.getSurname());
            preparedStatement.setDate(5, (Date)employee.getDOB());
            preparedStatement.setString(6, employee.getRank());
            preparedStatement.setInt(6, employee.getSalary());
            preparedStatement.setString(7, employee.getLocation().getName());

            if(preparedStatement.executeUpdate() != 1){
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    private boolean migratePatient(String tableName, Patient patient){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertPatientQuery);
            preparedStatement.setInt(1,patient.getId());
            preparedStatement.setString(2, patient.getName());
            preparedStatement.setString(3, patient.getSecondName());
            preparedStatement.setString(4, patient.getSurname());
            preparedStatement.setDate(5, (Date)patient.getDOB());
            preparedStatement.setString(6, patient.getDiagnosis());
            preparedStatement.setString(7, patient.getLocation().getName());

            if(preparedStatement.executeUpdate() != 1){
                return false;
            }
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
}