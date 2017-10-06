-- Drop the database if it exists
DROP DATABASE IF EXISTS hero_db;

-- Create a new database for the hotel SQL summative
CREATE DATABASE hero_db;

-- Switch to the hotel_db database
USE hero_db;

CREATE TABLE IF NOT EXISTS `organizations` (
 `organization_id` int(11) NOT NULL AUTO_INCREMENT,
 `organization_name` varchar(40) NOT NULL,
 `description` varchar(175),
 `street` varchar(30) NOT NULL,
 `city` varchar(20) NOT NULL,
 `state` varchar(20) NOT NULL,
 `zip` varchar(20) NOT NULL,
 `phone` varchar(20) NOT NULL,
 PRIMARY KEY (`organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `super_humans` (
 `superhuman_id` int(11) NOT NULL AUTO_INCREMENT,
 `superhuman_name` varchar(20) NOT NULL,
 `description` varchar(100) NOT NULL,
 `societal_impact` ENUM('HERO', 'VILLAIN', 'UNKNOWN'),
 PRIMARY KEY (superhuman_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `superhuman_x_orgs` (
 `organization_id` int(11) NOT NULL,
 `superhuman_id` int(11) NOT NULL,
 KEY `organization_id` (`organization_id`),
 KEY `superhuman_id` (`superhuman_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `super_powers` (
 `powertype_id` int(11) NOT NULL AUTO_INCREMENT,
 `power_type` varchar(30) NOT NULL,
 `description` varchar(100) NOT NULL,
 PRIMARY KEY (`powertype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS superhuman_x_powers (
 powertype_id int(11) NOT NULL,
 superhuman_id int(11) NOT NULL,
 KEY powertype_id (powertype_id),
 KEY superhuman_id (superhuman_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS sightings (
sighting_id int(11) NOT NULL AUTO_INCREMENT,
sighting_name VARCHAR(30) NOT NULL,
description VARCHAR(100) NOT NULL,
street varchar(30) NOT NULL,
city varchar(20) NOT NULL,
state varchar(20) NOT NULL,
zip varchar(20) NOT NULL,
latitude DECIMAL(7,4) NOT NULL,
longitude DECIMAL(7,4) NOT NULL,
sighting_date date,
PRIMARY KEY (sighting_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS superhuman_x_sightings (
sighting_id int(11) NOT NULL,
superhuman_id int(11) NOT NULL,
KEY sighting_id (sighting_id),
KEY superhuman_id (superhuman_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Constraints for bridge tables
--
ALTER TABLE superhuman_x_orgs
 ADD CONSTRAINT superhuman_x_orgs_ibfk_1 FOREIGN KEY (organization_id) REFERENCES organizations
(organization_id) ON DELETE NO ACTION,
 ADD CONSTRAINT superhuman_x_orgs_ibfk_2 FOREIGN KEY (superhuman_id) REFERENCES super_humans
(superhuman_id) ON DELETE NO ACTION;

ALTER TABLE superhuman_x_powers
 ADD CONSTRAINT superhuman_x_powers_ibfk_1 FOREIGN KEY (powertype_id) REFERENCES super_powers
(powertype_id) ON DELETE NO ACTION,
 ADD CONSTRAINT superhuman_x_powers_ibfk_2 FOREIGN KEY (superhuman_id) REFERENCES super_humans
(superhuman_id) ON DELETE NO ACTION;

ALTER TABLE superhuman_x_sightings
 ADD CONSTRAINT superhuman_x_sightings_ibfk_1 FOREIGN KEY (sighting_id) REFERENCES sightings
(sighting_id) ON DELETE NO ACTION,
 ADD CONSTRAINT superhuman_x_sightings_ibfk_2 FOREIGN KEY (superhuman_id) REFERENCES super_humans
(superhuman_id) ON DELETE NO ACTION;

INSERT INTO super_humans (superHuman_Id, superHuman_name, description)
VALUES (default, 'Question Man', 'Never stops pestering you with Questions.'),
(default, 'John Deere', 'Tractor'),
(default, 'Erik Larson', 'A super swell guy!'),
(default, 'Storm', 'A mutant with the ability to control weather.'),
(default, 'Thor', 'The Norse God of thunder and an alien from Asgard.');

INSERT INTO organizations (organization_id, organization_name, description, street, 
city, state, zip, phone)
VALUES (default, 'The Punctuation Gang', 'Rid the world of poorly formed sentences.',
'123 Sessame St.', 'Somewhere', 'TX', '54321', '1234567890'),
(default, 'Farmers United', 'A union of amazing SUPER farmers', '12 farmers market Rd.', 'Des Moines', 'IA', '90210', '9876541236'),
(default, "Xavier's School for Gifted Youngsters", 
'A special institute founded and led by Professor Charles Xavier to train young mutants in controlling their powers and help foster a friendly human-mutant relationship.', 
'1407 Gray Malkin Lane', 'North Salem', 'NY', '10560', '18003129951');

INSERT INTO super_powers (powertype_id, power_type, description)
VALUES (default, 'punctuation', 'Stumps the victim with an impossible question.'),
(default, 'green thumb', 'Can make plants grow impossibly quick.'),
(default, 'weather manipulation', 'Tbe ability to change the current weather at will.'),
(default, 'magic', 'The ability to harness the super natural.'),
(default, 'super strength', 'Impossible strength.'),
(default, 'thunder', 'The ability to control thunder.');

INSERT INTO sightings (sighting_id, sighting_name, description, street, city,
state, zip, latitude, longitude, sighting_date)
VALUES (default, 'Empire State Building', 'Flash Mob of people wearing Question Marks and asking Questions.',
'100 Empire St.', 'New York', 'New York', 78954, 100.52, 546.98, 20170227),
(default, 'Twin Cities Mega-Storm', 'A massive thunderstorm.  Storm and Thor had a falling out.', 
'15 S. 5th Street', 'Minneapolis', 'MN', '55402', 044.9789, -093.2718, 20170625);

INSERT INTO superhuman_x_sightings
VALUES (1, 1),
(2, 4),
(2, 5);

INSERT INTO superhuman_x_powers
VALUES (1, 1),
(2, 2),
(3, 4),
(4, 4),
(5, 5),
(6, 5);

INSERT INTO superhuman_x_orgs
VALUES (1,1),
(2, 2),
(3, 4);

-- ---------------------
-- ----TEST DB Creation
-- ---------------------

-- Drop the database if it exists
DROP DATABASE IF EXISTS hero_db_test;

-- Create a new database for the hotel SQL summative
CREATE DATABASE hero_db_test;

-- Switch to the hotel_db database
USE hero_db_test;

CREATE TABLE IF NOT EXISTS `organizations` (
 `organization_id` int(11) NOT NULL AUTO_INCREMENT,
 `organization_name` varchar(40) NOT NULL,
 `description` varchar(175),
 `street` varchar(30) NOT NULL,
 `city` varchar(20) NOT NULL,
 `state` varchar(20) NOT NULL,
 `zip` varchar(20) NOT NULL,
 `phone` varchar(20) NOT NULL,
 PRIMARY KEY (`organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `super_humans` (
 `superhuman_id` int(11) NOT NULL AUTO_INCREMENT,
 `superhuman_name` varchar(20) NOT NULL,
 `description` varchar(100) NOT NULL,
 `societal_impact` ENUM('HERO', 'VILLAIN', 'UNKNOWN'),
 PRIMARY KEY (superhuman_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `superhuman_x_orgs` (
 `organization_id` int(11) NOT NULL,
 `superhuman_id` int(11) NOT NULL,
 KEY `organization_id` (`organization_id`),
 KEY `superhuman_id` (`superhuman_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `super_powers` (
 `powertype_id` int(11) NOT NULL AUTO_INCREMENT,
 `power_type` varchar(30) NOT NULL,
 `description` varchar(100) NOT NULL,
 PRIMARY KEY (`powertype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS superhuman_x_powers (
 powertype_id int(11) NOT NULL,
 superhuman_id int(11) NOT NULL,
 KEY powertype_id (powertype_id),
 KEY superhuman_id (superhuman_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS sightings (
sighting_id int(11) NOT NULL AUTO_INCREMENT,
sighting_name VARCHAR(30) NOT NULL,
description VARCHAR(100) NOT NULL,
street varchar(30) NOT NULL,
city varchar(20) NOT NULL,
state varchar(20) NOT NULL,
zip varchar(20) NOT NULL,
latitude DECIMAL(7,4) NOT NULL,
longitude DECIMAL(7,4) NOT NULL,
sighting_date date,
PRIMARY KEY (sighting_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS superhuman_x_sightings (
sighting_id int(11) NOT NULL,
superhuman_id int(11) NOT NULL,
KEY sighting_id (sighting_id),
KEY superhuman_id (superhuman_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Constraints for bridge tables
--
ALTER TABLE superhuman_x_orgs
 ADD CONSTRAINT superhuman_x_orgs_ibfk_1 FOREIGN KEY (organization_id) REFERENCES organizations
(organization_id) ON DELETE NO ACTION,
 ADD CONSTRAINT superhuman_x_orgs_ibfk_2 FOREIGN KEY (superhuman_id) REFERENCES super_humans
(superhuman_id) ON DELETE NO ACTION;

ALTER TABLE superhuman_x_powers
 ADD CONSTRAINT superhuman_x_powers_ibfk_1 FOREIGN KEY (powertype_id) REFERENCES super_powers
(powertype_id) ON DELETE NO ACTION,
 ADD CONSTRAINT superhuman_x_powers_ibfk_2 FOREIGN KEY (superhuman_id) REFERENCES super_humans
(superhuman_id) ON DELETE NO ACTION;

ALTER TABLE superhuman_x_sightings
 ADD CONSTRAINT superhuman_x_sightings_ibfk_1 FOREIGN KEY (sighting_id) REFERENCES sightings
(sighting_id) ON DELETE NO ACTION,
 ADD CONSTRAINT superhuman_x_sightings_ibfk_2 FOREIGN KEY (superhuman_id) REFERENCES super_humans
(superhuman_id) ON DELETE NO ACTION;



INSERT INTO super_humans (superHuman_Id, superHuman_name, description)
VALUES (default, 'Question Man', 'Never stops pestering you with Questions.'),
(default, 'John Deere', 'Tractor'),
(default, 'Erik Larson', 'A super swell guy!'),
(default, 'Storm', 'A mutant with the ability to control weather.'),
(default, 'Thor', 'The Norse God of thunder and an alien from Asgard.');

INSERT INTO organizations (organization_id, organization_name, description, street, 
city, state, zip, phone)
VALUES (default, 'The Punctuation Gang', 'Rid the world of poorly formed sentences.',
'123 Sessame St.', 'Somewhere', 'TX', '54321', '1234567890'),
(default, 'Farmers United', 'A union of amazing SUPER farmers', '12 farmers market Rd.', 'Des Moines', 'IA', '90210', '9876541236'), 
(default, "Xavier's School for Gifted Youngsters", 
'A special institute founded and led by Professor Charles Xavier to train young mutants in controlling their powers and help foster a friendly human-mutant relationship.', 
'1407 Gray Malkin Lane', 'North Salem', 'NY', '10560', '18003129951');

INSERT INTO super_powers (powertype_id, power_type, description)
VALUES (default, 'punctuation', 'Stumps the victim with an impossible question.'),
(default, 'green thumb', 'Can make plants grow impossibly quick.'),
(default, 'weather manipulation', 'Tbe ability to change the current weather at will.'),
(default, 'magic', 'The ability to harness the super natural.'),
(default, 'super strength', 'Impossible strength.'),
(default, 'thunder', 'The ability to control thunder.');

INSERT INTO sightings (sighting_id, sighting_name, description, street, city,
state, zip, latitude, longitude, sighting_date)
VALUES (default, 'Empire State Building', 'Flash Mob of people wearing Question Marks and asking Questions.',
'100 Empire St.', 'New York', 'New York', '78954', 100.52, 546.98, 20170227),
(default, 'Twin Cities Mega-Storm', 'A massive thunderstorm.  Storm and Thor had a falling out.', 
'15 S. 5th Street', 'Minneapolis', 'MN', '55402', 044.9789, -093.2718, 20170625);

INSERT INTO superhuman_x_sightings
VALUES (1, 1),
(2, 4),
(2, 5);

INSERT INTO superhuman_x_powers
VALUES (1, 1),
(2, 2),
(3, 4),
(4, 4),
(5, 5),
(6, 5);

INSERT INTO superhuman_x_orgs
VALUES (1,1),
(2, 2),
(3, 4);

--
-- ADD SPRING SECURITY 
--

CREATE TABLE IF NOT EXISTS `users` (
 `user_id` int(11) NOT NULL AUTO_INCREMENT,
 `username` varchar(20) NOT NULL,
 `password` varchar(100) NOT NULL,
 `enabled` tinyint(1) NOT NULL,
 PRIMARY KEY (`user_id`),
 KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;
--
-- Dumping data for table `users`
--
INSERT INTO `users` (`user_id`, `username`, `password`, `enabled`) VALUES
(1, 'admin', 'password', 1),
(2, 'user', 'password', 1);
--
-- Table structure for table `authorities`
--
CREATE TABLE IF NOT EXISTS `authorities` (
 `username` varchar(20) NOT NULL,
 `authority` varchar(20) NOT NULL,
 KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `authorities`
--
INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('user', 'ROLE_USER');
--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);