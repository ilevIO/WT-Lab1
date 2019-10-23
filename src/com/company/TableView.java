package com.company;

import java.util.ArrayList;

public class TableView {
    private ArrayList<String> stringCells = new ArrayList<String>();
    public void addCell(String title) {
        this.stringCells.add(title);
    }
    public String selectCell(int num) {
        return stringCells.get(num);
    }
    public void clear() {
        this.stringCells.clear();
    };
    public void draw() {
        for (int i = 0; i < stringCells.size(); i++) {
            System.out.printf("%d. ", i+1);
            System.out.println(stringCells.get(i));
        }
    }
}
