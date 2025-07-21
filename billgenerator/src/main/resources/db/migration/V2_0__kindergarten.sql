CREATE TABLE `kindergarten` (
                                `id` INT (11) NOT NULL AUTO_INCREMENT,
                                `name` VARCHAR (255) NOT NULL,
                                `address` VARCHAR (255) NOT NULL,
                                `price` DECIMAL (10,2) DEFAULT NULL,
                                `pib` VARCHAR (255) NOT NULL,
                                `created_by` VARCHAR (255) DEFAULT NULL,
                                `last_modified_by` VARCHAR (255) DEFAULT NULL,
                                `created_date` datetime DEFAULT NULL,
                                `last_modified_date` datetime DEFAULT NULL,
                                PRIMARY KEY(`id`) USING BTREE,
                                UNIQUE KEY `pib` (`pib`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;