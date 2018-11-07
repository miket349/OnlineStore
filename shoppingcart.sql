drop table if exists ShoppingCart;

create table ShoppingCart (
    id      		int NOT NULL,
	name			varchar(5000) NOT NULL,
	description		varchar(5000),
	quantity		int NOT NULL,
    price     		double NOT NULL,
    total			double NOT NULL, 
	cart			boolean,
	orderNum		int
);