ALTER TABLE `kindergarten` ADD COLUMN account_number VARCHAR(255) DEFAULT NULL AFTER `price`;
ALTER TABLE `kindergarten` ADD CONSTRAINT `uq_account_number` UNIQUE (account_number);