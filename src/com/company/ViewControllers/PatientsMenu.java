package com.company.ViewControllers;

import com.company.ConvinienceIO;
import com.company.Hospital.Hospital;
import com.company.PeopleSortingOptions.SortPeopleByDateAdded;
import com.company.PeopleSortingOptions.SortPeopleByDateModified;
import com.company.PeopleSortingOptions.SortPeopleByName;
import com.company.Room;
import com.company.people.IEmployee;
import com.company.people.IPatient;
import com.company.people.IPerson;
import com.company.people.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/*TODO */
import java.sql.*;
import java.util.Date;

/**/
public class PatientsMenu extends ActionMenu {
    private boolean displayingPatientsTable = false;
    private TableView patientsTableView;
    private Comparator<IPerson> patientsSortingMethod = new SortPeopleByName();
    private void addPatient() {
        Patient newPatient = new Patient();
        String name;
        int id = -1;
        boolean validId = false;
        Scanner input = new Scanner(System.in);
        while (!validId) {
            System.out.print("ID: ");
            try {
                id = input.nextInt();
                if (id > 0) {
                    validId = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid ID. Try Again. ");
            }
        }
        if ((name = ConvinienceIO.getInput("Name: ")) != null) {
            newPatient.setName(name);
        } else {
            return;
        }
        if ((name = ConvinienceIO.getInput("Second name: ")) != null) {
            newPatient.setSecondName(name);
        } else {
            //might not have a second name
        }
        if ((name = ConvinienceIO.getInput("Last name: ")) != null) {
            newPatient.setSurname(name);
        } else {
            return;
        }
        String dobStr = ConvinienceIO.getInput("DOB dd.mm.yyyy: ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        while (true) {
            try {
                var dob = dateFormat.parse(dobStr);
                newPatient.setDOB(dob);
                break;
            } catch (ParseException e) {
                e.printStackTrace();
                dobStr = ConvinienceIO.getInput("try again. DOB dd.mm.yyyy: ");
            }
        }
        newPatient.setId(id);
        Hospital.shared.addPatient(newPatient);
        this.reloadPatientsTable();
    }
    private void removePatient() {
        System.out.print("ID: ");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        var patients = Hospital.shared.getPatients();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId() == id) {
                var patient = Hospital.shared.getPatients().get(i);
                if (patient.getLocation() != null) {
                    var occupant = new ArrayList<IPerson>();
                    occupant.add(patient);
                    patient.getLocation().removeOccupants(occupant);
                }
                Hospital.shared.getPatients().remove(i);
                //patients.remove(i);
                i--;
            }
        }
        reloadPatientsTable();
    }
    private void showDetailInList(List<IPatient> list) {
        System.out.print("Enter number of a patient: ");
        int num = 0;
        Scanner input = new Scanner(System.in);
        num = input.nextInt();
        if (num <= list.size()) {
            list.get(num - 1).showDetail();
        }
    }
    private void searchPatients() {
        Scanner input = new Scanner(System.in);
        System.out.print("ID: ");
        var idStr = input.nextLine();
        int id = -1;
        if (!idStr.isBlank()) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                id = -1;
            }
        }
        System.out.print("Name: ");
        var name = input.nextLine();
        if (name.isBlank()) {
            name = null;
        }
        System.out.print("Second name: ");
        var secondName = input.nextLine();
        if (secondName.isBlank()) {
            secondName = null;
        }
        System.out.print("Last name: ");
        var lastName = input.nextLine();
        if (lastName.isBlank()) {
            lastName = null;
        }
        System.out.print("DOB (dd.mm.yyyy): ");
        var dobStr = input.nextLine();
        Date dob = null;
        try {
            var dateFormat = new SimpleDateFormat("dd.mm.yyyy");
            dob = dateFormat.parse(dobStr);
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        List<IPatient> suitableEmployees = new ArrayList<IPatient>();
        int suitableCount = 0;
        for (int i = 0; i < Hospital.shared.getPatients().size(); i++) {
            if (Hospital.shared.getPatients().get(i).fitsDescription(id, name, secondName, lastName, dob)) {
                suitableEmployees.add(Hospital.shared.getPatients().get(i));
                suitableCount++;
                System.out.printf("%d, %s %s %s\n", suitableCount, Hospital.shared.getPatients().get(i).getName(), Hospital.shared.getPatients().get(i).getSecondName(), Hospital.shared.getPatients().get(i).getSurname());

            }
        }
        System.out.print("1. ");
        actions.get(5-1).draw();
        int actionNum;
        actionNum = input.nextInt();
        switch (actionNum) {
            case 1:
                showDetailInList(suitableEmployees);
                break;
            default:
                break;
        }
    }
    private void sortPatientsByName() {
        this.patientsSortingMethod = new SortPeopleByName();
        Hospital.shared.getPatients().sort(this.patientsSortingMethod);
        reloadPatientsTable();
    }
    private void sortPatientsByDateAdded() {
        this.patientsSortingMethod = new SortPeopleByDateAdded();
        Hospital.shared.getPatients().sort(this.patientsSortingMethod);
        reloadPatientsTable();
    }
    private void sortPatientsByDateModified() {
        this.patientsSortingMethod = new SortPeopleByDateModified();
        Hospital.shared.getPatients().sort(this.patientsSortingMethod);
        reloadPatientsTable();
    }
    private void displaySortOptions() {
        int sortKind = 0;
        while (true) {
            System.out.println("1: by name");
            System.out.println("2: by date added");
            System.out.println("3: by date modified");
            System.out.println("0: back");
            Scanner input = new Scanner(System.in);
            sortKind = input.nextInt();
            switch (sortKind) {
                case 1: sortPatientsByName();
                return;
                case 2:
                    sortPatientsByDateAdded();
                return;
                case 3: sortPatientsByDateModified();
                return;
                case 0: return;
                default: break;
            }

        }

    }
    private void sortPatients() {
        displaySortOptions();
    }
    private void displayPatientEditingOptions(IPatient patient) {
        while(true) {
            System.out.println("1: Name: "+patient.getName());
            System.out.println("2: Second name:"+patient.getSecondName());
            System.out.println("3: Last name: "+patient.getSurname());
            System.out.println("4: DOB: "+patient.getDOB());
            System.out.println("5: ID: "+patient.getId());
            System.out.println("6: Assigned doctors: "+patient.getDoctors());
            System.out.println("7: Ward: "+ (patient.getLocation() != null ? patient.getLocation().getName() : ""));
            System.out.println("0: return");
            Scanner input = new Scanner(System.in);
            int actionNum = input.nextInt();
            switch(actionNum) {
                case 1: {
                    System.out.print("Enter new name: ");
                    input = new Scanner(System.in);
                    String name = input.nextLine();
                    patient.setName(name);
                    break;
                }
                case 2: {
                    System.out.print("Enter new second name: ");
                    input = new Scanner(System.in);
                    String secondName = input.nextLine();
                    patient.setSecondName(secondName);
                    break;
                }
                case 3: {
                    System.out.print("Enter new last name: ");
                    input = new Scanner(System.in);
                    String name = input.nextLine();
                    patient.setSurname(name);
                    break;
                }
                case 4: {
                    System.out.print("Enter new DOB: ");
                    input = new Scanner(System.in);
                    String dobStr = input.nextLine();
                    try {
                        Date dob = new SimpleDateFormat("dd.MM.yyyy").parse(dobStr);
                        patient.setDOB(dob);
                    } catch (ParseException e) {
                    }
                    break;
                }
                case 5:
                    System.out.print("Enter new ID: ");
                    input = new Scanner(System.in);
                    String newIdStr = input.nextLine();
                    try {
                        int id = Integer.parseInt(newIdStr);
                        patient.setId(id);
                    } catch (NumberFormatException e) {

                    }
                    break;
                case 6:
                    System.out.print("Search doctor: ");
                    input = new Scanner(System.in);
                    System.out.print("ID: ");
                    var idStr = input.nextLine();
                    int id = 0;
                    if (!idStr.isBlank()) {
                        try {
                            id = Integer.parseInt(idStr);
                        } catch (NumberFormatException e) {
                            id = 0;
                        }
                    }
                    System.out.print("Name: ");
                    var name = input.nextLine();
                    if (name.isBlank()) {
                        name = null;
                    }
                    System.out.print("Second name: ");
                    var secondName = input.nextLine();
                    if (secondName.isBlank()) {
                        secondName = null;
                    }
                    System.out.print("Last name: ");
                    var lastName = input.nextLine();
                    if (lastName.isBlank()) {
                        lastName = null;
                    }
                    ArrayList<IEmployee> suitableEmployees = new ArrayList<IEmployee>();
                    int suitableCount = 0;
                    ArrayList<Integer> indexArray = new ArrayList<Integer>();
                    System.out.println("0. None");
                    for (int i = 0; i < Hospital.shared.getEmployees().size(); i++) {
                        if (Hospital.shared.getEmployees().get(i).fitsDescription(id, name, secondName, lastName, null)) {
                            suitableEmployees.add(Hospital.shared.getEmployees().get(i));
                            suitableCount++;
                            System.out.printf("%d. %s %s %s\n", suitableCount, Hospital.shared.getEmployees().get(i).getName(), Hospital.shared.getEmployees().get(i).getSecondName(), Hospital.shared.getEmployees().get(i).getSurname());
                            indexArray.add(i);
                        }
                    }
                    System.out.print("Enter number of doctor: ");
                    int selectionNum;
                    selectionNum = input.nextInt();
                    if (selectionNum == 0){
                        for (int i = 0; i < patient.getDoctors().size(); i++) {
                            //(ArrayList<IEmployee>)(patient.getDoctors()).get(i).removePatient(patient);
                        }
                        //patient.getDoctors().removeAll();
                    } else {
                        Hospital.shared.getEmployees().get(indexArray.get(selectionNum - 1)).addPatient(patient);
                        patient.assignDoctor(Hospital.shared.getEmployees().get(indexArray.get(selectionNum - 1)));
                    }
                    break;
                case 7:
                    String cabStr;
                    boolean cabinetFound = false;
                    List occupant = new ArrayList<IPerson>();
                    occupant.add(patient);
                    Room cabinet = null;
                    while (!cabinetFound) {
                        if ((cabStr = ConvinienceIO.getInput("Ward: ", true)) != null) {
                            cabinet = Hospital.shared.getWard(cabStr);
                            if (cabinet == null) {
                                System.out.printf("Ward %s does not exist. Create ward %s? y/n: ", cabStr, cabStr);
                                String yOrN = ConvinienceIO.getInput("");
                                while (true) {
                                    if (yOrN.equalsIgnoreCase("y") || yOrN.equalsIgnoreCase("n")) {
                                        break;
                                    } else {
                                        yOrN = ConvinienceIO.getInput("try again. y/n: ");
                                    }
                                }
                                if (yOrN.equalsIgnoreCase("y")) {
                                    cabinet = Hospital.shared.addWard(cabStr);
                                }
                                cabinetFound = true;
                            } else {
                                cabinetFound = true;
                            }
                            var prevLocation = patient.getLocation();
                            if (prevLocation != null) {
                                prevLocation.removeOccupants(occupant);
                            }
                            if (cabinet != null) {
                                cabinet.addOccupants(occupant);
                            }
                            patient.setLocation(cabinet);
                        } else {
                            cabinetFound = true;
                            var prevLocation = patient.getLocation();
                            if (prevLocation != null) {
                                prevLocation.removeOccupants(occupant);
                            }
                            if (cabinet != null) {
                                cabinet.addOccupants(occupant);
                            }
                            patient.setLocation(cabinet);
                            //might not have a cabinet
                        }
                    }
                    break;
                case 0:
                    break;
                default:
                    break;
            }
            if (actionNum > 0 && actionNum <= 7) {
                patient.setDateModified(new Date());
                Hospital.shared.serialize();
            } else if (actionNum == 0){
                return;
            }
        }
    }
    private void showDetail() {
        System.out.print("Enter number of a patient: ");
        int num = 0;
        Scanner input = new Scanner(System.in);
        num = input.nextInt();
        if (num <= Hospital.shared.getPatients().size()) {
            Hospital.shared.getPatients().get(num - 1).showDetail();
            displayPatientEditingOptions(Hospital.shared.getPatients().get(num-1));
        }
    }
    @Override
    protected String getName() {
        return "Patients";
    }
    @Override
    protected void initiateActions() {
        this.actions.add(new MenuAction("Add new patient", this.appDelegate));
        this.actions.add(new MenuAction("Remove patient", this.appDelegate));
        this.actions.add(new MenuAction("Find patient", this.appDelegate));
        this.actions.add(new MenuAction("Sort patients", this.appDelegate));
        this.actions.add(new MenuAction("Show detail", this.appDelegate));
        this.actions.add(new MenuAction("Display patients", this.appDelegate));
    }
    private void showPatientsTable() {
        this.displayingPatientsTable = true;
    }
    @Override
    protected void actionSelected(int num) {
        switch (num) {
            case 1:
                addPatient();
                break;
            case 2:
                removePatient();
                break;
            case 3: searchPatients();
            break;
            case 4: sortPatients();
            break;
            case 5: showDetail();
            break;
            case 6:
                this.displayingPatientsTable = !this.displayingPatientsTable;
                if (this.displayingPatientsTable) {
                    this.actions.get(6 - 1).setTitle("Hide patients");
                } else {
                    this.actions.get(6 - 1).setTitle("Display patients");
                }
                break;
            default: break;
        }
    }
    private void reloadPatientsTable() {
        patientsTableView.clear();
        List<IPatient> tmpPatients = Hospital.shared.getPatients();
        //tmpPatients.sort(this.patientsSortingMethod);
        for (int i = 0; i < tmpPatients.size(); i++) {
            patientsTableView.addCell(tmpPatients.get(i).getName() + " " + tmpPatients.get(i).getSecondName() + " " + tmpPatients.get(i).getSurname());
        }
    }
    @Override
    public void draw() {
        if (this.displayingPatientsTable) {
            patientsTableView.draw();
        }
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("%d. ", i+1);
            actions.get(i).draw();
        }
    }
    public PatientsMenu() {
        this.patientsTableView = new TableView();
        reloadPatientsTable();
    }
}
