CREATE TABLE `child` (
    `id` INT (11) NOT NULL AUTO_INCREMENT,
    `full_name` VARCHAR (255) NOT NULL,
    `parent_name` VARCHAR (255) NOT NULL,
    `group` VARCHAR (255) NOT NULL,
    `kindergarten_id` INT (11) NOT NULL,
    `parent_email` VARCHAR (255) NOT NULL,
    `discount` TINYINT UNSIGNED DEFAULT 0,
    `sibling_position` TINYINT UNSIGNED DEFAULT 1,
    `status` BOOLEAN DEFAULT true,
    `created_by` VARCHAR (255) DEFAULT NULL,
    `last_modified_by` VARCHAR (255) DEFAULT NULL,
    `created_date` datetime DEFAULT NULL,
    `last_modified_date` datetime DEFAULT NULL,
    PRIMARY KEY(`id`) USING BTREE,
    CONSTRAINT `fk_child_v1` FOREIGN KEY (`kindergarten_id`) REFERENCES `kindergarten`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;