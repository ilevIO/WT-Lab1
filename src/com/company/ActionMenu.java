package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class ActionMenu {
    public IAppDelegate appDelegate;
    public boolean shouldReturn = false;
    protected ArrayList<MenuAction> actions = new ArrayList<MenuAction>();
    //since I cannot figure out how to use closures in MenuAction like in swift
    //protected ArrayList<String> actionStrings;
    abstract protected String getName();
    abstract protected void initiateActions();
    abstract protected void actionSelected(int num);
    abstract public void draw();
    public void enter() {
        while(true) {
            this.draw();
            System.out.print("Please enter number of action: ");
            int num = 0;
            Scanner input = new Scanner(System.in);
            num = input.nextInt();
            if (num !=  0) {
                this.actionSelected(num);
                if (this.shouldReturn) {
                    return;
                }
            }
            else {
                this.shouldReturn = true;
                break;
            }
        }
    }
    public ActionMenu(IAppDelegate appDelegate) {
        this.appDelegate = appDelegate;
        this.initiateActions();
    }
    public ActionMenu() {
        this.appDelegate = null;
        this.initiateActions();
    }
}
