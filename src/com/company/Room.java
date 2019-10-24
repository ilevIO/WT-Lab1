package com.company;

import com.company.people.IPerson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private List occupants = new ArrayList();
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void addOccupants(List<IPerson> occupants) {
        for (int i = 0; i < occupants.size(); i++) {
            this.occupants.add(occupants.get(i));
        }
    }
    public void setOccupants(List occupants){
        this.occupants = occupants;
    }
    public void removeOccupants(List occupants) {
        for (int i = 0; i < occupants.size(); i++) {
            for (int j = 0; j < this.occupants.size(); j++){
                if (occupants.get(i).equals(this.occupants.get(j))) {
                    this.occupants.remove(j);
                    j--;
                    break;
                }
            }
        }
    }
    public List getOccupants() {
        return this.occupants;
    }
    public Room(String name) {
        this.name = name;
    }
    public Room(){}
}
