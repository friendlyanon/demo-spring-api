CREATE TABLE IF NOT EXISTS `hashes`
(
    `id`        INT UNSIGNED NOT NULL,
    `pattern`   INT          NOT NULL,
    `extension` VARCHAR(15)  NOT NULL,
    `hash`      BINARY(16)   NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `hash` (`hash`)
);
