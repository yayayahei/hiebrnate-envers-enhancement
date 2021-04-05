create table persons (
    id bigint(5) not null auto_increment primary key,
    first_name varchar(20) null,
    last_name varchar(20) null,
    full_name varchar(40) null,
    nick_name varchar(20) null,
    pseudonym varchar(20) null
)