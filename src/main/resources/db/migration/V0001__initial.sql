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
    uuid       uuid
);
INSERT INTO users (email, password, name, photo) VALUES('john@gmail.com', '12345', 'John', 'https://robohash.org/68.186.255.198.png');
INSERT INTO users (email, password, name, photo) VALUES('mary@gmail.com', '11111', 'Mary', 'https://images.pexels.com/photos/2913125/pexels-photo-2913125.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
INSERT INTO users (email, password, name, photo) VALUES('tom@gmail.com', 'qwert', 'Tom', 'https://i.pinimg.com/736x/90/f7/a4/90f7a49893bc987858e13e10ffc72a23.jpg');
INSERT INTO users (email, password, name, photo) VALUES('anna@gmail.com', '22222', 'Anna', 'https://i.pinimg.com/736x/90/f7/a4/90f7a49893bc987858e13e10ffc72a23.jpg');

create table if not exists messages
(
    id          serial
        primary key,
    created_at  timestamp default now() not null,
    text        text                    not null,
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


INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 17:46:20.000000','Byy',4,1);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 17:43:58.000000','How are you ?',1,4);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 17:45:16.000000','nice\n Are you fine ?',4,1);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 17:45:40.000000','Yes always',1,4);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 17:45:55.000000','https://nicesnippets.com/',4,1);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 17:45:57.000000','Byy',4,1);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 17:46:08.000000','https://nicesnippets.com',4,1);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 17:46:12.000000','Byy',4,1);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 17:46:16.000000','https://nicesnippets.com/',4,1);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 17:46:20.000000','Byy',4,1);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-01 12:48:51.000000','Hi',4,2);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-04 17:49:05.000000','Hello',2,4);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-07 17:49:15.000000','Hello\n How are you?',4,3);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 09:01:26.000000','Hello',3,4);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 09:02:26.000000','Fine',3,4);
INSERT INTO messages(created_at, text, sender_id, receiver_id) VALUES('2023-05-08 09:01:26.000000','And you?',3,4);
