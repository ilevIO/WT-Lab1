package com.company.ViewControllers;

import com.company.Hospital.Hospital;
import com.company.people.Person;

import java.util.List;
import java.util.Scanner;

public class CabinetsMenu extends ActionMenu {

    private boolean displayingCabinetsMenu = false;
    private TableView cabinetsTableView = new TableView();
    @Override
    protected String getName() {
        return "Cabinets";
    }

    @Override
    protected void initiateActions() {
        this.actions.add(new MenuAction("Add cabinet", this.appDelegate));
        this.actions.add(new MenuAction("Remove cabinet", this.appDelegate));
        this.actions.add(new MenuAction("Display cabinets", this.appDelegate));
        this.actions.add(new MenuAction("Show detail", this.appDelegate));
    }

    private void addNewCabinet() {
        Scanner input = new Scanner(System.in);
        boolean validCabinet = false;
        while (!validCabinet) {
            System.out.print("Name: ");
            String name = input.nextLine();
            if (name.length() > 0) {
                if (Hospital.shared.getCabinet(name) == null) {
                    Hospital.shared.addCabinet(name);
                    validCabinet = true;
                } else {
                    System.out.printf("Cabinet %s already exists. Try again. \n", name);
                }
            }
        }
    }

    /**
     * Removes cabinet from list and sets occupants' location to null;
     */
    private void removeCabinet() {
        Scanner input = new Scanner(System.in);
        System.out.print("Cabinet name: ");
        String cabName = input.nextLine();
        var cabinets = Hospital.shared.getCabinets();
        boolean cabinetFound = false;
        for(int i = 0; i < cabinets.size(); i++) {
            if (cabinets.get(i).getName().equalsIgnoreCase(cabName)) {
                List<Person> occupants = cabinets.get(i).getOccupants();
                for (int j = 0; j < occupants.size(); j++) {
                    occupants.get(i).setLocation(null);
                }
                cabinets.remove(i);
                Hospital.shared.serialize();
                cabinetFound = true;
                break;
            }
        }
        if (!cabinetFound) {
            System.out.printf("No cabinet with name '%s' to be found\n", cabName);
        }
    }
    private void showDetail() {
        System.out.print("Enter name of a cabinet: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        var cabinet = Hospital.shared.getCabinet(name);
        if (cabinet != null) {
            cabinet.showDetail();
        } else {
            System.out.printf("Cabinet %s does not exist\n", name);
        }
    }
    @Override
    protected void actionSelected(int num) {
        switch (num) {
            case 1:
                addNewCabinet();
                break;
            case 2:
                removeCabinet();
                break;
            case 3:
                displayingCabinetsMenu = !displayingCabinetsMenu;
                reloadCabinetsTableView();
                this.draw();
                break;
            case 4:
                showDetail();
            case 0:
                return;
            default:
                break;
        }
    }
    private void reloadCabinetsTableView() {
        cabinetsTableView.clear();
        var cabinets = Hospital.shared.getCabinets();
        for (int i = 0; i < cabinets.size(); i++) {
            cabinetsTableView.addCell(cabinets.get(i).getName());
        }
    }
    @Override
    public void draw() {
        if (this.displayingCabinetsMenu) {
            cabinetsTableView.draw();
        }
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("%d. ", i+1);
            actions.get(i).draw();
        }
    }
}
