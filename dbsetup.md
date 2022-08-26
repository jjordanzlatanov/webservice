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

create function update_block(id_par int, name_par varchar(100), code varchar(10))
```