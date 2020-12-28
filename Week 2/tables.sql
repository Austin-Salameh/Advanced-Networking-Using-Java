DROP TABLE address;

CREATE TABLE address(
id NUMBER (4) CONSTRAINT pk_address PRIMARY KEY,
LASTNAME varchar2(40),
FIRSTNAME varchar2(40),
STREET varchar2(40),
CITY varchar2(40),
STATE varchar2(40),
ZIP varchar2(40)
);

INSERT INTO address VALUES(1,'Smith','Tom','2919 Redwing Circle','Bellevue','NE','68123');

INSERT INTO address VALUES(2,'Actor','Marty','1000 Galvin Road South','Bellevue','NE','68005');

INSERT INTO address VALUES(3,'Aimes','Beverly','1 Park Place','Redmond','Washington','90922');

COMMIT;