package com.company.ViewControllers;

import com.company.Hospital.Hospital;
import com.company.people.Person;

import java.util.List;
import java.util.Scanner;

public class WardsMenu extends ActionMenu {

    private boolean displayingWardsMenu = false;
    private TableView wardsTableView = new TableView();
    @Override
    protected String getName() {
        return "Wards";
    }

    @Override
    protected void initiateActions() {
        this.actions.add(new MenuAction("Add ward", this.appDelegate));
        this.actions.add(new MenuAction("Remove ward", this.appDelegate));
        this.actions.add(new MenuAction("Display wards", this.appDelegate));
        this.actions.add(new MenuAction("Show detail", this.appDelegate));
    }

    private void addNewWard() {
        Scanner input = new Scanner(System.in);
        boolean validWard = false;
        while (!validWard) {
            System.out.print("Name: ");
            String name = input.nextLine();
            if (name.length() > 0) {
                if (Hospital.shared.getWard(name) == null) {
                    Hospital.shared.addWard(name);
                    validWard = true;
                } else {
                    System.out.printf("Ward %s already exists. Try again. \n", name);
                }
            }
        }
    }

    /**
     * Removes ward from list and sets occupants' location to null;
     */
    private void removeWard() {
        Scanner input = new Scanner(System.in);
        System.out.print("Ward name: ");
        String cabName = input.nextLine();
        var wards = Hospital.shared.getWards();
        boolean wardFound = false;
        for(int i = 0; i < wards.size(); i++) {
            if (wards.get(i).getName().equalsIgnoreCase(cabName)) {
                List<Person> occupants = wards.get(i).getOccupants();
                for (int j = 0; j < occupants.size(); j++) {
                    occupants.get(j).setLocation(null);
                }
                wards.remove(i);
                Hospital.shared.serialize();
                wardFound = true;
                break;
            }
        }
        if (!wardFound) {
            System.out.printf("No ward with name '%s' to be found\n", cabName);
        }
    }
    private void showDetail() {
        System.out.print("Enter name of a ward: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        var ward = Hospital.shared.getWard(name);
        if (ward != null) {
            ward.showDetail();
        } else {
            System.out.printf("Ward %s does not exist\n", name);
        }
    }
    @Override
    protected void actionSelected(int num) {
        switch (num) {
            case 1:
                addNewWard();
                break;
            case 2:
                removeWard();
                break;
            case 3:
                displayingWardsMenu = !displayingWardsMenu;
                reloadWardsTableView();
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
    private void reloadWardsTableView() {
        wardsTableView.clear();
        var wards = Hospital.shared.getWards();
        for (int i = 0; i < wards.size(); i++) {
            wardsTableView.addCell(wards.get(i).getName());
        }
    }
    @Override
    public void draw() {
        if (this.displayingWardsMenu) {
            wardsTableView.draw();
        }
        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("%d. ", i+1);
            actions.get(i).draw();
        }
    }
}
