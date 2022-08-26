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

## Create Functions 
```
sudo -i -i postgres psql  
\c wbservice

create function create_block(name varchar(100), code varchar(10)) returns void 
language plpgsql as $$ declare begin
insert into block (name, code) values (name, code);
end
$$;

create function create_employee(first_name varchar(100), surname varchar(100), 
last_name varchar(100), pin int)) returns void 
language plpgsql as $$ declare begin
insert into employee (first_name, surname, last_name, pin) 
values (first_name, surname, last_name, pin);
end
$$;

create function create_system(name varchar(100), code varchar(10), 
parent_system_id int) returns void language plpgsql as $$ declare begin
insert into system (name, code, parent_system_id) values (name, code, parent_system_id);
end
$$;

create function create_technical_service(name varchar(50), description varchar(4000)
creation_time timestamp(0) with time zone) returns void language plpgsql as $$ declare begin
insert into technical_service (name, description, creation_time) values (name, description, creation_time);
end
$$;

create function read_block() returns setof block language plpgsql as $$ declare begin
return query select * from block;
end
$$;

create function read_employee() returns setof employee language plpgsql as $$ declare begin
return query select * from employee;
end
$$;

create function read_system() returns setof system language plpgsql as $$ declare begin
return query select * from system;
end
$$;

create function read_technical_service() returns setof technical_service language plpgsql as $$ declare begin
return query select * from technical_service;
end
$$;

create function update_block(id_par int, name_par varchar(100), code_par varchar(10)) returns void language plpgsql
update block set name = coalesce(nullif(name_par, null), name), code = coalesce(nullif(code_par, null), code)
where id = id_par;
end
$$;

create function update_employee(id_par int, first_name_par varchar(100), surname_par varchar(100), last_name_par varchar(100),
pin_par int) returns void language plpgsql
update employee set first_name = coalesce(nullif(first_name_par, null), name), surname = coalesce(nullif(surname_par, null), surname),
last_name = coalesce(nullif(last_name_par, null), last_name), pin = coalesce(nullif(pin_par, null), pin) where id = id_par;
end
$$;

create function update_system(id_par int, name_par varchar(100), code_par varchar(10), parent_system_id_par int) returns void
language plpgsql as $$ declare begin
update system set name = coalesce(nullif(name_par, null), name), code = coalesce(nullif(code_par, null), code) where id = id_par;
case
    when parent_system_id_par = 0 then
        update system set parent_system_id = null where id = id_par;
    when parent_system_id_par is not null then
        update system set parent_system_id = parent_system_id_par where id = id_par;
    else
end case; 
end
$$;

create function update_technical_service(id_par int, name_par varchar(50), description_par varchar(4000),
creation_time_par timestamp(0) with time zone) returns void language plpgsql
update technical_service set name = coalesce(nullif(name_par, null), name), 
description = coalesce(nullif(description_par, null), description), 
creation_time = coalesce(nullif(creation_time_par, null), creation_time) where id = id_par;
end
$$;

create function delete_block(id_par int, name_par varchar(100), code_par varchar(10)) returns void language plpgsql as 
$$ declare begin
delete from block where (id_par is null or id = id_par) and (name_par is null or name = name_par) and 
(code_par is null or code = code_par);
end
$$;

create function delete_employee(id_par int, first_name_par varchar(100), surname_par varchar(100), last_name_par varchar(100),
pin_par int) returns void language plpgsql as $$ declare begin
delete from block where (id_par is null or id = id_par) and (first_name_par is null or first_name = first_name_par) and 
(surname_par is null or surname = surname_par) and (last_name_par is null or last_name = last_name_par) and (pin_par is null or pin = pin_par);
end
$$;

create function delete_system(id_par int, name_par varchar(100), code_par varchar(10), parent_system_id_par int) returns void
language plpgsql as $$ declare begin
delete from system where (id_par is null or id = id_par) and (name_par is null or name = name_par) and (code_par is null or code = code_par)
and (parent_system_id_par is null or parent_system_id = parent_system_id_par);
end
$$;
```