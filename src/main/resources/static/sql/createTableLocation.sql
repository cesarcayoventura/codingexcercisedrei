CREATE TABLE LOCATION (
id number GENERATED ALWAYS AS IDENTITY,
name   VARCHAR2(100) NOT NULL,
type   VARCHAR2(20) NOT NULL,
lat number(4, 1),
lng number(4, 1)
);
