package com.company.ViewControllers;

//import com.company.people.Doctor;
import com.company.ConvinienceIO;
import com.company.Hospital.Hospital;
import com.company.PeopleSortingOptions.SortPeopleByDateAdded;
import com.company.PeopleSortingOptions.SortPeopleByDateModified;
import com.company.PeopleSortingOptions.SortPeopleByName;
import com.company.Room;
import com.company.people.Employee;
import com.company.people.IEmployee;
import com.company.people.IPatient;
import com.company.people.IPerson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeesMenu extends ActionMenu {
    private boolean displayingEmployeeTable;
    private TableView employeesTableView = new TableView();
    private List<IEmployee> employees() {
        return Hospital.shared.getEmployees();
    } //= new ArrayList<IEmployee>();
    private Comparator<IPerson> employeeSortingMethod;
    @Override
    protected String getName() {
        return "Employees";
    }

    @Override
    protected void initiateActions() {
        this.actions.add(new MenuAction("Add new employee", this.appDelegate));
        this.actions.add(new MenuAction("Remove employee", this.appDelegate));
        this.actions.add(new MenuAction("Find employee", this.appDelegate));
        this.actions.add(new MenuAction("Sort employees", this.appDelegate));
        this.actions.add(new MenuAction("Show detail", this.appDelegate));
        this.actions.add(new MenuAction("Display employees", this.appDelegate));
    }

    @Override
    protected void actionSelected(int num) {
        switch (num) {
            case 1:
                addEmployee();
                break;
            case 2:
                removeEmployee();
                break;
            case 3: searchEmployees();
                break;
            case 4: sortEmployees();
                break;
            case 5: showDetail();
                break;
            case 6:
                this.displayingEmployeeTable = !this.displayingEmployeeTable;
                if (this.displayingEmployeeTable) {
                    this.actions.get(6 - 1).setTitle("Hide employee");
                } else {
                    this.actions.get(6 - 1).setTitle("Display employees");
                }
                this.reloadEmployeesTable();
                break;
            default: break;
        }
    }
    private void searchEmployees() {
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
        ArrayList<IEmployee> suitableEmployees = new ArrayList<IEmployee>();
        int suitableCount = 0;
        for (int i = 0; i < employees().size(); i++) {
            if (Hospital.shared.getEmployees().get(i).fitsDescription(id, name, secondName, lastName, dob)) {
                suitableEmployees.add(Hospital.shared.getEmployees().get(i));
                suitableCount++;
                System.out.printf("%d, %s %s %s\n", suitableCount, Hospital.shared.getEmployees().get(i).getName(), Hospital.shared.getEmployees().get(i).getSecondName(), Hospital.shared.getEmployees().get(i).getSurname());

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
    private void sortEmployeesByName() {
        Hospital.shared.getEmployees().sort(new SortPeopleByName());
        reloadEmployeesTable();
    }
    private void sortEmployeesByDateAdded() {
        Hospital.shared.getEmployees().sort(new SortPeopleByDateAdded());
        reloadEmployeesTable();
    }
    private void sortEmployeesByDateModified() {
        Hospital.shared.getEmployees().sort(new SortPeopleByDateModified());
        reloadEmployeesTable();
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
                case 1: sortEmployeesByName();
                    return;
                case 2: sortEmployeesByDateAdded();
                return;
                case 3: sortEmployeesByDateModified();
                return;
                case 0: return;
                default: break;
            }
        }
    }
    private void sortEmployees() {
        displaySortOptions();
    }
    private void showDetail() {
        System.out.print("Enter number of a patient: ");
        int num = 0;
        Scanner input = new Scanner(System.in);
        num = input.nextInt();
        if (num <= Hospital.shared.getEmployees().size()) {
            Hospital.shared.getEmployees().get(num - 1).showDetail();
            displayEmployeeEditingOptions(Hospital.shared.getEmployees().get(num - 1));
        }
    }
    private void showDetailInList(ArrayList<IEmployee> list) {
        System.out.print("Enter number of an employee: ");
        int num = 0;
        Scanner input = new Scanner(System.in);
        num = input.nextInt();
        if (num <= list.size()) {
            list.get(num - 1).showDetail();
        }
    }
    private void removeEmployee() {
        System.out.print("ID: ");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        var employees = Hospital.shared.getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                Hospital.shared.getEmployees().remove(i);
                i--;
            }
        }
        Hospital.shared.serialize();
        reloadEmployeesTable();
    }

    private void addEmployee() {
        String name;
        String secondName;
        String lastName;
        int id;
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
        } else {
            return;
        }
        if ((secondName = ConvinienceIO.getInput("Second name: ", true)) != null) {
        } else {
            //might not have a second name
        }
        if ((lastName = ConvinienceIO.getInput("Last name: ")) != null) {
        } else {
            return;
        }
        String dobStr = ConvinienceIO.getInput("DOB dd.mm.yyyy: ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dob;
        while (true) {
            try {
                dob = dateFormat.parse(dobStr);
                //newEmployee.setDOB(dob);
                break;
            } catch (ParseException e) {
                dobStr = ConvinienceIO.getInput("try again. DOB dd.mm.yyyy: ");
            }
        }
        int salary = 0;
        boolean salaryIsValid = false;
        while (!salaryIsValid) {
            String salaryStr;
            if ((salaryStr = ConvinienceIO.getInput("Salary: ")) != null) {
                while (true) {
                    try {
                        salary = Integer.parseInt(salaryStr);
                        if (salary >= 0) {
                            salaryIsValid = true;
                            break;
                        } else {
                            if ((salaryStr = ConvinienceIO.getInput("try again. Salary: ")) == null) {
                                return;
                            }
                        }
                    } catch (NumberFormatException e) {
                        if ((salaryStr = ConvinienceIO.getInput("try again. Salary: ")) == null) {
                            return;
                        } else {
                            salary = Integer.parseInt(salaryStr);
                            if (salary >= 0) {
                                salaryIsValid = true;
                                break;
                            }
                        }
                    }
                }
            } else {
                return;
            }
        }
        //read ID
        Employee newEmployee;
        String rankStr;
        if ((rankStr = ConvinienceIO.getInput("Rank: ")) != null) {

        } else {
            return;
        }
        String cabStr;
        boolean cabinetFound = false;
        Room cabinet = null;
        while(!cabinetFound) {
            if ((cabStr = ConvinienceIO.getInput("Cabinet: ", true)) != null) {
                cabinet = Hospital.shared.getCabinet(cabStr);
                if (cabinet == null) {
                    System.out.printf("Cabinet %s does not exist. Create cabinet %s? y/n: ", cabStr, cabStr);
                    String yOrN = ConvinienceIO.getInput("");
                    while (true) {
                        if (yOrN.equalsIgnoreCase("y") || yOrN.equalsIgnoreCase("n")) {
                            break;
                        } else {
                            yOrN = ConvinienceIO.getInput("try again. y/n: ");
                        }
                    }
                    if (yOrN.equalsIgnoreCase("y")){
                        cabinet = Hospital.shared.addCabinet(cabStr);
                    }
                    cabinetFound = true;
                } else {
                    cabinetFound = true;
                }
            } else {
                cabinetFound = true;
                //might not have a cabinet
            }
        }
        /*boolean isDoctor = false;
        String yOrN = ConvinienceIO.getInput("Is a doctor? y/n: ");
        while (true) {
            if (yOrN.equalsIgnoreCase("y") || yOrN.equalsIgnoreCase("n")) {
                break;
            } else {
                yOrN = ConvinienceIO.getInput("try again. DOB dd.mm.yyyy: ");
            }
        }
        if (yOrN.equalsIgnoreCase("y")){
            isDoctor = true;
        } else {
            isDoctor = false;
        }
        if (isDoctor) {
            newEmployee = new Doctor();
        } else {
            newEmployee = new Employee();
        }*/
        newEmployee = new Employee();
        newEmployee.setRank(rankStr);
        newEmployee.setName(name);
        newEmployee.setSecondName(secondName);
        newEmployee.setSurname(lastName);
        newEmployee.setSalary(salary);
        newEmployee.setLocation(cabinet);
        var occupants = new ArrayList<IPerson>();
        occupants.add(newEmployee);
        cabinet.addOccupants(occupants);
        newEmployee.setDOB(dob);
        Hospital.shared.addEmployee(newEmployee);
        try {
            Hospital.shared.getEmployees().sort(this.employeeSortingMethod);
        } catch (Exception e) {
            var employees = Hospital.shared.getEmployees();
            //e.printStackTrace();
        }

        this.reloadEmployeesTable();
    }
    private void displayEmployeeEditingOptions(IEmployee employee) {
        while(true) {
            System.out.println("0: return");
            System.out.println("1: Name: "+employee.getName());
            System.out.println("2: Second name: "+employee.getSecondName());
            System.out.println("3: Last name: "+employee.getSurname());
            System.out.println("4: DOB: "+employee.getDOB());
            System.out.println("5: ID: "+employee.getId());
            System.out.println("6: Assigned patients: " + employee.getPatients());
            System.out.println("7: Cabinet: "+ (employee.getLocation() != null ? employee.getLocation().getName() : ""));
            System.out.println("8: Salary: " + employee.getSalary());
            System.out.println("9: Rank: " + employee.getRank());
            /*System.out.println("1: Name");
            System.out.println("2: Second name");
            System.out.println("3: Last name");
            System.out.println("4: DOB");
            System.out.println("5: ID");
            System.out.println("6: Assigned patients");
            System.out.println("7: Cabinet");
            System.out.println("8: Salary");
            System.out.println("9: Rank");
            System.out.println("0: return");*/
            Scanner input = new Scanner(System.in);
            int actionNum = input.nextInt();
            switch(actionNum) {
                case 1: {
                    System.out.print("Enter new name: ");
                    input = new Scanner(System.in);
                    String name = input.nextLine();
                    employee.setName(name);
                    break;
                }
                case 2: {
                    System.out.print("Enter new second name: ");
                    input = new Scanner(System.in);
                    String secondName = input.nextLine();
                    employee.setSecondName(secondName);
                    break;
                }
                case 3: {
                    System.out.print("Enter new last name: ");
                    input = new Scanner(System.in);
                    String name = input.nextLine();
                    employee.setSurname(name);
                    break;
                }
                case 4: {
                    System.out.print("Enter new DOB: ");
                    input = new Scanner(System.in);
                    String dobStr = input.nextLine();
                    try {
                        Date dob = new SimpleDateFormat("dd.MM.yyyy").parse(dobStr);
                        employee.setDOB(dob);
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
                        employee.setId(id);
                    } catch (NumberFormatException e) {

                    }
                    break;
                case 6:
                    System.out.print("Search patient: ");
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
                    ArrayList<IPatient> suitableEmployees = new ArrayList<IPatient>();
                    int suitableCount = 0;
                    ArrayList<Integer> indexArray = new ArrayList<Integer>();
                    for (int i = 0; i < Hospital.shared.getPatients().size(); i++) {
                        if (Hospital.shared.getPatients().get(i).fitsDescription(id, name, secondName, lastName, null)) {
                            suitableEmployees.add(Hospital.shared.getPatients().get(i));
                            suitableCount++;
                            System.out.printf("%d. %s %s %s\n", suitableCount, Hospital.shared.getPatients().get(i).getName(), Hospital.shared.getPatients().get(i).getSecondName(), Hospital.shared.getPatients().get(i).getSurname());
                            indexArray.add(i);
                        }
                    }
                    System.out.print("Enter number of patient: ");
                    int selectionNum;
                    selectionNum = input.nextInt();
                    Hospital.shared.getPatients().get(indexArray.get(selectionNum)-1).assignDoctor(employee);
                    employee.addPatient(Hospital.shared.getPatients().get(indexArray.get(selectionNum)-1));
                    break;
                case 7:
                    String cabStr;
                    boolean cabinetFound = false;
                    List occupant = new ArrayList<IPerson>();
                    occupant.add(employee);
                    Room cabinet = null;
                    while(!cabinetFound) {
                        if ((cabStr = ConvinienceIO.getInput("Cabinet: ", true)) != null) {
                            cabinet = Hospital.shared.getWard(cabStr);
                            if (cabinet == null) {
                                System.out.printf("Cabinet %s does not exist. Create cabinet %s? y/n: ", cabStr, cabStr);
                                String yOrN = ConvinienceIO.getInput("");
                                while (true) {
                                    if (yOrN.equalsIgnoreCase("y") || yOrN.equalsIgnoreCase("n")) {
                                        break;
                                    } else {
                                        yOrN = ConvinienceIO.getInput("try again. y/n: ");
                                    }
                                }
                                if (yOrN.equalsIgnoreCase("y")){
                                    cabinet = Hospital.shared.addCabinet(cabStr);
                                }
                                cabinetFound = true;
                            } else {
                                cabinetFound = true;
                            }
                            var prevLocation = employee.getLocation();
                            if (prevLocation != null) {
                                prevLocation.removeOccupants(occupant);
                            }
                            if (cabinet != null) {
                                cabinet.addOccupants(occupant);
                            }
                            employee.setLocation(cabinet);
                        } else {
                            cabinetFound = true;
                            var prevLocation = employee.getLocation();
                            if (prevLocation != null) {
                                prevLocation.removeOccupants(occupant);
                            }
                            if (cabinet != null) {
                                cabinet.addOccupants(occupant);
                            }
                            employee.setLocation(cabinet);
                            //might not have a cabinet
                        }
                    }
                    break;
                case 8:
                    System.out.print("Enter new salary: ");
                    input = new Scanner(System.in);
                    String newSalary = input.nextLine();
                    try {
                        int salary = Integer.parseInt(newSalary);
                        employee.setSalary(salary);
                    } catch (NumberFormatException e) {

                    }
                    break;
                case 9:
                    System.out.print("Enter new rank: ");
                    input = new Scanner(System.in);
                    String rank = input.nextLine();
                    employee.setRank(rank);
                    break;
                case 0:
                    return;
                default:
                    break;
            }
            if (actionNum > 0 && actionNum <= 9) {
                employee.setDateModified(new Date());
                Hospital.shared.serialize();
            } else if (actionNum == 0){
                return;
            }
        }
    }
    private void reloadEmployeesTable() {
        employeesTableView.clear();
        var employees = Hospital.shared.getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            employeesTableView.addCell(employees.get(i).getName() + " " + employees.get(i).getSecondName() + " " + employees.get(i).getSurname());
        }
    }

    @Override
    public void draw() {
        if (this.displayingEmployeeTable) {
            employeesTableView.draw();
        }
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("%d. ", i+1);
            actions.get(i).draw();
        }
    }
}
