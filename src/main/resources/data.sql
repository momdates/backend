DELETE
FROM experiences;

DELETE
FROM userexperiences;

INSERT INTO roles (roleid, name)
    VALUES (1, 'admin');

INSERT INTO users (userid, username, email, password, location, zip)
    VALUES (1, 'Austin powell', 'test@test.com', '$2a$10$TqZ96X94hUEyu3F6vrSxJeIufQNRT7f7fZmWXTzK3uDqZGBCIrV/G', 'test', '55555');

INSERT INTO userroles (userid, roleid)
    VALUES (1, 1);

    INSERT INTO experiences (expid, title, expdesc, explocation, price)
        VALUES (1, 'test', 'test description', 'test location', 1.00);

        INSERT INTO userexperiences (userid, expid)
        VALUES (1, 1);

alter sequence hibernate_sequence restart with 20;