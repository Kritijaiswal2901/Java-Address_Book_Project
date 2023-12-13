-- UC1
-- creating new database for address books
create database address_book_service;
show databases;

-- output to the above query
-- address_book_service
-- information_schema
-- mysql
-- payroll_service
-- performance_schema
-- sys

-- UC2
-- using the created database
use address_book_service;

-- creating an address book table
create table address_book_1 (
    first_name varchar(50) not null,
    last_name varchar(50),
    address varchar(100),
    city varchar(20),
    state varchar(20),
    zip varchar(10),
    phone varchar(20), 
    email varchar(320)
);

-- UC3
-- creating new contacts in address book
insert into address_book_1 (first_name, last_name, address, city, state, zip, phone, email) values
	("Kriti", "Jaiswal", "Hazipur", "Patna", "Bihar", "74185", "919936574857", "kritijaiswal7485@gmail.com"),
    ("Sandhya", "Sharma", "Campel Road", "Amritsar", "Punjab", "456002", "91 9685741452", "sandhyasharma47@gmail.com"),
    ("Archana", "Singh", "pqr", "Ahmedabad", "Gujarat", "380001", "91 945612345", "kush@gmail.com");
select * from address_book_1;

-- output to the above query
-- Kriti	Jaiswal	Hazipur	Patna	Bihar	74185	919936574857	kritijaiswal7485@gmail.com
-- Sandhya	Sharma	Campel Road	Amritsar	Punjab	456002	91 9685741452	sandhyasharma47@gmail.com
-- Archana	Singh	pqr	Ahmedabad	Gujarat	380001	91 945612345	kush@gmail.com

-- UC4
-- editing the contact using first name
update address_book_1 set address = "Samastipur" where first_name = "Kriti";
select * from address_book_1;

-- output to the above query
-- Kriti	Jaiswal	Samastipur	Patna	Bihar	74185	919936574857	kritijaiswal7485@gmail.com
-- Sandhya	Sharma	Campel Road	Amritsar	Punjab	456002	91 9685741452	sandhyasharma47@gmail.com
-- Archana	Singh	pqr	Ahmedabad	Gujarat	380001	91 945612345	kush@gmail.com

-- UC5
-- deleting a contact using first_name
delete from address_book_1 where first_name = "Archana";
select * from address_book_1;

-- output to the above query
-- Kriti	Jaiswal	Samastipur	Patna	Bihar	74185	919936574857	kritijaiswal7485@gmail.com
-- Sandhya	Sharma	Campel Road	Amritsar	Punjab	456002	91 9685741452	sandhyasharma47@gmail.com

-- adding more data to table
insert into address_book_1 (first_name, last_name, address, city, state, zip, phone, email) values
	("Anju", "Sharma", "Danapur", "Patna", "Bihar", "784575", "91 9876542581", "anju0174@gmail.com"),
    ("Ishika", "Das", "Adampur", "Patna", "Bihar", "784572", "91 9871473698", "ishika7875@gmail.com");

-- UC6
-- retrieving people belonging to city
select * from address_book_1 where city = "Patna";

-- output to the above query
-- Kriti	Jaiswal	Samastipur	Patna	Bihar	74185	919936574857	kritijaiswal7485@gmail.com
-- Anju	Sharma	Danapur	Patna	Bihar	784575	91 9876542581	anju0174@gmail.com
-- Ishika	Das	Adampur	Patna	Bihar	784572	91 9871473698	ishika7875@gmail.com

-- retrieving people belonging to state
select * from address_book_1 where state = "Bihar";

-- output to the above query
-- Kriti	Jaiswal	Samastipur	Patna	Bihar	74185	919936574857	kritijaiswal7485@gmail.com
-- Anju	Sharma	Danapur	Patna	Bihar	784575	91 9876542581	anju0174@gmail.com
-- Ishika	Das	Adampur	Patna	Bihar	784572	91 9871473698	ishika7875@gmail.com

-- UC7
-- getting number of contacts by each city
select city, count(city) from address_book_1 group by city;

-- output to above query
-- Patna	3
-- Amritsar	1

-- getting number of contacts by every state
select state, count(state) from address_book_1 group by state;

-- output to above query
-- Bihar	3
-- Punjab	1

-- UC8
-- retrieve entries sorted by name for particular city
select * from address_book_1 where city = "Patna" order by first_name asc;

