package com.company;

public interface ISearchable {
    //returns number of characters
    int satisfies(String query);
    boolean reallySatisfies(String query);
}
