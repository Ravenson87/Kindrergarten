ALTER TABLE `kindergarten` ADD COLUMN kindergarten_phone VARCHAR(255) DEFAULT NULL AFTER `price`;
ALTER TABLE `kindergarten` ADD CONSTRAINT `uq_kindergarten_phone` UNIQUE (kindergarten_phone);