create table Team
(
    id int auto_increment,
    upload_time time,
    primary key(id)
);

create table Member
(
    id int auto_increment,
    email char(50),
    pw char(50),
    name char(50),
    group_id int,
    connected boolean,
    primary key(id),
    FOREIGN KEY(group_id) REFERENCES Team(id)
);

create table Invitation(
    id int auto_increment,
    to_id int,
    from_id int,
    primary key (id)
);