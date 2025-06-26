-- Insert countries
INSERT INTO country_master (country_id, country_name) VALUES (1, 'India');
INSERT INTO country_master (country_id, country_name) VALUES (2, 'USA');

-- Insert states
INSERT INTO state_master (state_id, state_name, country_id) VALUES (1, 'AP', 1);
INSERT INTO state_master (state_id, state_name, country_id) VALUES (2, 'TG', 1);
INSERT INTO state_master (state_id, state_name, country_id) VALUES (3, 'RI', 2);
INSERT INTO state_master (state_id, state_name, country_id) VALUES (4, 'NJ', 2);

-- Insert cities
INSERT INTO city_master (city_id, city_name, state_id) VALUES (1, 'Guntur', 1);
INSERT INTO city_master (city_id, city_name, state_id) VALUES (2, 'Ongole', 1);
INSERT INTO city_master (city_id, city_name, state_id) VALUES (3, 'Hyderabad', 2);
INSERT INTO city_master (city_id, city_name, state_id) VALUES (4, 'Warangal', 2);
INSERT INTO city_master (city_id, city_name, state_id) VALUES (5, 'Providence', 3);
INSERT INTO city_master (city_id, city_name, state_id) VALUES (6, 'New Port', 3);
INSERT INTO city_master (city_id, city_name, state_id) VALUES (7, 'Trenton', 4);
INSERT INTO city_master (city_id, city_name, state_id) VALUES (8, 'Newark', 4);