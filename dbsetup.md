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
create or replace function create_block(name_par varchar(100), code_par varchar(10))
returns setof block language plpgsql as $$ declare begin
    return query insert into block (name, code) values (name_par, code_par) returning *;
end
$$;

create or replace function create_system(name_par varchar(100), code_par varchar(10),
parent_system_id_par int) returns setof system language plpgsql as $$ declare begin
    if parent_system_id_par = 0 then
        parent_system_id_par = null;
    end if;
    
    return query insert into system (name, code, parent_system_id) values (name_par, code_par, parent_system_id_par) returning *;
end
$$;
    
create or replace function create_employee(first_name_par varchar(100), surname_par varchar(100),
last_name_par varchar(100),  pin_par int) returns setof employee language plpgsql as $$ declare begin
    return query insert into employee (first_name, surname, last_name, pin)  values (first_name_par, surname_par,
    last_name_par, pin_par) returning *;
end
$$;

create or replace function create_technical_request(name_par varchar(50), description_par varchar(4000), 
creation_time_par timestamp(0) without time zone)
returns setof technical_request language plpgsql as $$ declare begin
    return query insert into technical_request (name, description, creation_time) values (name_par, description_par,
    creation_time_par) returning *;
end
$$;

create or replace function create_activity(name_par varchar(12)) returns setof activity language plpgsql
as $$ declare begin
    return query insert into activity (name) values (name_par) returning *;
end
$$;

create or replace function create_technical_request_block_xref(technical_request_id_par int, block_id_par int)
returns setof technical_request_block_xref language plpgsql as $$ declare begin
    return query insert into technical_request_block_xref (technical_request_id, block_id) values (technical_request_id_par,
    block_id_par) returning *;
end
$$;

create or replace function create_technical_request_system_xref(technical_request_id_par int, system_id_par int)
returns setof technical_request_system_xref language plpgsql as $$ declare begin
    return query insert into technical_request_system_xref (technical_request_id, system_id) values (technical_request_id_par,
    system_id_par) returning *;
end
$$;

create or replace function create_technical_request_activity_xref(technical_request_id_par int, activity_id_par int,
employee_id_par int) returns setof technical_request_activity_xref language plpgsql as $$ declare begin
    return query insert into technical_request_activity_xref (technical_request_id, activity_id, employee_id)
    values (technical_request_id_par, activity_id_par, employee_id_par) returning *;
end
$$;

create or replace function create_user(username_par varchar(12), email_par varchar(40), password_par varchar(88))
returns setof users language plpgsql as $$ declare begin
    return query insert into users (username, email, password) values (username_par, email_par, password_par) returning *;
end
$$;
```

### Read
```
create or replace function read_block(id_par int, name_par varchar(100), code_par varchar(10))
returns setof block language plpgsql as $$ declare begin
    return query select * from block where (id_par = 0 or id = id_par)
    and (name_par = '' or name = name_par) and (code_par = '' or code = code_par);
end
$$;

create or replace function read_block_single(id_par int)
returns setof block language plpgsql as $$ declare begin
    return query select * from block where id = id_par limit 1;
end
$$;

create or replace function read_system(id_par int, name_par varchar(100), code_par varchar(10),
parent_system_id_par int) returns setof system language plpgsql as $$ declare begin
    return query select * from system where (id_par = 0 or id = id_par) and (name_par = '' or name = name_par)
    and (code_par = '' or code = code_par) and (parent_system_id_par = 0 or parent_system_id = parent_system_id_par);
end
$$;

create or replace function read_system_single(id_par int)
returns setof system language plpgsql as $$ declare begin
    return query select * from system where id = id_par limit 1;
end
$$;

create or replace function read_employee(id_par int, first_name_par varchar(100), surname_par varchar(100),
last_name_par varchar(100), pin_par int) returns setof employee language plpgsql as $$ declare begin
    return query select * from employee where (id_par = 0 or id = id_par) and (first_name_par = ''
    or first_name = first_name_par) and (surname_par = '' or surname = surname_par) and (last_name_par = ''
    or last_name = last_name_par) and (pin_par = 0 or pin = pin_par);
end
$$;

create or replace function read_employee_single(id_par int)
returns setof employee language plpgsql as $$ declare begin
    return query select * from employee where id = id_par limit 1;
end
$$;

create or replace function read_technical_request(id_par int, name_par varchar(100), description_par varchar(4000),
creation_time_par timestamp(0) without time zone) returns setof technical_request language plpgsql as $$ declare begin
    return query select * from technical_request where (id_par = 0 or id = id_par) and
    (name_par = '' or name = name_par) and (description_par = '' or description = description_par) and
    (creation_time_par is null or creation_time = creation_time_par);
