BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "container" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT,
	"capacity"	INTEGER,
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
	"purchase_price"	INTEGER,
	"selling_price"	INTEGER,
	"container_id"	INTEGER,
	FOREIGN KEY("container_id") REFERENCES "container"("id")
);
CREATE TABLE IF NOT EXISTS "sector" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT,
	"capacity"	INTEGER
);
INSERT INTO "sector" VALUES (1,'A',2);
INSERT INTO "sector" VALUES (2,'B',1);
INSERT INTO "sector" VALUES (3,'C',0);
INSERT INTO "sector" VALUES (4,'D',0);
INSERT INTO "sector" VALUES (5,'E',0);
INSERT INTO "sector" VALUES (6,'F',0);
INSERT INTO "sector" VALUES (7,'G',0);
INSERT INTO "sector" VALUES (8,'H',0);
INSERT INTO "container" VALUES (1,'1',2,1);
INSERT INTO "container" VALUES (2,'2',1,1);
INSERT INTO "container" VALUES (3,'3',2,2);
INSERT INTO "container" VALUES (4,'4',0,3);
INSERT INTO "container" VALUES (5,'5',0,4);
INSERT INTO "container" VALUES (6,'6',0,5);
INSERT INTO "container" VALUES (7,'7',0,6);
INSERT INTO "container" VALUES (8,'8',0,7);
INSERT INTO "container" VALUES (9,'9',0,8);
INSERT INTO "product" VALUES (1,'Milk',10,1,'liter',0.4,0.15,'1234ABC1','A1',1.85,2.35,1);
INSERT INTO "product" VALUES (1,'Sour cream',23,0.8,'kilogram',0.25,0.2,'1234ABC2','A1',2.70,3.50,1);
INSERT INTO "product" VALUES (1,'Ketchup',34,1.5,'kilogram',0.40,0.1,'1234ABC3','A2',2.10,3.30,2);
INSERT INTO "product" VALUES (1,'Pudding',56,0.2,'kilogram',0.10,0.08,'1234ABC4','A3',3.70,4.65,3);
INSERT INTO "product" VALUES (1,'Bread',67,950,'gram',0.15,0.5,'1234ABC5','B3',0.70,1.50,3);
COMMIT;
