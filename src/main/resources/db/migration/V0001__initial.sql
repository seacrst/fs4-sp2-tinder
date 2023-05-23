create table if not exists users
(
    id         serial
        constraint id
            primary key,
    email    text not null
                    unique,
    password text not null,
    name       text,
    photo      text,
    uuid       text
);

INSERT INTO users (email, password, name, photo) VALUES('john@gmail.com', '12345', 'John', 'https://robohash.org/68.186.255.198.png');
INSERT INTO users (email, password, name, photo) VALUES('mary@gmail.com', '11111', 'Mary', 'https://robohash.org/548.png?set=set4');
INSERT INTO users (email, password, name, photo) VALUES('tom@gmail.com', 'qwert', 'Tom', 'https://robohash.org/77.123.13.90.png');
INSERT INTO users (email, password, name, photo) VALUES('anna@gmail.com', '22222', 'Anna', 'https://robohash.org/7PD.png?set=set4');
INSERT INTO users (email, password, name, photo) VALUES('alex@gmail.com', '43212', 'Alex', 'https://robohash.org/12Z.png?set=set3');
INSERT INTO users (email, password, name, photo) VALUES('don@gmail.com', '22ss222', 'Don', 'https://robohash.org/SS0.png?set=set2');
INSERT INTO users (email, password, name, photo) VALUES('nora@gmail.com', 'dffe55', 'Nora', 'https://robohash.org/591.png?set=set4');
INSERT INTO users (email, password, name, photo) VALUES('kevin@gmail.com', '2ddg222', 'Kevin', 'https://robohash.org/tkitu');
INSERT INTO users (email, password, name, photo) VALUES('kity@gmail.com', '840629', 'Kity', 'https://robohash.org/kitys');
INSERT INTO users (email, password, name, photo) VALUES('harry@gmail.com', 'trewq', 'Harry', 'https://robohash.org/Y0C.png?set=set4');


create table if not exists messages
(
    id          serial
        primary key,
    created_at  text                    not null,
    body        text                    not null,
    sender_id   integer                 not null
        constraint messages_sender_id_fk
            references public.users,
    receiver_id integer                 not null
        constraint messages_receiver_id_fk
            references public.users
);

create table if not exists liked
(
    id       serial
        primary key,
    user_id  integer not null
        constraint liked_user_id_fk
            references public.users,
    liked_user_id integer
            constraint like_users_id_fk2
                references public.users
);


INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 17:46:20.000000','Byy',4,1);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 17:43:58.000000','How are you ?',1,4);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 17:45:16.000000','nice. Are you fine ?',4,1);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 17:45:40.000000','Yes always',1,4);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 17:45:55.000000','https://nicesnippets.com/',4,1);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 17:45:57.000000','Byy',4,1);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 17:46:08.000000','https://nicesnippets.com',4,1);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 17:46:12.000000','Byy',4,1);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 17:46:16.000000','https://nicesnippets.com/',4,1);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 17:46:20.000000','Byy',4,1);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-01 12:48:51.000000','Hi',4,2);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-04 17:49:05.000000','Hello',2,4);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-07 17:49:15.000000','Hello\n How are you?',4,3);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 09:01:26.000000','Hello',3,4);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 09:02:26.000000','Fine',3,4);
INSERT INTO messages(created_at, body, sender_id, receiver_id) VALUES('2023-05-08 09:01:26.000000','And you?',3,4);
