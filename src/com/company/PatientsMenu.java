package com.company;

import com.company.people.IEmployee;
import com.company.people.IPatient;
import com.company.people.IPerson;
import com.company.people.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/*TODO */

/**
 * Sorting options:
 */
class SortPeopleByName implements Comparator<IPerson> {
    @Override
    public int compare(IPerson person1, IPerson person2) {
        return person1.getName().compareToIgnoreCase(person2.getName());
    }
}
class SortPeopleByDateAdded implements Comparator<IPerson> {
    @Override
    public int compare(IPerson person1, IPerson person2) {
        return person1.getAdditionDate().compareTo(person2.getAdditionDate());
    }
}
class SortPeopleByDateModified implements Comparator<IPerson> {
    @Override
    public int compare(IPerson person1, IPerson person2) {
        return person1.getModificationDate().compareTo(person2.getModificationDate());
    }
}
/**/
public class PatientsMenu extends ActionMenu {
    private boolean displayingPatientsTable = false;
    private TableView patientsTableView;
    private Comparator<IPerson> patientsSortingMethod = new SortPeopleByName();
    private void addPatient() {
        Patient newPatient = new Patient();
        String name;
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
        Hospital.shared.addPatient(newPatient);
        this.reloadPatientsTable();
    }
    private void removePatient() {
        System.out.print("Enter patient's row: ");
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        Hospital.shared.getPatients().remove(row - 1);
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
            System.out.println("1: Name");
            System.out.println("2: Second name");
            System.out.println("3: Last name");
            System.out.println("4: DOB");
            System.out.println("5: ID");
            System.out.println("6: Assigned doctors");
            System.out.println("7: Ward");
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
                    System.out.print("DOB (dd.mm.yyyy): ");
                    var dobStr = input.nextLine();
                    Date dob = null;
                    try {
                        var dateFormat = new SimpleDateFormat("dd.mm.yyyy");
                        dob = dateFormat.parse(dobStr);
                    } catch (ParseException e) {
                        //e.printStackTrace();
                    }
                    ArrayList<IEmployee> suitableEmployees = new ArrayList<IEmployee>();
                    int suitableCount = 0;
                    ArrayList<Integer> indexArray = new ArrayList<Integer>();
                    for (int i = 0; i < Hospital.shared.getEmployees().size(); i++) {
                        if (Hospital.shared.getEmployees().get(i).fitsDescription(id, name, secondName, lastName, dob)) {
                            suitableEmployees.add(Hospital.shared.getEmployees().get(i));
                            suitableCount++;
                            System.out.printf("%d. %s %s %s\n", suitableCount, Hospital.shared.getEmployees().get(i).getName(), Hospital.shared.getEmployees().get(i).getSecondName(), Hospital.shared.getEmployees().get(i).getSurname());
                            indexArray.add(i);
                        }
                    }
                    System.out.print("Enter number of doctor: ");
                    //actions.get(5-1).draw();
                    int selectionNum;
                    selectionNum = input.nextInt();
                    Hospital.shared.getEmployees().get(indexArray.get(selectionNum)-1).addPatient(patient);
                    patient.assignDoctor(Hospital.shared.getEmployees().get(indexArray.get(selectionNum)-1));
                    break;
                case 7:
                    break;
                case 0:
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
