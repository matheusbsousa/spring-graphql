INSERT INTO brs.owner (first_name, last_name, phone_number, email, address)
values ('John', 'Doe', '123-456-7890', 'john@email.com', '123 Main St'),
       ('Jane', 'Smith', '987-654-3210', 'jane@email.com', '456 Elm St'),
       ('Mike', 'Wilson', '555-555-5555', 'mike@email.com', '789 Cedar St');

INSERT INTO brs.pet (name, weight, breed, is_vaccinated, training_level, is_boat_rental_eligible, owner_id)
VALUES ('Buddy', 20.5, 'Labrador', true, 5, true, 1),
       ('Luna', 18.0, 'Golden Retriever', true, 4, true, 2),
       ('Max', 22.0, 'Dalmatian', true, 6, true, 3),
       ('Bailey', 17.5, 'Boxer', true, 3, true, 1),
       ('Rosie', 23.0, 'Border Collie', true, 7, true, 2),
       ('Charlie', 30.0, 'Labrador', true, 2, false, 2),
       ('Daisy', 10.0, 'Poodle', true, 1, false, 3),
       ('Milo', 26.0, 'Dalmatian', true, 4, false, 1),
       ('Rocky', 27.5, 'Poodle', true, 4, false, 1),
       ('Lola', 29.0, 'Border Collie', true, 3, false, 3);
