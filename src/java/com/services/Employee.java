/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

/**
 *
 * @author Mahmoud
 */

public class Employee {
    private int eid;
    private String name;
    private int salary;
    
   
    public int getEid(){
        return eid;
    }   
      
    public String getName(){
        return name;
    }
    
    public int getSalary(){
        return salary;
    }
    
    public void setEid(int eid){
        this.eid=eid;
    }
    
    public void setName(String name){
        this.name=name;
    }
 
    public void setSalary(int salary){
        this.salary=salary;
    }
}
