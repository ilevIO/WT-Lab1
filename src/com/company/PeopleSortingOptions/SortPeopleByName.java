package com.company.PeopleSortingOptions;

import com.company.people.IPerson;

import java.util.Comparator;

/**
 * Sorting options:
 */
public class SortPeopleByName implements Comparator<IPerson> {
    @Override
    public int compare(IPerson person1, IPerson person2) {
        return person1.getName().compareToIgnoreCase(person2.getName());
    }
}
