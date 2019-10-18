package com.company;

public class Cabinet extends Room {
    private IDepartment department;
    private String name;
    public void setName(String newName) {
        this.name = newName;
    }
    public String getName() {
        return this.name;
    }
}
