DROP TABLE user_info_type;
DROP TABLE user_type;
DROP TABLE user_info;
DROP SEQUENCE user_type_seq;

CREATE TABLE user_info (
	email VARCHAR2(320) NOT NULL,
	password VARCHAR2(60) NOT NULL,
	name VARCHAR2(20) NOT NULL,
	avatar VARCHAR2(255),
	PRIMARY KEY (email)
);

CREATE TABLE user_type(
	id NUMBER NOT NULL,
	type 	VARCHAR2(30) NOT NULL,
	PRIMARY KEY (id)
);

CREATE SEQUENCE user_type_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE user_info_type (
	user_info_email VARCHAR2(320) NOT NULL,
	user_type_id NUMBER NOT NULL,
	PRIMARY KEY (user_info_email, user_type_id),
	CONSTRAINT fk_user_info FOREIGN KEY (user_info_email) REFERENCES user_info(email),
	CONSTRAINT fk_user_type FOREIGN KEY (user_type_id) REFERENCES user_type(id)
);
SELECT * FROM user_info;
