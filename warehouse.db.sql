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
INSERT INTO "container" VALUES (3,'3',2100,2);
INSERT INTO "container" VALUES (4,'4',1000,3);
INSERT INTO "container" VALUES (5,'5',500,4);
INSERT INTO "container" VALUES (6,'6',850,5);
INSERT INTO "container" VALUES (7,'7',630,6);
INSERT INTO "container" VALUES (8,'8',3400,7);
INSERT INTO "container" VALUES (9,'9',2000,8);
INSERT INTO "product" VALUES (1,'Milk',10,1,'liter',0.4,0.15,'1234ABC1','A1',1.85,2.35,1);
INSERT INTO "product" VALUES (2,'Sour cream',23,0.8,'kilogram',0.25,0.2,'1234ABC2','A1',2.70,3.50,1);
INSERT INTO "product" VALUES (3,'Ketchup',34,1.5,'kilogram',0.40,0.1,'1234ABC3','A2',2.10,3.30,2);
INSERT INTO "product" VALUES (4,'Pudding',56,0.2,'kilogram',0.10,0.08,'1234ABC4','A3',3.70,4.65,3);
INSERT INTO "product" VALUES (5,'Bread',67,950,'gram',0.15,0.5,'1234ABC5','B5',0.70,1.50,3);
COMMIT;
