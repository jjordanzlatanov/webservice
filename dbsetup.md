## Create Database
```
sudo -i -u postgres psql  
create user webservice with createdb password 'password1234';  
create database wbservice owner webservice;
\c wbservice
```

## Create Tables
```
mvn clean package  
java -jar target/webservice-1.0-SNAPSHOT.jar db migrate webservice.yaml
```

## Create Functions (CRUD)
### Create
```
create or replace function create_block(name varchar(100), code varchar(10))
returns void language plpgsql as $$ declare begin
insert into block (name, code) values (name, code);
end
$$;

create or replace function create_system(name varchar(100), code varchar(10), parent_system_id int)
returns void language plpgsql as $$ declare begin
if parent_system_id = 0 then
    parent_system_id = null;
end if;

insert into system (name, code, parent_system_id) values (name, code, parent_system_id);
end
$$;
    
create or replace function create_employee(first_name varchar(100), surname varchar(100), last_name varchar(100), 
pin int) returns void language plpgsql as $$ declare begin
insert into employee (first_name, surname, last_name, pin)  values (first_name, surname, last_name, pin);
end
$$;

create or replace function create_technical_request(name varchar(50), description varchar(4000), 
creation_time timestamp(0) with time zone)
returns void language plpgsql as $$ declare begin
insert into technical_request (name, description, creation_time) values (name, description, creation_time);
end
$$;

create or replace function create_activity(name varchar(12)) returns void language plpgsql as $$ declare begin
insert into activity (name) values (name);
end
$$;

create or replace function create_technical_request_block_xref(technical_request_id int, block_id int)
returns void language plpgsql as $$ declare begin
insert into technical_request_block_xref (technical_request_id, block_id) values (technical_request_id, block_id);
end
$$;

create or replace function create_technical_request_system_xref(technical_request_id int, system_id int)
returns void language plpgsql as $$ declare begin
insert into technical_request_system_xref (technical_request_id, system_id) values (technical_request_id, system_id);
end
$$;

create or replace function create_technical_request_activity_xref(technical_request_id int, activity_id int,
employee_id int) returns void language plpgsql as $$ declare begin
insert into technical_request_activity_xref (technical_request_id, activity_id, employee_id) values (technical_request_id,
activity_id, employee_id);
end
$$;
```

### Read
```
create or replace function read_block() returns setof block language plpgsql as $$ declare begin
return query select * from block;
end
$$;

create or replace function read_block_single(id_par int)
returns setof block language plpgsql as $$ declare begin
return query select * from block where id = id_par limit 1;
end
$$;

create or replace function read_system() returns setof system language plpgsql as $$ declare begin
return query select * from system;
end
$$;

create or replace function read_employee() returns setof employee language plpgsql as $$ declare begin
return query select * from employee;
end
$$;

create or replace function read_technical_request() returns setof technical_request language plpgsql as $$ declare begin
return query select * from technical_request;
end
$$;

create or replace function read_activity() returns setof activity language plpgsql as $$ declare begin
return query select * from activity;
end
$$;

create or replace function read_technical_request_block_xref()
returns setof technical_request_block_xref language plpgsql as $$ declare begin
return query select * from technical_request_block_xref;
end
$$;

create or replace function read_technical_request_system_xref() 
returns setof technical_request_system_xref language plpgsql as $$ declare begin
return query select * from technical_request_system_xref;
end
$$;

create or replace function read_technical_request_activity_xref() 
returns setof technical_request_activity_xref language plpgsql as $$ declare begin
return query select * from technical_request_activity_xref;
end
$$;
```

