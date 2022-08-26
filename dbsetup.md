## Create Database
```
sudo -i -u postgres psql  
create user webservice with createdb password 'password1234';  
create database wbservice owner webservice;
```

## Create Tables
```
mvn clean package  
java -jar target/webservice-1.0-SNAPSHOT.jar db migrate webservice.yaml
```

## Create Functions (CRUD)
### Create
```
sudo -i -i postgres psql  
\c wbservice

create or replace function create_block(name varchar(100), code varchar(10))
returns void language plpgsql as $$ declare begin
insert into block (name, code) values (name, code);
end
$$;

create or replace function create_employee(first_name varchar(100), surname varchar(100), last_name varchar(100), 
pin int) returns void language plpgsql as $$ declare begin
insert into employee (first_name, surname, last_name, pin)  values (first_name, surname, last_name, pin);
end
$$;

create or replace function create_system(name varchar(100), code varchar(10), parent_system_id int)
returns void language plpgsql as $$ declare begin
insert into system (name, code, parent_system_id) values (name, code, parent_system_id);
end
$$;

create or replace function create_technical_service(name varchar(50), description varchar(4000), 
creation_time timestamp(0) with time zone)
returns void language plpgsql as $$ declare begin
insert into technical_service (name, description, creation_time) values (name, description, creation_time);
end
$$;

create or replace function create_activity(technical_service_id int, name varchar(12), responsible_person_id int)
returns void language plpgsql as $$ declare begin
insert into activity (technical_service_id, name, responsible_person_id) values (technical_service_id, name,
responsible_person_id);
end
$$;

create or replace function technical_service_block_ref(technical_service_id int, block_id int)
returns void language plpgsql as $$ declare begin
insert into technical_service_block_xref (technical_service_id, block_id) values (technical_service_id, block_id);
end
$$;

create or replace function technical_service_system_ref(technical_service_id int, system_id int)
returns void language plpgsql as $$ declare begin
insert into technical_service_system_xref (technical_service_id, system_id) values (technical_service_id, system_id);
end
$$;
```

### Read
```
create or replace function read_block() returns setof block language plpgsql as $$ declare begin
return query select * from block;
end
$$;

create or replace function read_employee() returns setof employee language plpgsql as $$ declare begin
return query select * from employee;
end
$$;

create or replace function read_system() returns setof system language plpgsql as $$ declare begin
return query select * from system;
end
$$;

create or replace function read_technical_service() returns setof technical_service language plpgsql as $$ declare begin
return query select * from technical_service;
end
$$;

create or replace function read_activity() returns setof activity language plpgsql as $$ declare begin
return query select * from activity;
end
$$;

create or replace function read_technical_service_block_xref()
returns setof technical_service_block_xref language plpgsql as $$ declare begin
return query select * from technical_service_block_xref;
end
$$;

create or replace function read_technical_service_system_xref() 
returns setof technical_service_system_xref language plpgsql as $$ declare begin
return query select * from technical_service_system_xref;
end
$$;
```

