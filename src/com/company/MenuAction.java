package com.company;

public class MenuAction {
    //void -> void action;
    private String name;
    public IAppDelegate appDelegate;
    public void draw() {
        System.out.println(this.name);
    }
    public void execute() {
    }
    public void setTitle(String title) {
        this.name = title;
    }
    public MenuAction(String name) {
        this.appDelegate = null;
        this.name = name;
    }
    public MenuAction(String name, IAppDelegate appDelegate) {
        this.appDelegate = appDelegate;
        this.name = name;
    }
}
