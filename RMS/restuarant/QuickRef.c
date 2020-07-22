// Plan for URL
1. For getting all tables

URL: /RMS/getAllTables

Ref : /home/muthu/Downloads/todo-rest-webservices/src/main/java/com/todo/rest/webservices/todorestwebservices/controller/modal/TodoResource.java
/home/muthu/Angular/todo/src/app/services/data/todo-data.service.ts

###############################################

Table:

		tables.add(new Table(1, "Rushi", 10, 30, true));
		tables.add(new Table(2, "Hotel Pushpa", 15, 30, true));
		tables.add(new Table(3, "Mano", 15, 30, true));
		tables.add(new Table(4, "Gowri Sankar", 20, 30, true));
		tables.add(new Table(5, "Hotel Ganga", 30, 30, true));
		tables.add(new Table(6, "Hotel Aishwarya", 10, 30, true));
		tables.add(new Table(7, "Chettinad", 15, 30, true));
		tables.add(new Table(8, "Nila Restuarant", 9, 30, true));
		tables.add(new Table(9, "Le Arabia", 2, 30, true));

Product:
		
		products.add(new Product(1, "Rushi", "Grilled Chicken", 200, "Tasty!!!"));
		products.add(new Product(2, "Hotel Pushpa", "Mutton Biriyani", 250, "Very Good"));
		products.add(new Product(3, "Mano", "Chicken Kabab", 100, "Good"));
		products.add(new Product(4, "Gowri Sankar", "Grilled Mushroom", 50, "Nice"));
		products.add(new Product(5, "Hotel Ganga", "Veg Biriyani", 100, "Average"));
		
Order:

		orders.add(new Order(1, "Rushi", 10001, 2, 600, false));
		orders.add(new Order(2, "Hotel Pushpa", 10002, 2, 300, true));
		orders.add(new Order(3, "Mano", 10003, 1, 1000, true));
		orders.add(new Order(4, "Gowri Sankar", 10004, 1, 600, true));
		orders.add(new Order(5, "Hotel Ganga", 10005, 3, 600, false));		
		
###################################################

Pending Task:

1. Need to introduce product id in Product Table
2. API_URL need to be added in all services in Angular // Done
3. Index Restuarat page banner not applied fully // Done
4. UI alignment issues -> Textbox length in Add Pages // Done
5. Restore db from the sheet // Done
6. Remove hardcoded Restuarant values from RestuarantService ts file // Done



