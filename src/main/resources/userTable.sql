create table user_(
    user_id serial primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    phone_number varchar(15) not null
)