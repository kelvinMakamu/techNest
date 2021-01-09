CREATE DATABASE technest;
\c technest;
CREATE TABLE departments (id SERIAL PRIMARY KEY, name VARCHAR);
CREATE TABLE members (id SERIAL PRIMARY KEY, departmentId int, firstName VARCHAR, lastName VARCHAR);
CREATE TABLE roles (id SERIAL PRIMARY KEY, name VARCHAR);
CREATE TABLE member_role (id SERIAL PRIMARY KEY, memberId int, roleId int);
CREATE DATABASE technest_test WITH TEMPLATE technest;