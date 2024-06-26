ALTER TABLE brs.pet
    ADD COLUMN owner_id BIGINT NOT NULL;

ALTER TABLE brs.pet
    ADD CONSTRAINT pet_owner_fk FOREIGN KEY (owner_id) REFERENCES brs.owner (id);
