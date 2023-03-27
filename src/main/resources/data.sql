-- ------------------- Users ---------------------

INSERT INTO users (username, email, password, enabled, apikey)
VALUES ('joe', 'novi@gmail.com', '$2a$12$CkwV4HxJXUpLhNa4.LX2a.maAZHf5ZdCiAN2b5cBkP72cI1QFWG2y', TRUE,
        'mitdftd'); --    password    joe
INSERT INTO users (username, email, password, enabled, apikey)
VALUES ('michel', 'mike@gmail.com', '$2a$12$S9nJ5wbCpVn7BKicdecHJeERL3FMMvu2UVfe0sqYOxYN6JPX2ui7C', TRUE,
        'uhtfuiijh'); --    password      my
INSERT INTO users (username, email, password, enabled, apikey)
VALUES ('jimMy', 'curry@novi.nl', '$2a$12$d6kA7k7/sl4I2V7K6leNAeiuDTJW/fXmLeVfDRK9XoH959efyDazC', TRUE,
        'utdsa'); --    password    jim
INSERT INTO users (username, email, password, enabled, apikey)
VALUES ('angeLe', 'coleA@gmail.com', '$2a$12$M2yHmP3lO0805ZONK1aBtu6oJWAzio6EJfBiYZsEV1/ZU2VPUNi9.', TRUE,
        'hgfdfyu'); --    password    angel
INSERT INTO users (username, email, password, enabled, apikey)
VALUES ('poeAlan', 'crow@live.com', '$2a$12$VT6PI8R0iv9CIJ1l0vx/T.1D.s4BtGvucPMmlSZ6k2NJfLtKgEKUS', TRUE, 'drseedr');
--    password  angel

-- ----------------- Authorities ---------------------------

INSERT INTO authorities (username, authority)
VALUES ('joe', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('michel', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('jimMy', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('angeLe', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority)
VALUES ('poeAlan', 'ROLE_ADMIN');

-------------------- Accounts ------------------------------

INSERT INTO accounts (account_id, full_name, dob, iban, driving_license_number)
VALUES (8, 'Joe Doe', '1990-08-07', 'NLINGB928374748', 63930487);
INSERT INTO accounts (account_id, full_name, dob, iban, driving_license_number)
VALUES (10, 'Mike Anderson', '1968-10-01', 'NLINGB857205673', 025648392);
INSERT INTO accounts (account_id, full_name, dob, iban, driving_license_number)
VALUES (104, 'Jim Curry', '1990-09-30', 'NLINGB035295433', 85613284);
INSERT INTO accounts (account_id, full_name, dob, iban, driving_license_number)
VALUES (43, 'Angelina Cole', '1988-01-10', 'NLINGB035582549', 92650265);
INSERT INTO accounts (account_id, full_name, dob, iban, driving_license_number)
VALUES (51, 'Adgar Poe', '2000-12-12', 'NLINGB265419045', 9372050);
----------------------------------------------------------

UPDATE accounts
SET user_id = 'joe'
WHERE account_id = 8;
UPDATE accounts
SET user_id = 'michel'
WHERE account_id = 10;
UPDATE accounts
SET user_id = 'jimMy'
WHERE account_id = 104;
UPDATE accounts
SET user_id = 'angeLe'
WHERE account_id = 43;
UPDATE accounts
SET user_id = 'poeAlan'
WHERE account_id = 51;

-- ----------------- Memberships -------------------------
INSERT INTO membership (membership_id, costs, membership_start_date, membership_end_date, name, type, parking_included)
VALUES (7, 62.99, '2023-07-05', '2023-08-05', 'one month membership', 'one month', true);
INSERT INTO membership (membership_id, costs, membership_start_date, membership_end_date, name, type, parking_included)
VALUES (9, 12.99, '2023-02-25', '2023-02-26', 'one day membership', 'one day', false);
INSERT INTO membership (membership_id, costs, membership_start_date, membership_end_date, name, type, parking_included)
VALUES (13, 12.99, '2023-10-15', '2023-10-16', 'one day membership', 'one day', false);
INSERT INTO membership (membership_id, costs, membership_start_date, membership_end_date, name, type, parking_included)
VALUES (44, 587.99, '2023-03-01', '2024-03-01', 'one year membership', 'one year', true);
INSERT INTO membership (membership_id, costs, membership_start_date, membership_end_date, name, type, parking_included)
VALUES (103, 59.99, '2023-07-05', '2023-08-05', 'one month membership', 'one month', false);

---------------------------------------------------

UPDATE accounts
SET membership_id = 7
WHERE account_id = 8;
UPDATE accounts
SET membership_id = 9
WHERE account_id = 10;
UPDATE accounts
SET membership_id = 13
WHERE account_id = 104;
UPDATE accounts
SET membership_id = 44
WHERE account_id = 43;
UPDATE accounts
SET membership_id = 103
WHERE account_id = 51;

-- ------------- Vehicles -------------------
INSERT INTO vehicles (vehicle_id, brand, distance_range_without_charge, driving_license_required, is_rented, model,
                      type)
VALUES (3001, 'Mercedez-Benz', 350.00, true, false, 'EQS', 'ELECTRIC_CAR');
INSERT INTO vehicles (vehicle_id, brand, distance_range_without_charge, driving_license_required, is_rented, model,
                      type)
VALUES (3002, 'Volvo', 335.00, true, false, 'XC40 P8 Recharge', 'ELECTRIC_CAR');
INSERT INTO vehicles (vehicle_id, brand, distance_range_without_charge, driving_license_required, is_rented, model,
                      type)
VALUES (3003, 'Jaguar', 365.50, true, false, 'I-Pace', 'ELECTRIC_CAR');
INSERT INTO vehicles (vehicle_id, brand, distance_range_without_charge, driving_license_required, is_rented, model,
                      type)
VALUES (3011, 'IVA ', 65.50, true, false, 'E-GO S2', 'ELECTRIC_SCOOTER');
INSERT INTO vehicles (vehicle_id, brand, distance_range_without_charge, driving_license_required, is_rented, model,
                      type)
VALUES (3012, 'Senzo', 55.75, true, false, 'Balance', 'ELECTRIC_SCOOTER');
INSERT INTO vehicles (vehicle_id, brand, distance_range_without_charge, driving_license_required, is_rented, model,
                      type)
VALUES (3021, 'Aventon', 50.00, false, false, 'Balance', 'ELECTRIC_BICYCLE');

---------------------------------------------

UPDATE accounts SET vehicle_id = 3002 WHERE account_id = 8;

-- ------------- Garages --------------------

INSERT INTO garages (garage_name, country, zip_code)
VALUES ('Ams08', 'NETHERLANDS', '2078AB');
INSERT INTO garages (garage_name, country, zip_code)
VALUES ('Gnt10', 'BELGIUM', '8902JK');
INSERT INTO garages (garage_name, country, zip_code)
VALUES ('Ant01', 'BELGIUM', '9672MC');

---------------------------------------------

UPDATE vehicles
SET garage_name = 'Ams08'
WHERE vehicle_id = 3001;
UPDATE vehicles
SET garage_name = 'Ant01'
WHERE vehicle_id = 3003;
UPDATE vehicles
SET garage_name = 'Ant01'
WHERE vehicle_id = 3011;
UPDATE vehicles
SET garage_name = 'Ams08'
WHERE vehicle_id = 3012;
UPDATE vehicles
SET garage_name = 'Gnt10'
WHERE vehicle_id = 3021;

