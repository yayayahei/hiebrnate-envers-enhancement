alter table persons
    add column last_operation_user varchar(20) null;

CREATE TABLE `revision_info` (
    `revision_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `rev_timestamp` BIGINT(20) NOT NULL
);

create table persons_audit (
    `revision_id` bigint(5) NOT NULL,
    id bigint(5) not null,
    `revision_type` TINYINT NOT NULL,
    first_name varchar(20) null,
    last_name varchar(20) null,
    full_name varchar(40) null,
    nick_name varchar(20) null,
    pseudonym varchar(20) null,
    last_operation_user varchar(20) null,

    `first_name_mod` boolean NULL,
    `last_name_mod` boolean NULL,
    `full_name_mod` boolean NULL,
    `nick_name_mod` boolean NULL,
    `pseudonym_mod` boolean NULL,
     last_operation_user_mod boolean null
)