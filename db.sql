CREATE SCHEMA `double_shop`;

CREATE TABLE `double_shop`.`category`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `code`         VARCHAR(45) NOT NULL,
    `name`         VARCHAR(45) NOT NULL,
    `description`  VARCHAR(45) NULL,
    `status`       INT NULL,
    `created_by`   VARCHAR(45) NOT NULL,
    `updated_by`   VARCHAR(45) NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_time` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE
);


CREATE TABLE `double_shop`.`collar`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `code`         VARCHAR(45) NOT NULL,
    `name`         VARCHAR(45) NOT NULL,
    `description`  VARCHAR(45) NULL,
    `status`       INT         NOT NULL,
    `created_by`   VARCHAR(45) NOT NULL,
    `updated_by`   VARCHAR(45) NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_time` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE
);


CREATE TABLE `double_shop`.`brand`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `code`         VARCHAR(45) NOT NULL,
    `name`         VARCHAR(45) NOT NULL,
    `description`  VARCHAR(45) NULL,
    `status`       INT         NOT NULL,
    `created_by`   VARCHAR(45) NOT NULL,
    `updated_by`   VARCHAR(45) NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_time` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE
);


CREATE TABLE `double_shop`.`size`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `code`         VARCHAR(45) NOT NULL,
    `name`         VARCHAR(45) NOT NULL,
    `description`  VARCHAR(45) NULL,
    `status`       INT         NOT NULL,
    `created_by`   VARCHAR(45) NOT NULL,
    `updated_by`   VARCHAR(45) NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_time` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE
);


CREATE TABLE `double_shop`.`color`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `code`         VARCHAR(45) NOT NULL,
    `name`         VARCHAR(45) NOT NULL,
    `description`  VARCHAR(45) NULL,
    `status`       INT         NOT NULL,
    `created_by`   VARCHAR(45) NOT NULL,
    `updated_by`   VARCHAR(45) NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_time` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE
);


CREATE TABLE `double_shop`.`material`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `code`         VARCHAR(45) NOT NULL,
    `name`         VARCHAR(45) NOT NULL,
    `description`  VARCHAR(45) NULL,
    `status`       INT NULL,
    `created_by`   VARCHAR(45) NOT NULL,
    `updated_by`   VARCHAR(45) NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_time` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE
);


CREATE TABLE `double_shop`.`rank`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45) NOT NULL,
    `description`  VARCHAR(45) NULL,
    `from`         BIGINT NULL,
    `to`           BIGINT      NOT NULL,
    `percent`      INT NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_time` VARCHAR(45) NULL,
    `updated_by`   VARCHAR(45) NOT NULL,
    `created_by`   VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
);


CREATE TABLE `double_shop`.`employee`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45) NULL,
    `username`     VARCHAR(45) NOT NULL,
    `phone`        VARCHAR(45) NULL,
    `email`        VARCHAR(45) NULL,
    `gender`       INT NULL,
    `description`  varchar(145) null,
    `birth_day`    VARCHAR(45) NOT NULL,
    `role`         INT         NOT NULL,
    `status`       INT         NOT NULL,
    `district`     VARCHAR(45) NOT NULL,
    `provice`      VARCHAR(45) NOT NULL,
    `city`         VARCHAR(45) NOT NULL,
    `password`     VARCHAR(145) NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_time` VARCHAR(45) NULL,
    `updated_by`   VARCHAR(45) NOT NULL,
    `created_by`   VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
);


