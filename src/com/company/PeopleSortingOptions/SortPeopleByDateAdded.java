package com.company.PeopleSortingOptions;

import com.company.people.IPerson;

import java.util.Comparator;

public class SortPeopleByDateAdded implements Comparator<IPerson> {
    @Override
    public int compare(IPerson person1, IPerson person2) {
        return person1.getAdditionDate().compareTo(person2.getAdditionDate());
    }
}
