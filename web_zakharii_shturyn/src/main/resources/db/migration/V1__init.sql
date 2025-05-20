CREATE TABLE employee (
    employee_id SERIAL PRIMARY KEY,
    employee_position VARCHAR(255),
    employee_salary INTEGER
);

CREATE TABLE meal (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    pricetopay INTEGER,
    pricetocook INTEGER,
    timetoprepare INTEGER,
    allergens INTEGER
);

INSERT INTO meal (name, pricetopay, pricetocook, timetoprepare, allergens)
VALUES
('Paperoni', 249, 100, 10 , 0),
('BBQ', 199, 100, 8, 0),
('Carbonara', 299, 140, 14, 1),
('Caesar', 189, 70, 8 , 1),
('Margarita', 149, 40, 10 , 0),
('Salami' , 139, 40, 8 , 0),
('4 Cheese' , 129, 60, 6 , 1),
('Cola' , 39, 40 , 2, 0),
('Juice', 99, 40, 2, 1),
('Water', 1, 0, 2, 0);

CREATE TABLE meal_order (
    id SERIAL PRIMARY KEY,
    start_time TIME,
    price_to_get INTEGER,
    time_to_prepare INTEGER
);