end
$$;

create or replace function read_technical_request_single(id_par int)
returns setof technical_request language plpgsql as $$ declare begin
    return query select * from technical_request where id = id_par limit 1;
end
$$;

create or replace function read_activity(id_par int, name_par varchar(12)) returns setof activity language plpgsql
as $$ declare begin
    return query select * from activity where (id_par = 0 or id = id_par) and (name_par = '' or name = name_par);
end
$$;

create or replace function read_activity_single(id_par int)
returns setof activity language plpgsql as $$ declare begin
    return query select * from activity where id = id_par limit 1;
end
$$;

create or replace function read_technical_request_block_xref(id_par int, technical_request_id_par int, block_id_par int)
returns setof technical_request_block_xref language plpgsql as $$ declare begin
    return query select * from technical_request_block_xref where (id_par = 0 or id = id_par) and
    (technical_request_id_par = 0 or technical_request_id = technical_request_id_par) and
    (block_id_par = 0 or block_id = block_id_par);
end
$$;

create or replace function read_technical_request_block_xref_single(id_par int)
returns setof technical_request_block_xref language plpgsql as $$ declare begin
    return query select * from technical_request_block_xref where id = id_par limit 1;
end
$$;

create or replace function read_technical_request_system_xref(id_par int, technical_request_id_par int,
system_id_par int)  returns setof technical_request_system_xref language plpgsql as $$ declare begin
    return query select * from technical_request_system_xref where (id_par = 0 or id = id_par) and
    (technical_request_id_par = 0 or technical_request_id = technical_request_id_par) and
    (system_id_par = 0 or system_id = system_id_par);
end
$$;

create or replace function read_technical_request_system_xref_single(id_par int)
returns setof technical_request_system_xref language plpgsql as $$ declare begin
    return query select * from technical_request_system_xref where id = id_par limit 1;
end
$$;

create or replace function read_technical_request_activity_xref(id_par int, technical_request_id_par int,
activity_id_par int, employee_id_par int) returns setof technical_request_activity_xref language plpgsql
as $$ declare begin
    return query select * from technical_request_activity_xref where (id_par = 0 or id = id_par) and
    (technical_request_id_par = 0 or technical_request_id = technical_request_id_par) and
    (activity_id_par = 0 or activity_id = activity_id_par) and (employee_id_par = 0 or employee_id = employee_id_par);
end
$$;

create or replace function read_technical_request_activity_xref_single(id_par int)
returns setof technical_request_activity_xref language plpgsql as $$ declare begin
    return query select * from technical_request_activity_xref where id = id_par limit 1;
end
$$;

create or replace function read_user(id_par int, username_par varchar(12), email_par varchar(40), password_par varchar(88))
returns setof users language plpgsql as $$ declare begin
    return query select * from users where (id_par = 0 or id = id_par) and (username_par = '' or username = username_par)
    and (email_par = '' or email = email_par) and (password_par = '' or password = password_par);
end
$$;

create or replace function read_user_single(id_par int)
returns setof users language plpgsql as $$ declare begin
    return query select * from users where id = id_par limit 1;
end
$$;
```

### Update
```
create or replace function update_block(id_par int, name_par varchar(100), code_par varchar(10)) 
returns setof block language plpgsql as $$ declare begin
    return query update block set name = coalesce(nullif(name_par, ''), name),
    code = coalesce(nullif(code_par, ''), code) where id = id_par returning *;
end
$$;

create or replace function update_system(id_par int, name_par varchar(100), code_par varchar(10),
parent_system_id_par int) returns setof system language plpgsql as $$ declare begin
    if (parent_system_id_par = -1) then
        return query update system set name = coalesce(nullif(name_par, ''), name),
        code = coalesce(nullif(code_par, ''), code),
        parent_system_id = null where id = id_par returning *;
    end if;
    
    return query update system set name = coalesce(nullif(name_par, ''), name),
    code = coalesce(nullif(code_par, ''), code),
    parent_system_id = coalesce(nullif(parent_system_id_par, 0), parent_system_id) where id = id_par returning *;
end
$$;

create or replace function update_employee(id_par int, first_name_par varchar(100),
surname_par varchar(100), last_name_par varchar(100), pin_par int) returns setof employee
language plpgsql as $$ declare begin
    return query update employee set
    first_name = coalesce(nullif(first_name_par, ''), first_name),
    surname = coalesce(nullif(surname_par, ''), surname),
    last_name = coalesce(nullif(last_name_par, ''), last_name), pin = coalesce(nullif(pin_par, 0), pin)
    where id = id_par returning *;
end
$$;

