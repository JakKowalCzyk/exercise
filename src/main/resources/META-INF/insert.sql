drop table if EXISTS restaurant;

CREATE TABLE restaurant(id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(255), foodType VARCHAR(255), positionX int, positionY INT );

INSERT INTO restaurant(id, name, foodType, positionX, positionY) VALUES (1, 'restauracja', 'MEET', 5, 6);
INSERT INTO restaurant(id, name, foodType, positionX, positionY) VALUES (2, 'restauracja2', 'DINNER', 6, 9);
INSERT INTO restaurant(id, name, foodType, positionX, positionY) VALUES (3, 'restauracja3', 'FASTFOOD', 7, 0);
INSERT INTO restaurant(id, name, foodType, positionX, positionY) VALUES (4, 'restauracja4', 'MEET', 53, 65);
