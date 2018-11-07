drop table if exists History;

create table History (
    id      		int,
	name			varchar(5000) NOT NULL,
	description		varchar(5000),
	quantity		int NOT NULL,
    price     		double,
    total			double NOT NULL, 
	cart			boolean,
	orderNum		int NOT NULL
);