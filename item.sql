drop table if exists Items;

create table Items (
    id      		integer auto_increment primary key,
	name			varchar(5000) NOT NULL,
	description		varchar(5000) NOT NULL,
	quantity		int NOT NULL,
    price     		double NOT NULL,
    total			double,
	cart			boolean, 
	orderNum		int
);

insert into Items (name, description, quantity, price, cart) values ("Boba Milk Tea", "A tea with lactose-free milk and added boba topping", 50, 2.50, false);
insert into Items (name, description, quantity, price, cart) values ("Pepperoni Pizza", "A pizza with pepperoni topping", 75, 8.75, false);