CREATE TABLE room(
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    location VARCHAR(150) NOT NULL,
    max_seats INT NOT NULL CHECK (max_seats > 0)
);

CREATE TABLE conference(
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    date_time TIMESTAMP NOT NULL,
    is_canceled BIT NOT NULL,
    room_id INT REFERENCES room (id)
);

CREATE TABLE participant(
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    conference_id INT REFERENCES conference (id)
);