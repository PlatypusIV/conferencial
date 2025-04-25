CREATE TABLE participant(
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL
);

CREATE TABLE conference(
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    date_time TIMESTAMP NOT NULL,
    location SERIAL,
    FOREIGN KEY (location)
    REFERENCES room (id)
);