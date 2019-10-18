package com.company;

public class CabinetsMenu extends ActionMenu {

    @Override
    protected String getName() {
        return "Cabinets";
    }

    @Override
    protected void initiateActions() {
        this.actions.add(new MenuAction("Add cabinet", this.appDelegate));
        this.actions.add(new MenuAction("Edit cabinet", this.appDelegate));
        this.actions.add(new MenuAction("Remove cabinet", this.appDelegate));
    }

    @Override
    protected void actionSelected(int num) {

    }

    @Override
    public void draw() {

    }
}
