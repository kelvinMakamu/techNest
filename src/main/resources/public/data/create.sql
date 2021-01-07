CREATE DATABASE technest;
\c technest;
CREATE TABLE departments (id SERIAL PRIMARY KEY, name VARCHAR, createdAt TIMESTAMP DEFAULT NOW());
CREATE TABLE members (id SERIAL PRIMARY KEY, departmentId int, firstName VARCHAR, lastName VARCHAR, createdAt TIMESTAMP DEFAULT NOW());
CREATE TABLE roles (id SERIAL PRIMARY KEY, name VARCHAR, createdAt TIMESTAMP DEFAULT NOW());
CREATE TABLE member_role (id SERIAL PRIMARY KEY, memberId int, roleId int, createdAt TIMESTAMP DEFAULT NOW());
CREATE DATABASE technest_test WITH TEMPLATE technest;