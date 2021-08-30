drop table if exists employee_entity;
create table employee_entity ( employee_id int auto_increment primary key,employee_email varchar(255)  unique not null, employee_name varchar(255), employee_designation varchar(255), employee_address varchar(255));
