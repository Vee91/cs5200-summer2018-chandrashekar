-- MySQL Script generated by MySQL Workbench
-- Thu Aug  9 06:09:48 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cs5200_summer2018_chandrashekar
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cs5200_summer2018_chandrashekar
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cs5200_summer2018_chandrashekar` DEFAULT CHARACTER SET latin1 ;
USE `cs5200_summer2018_chandrashekar` ;

-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`aircrafts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`aircrafts` (
  `code` VARCHAR(3) NOT NULL,
  `name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`airlines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`airlines` (
  `code` VARCHAR(6) NOT NULL,
  `name` VARCHAR(65) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`person` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `phone` INT(10) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `username` VARCHAR(30) NULL DEFAULT NULL,
  `password` VARCHAR(60) NULL DEFAULT NULL,
  `role` VARCHAR(8) NULL DEFAULT NULL,
  `dob` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`booking_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`booking_details` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(30) NULL DEFAULT NULL,
  `booking_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `active` BIT(1) NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  INDEX `fk_booking_user_idx` (`user` ASC),
  CONSTRAINT `fk_booking_user`
    FOREIGN KEY (`user`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`person` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`cities` (
  `code` VARCHAR(4) NOT NULL,
  `name` VARCHAR(65) NOT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`flights`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`flights` (
  `flight_number` INT(11) NOT NULL,
  `origin_airport` VARCHAR(4) NULL DEFAULT NULL,
  `origin_terminal` VARCHAR(4) NULL DEFAULT NULL,
  `destination_airport` VARCHAR(4) NULL DEFAULT NULL,
  `destination_terminal` VARCHAR(4) NULL DEFAULT NULL,
  `airline` VARCHAR(6) NULL DEFAULT NULL,
  `aircraft` VARCHAR(3) NULL DEFAULT NULL,
  PRIMARY KEY (`flight_number`),
  INDEX `fk_flight_airline_idx` (`airline` ASC),
  INDEX `fk_flight_aircraft_idx` (`aircraft` ASC),
  INDEX `fk_flight_origin_idx` (`origin_airport` ASC),
  INDEX `fk_flight_destination_idx` (`destination_airport` ASC),
  CONSTRAINT `fk_flight_aircraft`
    FOREIGN KEY (`aircraft`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`aircrafts` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flight_airline`
    FOREIGN KEY (`airline`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`airlines` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flight_destination`
    FOREIGN KEY (`destination_airport`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`cities` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flight_origin`
    FOREIGN KEY (`origin_airport`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`cities` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`schedule` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `flight` INT(11) NULL DEFAULT NULL,
  `departure` DATETIME NULL DEFAULT NULL,
  `arrival` DATETIME NULL DEFAULT NULL,
  `status` VARCHAR(15) NULL DEFAULT 'On Time',
  `seats_remaining` INT(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_schedule_flight_idx` (`flight` ASC),
  CONSTRAINT `fk_schedule_flight`
    FOREIGN KEY (`flight`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`flights` (`flight_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`booking` (
  `booking` INT(11) NOT NULL AUTO_INCREMENT,
  `schedule` INT(11) NOT NULL,
  PRIMARY KEY (`booking`, `schedule`),
  INDEX `fk_booking_itinerary_idx` (`schedule` ASC),
  CONSTRAINT `fk_booking`
    FOREIGN KEY (`booking`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`booking_details` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_schedule`
    FOREIGN KEY (`schedule`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`schedule` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`creditcard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`creditcard` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(30) NOT NULL,
  `card_number` VARCHAR(19) NOT NULL,
  `full_name` VARCHAR(45) NOT NULL,
  `exp_month` INT(2) NOT NULL,
  `exp_year` INT(4) NOT NULL,
  `security_code` INT(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `card_number_UNIQUE` (`card_number` ASC),
  INDEX `fk_card_person_idx` (`user` ASC),
  CONSTRAINT `fk_card_person`
    FOREIGN KEY (`user`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`person` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`employee` (
  `id` INT(11) NOT NULL,
  `salary` INT(7) NULL DEFAULT NULL,
  `designation` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_employee_person`
    FOREIGN KEY (`id`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`fight_crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`fight_crew` (
  `schedule` INT(11) NOT NULL,
  `employee` INT(11) NOT NULL,
  INDEX `fk_crew_schedule_idx` (`schedule` ASC),
  INDEX `fk_crew_employee_idx` (`employee` ASC),
  CONSTRAINT `fk_crew_employee`
    FOREIGN KEY (`employee`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_crew_schedule`
    FOREIGN KEY (`schedule`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`schedule` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`itinerary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`itinerary` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `origin` VARCHAR(4) NOT NULL,
  `destination` VARCHAR(4) NOT NULL,
  `duration` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_itinerary_origin_idx` (`origin` ASC),
  INDEX `fk_itinerary_destination_idx` (`destination` ASC),
  CONSTRAINT `fk_itinerary_destination`
    FOREIGN KEY (`destination`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`cities` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itinerary_origin`
    FOREIGN KEY (`origin`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`cities` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`flights_in_itinerary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`flights_in_itinerary` (
  `itinerary` INT(11) NOT NULL,
  `schedule` INT(11) NOT NULL,
  PRIMARY KEY (`itinerary`, `schedule`),
  INDEX `fk_itinerary_flight_idx` (`schedule` ASC),
  CONSTRAINT `fk_itinerary`
    FOREIGN KEY (`itinerary`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`itinerary` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itinerary_schedule`
    FOREIGN KEY (`schedule`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`schedule` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`hello`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`hello` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`passenger` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `booking` INT(11) NOT NULL,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `adult` BIT(1) NULL DEFAULT NULL,
  `phone_no` VARCHAR(10) NULL DEFAULT NULL,
  `gender` BIT(1) NULL DEFAULT NULL,
  `seat_number` VARCHAR(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_passengers_booking_idx` (`booking` ASC),
  CONSTRAINT `fk_passengers_booking`
    FOREIGN KEY (`booking`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`booking` (`booking`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`sample`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`sample` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`transaction` (
  `booking` INT(11) NOT NULL,
  `amount_payable` DECIMAL(8,2) NOT NULL,
  `tax` DECIMAL(8,2) NULL DEFAULT NULL,
  `paid_from` INT(11) NULL DEFAULT NULL,
  `payment_status` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`booking`),
  INDEX `fk_payment_creditcard_idx` (`paid_from` ASC),
  CONSTRAINT `fk_payment_booking`
    FOREIGN KEY (`booking`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`booking` (`booking`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_creditcard`
    FOREIGN KEY (`paid_from`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`creditcard` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cs5200_summer2018_chandrashekar`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs5200_summer2018_chandrashekar`.`user` (
  `id` INT(11) NOT NULL,
  `reward_points` INT(5) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_person`
    FOREIGN KEY (`id`)
    REFERENCES `cs5200_summer2018_chandrashekar`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;