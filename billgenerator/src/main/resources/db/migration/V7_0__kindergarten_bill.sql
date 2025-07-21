CREATE TABLE `kindergarten_bill` (
    `id` INT (11) NOT NULL AUTO_INCREMENT,
    `kindergarten_id` INT (11) NOT NULL,
    `bill_id` INT (11) NOT NULL,
    `created_by` VARCHAR (255) DEFAULT NULL,
    `last_modified_by` VARCHAR (255) DEFAULT NULL,
    `created_date` datetime DEFAULT NULL,
    `last_modified_date` datetime DEFAULT NULL,
    PRIMARY KEY(`id`) USING BTREE,
    UNIQUE KEY `uq_kindergarten_bill` (`kindergarten_id`, `bill_id`) USING BTREE,
    CONSTRAINT `fk_kindergarten_bill_v1` FOREIGN KEY (`kindergarten_id`) REFERENCES `kindergarten`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_kindergarten_bill_v2` FOREIGN KEY (`bill_id`) REFERENCES `bill`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;