CREATE TABLE `double_shop`.`customer`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `id_rank`      BIGINT NULL,
    `username`     VARCHAR(45) NULL,
    `name`         VARCHAR(45) NULL,
    `gender`       INT NULL,
    `phone`        VARCHAR(45) NULL,
    `birth_day`    VARCHAR(45) NULL,
    `email`        VARCHAR(45) NULL,
    `password`     VARCHAR(145) NULL,
    `status`       INT NULL,
    `created_by`   VARCHAR(45) NOT NULL,
    `updated_by`   VARCHAR(45) NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_time` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX          `FK_CUS_RANK_idx` (`id_rank` ASC) VISIBLE,
    CONSTRAINT `FK_CUS_RANK`
        FOREIGN KEY (`id_rank`)
            REFERENCES `double_shop`.`rank` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE `double_shop`.`voucher`
(
    `id`               BIGINT       NOT NULL AUTO_INCREMENT,
    `code`             VARCHAR(45)  NOT NULL,
    `discount_amount`  BIGINT NULL,
    `discount_percent` INT NULL,
    `quantity`         INT          NOT NULL,
    `start_date`       VARCHAR(45)  NOT NULL,
    `end_date`         VARCHAR(45)  NOT NULL,
    `created_by`       VARCHAR(45)  NOT NULL,
    `created_time`     VARCHAR(45)  NOT NULL,
    `updated_time`     VARCHAR(45) NULL,
    `updated_by`       VARCHAR(45) NULL,
    `minimum_order`    bigint       not null,
    `name`             varchar(100) not null,

    `status`           INT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `double_shop`.`customer_voucher`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `id_voucher`  BIGINT NOT NULL,
    `id_customer` BIGINT NOT NULL,
    `usage_date`  VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX         `FK_CUSV_VOU_idx` (`id_voucher` ASC) VISIBLE,
    INDEX         `FK_CUSV_CUS_idx` (`id_customer` ASC) VISIBLE,
    CONSTRAINT `FK_CUSV_VOU`
        FOREIGN KEY (`id_voucher`)
            REFERENCES `double_shop`.`voucher` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_CUSV_CUS`
        FOREIGN KEY (`id_customer`)
            REFERENCES `double_shop`.`customer` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE `double_shop`.`address`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `id_customer`  BIGINT      NOT NULL,
    `district`     VARCHAR(45) NOT NULL,
    `province`     VARCHAR(45) NOT NULL,
    `city`         VARCHAR(45) NOT NULL,
    `description`  VARCHAR(45) NOT NULL,
    `is_defaul`    INT         NOT NULL,
    `created_by`   VARCHAR(45) NOT NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_by`   VARCHAR(45) NULL,
    `updated_time` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX          `FK_ADD_CUS_idx` (`id_customer` ASC) VISIBLE,
    CONSTRAINT `FK_ADD_CUS`
        FOREIGN KEY (`id_customer`)
            REFERENCES `double_shop`.`customer` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE `double_shop`.`product`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `code`         VARCHAR(45) NOT NULL,
    `images`       VARCHAR(255),
    `name`         VARCHAR(45) NOT NULL,
    `status`       INT         NOT NULL,
    `created_by`   VARCHAR(45) NOT NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_time` VARCHAR(45) NULL,
    `updated_by`   VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);

