INSERT INTO users (id, email,password, username)
VALUES ( 4,  'novi@gmail.com', 'joejoe', 'joe'),
       ( 24,  'mike@gmail.com', 'jdhfksjd', 'mikkie'),
       ( 27, 'curry@novi.nl', 'ksgjcjvr', 'jimMy'),
       ( 33, 'coleA@gmail.com', 'oh7dbhfi', 'angelLe'),
       ( 101, 'crow@live.com', 'kedhd7dj', 'poeAlan');



INSERT INTO membership (membership_id, costs, membership_start_date, membership_end_date, name, type, parking_included)
VALUES ( 7, 62.99 ,'2023-07-05', '2023-08-05','one month membership', 'one month', true ),
       ( 9, 12.99, '2023-02-25', '2023-02-26', 'one day membership', 'one day', false),
       ( 13, 12.99,'2023-10-15','2023-10-16', 'one day membership', 'one day', false ),
       ( 44, 587.99, '2023-03-01','2024-03-01', 'one year membership', 'one year', true),
       ( 103, 59.99, '2023-07-05', '2023-08-05','one month membership', 'one month', false);

INSERT INTO garages (garage_name, country, zip_code)
VALUES ('Ams08', 'NETHERLANDS', '2078AB'),
       ('Gnt10', 'BELGIUM', '8902JK'),
       ('Antw01', 'BELGIUM', '9672MC');

INSERT INTO vehicles (vehicle_id, brand, distance_range_without_charge, driving_license_required, is_rented, model, type, garage_name)
VALUES (3001, 'Mercedez-Benz', 350.00, true, false, 'EQS', 'ELECTRIC_CAR', 'Ams08'),
       (3002, 'Volvo', 335.00, true, false, 'XC40 P8 Recharge', 'ELECTRIC_CAR', 'Ams08'),
       (3003, 'Jaguar', 365.50, true, false, 'I-Pace', 'ELECTRIC_CAR', 'Antw01'),
       (3011, 'IVA ', 65.50, true, false, 'E-GO S2', 'ELECTRIC_SCOOTER', 'Antw01'),
       (3012, 'Senzo', 55.75, true, false, 'Balance', 'ELECTRIC_SCOOTER', 'Antw01'),
       (3021, 'Aventon', 50.00, false, false, 'Balance', 'ELECTRIC_BICYCLE', 'Gnt10');


INSERT INTO accounts (account_id, full_name, dob, iban, driving_license_number, membership_id, user_id , vehicle_id)
VALUES ( 8, 'Joe Doe','1990-08-07', 'NLINGB928374748', '63930487', 7, 4 , 3001),
       ( 10, 'Mike Anderson', '1968-10-01','NLINGB857205673', '025648392', 9, 24 , 3011),
       ( 104, 'Jim Curry', '1990-09-30', 'NLINGB035295433', '85613284', 13, 27, 3012),
       (43,  'Angelina Cole', '1988-01-10','NLINGB035582549', '92650265',  44, 33, 3003 ),
       ( 51,  'Adgar Poe','2000-12-12','NLINGB265419045', '12932018', 103, 101, 3002);