### Update
```
create or replace function update_block(id_par int, name_par varchar(100), code_par varchar(10)) 
returns void language plpgsql as $$ declare begin
update block set name = coalesce(nullif(name_par, ''), name), code = coalesce(nullif(code_par, ''), code)
where id = id_par;
end
$$;

create or replace function update_system(id_par int, name_par varchar(100), code_par varchar(10),
parent_system_id_par int) returns void language plpgsql as $$ declare begin
update system set name = coalesce(nullif(name_par, ''), name), code = coalesce(nullif(code_par, ''), code)
where id = id_par;
case
    when parent_system_id_par = -1 then
        update system set parent_system_id = null where id = id_par;
    when parent_system_id_par != 0 then
        update system set parent_system_id = parent_system_id_par where id = id_par;
    else
end case;
end
$$;

create or replace function update_employee(id_par int, first_name_par varchar(100), surname_par varchar(100),
last_name_par varchar(100), pin_par int) returns void language plpgsql as $$ declare begin
update employee set first_name = coalesce(nullif(first_name_par, ''), first_name),
surname = coalesce(nullif(surname_par, ''), surname),
last_name = coalesce(nullif(last_name_par, ''), last_name), pin = coalesce(nullif(pin_par, 0), pin)
where id = id_par;
end
$$;

create or replace function update_technical_request(id_par int, name_par varchar(50), description_par varchar(4000),
creation_time_par timestamp(0) with time zone) returns void language plpgsql as $$ declare begin
update technical_request set name = coalesce(nullif(name_par, ''), name),
description = coalesce(nullif(description_par, ''), description),
creation_time = coalesce(nullif(creation_time_par, null), creation_time) where id = id_par;
end
$$;

create or replace function update_activity(id_par int, name_par varchar(12)) returns void language plpgsql
as $$ declare begin update activity set name = coalesce(nullif(name_par, ''), name) where id = id_par;
end
$$;

create or replace function update_technical_request_block_xref(id_par int, technical_request_id_par int,
block_id_par int) returns void language plpgsql as $$ declare begin
update technical_request_block_xref set 
technical_request_id = coalesce(nullif(technical_request_id_par, 0), technical_request_id),
block_id = coalesce(nullif(block_id_par, 0), block_id) where id = id_par;
end
$$;

create or replace function update_technical_request_system_xref(id_par int, technical_request_id_par int,
system_id_par int) returns void language plpgsql as $$ declare begin
update technical_request_system_xref set 
technical_request_id = coalesce(nullif(technical_request_id_par, 0), technical_request_id),
system_id = coalesce(nullif(system_id_par, 0), system_id) where id = id_par;
end
$$;

create or replace function update_technical_request_activity_xref(id_par int, technical_request_id_par int,
activity_id_par int, employee_id_par int) returns void language plpgsql as $$ declare begin
update technical_request_activity_xref set 
technical_request_id = coalesce(nullif(technical_request_id_par, 0), technical_request_id),
activity_id = coalesce(nullif(activity_id_par, 0), activity_id),
employee_id = coalesce(nullif(employee_id_par, 0), employee_id) where id = id_par;
end
$$;
```

### Delete
```
create or replace function delete_block(id_par int, name_par varchar(100), code_par varchar(10))
returns void language plpgsql as $$ declare begin
delete from block where (id_par = 0 or id = id_par) and (name_par = '' or name = name_par) and 
(code_par = '' or code = code_par);
end
$$;

create or replace function delete_system(id_par int, name_par varchar(100), code_par varchar(10),
parent_system_id_par int) returns void language plpgsql as $$ declare begin
delete from system where (id_par = 0 or id = id_par) and (name_par = '' or name = name_par)
and (code_par = '' or code = code_par) and (parent_system_id_par = 0 or parent_system_id = parent_system_id_par);
end
$$;

create or replace function delete_employee(id_par int, first_name_par varchar(100), surname_par varchar(100),
last_name_par varchar(100), pin_par int) returns void language plpgsql as $$ declare begin
delete from employee where (id_par = 0 or id = id_par) and (first_name_par = '' or first_name = first_name_par)
and (surname_par = '' or surname = surname_par) and (last_name_par = '' or last_name = last_name_par)
and (pin_par = 0 or pin = pin_par);
end
$$;

create or replace function delete_technical_request(id_par int, name_par varchar(50), description_par varchar(4000),
creation_time_par timestamp(0) with time zone) returns void language plpgsql as $$ declare begin
delete from technical_request where (id_par = 0 or id = id_par) and (name_par = '' or name = name_par)
and (description_par = '' or description = description_par)
and (creation_time_par is null or creation_time = creation_time_par);
end
$$;

create or replace function delete_activity(id_par int, name_par varchar(12)) returns void language plpgsql
as $$ declare begin delete from activity where (id_par = 0 or id = id_par) and (name_par = '' or name = name_par);
end
$$;

create or replace function delete_technical_request_block_xref(id_par int, technical_request_id_par int, block_id_par int)
returns void language plpgsql as $$ declare begin
delete from technical_request_block_xref where (id_par = 0 or id = id_par) and (technical_request_id_par = 0
or technical_request_id = technical_request_id_par) and (block_id_par = 0 or block_id = block_id_par);
end
$$;

create or replace function delete_technical_request_system_xref(id_par int, technical_request_id_par int, system_id_par int)
returns void language plpgsql as $$ declare begin
delete from technical_request_system_xref where (id_par = 0 or id = id_par) and (technical_request_id_par = 0
or technical_request_id = technical_request_id_par) and (system_id_par = 0 or system_id = system_id_par);
end
$$;

create or replace function delete_technical_request_activity_xref(id_par int, technical_request_id_par int, activity_id_par int,
employee_id_par int) returns void language plpgsql as $$ declare begin
delete from technical_request_activity_xref where (id_par = 0 or id = id_par) and (technical_request_id_par = 0
or technical_request_id = technical_request_id_par) and (activity_id_par = 0 or activity_id = activity_id_par)
and (employee_id_par = 0 or employee_id = employee_id_par);
end
$$;
```