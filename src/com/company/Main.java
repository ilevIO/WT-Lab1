package com.company;

public class Main {
    private static App app;
    private static void initiateApp() {
        app = new App();
    }
    public static void main(String[] args) {
        initiateApp();
        app.enter();
	// write your code here
    }
}
