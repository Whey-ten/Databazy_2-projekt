
drop table if exists items cascade;
create table items (
    id serial primary key,
    name varchar,
    /*value varchar,*/
    rarity varchar,
    hp_stat numeric,
    power_stat numeric,
    defense_stat numeric,
    type varchar
);

drop table if exists heads cascade;
create table heads (
    id serial primary key,
    head_type varchar
);

drop table if exists bodies cascade;
create table bodies (
    id serial primary key,
    body_type varchar
);

drop table if exists hairs cascade;
create table hairs (
    id serial primary key,
    hair_style varchar
);

drop table if exists shirts cascade;
create table shirts (
    id serial primary key,
    shirt_style varchar
);

drop table if exists pants cascade;
create table pants (
    id serial primary key,
    pants_style varchar
);

drop table if exists races cascade;
create table races(
    id serial primary key,
    name varchar,
    lore varchar
);

drop table if exists classes cascade;
create table classes(
    id serial primary key,
    name varchar,
    info varchar,
    class_hp numeric,
    class_power numeric,
    class_defense numeric
);

drop table if exists skills cascade;
create table skills(
    id serial primary key,
    name varchar,
    type varchar,
    effectivity numeric
);

drop table if exists levels cascade;
create table levels(
    id serial primary key,
    exp_to_next_lvl numeric,
    upgrade_hp numeric,
    upgrade_power numeric,
    upgrade_defense numeric
);

drop table if exists players cascade;
create table players(
    id serial primary key,
    username varchar,
    first_name varchar,
    last_name varchar,
    email varchar,
    credits numeric
);

drop table if exists equip cascade;
create table equip(
    id serial primary key,
    weapon integer references items,
    armor integer references items
);

drop table if exists characters cascade;
create table characters(
    id serial primary key,
    player_id integer references players on delete cascade,
    name varchar,
    sex varchar,
    race_id integer references races on delete cascade,
    class_id integer references classes on delete cascade,
    c_exp numeric,
    level numeric,
    max_hp numeric,
    current_hp numeric,
    power numeric,
    defense numeric,
    is_alive boolean,
    head integer references heads on delete cascade,
    body integer references bodies on delete cascade,
    hair integer references hairs on delete cascade,
    shirt integer references shirts on delete cascade,
    pants integer references pants on delete cascade,
    equipment integer references equip on delete cascade

);

drop table if exists available cascade;
create table available(
    skill_id integer references skills on delete cascade,
    class_id integer references classes on delete cascade
);

drop table if exists possible_classes cascade;
create table possible_classes(
    race_id integer references races on delete cascade,
    class_id integer references classes on delete cascade
);

drop table if exists battle_log cascade;
create table battle_log(
    id serial primary key,
    winner integer references characters on delete cascade,
    looser integer references characters on delete cascade,
    time_of_battle timestamp
);

drop table if exists character_market cascade;
create table character_market(
    id serial primary key,
    id_buyer integer references players on delete cascade,
    id_seller integer references players on delete cascade,
    id_character integer references characters on delete cascade,
    time_of_purchase timestamp
);

drop table if exists credits_history cascade;
create table credits_history(
    id serial primary key,
    id_player integer references players on delete cascade,
    type varchar,
    amount numeric,
    date timestamp
);

drop table if exists goods cascade;
create table goods(
    id serial primary key,
    name varchar,
    description varchar,
    price numeric
);

drop table if exists ingame_store cascade;
create table ingame_store(
    id serial primary key,
    id_player integer references players on delete cascade,
    id_character integer references characters on delete cascade,
    id_product integer references goods on delete cascade,
    time_of_purchase date
);