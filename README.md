# JerseyEmployeeService Project

This Java Project is a Restful Webservice Provider that connects to an Employee Oracle Database
For The Following Operations (CRUD Operations) on the Employee Database

1- Create Employee Record

2- Retrieve Employee Record

3- Update Employee Record

4- Delete Employee Record


## Technologies

This project is using Following Technologies

1- JERSEY framework : it is a reference implementation of JAX-RS webservice API.

2- JDBC (Java Database Connectivity) : it is a Java DB API used to connect and execute the query with the database

3- Jackson API : it is a Java Library API used to convert Java Object into JSON and Vice Versa

## Authentication

This Project has also Username Token Security or Basic Authentication to protect webservice against Authentication Attacks.

## How it works ?

1- The Client APP will send an HTTP Request that carry a message of required DB Operation in JSON Format to this webservice

2- The message will contain also username and Password

3- The webservice will validate whether it authenticate the client or not 

if it authenticate the client, it will allow the Client to access the service for Required DB Operation
otherwise it will throw an exception


## Notes

1- service userName : mahmoud

service password : @123

2- Employee Record in DB contains Following Fields

A- eid (employee id) : Primary Key

B- name

C- salary
