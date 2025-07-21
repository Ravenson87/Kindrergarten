CREATE TABLE `child_program` (
    `id` INT (11) NOT NULL AUTO_INCREMENT,
    `child_id` INT (11) NOT NULL,
    `program_id` INT (11) NOT NULL,
    `created_by` VARCHAR (255) DEFAULT NULL,
    `last_modified_by` VARCHAR (255) DEFAULT NULL,
    `created_date` datetime DEFAULT NULL,
    `last_modified_date` datetime DEFAULT NULL,
    PRIMARY KEY(`id`) USING BTREE,
    UNIQUE KEY `uq_child_program` (`child_id`, `program_id`) USING BTREE,
    CONSTRAINT `fk_child_program_v1` FOREIGN KEY (`child_id`) REFERENCES `child`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_child_program_v2` FOREIGN KEY (`program_id`) REFERENCES `programs`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;