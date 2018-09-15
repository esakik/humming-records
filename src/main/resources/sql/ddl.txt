drop table if exists members cascade;
CREATE TABLE members(
  id serial PRIMARY KEY
 ,name text NOT NULL
 ,email text NOT NULL UNIQUE
 ,password text NOT NULL
 ,address text NOT NULL
 ,telephone text NOT NULL
);

drop table if exists orders cascade;
CREATE TABLE orders(
  id serial PRIMARY KEY
 ,member_id Integer NOT NULL
 ,status Integer NOT NULL
 ,total_price Integer NOT NULL
 ,order_date Date
 ,delivery_name text
 ,delivery_email text
 ,delivery_address text
 ,delivery_telephone text
 ,delivery_time Timestamp
);

drop table if exists order_items cascade;
CREATE TABLE order_items(
  id serial PRIMARY KEY
 ,item_id Integer NOT NULL
 ,order_id Integer NOT NULL
 ,quantity Integer NOT NULL
);

drop table if exists items cascade;
CREATE TABLE items(
  id serial PRIMARY KEY
 ,singer text NOT NULL
 ,song text NOT NULL
 ,price Integer NOT NULL
 ,stock Integer NOT NULL
 ,amount Integer NOT NULL
 ,description text NOT NULL
 ,image text NOT NULL
);