BEGIN TRANSACTION;

DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS beers;
DROP TABLE IF EXISTS breweries;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_review_id;
DROP SEQUENCE IF EXISTS seq_brewery_id;
DROP SEQUENCE IF EXISTS seq_beer_id;
DROP SEQUENCE IF EXISTS seq_user_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);


CREATE SEQUENCE seq_brewery_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE breweries (
    address character varying(200),
    description character varying(1000),
    city character varying(100),
    country character varying(5),
    brewery_id integer DEFAULT nextval('public.seq_brewery_id'::regclass) NOT NULL,
    lat character varying(50),
    long character varying(50),
    brewery_name character varying(1000) NOT NULL,
    phones character varying(200),
    zip character varying(20),
    province character varying(50),
    websites character varying(1000),
    brewery_img character varying(1000),
    owner_id integer,
    is_active boolean DEFAULT true
);
    CONSTRAINT PK_brewery PRIMARY KEY (brewery_id),
    CONSTRAINT FK_brewery_owner FOREIGN KEY (owner_id) REFERENCES users (user_id)
);


CREATE SEQUENCE seq_beer_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE beers (
    beer_id int DEFAULT nextval('seq_beer_id'::regclass) NOT NULL,
    beer_name varchar(50) NOT NULL,
    beer_img varchar(50) NOT NULL,
    description varchar(200),
    abv numeric(4, 2) NOT NULL,
    beer_type varchar(20) NOT NULL,
    brewery_id int NOT NULL,
    is_active boolean DEFAULT true,
    CONSTRAINT PK_beer PRIMARY KEY (beer_id),
    CONSTRAINT FK_brewery FOREIGN KEY (brewery_id) REFERENCES breweries (brewery_id)
);


CREATE SEQUENCE seq_review_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE reviews (
    review_id int DEFAULT nextval('seq_review_id'::regclass) NOT NULL,
    user_id int NOT NULL,
    beer_id int NOT NULL,
    rating int NOT NULL,
    review varchar(200),
    create_date timestamp DEFAULT NOW(),
    CONSTRAINT PK_review PRIMARY KEY (review_id),
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_beer FOREIGN KEY (beer_id) REFERENCES beers (beer_id)
);


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('brewer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');

COMMIT TRANSACTION;
