
create table if not exists rg_hotel
(
    id         bigserial not null
        primary key,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    name       varchar(128)
);

create table if not exists rg_hotel_room
(
    id         bigserial not null
        primary key,
    hotel_id bigint not null
        constraint fk_rg_hotel_room_hotel_id
            references rg_hotel,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    number     varchar(64)
);

create table if not exists rg_user
(
    created_at timestamp without time zone,
    id         bigserial
        primary key,
    updated_at timestamp without time zone,
    password   varchar(255),
    username   varchar(255)
        constraint uk_rg_user_username unique
);

create table if not exists rg_customer
(
    id         bigserial
        primary key,
    updated_at timestamp without time zone,
    created_at timestamp without time zone,
    user_id    bigint not null
        unique
        constraint fk_rg_customer_user_id
            references rg_user,
    phone      varchar(255)
);

create table if not exists rg_reservation
(
    id            bigserial
        primary key,
    paid          boolean not null,
    created_at    timestamp without time zone,
    customer_id   bigint  not null
        constraint fk_rg_reservation_customer_id
            references rg_customer,
    hotel_room_id bigint  not null
        constraint fk_rg_reservation_hotel_room_id
            references rg_hotel_room,
    updated_at    timestamp without time zone
);

create table if not exists rg_staff
(
    id         bigserial
        primary key,
    position   varchar(32),
    created_at timestamp without time zone,
    hotel_id   bigint not null
        constraint fk_rg_staff_hotel_id
            references rg_hotel,
    updated_at timestamp without time zone,
    user_id    bigint not null
        unique
        constraint fk_rg_staff_user_id
            references rg_user
);
