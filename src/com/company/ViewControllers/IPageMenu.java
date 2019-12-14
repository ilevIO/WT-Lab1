package com.company.ViewControllers;

import com.company.IAppDelegate;

import java.util.ArrayList;

public interface IPageMenu {
    void setAppDelegate(IAppDelegate appDelegate);
    String getName();
    void initiateActions();
}
