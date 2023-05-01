DROP TABLE IF EXISTS frost_plants;
DROP TABLE IF EXISTS plant_date;
DROP TABLE IF EXISTS plants;
DROP TABLE IF EXISTS frost_dates;

CREATE TABLE frost_dates (
-- This is the Primary Key
	state VARCHAR(50) NOT NULL,
    first_frost VARCHAR(50),
    last_frost VARCHAR(50),
    PRIMARY KEY (state)
);

CREATE TABLE plants (
-- This is the Primary Key
    plant_id INT AUTO_INCREMENT NOT NULL,
-- Foregin Key form the project table
   plant_name VARCHAR(128) NOT NULL,
   annual_type VARCHAR(128) NOT null,
   flowering BOOLEAN,
   maturity_days INT not NULL,
   PRIMARY KEY (plant_id)
);

CREATE TABLE plant_date (
-- This is the primary key
	date_id INT AUTO_INCREMENT NOT NULL,
-- This is a foregien key from the project table
	plant_id INT NOT NULL,
    planned_date VARCHAR(50) NOT NULL,
    start_date VARCHAR(50) NOT NULL,
    maturity_date VARCHAR(50) not null,
    PRIMARY KEY (date_id),
    FOREIGN KEY (plant_id) REFERENCES plants (plant_id) ON DELETE CASCADE
);

CREATE TABLE frost_plants (
-- This will bring in forgein keys from the project and category table
	state VARCHAR(50) NOT NULL,
    plant_id INT NOT NULL,
    FOREIGN KEY (state) REFERENCES frost_dates (state) ON DELETE CASCADE,
    FOREIGN KEY (plant_id) REFERENCES plants (plant_id) ON DELETE CASCADE,
    UNIQUE KEY (state, plant_id)
);