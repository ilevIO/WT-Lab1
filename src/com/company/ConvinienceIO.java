package com.company;

import java.util.Scanner;

public class ConvinienceIO {
    public static String getInput(String prompt) {
        Scanner input = new Scanner(System.in);
        String name = "";
        while (name.length() == 0) {
            System.out.print(prompt);
            name = input.nextLine();
            if (name.equalsIgnoreCase("..")) {
                return null;
            }
        }
        return name;
    }
    public static String getInput(String prompt, boolean canSkip) {
        Scanner input = new Scanner(System.in);
        String name = "";
        System.out.print(prompt);
        name = input.nextLine();
        return name;
    }
}
