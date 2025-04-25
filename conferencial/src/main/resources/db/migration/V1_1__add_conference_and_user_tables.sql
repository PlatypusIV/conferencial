CREATE TABLE public.participant(
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL
);

CREATE TABLE public.conference(
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    date_time TIMESTAMP NOT NULL,
    location INT,
    FOREIGN KEY (location)
    REFERENCES public.room (id)

);