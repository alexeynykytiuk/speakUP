create table teacher(
    id serial primary key,
    user_name varchar(40),
    password varchar(200),
    first_name varchar(40),
    last_name varchar(40),
    role varchar(40)
);

create table groups(
    id serial primary key,
    name varchar(100),
    price int,
    teacher_id int,
    foreign key (teacher_id) references teacher(id) on delete cascade
);

create table report(
    id serial primary key,
    dates date,
    total_price int,
    number_of_lessons int,
    teacher_id int,
    foreign key (teacher_id) references teacher(id) on delete cascade
);

create table group_report(
    id serial primary key,
    group_id int,
    report_id int,
    foreign key (group_id) references groups(id) on delete cascade,
    foreign key (report_id) references report(id) on delete cascade
)

