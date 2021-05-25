CREATE TABLE `client` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(60) NOT NULL,
    `email` varchar(255) NOT NULL,
    `phone` varchar(11) DEFAULT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email` ASC) VISIBLE);
)