### Update
```
create or replace function update_block(id_par int, name_par varchar(100), code_par varchar(10)) 
returns void language plpgsql as $$ declare begin
update block set name = coalesce(nullif(name_par, null), name), code = coalesce(nullif(code_par, null), code)
where id = id_par;
end
$$;

create or replace function update_employee(id_par int, first_name_par varchar(100), surname_par varchar(100),
last_name_par varchar(100), pin_par int) returns void language plpgsql as $$ declare begin
update employee set first_name = coalesce(nullif(first_name_par, null), name),
surname = coalesce(nullif(surname_par, null), surname),
last_name = coalesce(nullif(last_name_par, null), last_name), pin = coalesce(nullif(pin_par, null), pin)
where id = id_par;
end
$$;

create or replace function update_system(id_par int, name_par varchar(100), code_par varchar(10), parent_system_id_par int)
returns void language plpgsql as $$ declare begin
update system set name = coalesce(nullif(name_par, null), name), code = coalesce(nullif(code_par, null), code)
where id = id_par;
case
    when parent_system_id_par = 0 then
        update system set parent_system_id = null where id = id_par;
    when parent_system_id_par is not null then
        update system set parent_system_id = parent_system_id_par where id = id_par;
    else
end case;
end
$$;

create or replace function update_technical_service(id_par int, name_par varchar(50), description_par varchar(4000),
creation_time_par timestamp(0) with time zone) returns void language plpgsql as $$ declare begin
update technical_service set name = coalesce(nullif(name_par, null), name),
description = coalesce(nullif(description_par, null), description),
creation_time = coalesce(nullif(creation_time_par, null), creation_time) where id = id_par;
end
$$;

create or replace function update_activity(technical_service_id_par int, name_par varchar(12), responsible_person_id_par int)
returns void language plpgsql as $$ declare begin
update activity set technical_service_id = coalesce(nullif(technical_service_id_par, null), technical_service_id),
name = coalesce(nullif(name_par, null), name), responsible_person_id = coalesce(nullif(responsible_person_id_par, null),
responsible_person_id) where id = id_par;
end
$$;

create or replace function update_technical_service_block_ref(technical_service_id_par int, block_id_par int)
returns void language plpgsql as $$ declare begin
update technical_service_block_xref set technical_service_id = coalesce(nullif(technical_service_id_par, null),
technical_service_id), block_id = coalesce(nullif(block_id_par, null), block_id) where id = id_par;
end
$$;

create or replace function update_technical_service_system_ref(technical_service_id_par int, system_id_par int)
returns void language plpgsql as $$ declare begin
update technical_service_system_xref set technical_service_id = coalesce(nullif(technical_service_id_par, null),
technical_service_id), system_id = coalesce(nullif(system_id_par, null), system_id) where id = id_par;
end
$$;
```

### Delete
```
create or replace function delete_block(id_par int, name_par varchar(100), code_par varchar(10))
returns void language plpgsql as $$ declare begin
delete from block where (id_par is null or id = id_par) and (name_par is null or name = name_par) and 
(code_par is null or code = code_par);
end
$$;

create or replace function delete_employee(id_par int, first_name_par varchar(100), surname_par varchar(100),
last_name_par varchar(100), pin_par int) returns void language plpgsql as $$ declare begin
delete from block where (id_par is null or id = id_par) and (first_name_par is null or first_name = first_name_par)
and (surname_par is null or surname = surname_par) and (last_name_par is null or last_name = last_name_par)
and (pin_par is null or pin = pin_par);
end
$$;

create or replace function delete_system(id_par int, name_par varchar(100), code_par varchar(10), parent_system_id_par int)
returns void language plpgsql as $$ declare begin
delete from system where (id_par is null or id = id_par) and (name_par is null or name = name_par)
and (code_par is null or code = code_par) and (parent_system_id_par is null or parent_system_id = parent_system_id_par);
end
$$;

create or replace function delete_technical_service(id_par int, name_par varchar(50), description_par varchar(4000),
creation_time_par timestamp(0) with time zone) returns void language plpgsql as $$ declare begin
delete from technical_service where (id_par is null or id = id_par) and (name_par is null or name = name_par)
and (description_par is null or description = description_par)
and (creation_time_par is null or creation_time = creation_time_par);
end
$$;

create or replace function delete_activity(technical_service_id_par int, name_par varchar(12), responsible_person_id_par int)
returns void language plpgsql as $$ declare begin
delete from activity where (id_par is null or id = id_par) and (name_par is null or name = name_par)
and (responsible_person_id_par is null or responsible_person_id = responsible_person_id_par);
end
$$;

create or replace function delete_technical_service_block_ref(technical_service_id_par int, block_id_par int)
returns void language plpgsql as $$ declare begin
delete from technical_service_block_xref where (technical_service_id_par is null
or technical_service_id = technical_service_id_par) and (block_id_par is null or block_id = block_id_par);
end
$$;

create or replace function delete_technical_service_system_ref(technical_service_id_par int, system_id_par int)
returns void language plpgsql as $$ declare begin
delete from technical_service_system_xref where (technical_service_id_par is null
or technical_service_id = technical_service_id_par) and (system_id_par is null or system_id = system_id_par);
end
$$;
```