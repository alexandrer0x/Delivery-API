CREATE TABLE `delivery` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `client_id` bigint NOT NULL,
    `fee` decimal(10,2) NOT NULL,
    `status` varchar(20) NOT NULL,
    `request_date` datetime NOT NULL,
    `delivery_date` datetime DEFAULT NULL,

    `receiver_name` varchar(60) NOT NULL,
    `receiver_number` varchar(10) NOT NULL,
    `receiver_street` varchar(60) NOT NULL,
    `receiver_complement` varchar(60) DEFAULT NULL,
    `receiver_district` varchar(30) NOT NULL,

    PRIMARY KEY (`id`),
    KEY `fk_client_id_idx` (`client_id`),
    CONSTRAINT `fk_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
);