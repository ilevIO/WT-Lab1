package com.company;

import java.util.ArrayList;

public class MainMenu extends ActionMenu {
    @Override
    protected String getName() {
        return "Main";
    }

    @Override
    protected void initiateActions() {
        this.actions = new ArrayList<MenuAction>();
        this.actions.add(new MenuAction("Patients", this.appDelegate));
        this.actions.add(new MenuAction("Employees", this.appDelegate));
        this.actions.add(new MenuAction("Cabinets", this.appDelegate));
        this.actions.add(new MenuAction("Wards", this.appDelegate));
    }

    @Override
    protected void actionSelected(int num) {
        //actions.get(num-1).execute();
        switch (num) {
            case 1:
                appDelegate.segueTo(new PatientsMenu());
                break;
            case 2:
                appDelegate.segueTo(new EmployeesMenu());
                break;
            case 3:
                appDelegate.segueTo(new CabinetsMenu());
                break;
            case 4:
                appDelegate.segueTo(new WardsMenu());
                break;
            //appDelegate.segueTo();
            default:
                break;
        }
    }

    @Override
    public void draw() {
        for (int i = 0; i < this.actions.size(); i++) {
            System.out.printf("%d. ", i + 1);
            this.actions.get(i).draw();
        }
    }
}
