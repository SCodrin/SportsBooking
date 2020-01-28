DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS sport;
DROP TABLE IF EXISTS cost;


CREATE TABLE location (
  location_id INT AUTO_INCREMENT  PRIMARY KEY,
  country VARCHAR(250) NOT NULL,
  region VARCHAR(250) NOT NULL,
  city VARCHAR(250) NOT NULL
);

-- INSERT INTO location (country, region, city) VALUES
--   ('Romania', 'Brasov', 'Sinaia'),
--   ('Romania', 'Brasov', 'Busteni'),
--   ('Romania', 'Brasov', 'Predeal');


CREATE TABLE sport (
  sport_id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL
);
--
-- INSERT INTO sport (name, startDate, endDate) VALUES
--   ('SKI', '01/02/2020', '02/02/2020'),
--   ('ATV', '01/02/2020', '02/02/2020');
--

CREATE TABLE cost (
  cost_id INT AUTO_INCREMENT  PRIMARY KEY,
  location_id INT  NOT NULL,
  sport_id INT  NOT NULL,
  amount VARCHAR(250) NOT NULL,
  spots INT NOT NULL
);
--
-- INSERT INTO cost (locationId, sportId,amount, seats) VALUES
--   (1,1,'23', 23),
--   (1,1,'23', 23),
--   (1,1, '23', 23),
