CREATE TABLE vacancies(
    id bigserial,
    name varchar(200) not null,
    title varchar(200) not null,
    company_name varchar(200) not null,
    is_remote boolean,
    url varchar(200) not null,
    date date,
    city varchar(100) not null,
    seniority varchar(100) not null,
    rank integer,
    PRIMARY KEY (id)
);