package com.company;

import com.company.people.Employee;

import java.util.ArrayList;
import java.util.List;

public class HospitalToSave {
   private List employees=new ArrayList();
   public HospitalToSave(){}
   public List getEmployees() {
       return employees;
   }
   public void setEmployees(List employees) {
       this.employees = employees;
   }
   public String toString(){
       String movies="";
       for (Object movie : getEmployees()){
           movies += ((Employee)movie).getName()+", ";
       }
       return movies;
   }
}
