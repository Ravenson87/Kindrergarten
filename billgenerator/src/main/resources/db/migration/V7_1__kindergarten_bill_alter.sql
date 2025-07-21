ALTER TABLE kindergarten_bill DROP FOREIGN KEY `fk_kindergarten_bill_v1`;
ALTER TABLE kindergarten_bill DROP FOREIGN KEY `fk_kindergarten_bill_v2`;
ALTER TABLE kindergarten_bill DROP INDEX `uq_kindergarten_bill`;
ALTER TABLE kindergarten_bill ADD CONSTRAINT uq_bill UNIQUE (`bill_id`);
ALTER TABLE kindergarten_bill ADD CONSTRAINT  `fk_kindergarten_bill_v3`
FOREIGN KEY (`kindergarten_id`) REFERENCES `kindergarten`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE kindergarten_bill ADD CONSTRAINT  `fk_kindergarten_bill_v4`
FOREIGN KEY (`bill_id`) REFERENCES `bill`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;