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
--INSERT INTO users (email, password, name, photo) VALUES('john@gmail.com', '12345', 'John', 'https://robohash.org/68.186.255.198.png');
--INSERT INTO users (email, password, name, photo) VALUES('mary@gmail.com', '11111', 'Mary', 'https://images.pexels.com/photos/2913125/pexels-photo-2913125.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
--INSERT INTO users (email, password, name, photo) VALUES('tom@gmail.com', 'qwert', 'Tom', 'https://i.pinimg.com/736x/90/f7/a4/90f7a49893bc987858e13e10ffc72a23.jpg');
--INSERT INTO users (email, password, name, photo) VALUES('anna@gmail.com', '22222', 'Anna', 'https://i.pinimg.com/736x/90/f7/a4/90f7a49893bc987858e13e10ffc72a23.jpg');

INSERT INTO users (email, password, name, photo) VALUES('john@gmail.com', '12345', 'John', 'https://robohash.org/68.186.255.198.png');
<<<<<<< HEAD
INSERT INTO users (email, password, name, photo) VALUES('mary@gmail.com', '11111', 'Mary', 'https://images.pexels.com/photos/2913125/pexels-photo-2913125.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
INSERT INTO users (email, password, name, photo) VALUES('tom@gmail.com', 'qwert', 'Tom', 'https://i.pinimg.com/736x/90/f7/a4/90f7a49893bc987858e13e10ffc72a23.jpg');
INSERT INTO users (email, password, name, photo) VALUES('anna@gmail.com', '22222', 'Anna', 'https://jsc-dorian-gray.com/wp-content/uploads/2019/10/Nice-Girl_2x3_211019_01.jpg');
INSERT INTO users (email, password, name, photo) VALUES('chewbacca@gmail.com', '33333', 'Chewbacca', 'https://static.wikia.nocookie.net/rustarwars/images/7/7f/ChewbaccaTFAHS-Fathead.png');
INSERT INTO users (email, password, name, photo) VALUES('ellie@gmail.com', '44444', 'Ellie', 'https://media-cldnry.s-nbcnews.com/image/upload/t_fit-1240w,f_auto,q_auto:best/rockcms/2023-01/230113-bella-ramsey-last-of-us-mn-1010-d6a81f.jpg');
INSERT INTO users (email, password, name, photo) VALUES('quentin@gmail.com', '55555', 'Quentin', 'https://flxt.tmsimg.com/assets/52431_v9_bb.jpg');
INSERT INTO users (email, password, name, photo) VALUES('wednesday@gmail.com', '55555', 'Wednesday', 'https://villagepipol.com/wp-content/uploads/2022/12/Wednesday-scaled.jpg');
=======
INSERT INTO users (email, password, name, photo) VALUES('mary@gmail.com', '11111', 'Mary', 'https://robohash.org/548.png?set=set4');
INSERT INTO users (email, password, name, photo) VALUES('tom@gmail.com', 'qwert', 'Tom', 'https://robohash.org/77.123.13.90.png');
INSERT INTO users (email, password, name, photo) VALUES('anna@gmail.com', '22222', 'Anna', 'https://robohash.org/7PD.png?set=set4');
INSERT INTO users (email, password, name, photo) VALUES('alex@gmail.com', '22222', 'Alex', 'https://robohash.org/12Z.png?set=set3');
INSERT INTO users (email, password, name, photo) VALUES('don@gmail.com', '22ss222', 'Don', 'https://robohash.org/SS0.png?set=set2');
INSERT INTO users (email, password, name, photo) VALUES('nora@gmail.com', '22222', 'Nora', 'https://robohash.org/591.png?set=set4');
INSERT INTO users (email, password, name, photo) VALUES('kevin@gmail.com', '22222', 'Kevin', 'https://robohash.org/tkitu');
INSERT INTO users (email, password, name, photo) VALUES('kity@gmail.com', '22222', 'Kity', 'https://robohash.org/kitys');
INSERT INTO users (email, password, name, photo) VALUES('harry@gmail.com', '22222', 'Harry', 'https://robohash.org/Y0C.png?set=set4');

>>>>>>> origin/dev

create table if not exists messages
(
    id          serial
        primary key,
--     created_at  timestamp default now() not null,
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
