DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;

CREATE TABLE lottery (
                          id SERIAL PRIMARY KEY,
                          ticket VARCHAR(255) UNIQUE NOT NULL ,
                          price INTEGER NOT NULL,
                          amount INTEGER NOT NULL CHECK (amount >= 0)
);
CREATE TABLE user_ticket (
                         id SERIAL PRIMARY KEY,
                         userID VARCHAR(255) NOT NULL ,
                         ticket VARCHAR(255) REFERENCES lottery(ticket),
                         amount INTEGER NOT NULL
);

