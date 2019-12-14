package com.company.ViewControllers;

import com.company.Hospital.Hospital;

import java.util.ArrayList;

public class MainMenu extends ActionMenu {

    public static final int PATIENT_SELECTED = 1;

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
        this.actions.add(new MenuAction("DB Migrate", this.appDelegate));
    }

    @Override
    protected void actionSelected(int num) {
        //actions.get(num-1).execute();
        switch (num) {
            case PATIENT_SELECTED:
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
            case 5:
                //appDelegate.segueTo(
                Hospital.shared.migrate();
                System.out.println("Completed migration");
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
