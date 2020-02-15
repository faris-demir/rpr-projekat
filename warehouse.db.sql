BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "sector" (
    "id"	INTEGER PRIMARY KEY AUTOINCREMENT,
    "name"	TEXT,
    "capacity"	INTEGER
);
CREATE TABLE IF NOT EXISTS "container" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT,
	"capacity"	NUMBER,
	"sector_id"	INTEGER,
	FOREIGN KEY("sector_id") REFERENCES "sector"("id")
);
CREATE TABLE IF NOT EXISTS "product" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT,
	"quantity"	INTEGER,
	"weight"	NUMBER,
	"unit"	TEXT,
	"package_height"	NUMBER,
	"package_width"	NUMBER,
	"serial_number"	TEXT,
	"location_tag"	TEXT,
	"purchase_price"	NUMBER,
	"selling_price"	NUMBER,
	"container_id"	INTEGER,
	FOREIGN KEY("container_id") REFERENCES "container"("id")
);
CREATE TABLE IF NOT EXISTS "sales" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "product_name" TEXT,
    "sold_quantity" INTEGER,
    "sale_date" TEXT,
    "product_price" NUMBER,
    "total_price" NUMBER
);
CREATE TABLE IF NOT EXISTS "orders" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "product_name" TEXT,
    "ordered_quantity" INTEGER,
    "order_date" TEXT,
    "product_price" NUMBER,
    "total_price" NUMBER
);
INSERT INTO "sector" VALUES (1,'A',9);
INSERT INTO "sector" VALUES (2,'B',9);
INSERT INTO "sector" VALUES (3,'C',9);
INSERT INTO "sector" VALUES (4,'D',9);
INSERT INTO "sector" VALUES (5,'E',9);
INSERT INTO "sector" VALUES (6,'F',9);
INSERT INTO "sector" VALUES (7,'G',9);
INSERT INTO "sector" VALUES (8,'H',9);
INSERT INTO "container" VALUES (1,'1',3000,1);
INSERT INTO "container" VALUES (2,'2',2000,1);
INSERT INTO "container" VALUES (3,'3',2100,1);
INSERT INTO "container" VALUES (4,'4',1000,1);
INSERT INTO "container" VALUES (5,'5',500,1);
INSERT INTO "container" VALUES (6,'6',850,1);
INSERT INTO "container" VALUES (7,'7',630,1);
INSERT INTO "container" VALUES (8,'8',3400,1);
INSERT INTO "container" VALUES (9,'9',2000,1);
INSERT INTO "container" VALUES (10,'1',3000,2);
INSERT INTO "container" VALUES (11,'2',2000,2);
INSERT INTO "container" VALUES (12,'3',2100,2);
INSERT INTO "container" VALUES (13,'4',1000,2);
INSERT INTO "container" VALUES (14,'5',500,2);
INSERT INTO "container" VALUES (15,'6',850,2);
INSERT INTO "container" VALUES (16,'7',630,2);
INSERT INTO "container" VALUES (17,'8',3400,2);
INSERT INTO "container" VALUES (18,'9',2000,2);
INSERT INTO "container" VALUES (19,'1',3000,3);
INSERT INTO "container" VALUES (20,'2',2000,3);
INSERT INTO "container" VALUES (21,'3',2100,3);
INSERT INTO "container" VALUES (22,'4',1000,3);
INSERT INTO "container" VALUES (23,'5',500,3);
INSERT INTO "container" VALUES (24,'6',850,3);
INSERT INTO "container" VALUES (25,'7',630,3);
INSERT INTO "container" VALUES (26,'8',3400,3);
INSERT INTO "container" VALUES (27,'9',2000,3);
INSERT INTO "container" VALUES (28,'1',3000,4);
INSERT INTO "container" VALUES (29,'2',2000,4);
INSERT INTO "container" VALUES (30,'3',2100,4);
INSERT INTO "container" VALUES (31,'4',1000,4);
INSERT INTO "container" VALUES (32,'5',500,4);
INSERT INTO "container" VALUES (33,'6',850,4);
INSERT INTO "container" VALUES (34,'7',630,4);
INSERT INTO "container" VALUES (35,'8',3400,4);
INSERT INTO "container" VALUES (36,'9',2000,4);
INSERT INTO "container" VALUES (37,'1',3000,5);
INSERT INTO "container" VALUES (38,'2',2000,5);
INSERT INTO "container" VALUES (39,'3',2100,5);
INSERT INTO "container" VALUES (40,'4',1000,5);
INSERT INTO "container" VALUES (41,'5',500,5);
INSERT INTO "container" VALUES (42,'6',850,5);
INSERT INTO "container" VALUES (43,'7',630,5);
INSERT INTO "container" VALUES (44,'8',3400,5);
INSERT INTO "container" VALUES (45,'9',2000,5);
INSERT INTO "container" VALUES (46,'1',3000,6);
INSERT INTO "container" VALUES (47,'2',2000,6);
INSERT INTO "container" VALUES (48,'3',2100,6);
INSERT INTO "container" VALUES (49,'4',1000,6);
INSERT INTO "container" VALUES (50,'5',500,6);
INSERT INTO "container" VALUES (51,'6',850,6);
INSERT INTO "container" VALUES (52,'7',630,6);
INSERT INTO "container" VALUES (53,'8',3400,6);
INSERT INTO "container" VALUES (54,'9',2000,6);
INSERT INTO "container" VALUES (55,'1',3000,7);
INSERT INTO "container" VALUES (56,'2',2000,7);
INSERT INTO "container" VALUES (57,'3',2100,7);
INSERT INTO "container" VALUES (58,'4',1000,7);
INSERT INTO "container" VALUES (59,'5',500,7);
INSERT INTO "container" VALUES (60,'6',850,7);
INSERT INTO "container" VALUES (61,'7',630,7);
INSERT INTO "container" VALUES (62,'8',3400,7);
INSERT INTO "container" VALUES (63,'9',2000,7);
INSERT INTO "container" VALUES (64,'1',3000,8);
INSERT INTO "container" VALUES (65,'2',2000,8);
INSERT INTO "container" VALUES (66,'3',2100,8);
INSERT INTO "container" VALUES (67,'4',1000,8);
INSERT INTO "container" VALUES (68,'5',500,8);
INSERT INTO "container" VALUES (69,'6',850,8);
INSERT INTO "container" VALUES (70,'7',630,8);
INSERT INTO "container" VALUES (71,'8',3400,8);
INSERT INTO "container" VALUES (72,'9',2000,8);
INSERT INTO "product" VALUES (1,'Milk',10,1,'liter',0.4,0.15,'1234ABC1','A1',1.85,2.35,1);
INSERT INTO "product" VALUES (2,'Sour cream',23,0.8,'kilogram',0.25,0.2,'1234ABC2','A1',2.70,3.50,1);
INSERT INTO "product" VALUES (3,'Ketchup',34,1.5,'kilogram',0.40,0.1,'1234ABC3','A2',2.10,3.30,2);
INSERT INTO "product" VALUES (4,'Pudding',56,0.2,'kilogram',0.10,0.08,'1234ABC4','A3',3.70,4.65,3);
INSERT INTO "product" VALUES (5,'Bread',67,950,'gram',0.15,0.5,'1234ABC5','B5',0.70,1.50,3);
COMMIT;