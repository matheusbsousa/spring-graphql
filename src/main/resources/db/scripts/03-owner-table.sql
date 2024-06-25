CREATE SEQUENCE brs.owner_id_seq;

CREATE TABLE brs.owner
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('brs.owner_id_seq'),
    first_name   VARCHAR(20)     NOT NULL,
    last_name    VARCHAR(20)     NOT NULL,
    phone_number VARCHAR(20)     NOT NULL,
    email       VARCHAR(100)     NOT NULL,
    address     VARCHAR(120)     NOT NULL
);
