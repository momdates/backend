DELETE
FROM experiences;

DELETE
FROM userexperiences;

INSERT INTO roles (roleid, name)
    VALUES (1, 'user');

INSERT INTO users (userid, username, email, name, password, location, zip)
    VALUES (1, 'test@test.com', 'test@test.com', 'Austin powell', '$2a$10$TqZ96X94hUEyu3F6vrSxJeIufQNRT7f7fZmWXTzK3uDqZGBCIrV/G', 'test', '55555');

INSERT INTO userroles (userid, roleid)
    VALUES (1, 1);

    INSERT INTO experiences (expid, title, explocation, kids, dates, blurb, availability, expdesc, expimgurl, price)
        VALUES (1, 'test1', 'location1', 'kid free','1/2/2019', 'short blurb here', '5 spots', 'short description here','image.url', 1.00),
        (2, 'test2', 'location2', 'kid free','1/2/2019', 'short blurb here2', '52 spots', 'short description here2','image.url2', 2.00),
        (3, 'test3', 'location3', 'id friendly','1/2/2019', 'short blurb here3', '53 spots', 'short description here3','image.url3', 3.00),
        (4, 'test4', 'location4', 'kid free','1/2/2019', 'short blurb here4', '54 spots', 'short description here4','image.url4', 4.00);

        INSERT INTO userexperiences (userid, expid)
        VALUES (1, 1);

alter sequence hibernate_sequence restart with 20;