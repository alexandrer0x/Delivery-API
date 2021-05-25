CREATE TABLE `event` (
	`id` bigint NOT NULL AUTO_INCREMENT,
    `delivery_id` bigint NOT NULL,
	`description` text NOT NULL,
    `record_date` datetime NOT NULL,

    PRIMARY KEY(`id`),
    KEY `fk_delivery_id_idx` (`delivery_id`),
    CONSTRAINT `fk_delivery_id` FOREIGN KEY (`delivery_id`) REFERENCES `delivery` (`id`)
);