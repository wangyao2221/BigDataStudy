create table day_vedio_access_top_stat (
    day varchar(8) not null,
    cms_id bigint(10) not null,
    times bigint(10) not null,
    primary key (day, cms_id)
);

create table day_vedio_city_access_top_stat (
    day varchar(8) not null,
    cms_id bigint(10) not null,
    city varchar(20) not null,
    times bigint(10) not null,
    times_rank int not null,
    primary key (day, cms_id, city)
);