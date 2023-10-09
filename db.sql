CREATE SCHEMA `double_shop` ;

CREATE TABLE `double_shop`.`rank` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(145) NULL,
  `from` BIGINT NULL,
  `to` BIGINT NULL,
  `percent` INT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `double_shop`.`customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_rank` BIGINT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `gender` INT NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NOT NULL,
  `status` INT NOT NULL,
  `created_by` BIGINT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_R_C_idx` (`id_rank` ASC) VISIBLE,
  CONSTRAINT `FK_R_C`
    FOREIGN KEY (`id_rank`)
    REFERENCES `double_shop`.`rank` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
CREATE TABLE `double_shop`.`address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_customer` BIGINT NOT NULL,
  `district` VARCHAR(45) NOT NULL,
  `province` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NOT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NOT NULL,
  `description` VARCHAR(145) NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_A_C_idx` (`id_customer` ASC) VISIBLE,
  CONSTRAINT `FK_A_C`
    FOREIGN KEY (`id_customer`)
    REFERENCES `double_shop`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE `double_shop`.`product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `status` INT NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `double_shop`.`review` (
  `id` BIGINT NOT NULL,
  `id_customer` BIGINT NOT NULL,
  `id_product` BIGINT NOT NULL AUTO_INCREMENT,
  `product_type` NVARCHAR(45) NOT NULL,
  `product_color` NVARCHAR(45) NOT NULL,
  `rating` INT NOT NULL,
  `comment` VARCHAR(145) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_REVIEW_C_idx` (`id_customer` ASC) VISIBLE,
  INDEX `FK_REVIEW_P_idx` (`id_product` ASC) VISIBLE,
  CONSTRAINT `FK_REVIEW_C`
    FOREIGN KEY (`id_customer`)
    REFERENCES `double_shop`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_REVIEW_P`
    FOREIGN KEY (`id_product`)
    REFERENCES `double_shop`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



CREATE TABLE `double_shop`.`category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `status` VARCHAR(45) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE);
  
  
  CREATE TABLE `double_shop`.`size` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `status` VARCHAR(45) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE);
  

CREATE TABLE `double_shop`.`collar` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `status` VARCHAR(45) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE);


