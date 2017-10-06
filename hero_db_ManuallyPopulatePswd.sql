use hero_db;

UPDATE `hero_db`.`users`
SET
`password` = '$2a$10$0Gcn6BZGakKu5K7I7VPdjusxvXatXd3.UqpC4OOVgMWYUMRqwiyBC'
WHERE `user_id` = 2;
SELECT * FROM hero_db.users;