create or replace function update_technical_request(id_par int, name_par varchar(50),
description_par varchar(4000), creation_time_par timestamp(0) without time zone) returns setof
technical_request language plpgsql as $$ declare begin
    return query update technical_request set name = coalesce(nullif(name_par, ''), name),
    description = coalesce(nullif(description_par, ''), description),
    creation_time = coalesce(nullif(creation_time_par, null), creation_time) where id = id_par returning *;
end
$$;

create or replace function update_activity(id_par int, name_par varchar(12)) returns setof
activity language plpgsql as $$ declare begin
    return query update activity set
    name = coalesce(nullif(name_par, ''), name) where id = id_par returning *;
end
$$;

create or replace function update_technical_request_block_xref(id_par int, technical_request_id_par int,
block_id_par int) returns setof technical_request_block_xref language plpgsql as $$ declare begin
    return query update technical_request_block_xref set 
    technical_request_id = coalesce(nullif(technical_request_id_par, 0), technical_request_id),
    block_id = coalesce(nullif(block_id_par, 0), block_id) where id = id_par returning *;
end
$$;

create or replace function update_technical_request_system_xref(id_par int, technical_request_id_par int,
system_id_par int) returns setof technical_request_system_xref language plpgsql as $$ declare begin
    return query update technical_request_system_xref set 
    technical_request_id = coalesce(nullif(technical_request_id_par, 0), technical_request_id),
    system_id = coalesce(nullif(system_id_par, 0), system_id) where id = id_par returning *;
end
$$;

create or replace function update_technical_request_activity_xref(id_par int, technical_request_id_par int,
activity_id_par int, employee_id_par int) returns setof technical_request_activity_xref
language plpgsql as $$ declare begin
    return query update technical_request_activity_xref set 
    technical_request_id = coalesce(nullif(technical_request_id_par, 0), technical_request_id),
    activity_id = coalesce(nullif(activity_id_par, 0), activity_id),
    employee_id = coalesce(nullif(employee_id_par, 0), employee_id) where id = id_par returning *;
end
$$;

create or replace function update_user(id_par int, username_par varchar(12), email_par varchar(40),
password_par varchar(88)) returns setof users language plpgsql as $$ declare begin
    return query update users set username = coalesce(nullif(username_par, ''), username),
    email = coalesce(nullif(email_par, ''), email),
    password = coalesce(nullif(password_par, ''), password) where id = id_par returning *;
end
$$;
```

### Delete
```
create or replace function delete_block(id_par int, name_par varchar(100), code_par varchar(10))
returns setof block language plpgsql as $$ declare begin
    return query delete from block where (id_par = 0 or id = id_par) and (name_par = '' or name = name_par) and 
    (code_par = '' or code = code_par) returning *;
end
$$;

create or replace function delete_system(id_par int, name_par varchar(100), code_par varchar(10),
parent_system_id_par int) returns setof system language plpgsql as $$ declare begin
    return query delete from system where (id_par = 0 or id = id_par) and (name_par = '' or name = name_par)
    and (code_par = '' or code = code_par) and (parent_system_id_par = 0 or parent_system_id = parent_system_id_par)
    returning *;
end
$$;

create or replace function delete_employee(id_par int, first_name_par varchar(100), surname_par varchar(100),
last_name_par varchar(100), pin_par int) returns setof employee language plpgsql as $$ declare begin
    return query delete from employee where (id_par = 0 or id = id_par)
    and (first_name_par = '' or first_name = first_name_par)
    and (surname_par = '' or surname = surname_par) and (last_name_par = '' or last_name = last_name_par)
    and (pin_par = 0 or pin = pin_par) returning *;
end
$$;

create or replace function delete_technical_request(id_par int, name_par varchar(50), description_par varchar(4000),
creation_time_par timestamp(0) without time zone) returns setof technical_request language plpgsql as $$ declare begin
    return query delete from technical_request where (id_par = 0 or id = id_par) and (name_par = '' or name = name_par)
    and (description_par = '' or description = description_par)
    and (creation_time_par is null or creation_time = creation_time_par) returning *;
end
$$;

create or replace function delete_activity(id_par int, name_par varchar(12)) returns setof activity language plpgsql
as $$ declare begin
    return query delete from activity where (id_par = 0 or id = id_par)
    and (name_par = '' or name = name_par) returning *;
end
$$;

create or replace function delete_technical_request_block_xref(id_par int, technical_request_id_par int,
block_id_par int) returns setof technical_request_block_xref language plpgsql
as $$ declare begin
    return query delete from technical_request_block_xref where (id_par = 0 or id = id_par)
    and (technical_request_id_par = 0 or technical_request_id = technical_request_id_par)
    and (block_id_par = 0 or block_id = block_id_par) returning *;
