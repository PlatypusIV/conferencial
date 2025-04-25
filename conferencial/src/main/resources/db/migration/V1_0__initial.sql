CREATE TABLE public.room(
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    location VARCHAR(150) NOT NULL,
    max_seats INT NOT NULL CHECK (max_seats > 0)
);
