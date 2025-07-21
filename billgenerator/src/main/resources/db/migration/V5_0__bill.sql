CREATE TABLE `bill` (
    `id` INT (11) NOT NULL AUTO_INCREMENT,
    `year` SMALLINT NOT NULL,
    `month` VARCHAR (255) NOT NULL,
    `deadline` datetime DEFAULT NULL,
    `kindergarten_id` INT (11) NOT NULL,
    `child_id` INT (11) NOT NULL,
    `created_by` VARCHAR (255) DEFAULT NULL,
    `last_modified_by` VARCHAR (255) DEFAULT NULL,
    `created_date` datetime DEFAULT NULL,
    `last_modified_date` datetime DEFAULT NULL,
    PRIMARY KEY(`id`) USING BTREE,
    CONSTRAINT `fk_bill_v1` FOREIGN KEY (`kindergarten_id`) REFERENCES `kindergarten`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_bill_v2` FOREIGN KEY (`child_id`) REFERENCES `child`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;