end
$$;

create or replace function delete_technical_request_system_xref(id_par int,
technical_request_id_par int, system_id_par int) returns setof technical_request_system_xref
language plpgsql as $$ declare begin
    return query delete from technical_request_system_xref
    where (id_par = 0 or id = id_par) and (technical_request_id_par = 0
    or technical_request_id = technical_request_id_par) and (system_id_par = 0
    or system_id = system_id_par) returning *;
end
$$;

create or replace function delete_technical_request_activity_xref(id_par int,
technical_request_id_par int, activity_id_par int, employee_id_par int) returns setof
technical_request_activity_xref language plpgsql as $$ declare begin
    return query delete from technical_request_activity_xref where (id_par = 0
    or id = id_par) and (technical_request_id_par = 0
    or technical_request_id = technical_request_id_par)
    and (activity_id_par = 0 or activity_id = activity_id_par)
    and (employee_id_par = 0 or employee_id = employee_id_par) returning *;
end
$$;

create or replace function delete_user(id_par int, username_par varchar(12), email_par varchar(40),
password_par varchar(88)) returns setof users language plpgsql as $$ declare begin
    return query delete from users where (id_par = 0 or id = id_par)
    and (username_par = '' or username = username_par) and (email_par = '' or email = email_par)
    and (password_par = '' or password = password_par) returning *;
end
$$;
```

## Drop extra functions
```
drop function check_employee_in_technical_request, read_report_block_codes, read_system_child_ids,
read_system_parent_ids, read_report_systems, read_report_employees, create_salt, check_salt;
```

### Employee can't change name while in technical request
```
create or replace function check_employee_in_technical_request(employee_id int) returns boolean language plpgsql
as $$ declare begin
    perform * from read_technical_request_activity_xref(0, 0, 0, employee_id) limit 1;
    return found;
end
$$;
```

### Report
```
create or replace function read_report_technical_requests(query varchar) returns setof technical_request
language plpgsql as $$ declare begin
    return query execute query;
end
$$;

create or replace function read_report_block_codes(technical_request_id_par int) returns varchar(10)[] language plpgsql
as $$ declare begin
    return array(select code from block where id
    in (select block_id from technical_request_block_xref where technical_request_id = technical_request_id_par));
end
$$;

create or replace function read_system_child_ids(system_codes varchar(10)[]) returns int[] language plpgsql
as $$ declare begin
    return array(
        with recursive subsystems as (
            select id from system where code = any(system_codes)
        union
            select system.id from system inner join subsystems on subsystems.id = system.parent_system_id
        ) select id from subsystems
    );
end
$$;

create or replace function read_system_parent_ids(technical_request_id_par int) returns
table(
    id int
)
language plpgsql as $$ declare begin
    return query
    with recursive sursystems as (
        select system.id, system.parent_system_id from system where system.id
        in (select system_id from technical_request_system_xref where technical_request_id = technical_request_id_par)
    union
        select system.id, system.parent_system_id from system
        inner join sursystems on sursystems.parent_system_id = system.id
    ) select sursystems.id from sursystems;
end
$$;

create or replace function read_report_systems(technical_request_id_par int) 
returns table(
    code varchar(10),
    name varchar(100),
    parent_system_id int
)
language plpgsql as $$ declare begin
    return query select system.code, system.name, system.parent_system_id from system where system.id
    in (select * from read_system_parent_ids(technical_request_id_par));
end
$$;

create or replace function read_report_employees(technical_request_id_par int)
returns table(
    first_name varchar(100),
    surname varchar(100),
    last_name varchar(100),
    activity_name varchar(12)
)
language plpgsql as $$ declare begin
    return query select e.first_name, e.surname, e.last_name, act.name as activity_name from employee e
    left join activity act on act.id in (select activity_id from technical_request_activity_xref
    where technical_request_id = technical_request_id_par and employee_id = e.id) where act.name is not null;
end
$$;
```

### User
```
create or replace function create_salt(user_id_par int, salt_par varchar(172)) returns void
language plpgsql as $$ declare begin
insert into salt (user_id, value) values (user_id_par, salt_par);
end
$$;

create or replace function check_salt(salt_par varchar(172)) returns boolean language plpgsql
as $$ declare begin
    perform * from salt where value = salt_par limit 1;
    return found;
end
$$;

create or replace function get_salt(username_par varchar(12)) returns varchar(172) language plpgsql
as $$ declare begin
    return (select value from salt where user_id in (select id from users where username = username_par));
end
$$;

create or replace function check_hash(hash_par varchar(88)) returns boolean language plpgsql
as $$ declare begin
    perform * from users where password = hash_par limit 1;
    return found;
end
$$;
```