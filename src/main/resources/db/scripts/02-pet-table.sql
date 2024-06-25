CREATE SEQUENCE brs.pet_id_seq;

CREATE TABLE brs.pet
(
    id                      BIGINT PRIMARY KEY DEFAULT nextval('brs.pet_id_seq'),
    name                    VARCHAR(100)     NOT NULL,
    weight                  DOUBLE PRECISION NOT NULL,
    breed                   VARCHAR(100)     NOT NULL,
    is_vaccinated           BOOLEAN          NOT NULL,
    training_level          INTEGER          NOT NULL,
    is_boat_rental_eligible BOOLEAN          NOT NULL
);
