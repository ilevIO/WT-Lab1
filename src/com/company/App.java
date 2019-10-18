package com.company;

public class App implements IAppDelegate {
    private ActionMenu currentMenu;
    private ActionMenu mainMenu;
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
    App () {
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