-- output to above query
-- Anju	Sharma	Danapur	Patna	Bihar	784575	91 9876542581	anju0174@gmail.com
-- Ishika	Das	Adampur	Patna	Bihar	784572	91 9871473698	ishika7875@gmail.com
-- Kriti	Jaiswal	Samastipur	Patna	Bihar	74185	919936574857	kritijaiswal7485@gmail.com

-- UC 9
-- adding name and type to address book
alter table address_book_1 add column type varchar(20), add column name varchar(50);

-- adding name and type to all contacts
update address_book_1 set name = "book1";
update address_book_1 set type = "friends" where first_name in ("Anju", "Ishika");
update address_book_1 set type = "family" where first_name in ("Kriti", "Sandhya");
select * from address_book_1;

-- output to above query
-- Kriti	Jaiswal	Samastipur	Patna	Bihar	74185	919936574857	kritijaiswal7485@gmail.com	family	book1
-- Sandhya	Sharma	Campel Road	Amritsar	Punjab	456002	91 9685741452	sandhyasharma47@gmail.com	family	book1
-- Anju	Sharma	Danapur	Patna	Bihar	784575	91 9876542581	anju0174@gmail.com	friends	book1
-- Ishika	Das	Adampur	Patna	Bihar	784572	91 9871473698	ishika7875@gmail.com	friends	book1


-- UC10
-- get number of contacts by type
select type, count(type) from address_book_1 group by type;

-- output to above query
-- family	2
-- friends	2

-- UC12
-- modify tables to create separate entities
-- creating table for a new address book
create table address_book_2(
	person_id int not null,
    address_id int not null,
    name varchar(50),
    type varchar(20),
    primary key(person_id),
    foreign key(person_id) references person_details(person_id),
    foreign key(address_id) references address_details(address_id)
);

-- creaating table for person details
create table person_details(
	person_id int not null auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50),
    phone varchar(20),
    email varchar(320) not null,
    primary key(person_id, first_name, email)
);

-- creating table for address details
create table address_details(
	address_id int not null auto_increment,
    address varchar(100) not null,
    city varchar(20) not null,
    state varchar(20),
    zip varchar(10),
    primary key(address_id)
);

-- inserting some values into the tables
insert into person_details(first_name, last_name, phone, email) values
	("Kriti", "Jaiswal", "91 9875874859", "kritijaiswal2901@gmail.com"),
    ("Rahul", "Sharma", "91 7487558456", "rahul@gmail.com"),
	("Sandhya", "Sharma", "91 8757845875", "sandhya1485@gmail.com"),
    ("Aditya", "Das", "91 9857425741", "aditya@gmail.com");
insert into address_details(address, city, state, zip) values
	("Hazipur", "Patna", "Bihar", "74185"),
    ("Campel Road", "Amritsar", "Punjab", "456002"),
    ("Indira Nagae", "Lucknow", "UP", "265748"),
    ("Hitech City", "Hyderabad", "Telengana", "985745");
insert into address_book_2(person_id, address_id, name, type) values
	(1,1,"book2","Family"),
    (2,2,"book2","Family"),
    (3,3,"book2","Friend"),
    (4,4,"book2","Friend");
    
-- UC13
-- ability to execute all queries as in UC6,7,8,10

-- retrieving people belonging to a city
select * from person_details where person_id in 
	(select person_id from address_book_2 as ab inner join address_details as ad on ab.address_id=ad.address_id where city = "Patna");

-- output to above query is
-- 1	Kriti	Jaiswal	91 9875874859	kritijaiswal2901@gmail.com

-- count contacts by city or state
select state, count(person_id) from address_book_2 as ab inner join address_details as ad on ab.address_id=ad.address_id where state = "Bihar";

-- output to above query is
-- Bihar	1

-- get contacts sorted by name for given city
select * from person_details where person_id in 
	(select person_id from address_book_2 as ab inner join address_details as ad on ab.address_id=ad.address_id where city = "Patna")
order by first_name asc;

-- output to above query
-- 1	Kriti	Jaiswal	91 9875874859	kritijaiswal2901@gmail.com

-- count contacts by type
select type, count(type) from address_book_2 group by type;

-- output to above query
-- Family	2
-- Friend	2

-- UC18
-- adding the date_added attribute to contacts
alter table address_book_2 add column date_added date null;
update address_book_2 set date_added = "2023-12-11";
select * from address_book_2;

-- output to above query
-- 1	1	book2	Family	2023-12-11
-- 2	2	book2	Family	2023-12-11
-- 3	3	book2	Friend	2023-12-11
-- 4	4	book2	Friend	2023-12-11