CREATE TABLE `double_shop`.`review`
(
    `id`            BIGINT      NOT NULL AUTO_INCREMENT,
    `id_customer`   BIGINT      NOT NULL,
    `id_product`    BIGINT      NOT NULL,
    `product_type`  VARCHAR(45) NOT NULL,
    `product_color` VARCHAR(45) NOT NULL,
    `rating`        INT         NOT NULL,
    `comment`       VARCHAR(145) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX           `FK_REVIEW_CUS_idx` (`id_customer` ASC) VISIBLE,
    INDEX           `FK_REVIEW_PRODUCT_idx` (`id_product` ASC) VISIBLE,
    CONSTRAINT `FK_REVIEW_CUS`
        FOREIGN KEY (`id_customer`)
            REFERENCES `double_shop`.`customer` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_REVIEW_PRODUCT`
        FOREIGN KEY (`id_product`)
            REFERENCES `double_shop`.`product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `double_shop`.`detail_product`
(
    `id`           BIGINT      NOT NULL AUTO_INCREMENT,
    `id_color`     BIGINT      NOT NULL,
    `id_product`   BIGINT      NOT NULL,
    `id_size`      BIGINT      NOT NULL,
    `id_brand`     BIGINT      NOT NULL,
    `id_collar`    BIGINT      NOT NULL,
    `id_category`  BIGINT      NOT NULL,
    `id_material`  BIGINT      NOT NULL,
    `quantity`     BIGINT      NOT NULL,
    `status`       INT         NOT NULL,
    `created_by`   VARCHAR(45) NOT NULL,
    `created_time` VARCHAR(45) NOT NULL,
    `updated_by`   VARCHAR(45) NULL,
    `updated_time` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    INDEX          `FK_DT_COLOR_idx` (`id_color` ASC) VISIBLE,
    INDEX          `FK_DT_PRO_idx` (`id_product` ASC) VISIBLE,
    INDEX          `FK_DT_SIZE_idx` (`id_size` ASC) VISIBLE,
    INDEX          `FK_DT_BRAND_idx` (`id_brand` ASC) VISIBLE,
    INDEX          `FK_DT_CATE_idx` (`id_category` ASC) VISIBLE,
    INDEX          `FK_DT_COLLAR_idx` (`id_collar` ASC) VISIBLE,
    INDEX          `FK_DT_MT_GT_idx` (`id_collar` ASC) VISIBLE,
    CONSTRAINT `FK_DT_COLOR`
        FOREIGN KEY (`id_color`)
            REFERENCES `double_shop`.`color` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_DT_PRO`
        FOREIGN KEY (`id_product`)
            REFERENCES `double_shop`.`product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_DT_SIZE`
        FOREIGN KEY (`id_size`)
            REFERENCES `double_shop`.`size` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_DT_BRAND`
        FOREIGN KEY (`id_brand`)
            REFERENCES `double_shop`.`brand` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_DT_COLLAR`
        FOREIGN KEY (`id_collar`)
            REFERENCES `double_shop`.`collar` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_DT_CATE`
        FOREIGN KEY (`id_category`)
            REFERENCES `double_shop`.`category` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_DT_MATERIAL`
        FOREIGN KEY (`id_material`)
            REFERENCES `double_shop`.`material` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `double_shop`.`bill`
(
    `id`             BIGINT      NOT NULL AUTO_INCREMENT,
    `id_customer`    BIGINT      NOT NULL,
    `id_employee`    BIGINT      NOT NULL,
    `id_voucher`     BIGINT NULL,
    `code`           VARCHAR(45) NOT NULL,
    `phone`          VARCHAR(45) NOT NULL,
    `order_date`     VARCHAR(45) NOT NULL,
    `discount_amout` BIGINT NULL,
    `total_amount`   BIGINT      NOT NULL,
    `confirm_date`   VARCHAR(45) NOT NULL,
    `ship_date`      VARCHAR(45) NULL,
    `receive_date`   VARCHAR(45) NULL,
    `note`           VARCHAR(45) NULL,
    `payment`        VARCHAR(45) NULL,
    `money_ship`     BIGINT NULL,
    `status`         INT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX            `FK_BILL_customer_idx` (`id_customer` ASC) VISIBLE,
    INDEX            `FK_BILL_employee_idx` (`id_employee` ASC) VISIBLE,
    INDEX            `FK_BILL_voucher_idx` (`id_voucher` ASC) VISIBLE,
    CONSTRAINT `FK_BILL_customer`
        FOREIGN KEY (`id_customer`)
            REFERENCES `double_shop`.`customer` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_BILL_employee`
        FOREIGN KEY (`id_employee`)
            REFERENCES `double_shop`.`employee` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_BILL_voucher`
        FOREIGN KEY (`id_voucher`)
            REFERENCES `double_shop`.`voucher` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `double_shop`.`cart`
(
    `id`         BIGINT NOT NULL AUTO_INCREMENT,
    `id_product` BIGINT NOT NULL,
    `quantity`   BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX        `FK_CART_DTPRO_idx` (`id_product` ASC) VISIBLE,
    CONSTRAINT `FK_CART_DTPRO`
        FOREIGN KEY (`id_product`)
            REFERENCES `double_shop`.`detail_product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE `double_shop`.`detail_bill`
(
    `id`                BIGINT NOT NULL AUTO_INCREMENT,
    `id_bill`           BIGINT NOT NULL,
    `id_detail_product` BIGINT NOT NULL,
    `quantity`          BIGINT NOT NULL,
    `price`             BIGINT NOT NULL,
    `status`            INT    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX               `FK_PRO_BILL_idx` (`id_bill` ASC) VISIBLE,
    INDEX               `FK_PRO_DP_idx` (`id_detail_product` ASC) VISIBLE,
    CONSTRAINT `FK_PRO_BILL`
        FOREIGN KEY (`id_bill`)
            REFERENCES `double_shop`.`bill` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `FK_PRO_DP`
        FOREIGN KEY (`id_detail_product`)
            REFERENCES `double_shop`.`detail_product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `double_shop`.`promotion`
(
    `id`               BIGINT AUTO_INCREMENT PRIMARY KEY,
    `code`             VARCHAR(255),
    `name`             VARCHAR(255),
    `discount_amount` BIGINT,
    `discount_percent` INT,
    `status`           INT,
    `start_date`       DATE,
    `end_date`         DATE,
    `created_by`       VARCHAR(255) NOT NULL,
    `updated_by`       VARCHAR(255),
    `created_time`     VARCHAR(255) NOT NULL,
    `updated_time`     VARCHAR(255),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
);

CREATE TABLE `double_shop`.`detail_promotion`
(
    `id`                BIGINT NOT NULL,
    `id_detail_product` BIGINT NULL,
    `id_promotion`      BIGINT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `iddetail_promotion_UNIQUE` (`id` ASC) VISIBLE,
    INDEX               `id_promotion_idx` (`id_promotion` ASC) VISIBLE,
    INDEX               `id_detail_product_idx` (`id_detail_product` ASC) VISIBLE,
    CONSTRAINT `id_promotion`
        FOREIGN KEY (`id_promotion`)
            REFERENCES `double_shop`.`promotion` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `id_detail_product`
        FOREIGN KEY (`id_detail_product`)
            REFERENCES `double_shop`.`detail_product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

ALTER TABLE `double_shop`.`detail_promotion`
    CHANGE COLUMN `id` `id` BIGINT NOT NULL AUTO_INCREMENT;

CREATE TABLE `double_shop`.`bill_history`
(
    `id` BIGINT       NOT NULL AUTO_INCREMENT,
    `id_bill`         BIGINT       NOT NULL,
    `description`     VARCHAR(245) NOT NULL,
    `created_by`      VARCHAR(45)  NOT NULL,
    `created_time`    VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`id`),
    INDEX             `FK_HIS_BILL_idx` (`id_bill` ASC) VISIBLE,
    CONSTRAINT `FK_HIS_BILL`
        FOREIGN KEY (`id_bill`)
            REFERENCES `double_shop`.`bill` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
