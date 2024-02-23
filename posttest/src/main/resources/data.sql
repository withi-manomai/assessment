-- lottery table
INSERT INTO lottery (ticket, price, amount) VALUES ('000111', 80, 5);
INSERT INTO lottery (ticket, price, amount) VALUES ('000222', 80, 10);
INSERT INTO lottery (ticket, price, amount) VALUES ('000333', 80, 15);
-- user_ticket table
INSERT INTO user_ticket (userID, ticket, amount) VALUES ('0000011111', '000111', 1);
INSERT INTO user_ticket (userID, ticket, amount) VALUES ('0000022222', '000222', 1);
INSERT INTO user_ticket (userID, ticket, amount) VALUES ('0000033333', '000333', 1);