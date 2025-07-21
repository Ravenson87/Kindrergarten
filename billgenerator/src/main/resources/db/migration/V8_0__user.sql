CREATE TABLE `user` (
                                `id` INT (11) NOT NULL AUTO_INCREMENT,
                                `kindergarten_name` VARCHAR (255) NOT NULL,
                                `username` VARCHAR (255) NOT NULL,
                                `password` VARCHAR (255) NOT NULL,
                                `email` VARCHAR (255) NOT NULL,
                                `phone` VARCHAR(24) DEFAULT NULL,
                                `status` BOOLEAN DEFAULT true,
                                `link_expired` datetime DEFAULT NULL,
                                `created_by` VARCHAR (255) DEFAULT NULL,
                                `last_modified_by` VARCHAR (255) DEFAULT NULL,
                                `created_date` datetime DEFAULT NULL,
                                `last_modified_date` datetime DEFAULT NULL,
                                PRIMARY KEY(`id`) USING BTREE,
                                UNIQUE KEY `uq_username` (`username`) USING BTREE,
                                UNIQUE KEY `uq_password` (`password`) USING BTREE,
                                UNIQUE KEY `uq_email` (`email`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;