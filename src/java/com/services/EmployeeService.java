/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/employeeService")
public class EmployeeService {


// CRUD Application 
    
    @GET
    @Path("/sayHello")
    public String sayHello(@QueryParam("name") String name){
        return "Hello "+name;
    }
        
// Create Operation
    @POST
    @Path("/createEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployee(Employee emp) {
        int eid = emp.getEid();
        String name = emp.getName();
        int salary = emp.getSalary();
        Response res = null;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt = conn.prepareStatement("INSERT INTO employee(EID, NAME,SALARY) VALUES (?,?,?)");
            stmt.setInt(1, eid);
            stmt.setString(2, name);
            stmt.setInt(3, salary);
            int noOfRecordsUpdated = stmt.executeUpdate();
            if (noOfRecordsUpdated > 0) {
                res = Response.status(200).entity("Employee Inserted Successfully...").build();
            } else {
                res = Response.status(201).entity("Employee Inserted Failed...").build();
            }
        } catch (SQLException e) {
            res = Response.status(202).entity("Employee Inserted Failed...").build();
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }}

        return res;
    }

    // Jackson API Read Operation
    @GET
    @Path("/getEmployeeById")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeById(@QueryParam("eid") int eid) {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Employee emp = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM employee WHERE EID = '" + eid + "'");
            if (rs.next()) {
                emp = new Employee();
                emp.setEid(rs.getInt(1));
                emp.setName(rs.getString(2));
                emp.setSalary(rs.getInt(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return emp;
    }

    //Simple JSON API Read Operation
//    @GET
//    @Path("/getEmployeeById2")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getEmployeeById2(@QueryParam("eid") int eid) {
//        Connection conn = ConnectionFactory.getConnection();
//        Statement stmt = null;
//        ResultSet rs = null;
//        JSONObject emp = null;
//        try {
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT * FROM employee WHERE EID = '" + eid + "'");
//            if (rs.next()) {
//                emp = new JSONObject();
//                emp.put("EID", rs.getInt(1));
//                emp.put("Name", rs.getString(2));
//                emp.put("Salary", rs.getInt(3));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                rs.close();
//                stmt.close();
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return emp.toJSONString();
//    }
    
    // Update Operation
    @PUT
    @Path("/updateEmployeeSalary")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployeeSalary(Employee emp) {
        Response res = null;
        int noOfUpdatedRecords = 0;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt=null;
        try {
            stmt = conn.prepareStatement("UPDATE employee SET SALARY=? WHERE EID=?");
            stmt.setInt(1, emp.getSalary());
            stmt.setInt(2, emp.getEid());
            noOfUpdatedRecords = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        if (noOfUpdatedRecords > 0) {
            res = Response.status(200).entity("Employee Salary Updated Successfully...").build();
        } else {
            res = Response.status(201).entity("Employee Salary Updated Failed...").build();
        }
        return res;
    }
    
    // Delete Operation
    @DELETE
    @Path("/deleteEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(Employee emp) {
        Response res = null;
        int noOfUpdatedRecords = 0;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt=null;
        try {
             stmt = conn.prepareStatement("DELETE FROM employee WHERE eid=?");
            stmt.setInt(1, emp.getEid());
            noOfUpdatedRecords = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        if (noOfUpdatedRecords > 0) {
            res = Response.status(200).entity("Employee Deleted Successfully...").build();
        } else {
            res = Response.status(201).entity("Employee Deleted Failed...").build();
        }

        return res;

    }
}
