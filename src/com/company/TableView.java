package com.company;

import java.util.ArrayList;

public class TableView {
    private ArrayList<String> stringCells;
    public void addCell(String title) {

    }
    public void selectCell(int num) {

    }
    public void draw() {
        for (int i = 0; i < stringCells.size(); i++) {
            System.out.printf("%d. ");
            System.out.print(stringCells.get(i));
        }
    }
}
