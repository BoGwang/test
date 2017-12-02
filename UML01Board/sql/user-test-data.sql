INSERT INTO user_type(id, type) VALUES (user_type_seq.NEXTVAL, 'ADMIN');
INSERT INTO user_type(id, type) VALUES (user_type_seq.NEXTVAL, 'USER');
INSERT INTO user_info(email, password, name, avatar) VALUES ('admin@gmail.com', '$2a$10$X871vx09nTEuvMtAZjs6XOhk3yMxPHtBVedqj8jHg4LfHGmHNRM6y', '관리자', null);
INSERT INTO user_info(email, password, name, avatar) VALUES ('seongyu@gmail.com', '$2a$10$X871vx09nTEuvMtAZjs6XOhk3yMxPHtBVedqj8jHg4LfHGmHNRM6y', '박선규', null);

INSERT INTO user_info_type(user_info_email, user_type_id) VALUES('admin@gmail.com', 1);
INSERT INTO user_info_type(user_info_email, user_type_id) VALUES('admin@gmail.com', 2);
INSERT INTO user_info_type(user_info_email, user_type_id) VALUES('seongyu@gmail.com', 2);