package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
/**/
class SortPatientsByName implements Comparator<IPatient> {

    @Override
    public int compare(IPatient patient1, IPatient patient2) {
        return patient1.getName().compareToIgnoreCase(patient2.getName());
    }
}
/**/
public class PatientsMenu extends ActionMenu {
    private TableView patientsTableView;
    private ArrayList<IPatient> patients = new ArrayList<IPatient>();
    private String getInput(String prompt) {
        Scanner input = new Scanner(System.in);
        String name = "";
        while (name.length() == 0) {
            System.out.print(prompt);
            name = input.nextLine();
            if (name.equalsIgnoreCase("0")) {
                return null;
            }
        }
        return name;
    }
    private void addPatient() {
        IPatient newPatient = new Patient();
        String name;
        if ((name = getInput("Name: ")) != null) {
            newPatient.setName(name);
        } else {
            return;
        }
        if ((name = getInput("Second name: ")) != null) {
            newPatient.setSecondName(name);
        } else {
            //might not have a second name
        }
        if ((name = getInput("Last name: ")) != null) {
            newPatient.setSurname(name);
        } else {
            return;
        }
        //read DOB
        //read ID
        this.patients.add(newPatient);
        this.reloadPatientsTable();

    }
    private void removePatient() {
        System.out.print("Enter patient's ID or row: ");
    }
    private void searchPatients() {
        System.out.print(": ");
        String query;
        //get input
        for (int i = 0; i < patients.size(); i++) {
            //int precision = patients.get(i).satisfies(query));
            //if (patients.get(i).)
        }
    }
    private void sortPatientsByName() {
        this.patients.sort(new SortPatientsByName());
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
                break;
                /*case 2: sortPatientsByDateAdded();
                break;
                case 3: sortPatientsByDateModified();
                break;*/
                case 0: return;
                default: break;
            }

        }

    }
    private void sortPatients() {
        displaySortOptions();
    }
    private void showDetail() {
        System.out.print("...");
        int num = 0;
        Scanner input = new Scanner(System.in);
        num = input.nextInt();
        if (num < this.patients.size()) {
            this.patients.get(num - 1).showDetail();
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
            default: break;
        }
    }
    private void reloadPatientsTable() {
        patientsTableView.clear();
        for (int i = 0; i < patients.size(); i++) {
            patientsTableView.addCell(patients.get(i).getName() + patients.get(i).getSecondName()+patients.get(i).getSurname());
        }
    }
    private void loadPatients() {
        this.patients.add(Patient.random());
        this.patients.add(Patient.random());
        this.patients.add(Patient.random());
        reloadPatientsTable();
    }
    @Override
    public void draw() {
        patientsTableView.draw();
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("%d. ", i+1);
            actions.get(i).draw();
        }
    }
    public PatientsMenu() {
        this.patientsTableView = new TableView();
        this.loadPatients();
    }
    public PatientsMenu(IAppDelegate appDelegate) {

    }
}
