package com.company;

import com.company.people.IEmployee;

import java.util.Collection;

public interface IDepartment {
    Collection<IEmployee> getEmployees();
}
