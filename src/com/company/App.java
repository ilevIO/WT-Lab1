package com.company;

public class App implements IAppDelegate {
    private ActionMenu currentMenu;
    private ActionMenu mainMenu;
    //public static Hospital hospital = new Hospital();
    //private ArrayList<PageMenu> menues;
    public void drawMainMenu() {

    }
    private void initiateMenu() {
        mainMenu = new MainMenu();
        mainMenu.appDelegate = this;
        currentMenu = mainMenu;
    }
    public void enter() {
        this.currentMenu.enter();
    }
    private void readData() {

    }
    App () {
        readData();
        initiateMenu();
        this.currentMenu = mainMenu;
    }

    @Override
    public void segueTo(ActionMenu menu) {
        this.currentMenu = menu;
        menu.appDelegate = this;
        enter();
    }
}
