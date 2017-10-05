-- --------------------------------------------
-- Agrega PK a serie
-- 
ALTER TABLE `facturatron`.`serie` 
CHANGE COLUMN `serie` `serie` VARCHAR(10) NOT NULL ,
ADD PRIMARY KEY (`serie`);
