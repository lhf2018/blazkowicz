CREATE TABLE IF NOT EXISTS tb_blazkowicz_rule
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    rule_id         BIGINT       NOT NULL,
    version         INT          NOT NULL DEFAULT 0,
    rule_name       VARCHAR(255) NOT NULL,
    conditions      TEXT         NOT NULL,
    logic           VARCHAR(255) NOT NULL,
    left_param_type VARCHAR(63),
    UNIQUE KEY uk_rule_id (rule_id),
    UNIQUE KEY uk_rule_name (rule_name)
);

CREATE TABLE IF NOT EXISTS tb_blazkowicz_strategy
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    strategy_id       BIGINT       NOT NULL,
    version           INT          NOT NULL DEFAULT 0,
    business_identity VARCHAR(63)  NOT NULL,
    prevention_type   VARCHAR(63)  NOT NULL,
    name              VARCHAR(255) NOT NULL,
    description       VARCHAR(512),
    introduced_rule_id BIGINT      NOT NULL,
    disposal_config   TEXT,
    UNIQUE KEY uk_strategy (business_identity, prevention_type, name),
    KEY idx_strategy_lookup (business_identity, prevention_type, introduced_rule_id)
);

CREATE TABLE IF NOT EXISTS tb_blazkowicz_event
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    event_id      BIGINT       NOT NULL,
    version       INT          NOT NULL DEFAULT 0,
    name          VARCHAR(255) NOT NULL,
    code          VARCHAR(127) NOT NULL,
    last_operator VARCHAR(127),
    description   TEXT,
    access_method VARCHAR(63)  NOT NULL,
    features      TEXT,
    strategy_pack TEXT,
    gmt_create    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    gmt_modified  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_event_id (event_id),
    UNIQUE KEY uk_event_code (code)
);
