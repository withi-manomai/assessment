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

-- lottery table
INSERT INTO lottery (ticket, price, amount) VALUES ('000111', 80, 5);
INSERT INTO lottery (ticket, price, amount) VALUES ('000222', 80, 10);
INSERT INTO lottery (ticket, price, amount) VALUES ('000333', 80, 15);
INSERT INTO lottery (ticket, price, amount) VALUES ('000444', 80, 0);
-- user_ticket table
INSERT INTO user_ticket (userID, ticket, amount) VALUES ('0000011111', '000111', 1);
INSERT INTO user_ticket (userID, ticket, amount) VALUES ('0000022222', '000222', 1);
INSERT INTO user_ticket (userID, ticket, amount) VALUES ('0000033333', '000333', 1);