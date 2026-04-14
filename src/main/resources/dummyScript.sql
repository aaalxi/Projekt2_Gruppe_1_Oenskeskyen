USE oenskeskyen;

# uncomment linjerne under, kør dem og recomment dem
# gå ind i databaseScript.sql, kør det en enkelt gang, kom tilbage og kør dette script igen
##########################################################################################
# DROP TABLE IF EXISTS reservation;
# DROP TABLE IF EXISTS wish;
# DROP TABLE IF EXISTS wish_list;
# DROP TABLE IF EXISTS users;
##########################################################################################

# users dummy data
INSERT INTO users(username, email, password, birthday, created_at)
VALUES(
          'Rune', 'test1@gmail.com', '1234', '1966-01-05', '2020-01-01'
      );
INSERT INTO users(username, email, password, birthday, created_at)
VALUES(
        'Nico', 'test2@hotmail.com', '1234', '2001-12-16', '2019-08-05'
      );
INSERT INTO users(username, email, password, birthday, created_at)
VALUES(
          'Daniel', 'test3@gmail.com', '1234', '1999-03-05', '2015-08-28'
      );
INSERT INTO users(username, email, password, birthday, created_at)
VALUES(
          'Ava', 'test4@gmail.com', '1234', '1999-03-05', '2015-08-28'
      );

# wishlist dummy data
INSERT INTO wish_list(id, user_id, title, share_token, created_at)
VALUES(
          1, 1, 'fødzz', 'ry82347', '2008-01-05 11:09:01'
      );
INSERT INTO wish_list(id, user_id, title, share_token, created_at)
VALUES(
          2, 2, 'øsnker', 'fnwu0n974yvvw', '2010-03-09 02:02:22'
      );
INSERT INTO wish_list(id, user_id, title, share_token, created_at)
VALUES(
          3, 3, 'randomting', 'jfwuieyr7874', '2026-07-02 03:05:59'
      );

# wish dummy data
INSERT INTO wish(id, wish_list_id, name, description, url, price, currency, priority, created_at)
VALUES(
          1, 1, 'bil', 'sort audi', 'audi.com', 66666.66, 'DKK',5, '2025-04-04'
      );
INSERT INTO wish(id, wish_list_id, name, description, url, price, currency, priority, created_at)
VALUES(
          2, 2, 'anders and bamse', 'anders and bamse', 'quackquackquack.dk', 50.00,'SEK',2, '2023-11-12'
      );
INSERT INTO wish(id, wish_list_id, name, description, url, price, currency, priority, created_at)
VALUES(
          3, 3, 'sokker', 'ønsker mig lyserøde nike sokker med pletter på', 'nike.com', 1,'GBP',4, '1972-10-25 11:10:09'
      );

# reservation dummy data
INSERT INTO reservation(id, wish_id, reserved_by_user_id, reserved_at)
VALUES(
          1, 1, 1, '1992-12-04 01:10:34'
      );
INSERT INTO reservation(id, wish_id, reserved_by_user_id, reserved_at)
VALUES(
          2, 2, 2, '1992-12-04'
      );
INSERT INTO reservation(id, wish_id, reserved_by_user_id, reserved_at)
VALUES(
          3, 3, 3, '2002-09-04'
      );