CREATE TABLE `test`.`servicestatus` (
  `id` INT NOT NULL,
  `service_id` INT NOT NULL,
  `consumer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_idx` (`service_id` ASC),
  INDEX `refer_consumer_idx` (`consumer_id` ASC),
  CONSTRAINT `refer_service`
    FOREIGN KEY (`service_id`)
    REFERENCES `test`.`service` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `refer_consumer`
    FOREIGN KEY (`consumer_id`)
    REFERENCES `test`.`consumer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


    ALTER TABLE `test`.`servicestatus`
    CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