CREATE TABLE `double_shop`.`brand` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `status` VARCHAR(45) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE);
  
  CREATE TABLE `double_shop`.`color` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `status` VARCHAR(45) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE);
  
  
    CREATE TABLE `double_shop`.`material` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `status` VARCHAR(45) NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE);
  
  
  CREATE TABLE `double_shop`.`detail_product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_color` BIGINT NOT NULL,
  `id_product` BIGINT NOT NULL,
  `id_size` BIGINT NOT NULL,
  `id_brand` BIGINT NOT NULL,
  `id_collar` BIGINT NOT NULL,
  `id_category` BIGINT NOT NULL,
  `quantity` BIGINT NOT NULL,
  `status` INT NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_PRODUCT_DP_idx` (`id_product` ASC) VISIBLE,
  INDEX `FK_COLOR_DP_idx` (`id_color` ASC) VISIBLE,
  INDEX `FK_BRAND_DP_idx` (`id_brand` ASC) VISIBLE,
  INDEX `FK_SIZE_DP_idx` (`id_size` ASC) VISIBLE,
  INDEX `FK_CATEGORY_DP_idx` (`id_category` ASC) VISIBLE,
  INDEX `FK_COLLAR_DP_idx` (`id_collar` ASC) VISIBLE,
  CONSTRAINT `FK_PRODUCT_DP`
    FOREIGN KEY (`id_product`)
    REFERENCES `double_shop`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COLOR_DP`
    FOREIGN KEY (`id_color`)
    REFERENCES `double_shop`.`color` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_BRAND_DP`
    FOREIGN KEY (`id_brand`)
    REFERENCES `double_shop`.`brand` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_SIZE_DP`
    FOREIGN KEY (`id_size`)
    REFERENCES `double_shop`.`size` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CATEGORY_DP`
    FOREIGN KEY (`id_category`)
    REFERENCES `double_shop`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COLLAR_DP`
    FOREIGN KEY (`id_collar`)
    REFERENCES `double_shop`.`collar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `double_shop`.`detail_material` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_detail_product` BIGINT NOT NULL,
  `id_material` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_DM_DP_idx` (`id_detail_product` ASC) VISIBLE,
  INDEX `FK_DM_M_idx` (`id_material` ASC) VISIBLE,
  CONSTRAINT `FK_DM_M`
    FOREIGN KEY (`id_material`)
    REFERENCES `double_shop`.`material` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_DM_DP`
    FOREIGN KEY (`id_detail_product`)
    REFERENCES `double_shop`.`detail_product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    
    
CREATE TABLE `double_shop`.`employee` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `district` VARCHAR(45) NOT NULL,
  `provice` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `gender` INT NOT NULL,
  `birth_day` DATE NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `status` INT NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `double_shop`.`promotion` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(145) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NOT NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `double_shop`.`cart` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_detail_product` BIGINT NOT NULL,
  `id_customer` BIGINT NOT NULL,
  `quantity` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_CART_C_idx` (`id_customer` ASC) VISIBLE,
  INDEX `FK_CART_DP_idx` (`id_detail_product` ASC) VISIBLE,
  CONSTRAINT `FK_CART_C`
    FOREIGN KEY (`id_customer`)
    REFERENCES `double_shop`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CART_DP`
    FOREIGN KEY (`id_detail_product`)
    REFERENCES `double_shop`.`detail_product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



  CREATE TABLE `double_shop`.`voucher` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_promotion` BIGINT NOT NULL,
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `discount_amout` BIGINT NULL,
  `discount_percentage` INT NULL,
  `quantity` BIGINT NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NOT NULL,
  `created_time` DATETIME NULL,
  `updated_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_VOUVHER_P_idx` (`id_promotion` ASC) VISIBLE,
  CONSTRAINT `FK_VOUVHER_P`
    FOREIGN KEY (`id_promotion`)
    REFERENCES `double_shop`.`promotion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `double_shop`.`customer_voucher` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_voucher` BIGINT NOT NULL,
  `id_customer` BIGINT NOT NULL,
  `usage_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_VOUCHER_V_idx` (`id_voucher` ASC) VISIBLE,
  INDEX `FK_VOUCHER_C_idx` (`id_customer` ASC) VISIBLE,
  CONSTRAINT `FK_VOUCHER_C`
    FOREIGN KEY (`id_customer`)
    REFERENCES `double_shop`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_VOUCHER_V`
    FOREIGN KEY (`id_voucher`)
    REFERENCES `double_shop`.`voucher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `double_shop`.`order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_customer` BIGINT NULL,
  `id_employee` BIGINT NULL,
  `id_voucher` BIGINT NULL,
  `code` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `order_date` DATE NOT NULL,
  `discount_amout` DECIMAL NULL,
  `total_amout` DECIMAL NOT NULL,
  `confirm_date` DATE NULL,
  `ship_date` DATE NULL,
  `receive_date` DATE NULL,
  `note` VARCHAR(45) NULL,
  `money_ship` DATE NOT NULL,
  `status` INT NOT NULL,
  `created_by` BIGINT NOT NULL,
  `updated_by` BIGINT NULL,
  `created_time` DATETIME NULL,
  `updated_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_ORDER_V_idx` (`id_voucher` ASC) VISIBLE,
  INDEX `FK_ORDER_E_idx` (`id_employee` ASC) VISIBLE,
  INDEX `FK_ORDER_C_idx` (`id_customer` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  CONSTRAINT `FK_ORDER_C`
    FOREIGN KEY (`id_customer`)
    REFERENCES `double_shop`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ORDER_E`
    FOREIGN KEY (`id_employee`)
    REFERENCES `double_shop`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ORDER_V`
    FOREIGN KEY (`id_voucher`)
    REFERENCES `double_shop`.`voucher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);





