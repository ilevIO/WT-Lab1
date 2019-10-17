package com.company;

public class Patient extends Person implements IPatient {
    public Patient() {

    }

    @Override
    public void showDetail() {
        System.out.printf("Name: %s\n", this.getName());
        System.out.printf("Second name: %s\n", this.getSecondName());
        System.out.printf("Last name: %s\n", this.getSurname());
        System.out.print("DOB: ");
        System.out.println(this.getDOB());
    }
}