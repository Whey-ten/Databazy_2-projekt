
truncate table items, equip, levels, classes, available, skills, races, possible_classes, characters, heads, bodies, hairs, shirts, pants, players, battle_log, character_market, credits_history, ingame_store, goods restart identity cascade;

---- pomocne tabulky na generovanie dat

-- first names

drop table if exists first_names cascade;
create table first_names
(
    first_name varchar
);

insert into first_names (first_name)
values	('James'), ('Willie'), ('Chad'), ('Zachary'), ('Mathew'),
	('John'), ('Ralph'), ('Jacob'), ('Corey'), ('Tyrone'),
	('Robert'), ('Lawrence'), ('Lee'), ('Herman'), ('Darren'),
	('Michael'), ('Nicholas'), ('Melvin'), ('Maurice'), ('Lonnie'),
	('William'), ('Roy'), ('Alfred'), ('Vernon'), ('Lance'),
	('David'), ('Benjamin'), ('Kyle'), ('Roberto'), ('Cody');

-- last names

drop table if exists last_names cascade;
create table last_names
(
    last_name varchar
);

insert into last_names (last_name)
values	('Smith'), ('Jones'), ('Taylor'), ('Williams'), ('Brown'),
	('Davies'), ('Evans'), ('Wilson'), ('Thomas'), ('Roberts'),
	('Johnson'), ('Lewis'), ('Walker'), ('Robinson'), ('Wood'),
	('Thompson'), ('White'), ('Watson'), ('Jackson'), ('Wright'),
	('Green'), ('Harris'), ('Cooper'), ('King'), ('Lee'),
	('Martin'), ('Clarke'), ('James'), ('Morgan'), ('Hughes'),
	('Edwards'), ('Hill'), ('Moore'), ('Clark'), ('Harrison'),
	('Scott'), ('Young'), ('Morris'), ('Hall'), ('Ward'),
	('Turner'), ('Carter'), ('Phillips'), ('Mitchell'), ('Patel'),
	('Adams'), ('Campbell'), ('Anderson'), ('Allen'), ('Cook');

create or replace function random_first_name() returns varchar language sql as
$$
select first_name from first_names tablesample system_rows(10) order by random() limit 1
$$;

create or replace function random_last_name() returns varchar language sql as
$$
select last_name from last_names tablesample system_rows(10) order by random() limit 1
$$;

-- #############################################################
insert into races (name, lore) values
    ('human', 'plain normal human'),
    ('demon', 'race born in hell, typically taller than humans'),
    ('dwarf', 'species usually found in mines, generally strong and short'),
    ('elf', 'close to human looks, love nature'),
    ('undead', 'legion usually made from skeletons or zombies'),
    ('angelic', 'natural enemies of demons, born from gods light'),
    ('beast_people', 'similar to humans but have features of beasts');

insert into classes (name, info, class_hp, class_power, class_defense) values
    ('fighter', 'Strong melee fighter with balanced stats.', 100, 45, 70),
    ('barbarian', 'Fierce warrior of primitive background who can deal deadly damage.', 110, 60, 45),
    ('monk', 'Master of martial arts, harness the power of body in pursuit of perfection.', 90, 60, 65 ),
    ('druid', 'Wielding power of nature and adopting animal forms.', 90,60, 60 ),
    ('cleric', 'A priestly champion who wields divine magic in service of higher power.', 90, 55, 70),
    ('paladin', 'A holy warrior bound to sacred oath.', 100, 50, 65),
    ('rogue', 'Sneaky scoundrel who uses darkness and trickery to overcome enemies.', 80, 80, 55),
    ('mage', 'Scholar that wields magic and is capable of destruction.', 75, 90, 50);

insert into heads(head_type) values
    ('top_hat'), ('hood'), ('face_mask_1'), ('face_mask_2'), ('face_mask_3'), ('tiara'), ('yarmulkes'),
    ('papakha'), ('ushanka'), ('amigasa'), ('war bonnet');

insert into bodies(body_type) values
    ('albino_thin'), ('asian_thin'), ('caucasian_thin'),
    ('albino_average'), ('asian_average'), ('caucasian_average'),
    ('albino_fat'), ('asian_fat'), ('caucasian_fat');

insert into hairs(hair_style) values
    ('bald'), ('navy_cut'), ('short_hair'), ('helmet_hair'), ('spiky_hair'),
    ('mohawk'), ('long_hair'), ('side_cut'), ('hair_bun'), ('chaos');

insert into shirts(shirt_style) values
    ('naked'), ('bra'), ('t-shirt'), ('cotton_shirt'), ('leather_jacket'), ('chain_mail'),
    ('mage_robes'), ('priest_robes'), ('black_cloth'), ('animal_fur');

insert into pants(pants_style) values
    ('underwear'), ('shorts'), ('long_pants'), ('animal_fur'), ('kilt'), ('skirt');

insert into levels(exp_to_next_lvl, upgrade_hp, upgrade_power, upgrade_defense) values
    (100, 1, 1, 1),
    (200, 1.2, 1.1, 1.1),
    (400, 1.3, 1.2, 1.2),
    (800, 1.3, 1.2, 1.2),
    (1600, 1.5, 1.5, 1.5),
    (3200, 1.6, 1.5, 1.5),
    (6400, 1.7, 1.5, 1.5),
    (12800, 2, 1.8, 1.8),
    (15600, 2.1, 1.9, 1.9),
    (31200, 2.2, 2, 2);

insert into goods(name, description, price) values
    ('new_head', 'You are able once again to choose new head', 100),
    ('new_body', 'You are able once again to choose new body', 100),
    ('new_hair', 'You are able once again to choose new hair style', 100),
    ('new_shirt', 'You are able once again to choose new shirt', 100),
    ('new_pants', 'You are able once again to choose new pants', 100);

insert into skills(name, type, effectivity) values
    ('slash', 'offensive', 1.2), ('punch', 'offensive', 1.05) , ('crush', 'offensive', 1.25),
    ('SUPER_DUPER_ULTIMATE_HIT_x_9000', 'offensive-ultimate', 2),
    ('shot', 'offensive', 1.1), ('double-shot', 'offensive', 1.2), ('head-shot', 'offensive', 1.25),
    ('ENCHANTED_CRYSTAL_ARROW', 'offensive-ultimate', 1.99),
    ('fire-strike', 'offensive', 1.1), ('water-bolt', 'offensive', 1.2), ('shadow-rush', 'offensive', 1.3),
    ('ELEMENTAL_AURORA', 'offensive-ultimate', 2.2),
    ('small-heal', 'support', 30), ('medium-heal', 'support', 60), ('MAX-heal', 'support', 75);

insert into items(name, rarity, hp_stat, power_stat, defense_stat, type) values
    ('wooden_sword', 'common', 0, 1.05, 0, 'weapon'), ('iron_sword', 'uncommon', 0, 1.075, 0, 'weapon'), ('silver_sword', 'rare', 0, 1.10, 0, 'weapon'),
    ('Aerondight', 'legendary', 0, 1.15, 0, 'weapon'),
    ('small_bow', 'common', 0, 1.06, 0, 'weapon'), ('bow', 'uncommon', 0, 1.08, 0, 'weapon'), ('crossbow', 'rare', 0, 1.11, 0, 'weapon'),
    ('Davids slingshot', 'legendary', 0, 1.16, 0, 'weapon'),
    ('walking_stick', 'common', 0, 1.06, 0, 'weapon'), ('stick_with_ruby', 'uncommon', 0, 1.08, 0, 'weapon'), ('ball_of_chaos', 'rare', 0, 1.11, 0, 'weapon'),
    ('pin_of_great_responsibility', 'legendary', 0, 1.20, 0, 'weapon'),
    ('chain_mail', 'common', 0, 0, 1.05,'armor'), ('iron_breastplate', 'uncommon', 0, 0, 1.075, 'armor'), ('full-body_defense', 'rare', 0, 0, 1.10, 'armor'),
    ('speedo_swim_trunks', 'legendary', 0, 0, 1.20, 'armor');

insert into possible_classes(race_id, class_id) values
    (1, 1), (1, 3), (1, 8),
    (2, 1), (2, 2), (2, 8),
    (3, 1), (3, 4), (3, 5),
    (4, 3), (4, 4), (4, 7),
    (5, 2), (5, 7), (5, 8),
    (6, 3), (6, 5), (6, 6),
    (7, 1), (7, 2), (7, 4);

insert into available(class_id, skill_id) values
    (1, 1), (1, 2), (1, 3), (1, 4),
    (2, 1), (2, 2), (2, 3), (2, 4),
    (3, 1), (3, 2), (3, 3), (3, 4),
    (4, 9), (4, 10), (4, 11), (4, 12),
    (5, 13), (5, 10), (5, 9), (5, 12),
    (6, 5), (6, 6), (6, 7), (6, 8),
    (7, 5), (7, 6), (7, 7), (7, 8),
    (8, 9), (8, 10), (8, 11), (8, 12);

insert into players(username, first_name, last_name, email, credits)
select  'player_' || i as username,
        random_first_name(),
        random_last_name(),
        'player_' || i ||'@gamemail.com' as email,
        ceil(random() * 10) * 100 as credits
from generate_series(1, 100000) as seq(i);

ALTER TABLE equip
    DROP CONSTRAINT equip_weapon_fkey,
    DROP CONSTRAINT equip_armor_fkey;

insert into equip(weapon, armor)
select  ceil(random() * 12) as weapon,
        floor(random()*(16-13+1))+13 as armor
from generate_series(1,500000) as seq(i);

ALTER TABLE equip
    ADD CONSTRAINT equip_weapon_fkey FOREIGN KEY (weapon) REFERENCES items ON DELETE CASCADE,
    ADD CONSTRAINT equip_armor_fkey FOREIGN KEY (armor) REFERENCES items ON DELETE CASCADE;

create or replace function random_player(dummy_in integer) returns table (id integer) language sql as
$$
select id from players tablesample system_rows(10) order by random() LIMIT 1
$$;

create or replace function random_race(dummy_in integer) returns table (id integer) language sql as
$$
select id from races tablesample system_rows(10) order by random() LIMIT 1
$$;

ALTER TABLE characters
    DROP CONSTRAINT characters_race_id_fkey,
    DROP CONSTRAINT characters_class_id_fkey,
    DROP CONSTRAINT characters_head_fkey,
    DROP CONSTRAINT characters_body_fkey,
    DROP CONSTRAINT characters_hair_fkey,
    DROP CONSTRAINT characters_shirt_fkey,
    DROP CONSTRAINT characters_pants_fkey,
    DROP CONSTRAINT characters_equipment_fkey;

DROP INDEX IF EXISTS character_race_index;
DROP INDEX IF EXISTS character_class_index;
DROP INDEX IF EXISTS character_player_index;

insert into characters(player_id, name, sex, race_id, class_id, c_exp, level, max_hp, current_hp, power, defense, is_alive, head, body, hair, shirt, pants, equipment)
select  FLOOR(random()*(100000-1+1))+1 as player_id,
        'beta_char ' || i as name,
        case floor(random() * 2)
            when 0 then 'male' when 1 then 'female'
        end as sex,
        /*ceil(random() * 7) as race_id,*/
        race_1 as race_id,
        (select class_id
         from possible_classes
         where race_1 = possible_classes.race_id
         order by random() limit 1) as class_id,
        /*NULL as class_id,*/
        0 as c_exp,
        1 as level,
        0 as max_hp,
        0 as current_hp,
        0 as power,
        0 as defense,
        true as is_alive,
        ceil(random() * 11) as head,
        ceil(random() * 9) as body,
        ceil(random() * 10) as hair,
        ceil(random() * 10) as shirt,
        ceil(random() * 6) as pants,
        i as equipment
from generate_series(1,500000) as seq(i),
lateral random_race(i) as race_1;
/*
lateral random_player(i) as player_1,
;
*/

ALTER TABLE characters
    ADD CONSTRAINT characters_race_id_fkey FOREIGN KEY (race_id) references races on delete cascade,
    ADD CONSTRAINT characters_class_id_fkey FOREIGN KEY (class_id) references classes on delete cascade,
    ADD CONSTRAINT characters_head_fkey FOREIGN KEY (head) references heads on delete cascade,
    ADD CONSTRAINT characters_body_fkey FOREIGN KEY (body) references bodies on delete cascade,
    ADD CONSTRAINT characters_hair_fkey FOREIGN KEY (hair) references hairs on delete cascade,
    ADD CONSTRAINT characters_shirt_fkey FOREIGN KEY (shirt) references shirts on delete cascade,
    ADD CONSTRAINT characters_pants_fkey FOREIGN KEY (pants) references pants on delete cascade,
    ADD CONSTRAINT characters_equipment_fkey FOREIGN KEY (equipment) references equip on delete cascade;


-- updaty v chare

update characters
set max_hp = c.class_hp,
    current_hp = c.class_hp,
    power = c.class_power,
    defense = c.class_defense
from classes as c
where characters.class_id = c.id;

CREATE INDEX character_race_index ON characters(race_id);
CREATE INDEX character_class_index ON characters(class_id);
CREATE INDEX character_player_index ON characters(player_id);

-- battle log
create or replace function random_character(dummy_in integer, id_x integer ) returns table (id integer ) language sql as
$$
select id from characters tablesample system_rows(10) WHERE id != id_x order by random() LIMIT 1
$$;

create or replace function random_timestamp(dummy_in integer) returns TIMESTAMP language sql as
$$
select
    (date '2021-01-01' + floor(random()*100)::integer)::timestamp
    + floor(random() * 24 ) * interval '1 hour' as timestamp;
$$;

DROP INDEX IF EXISTS battle_log_winner_index;
DROP INDEX IF EXISTS battle_log_looser_index;

ALTER TABLE battle_log
    DROP CONSTRAINT battle_log_winner_fkey,
    DROP CONSTRAINT battle_log_looser_fkey;
/*
insert into battle_log(winner, looser, time_of_battle)
select  character_1 as winner,
        random_character(i,character_1) as looser,
        /* random_timestamp(i) as time_of_battle, */
        NOW() - (random() * (interval '90 days'))
from generate_series(1,1000000) as seq(i),
lateral random_character(i,0) as character_1;
*/

insert into battle_log(winner, looser, time_of_battle)
select  FLOOR(random()*(500000-1+1))+1 as winner,
        FLOOR(random()*(500000-1+1))+1 as looser,
        random_timestamp(i) as time_of_battle
from generate_series(1,3000000) as seq(i);


ALTER TABLE battle_log
    ADD CONSTRAINT battle_log_winner_fkey FOREIGN KEY (winner) REFERENCES characters on delete cascade,
    ADD CONSTRAINT battle_log_looser_fkey FOREIGN KEY (looser) REFERENCES characters on delete cascade;

CREATE INDEX battle_log_winner_index ON battle_log(winner);
CREATE INDEX battle_log_looser_index ON battle_log(looser);


-- character market
DROP INDEX IF EXISTS market_buyer_index;
DROP INDEX IF EXISTS market_seller_index;
DROP INDEX IF EXISTS market_character_index;

ALTER TABLE character_market
    DROP CONSTRAINT character_market_id_buyer_fkey,
    DROP CONSTRAINT character_market_id_seller_fkey,
    DROP CONSTRAINT character_market_id_character_fkey;

insert into character_market(id_buyer, id_seller, id_character, time_of_purchase)
select  player_1 as id_buyer,
        player_2 as id_seller,
        character_1 as id_character,
        random_timestamp(i) as time_of_purchase
from generate_series(1,100000) as seq(i),
lateral random_player(i) as player_1,
lateral random_player(i) as player_2,
lateral random_character(i,0) as character_1
WHERE player_1 != player_2;

ALTER TABLE character_market
    ADD CONSTRAINT character_market_id_buyer_fkey FOREIGN KEY (id_buyer) references players on delete cascade,
    ADD CONSTRAINT character_market_id_seller_fkey FOREIGN KEY (id_seller) references players on delete cascade,
    ADD CONSTRAINT character_market_id_character_fkey FOREIGN KEY (id_character)references characters on delete cascade;

CREATE INDEX market_buyer_index ON character_market(id_buyer);
CREATE INDEX market_seller_index ON character_market(id_seller);
CREATE INDEX market_character_index ON character_market(id_character);

-- ingame store
create or replace function random_product(dummy_in integer ) returns table (id integer ) language sql as
$$
select id from goods order by random() LIMIT 1
$$;

DROP INDEX IF EXISTS store_player_index;
DROP INDEX IF EXISTS store_character_index;

ALTER TABLE ingame_store
    DROP CONSTRAINT ingame_store_id_player_fkey,
    DROP CONSTRAINT ingame_store_id_character_fkey,
    DROP CONSTRAINT ingame_store_id_product_fkey;

insert into ingame_store(id_player, id_character, id_product, time_of_purchase)
select  player_1 as id_player,
        character_1 as id_character,
        ceil(random() * 5) as id_product,
        random_timestamp(i) as time_of_purchase
from generate_series(1,50000) as seq(i),
lateral random_player(i) as player_1,
lateral random_character(i,0) as character_1;

ALTER TABLE ingame_store
    ADD CONSTRAINT ingame_store_id_player_fkey FOREIGN KEY (id_player) references players on delete cascade,
    ADD CONSTRAINT ingame_store_id_character_fkey FOREIGN KEY (id_character) references characters on delete cascade,
    ADD CONSTRAINT ingame_store_id_product_fkey FOREIGN KEY (id_product) references goods on delete cascade;

CREATE INDEX store_player_index ON ingame_store(id_player);
CREATE INDEX store_character_index ON ingame_store(id_character);

--credits history
DROP INDEX IF EXISTS credits_player_index;

ALTER TABLE credits_history
    DROP CONSTRAINT credits_history_id_player_fkey;

insert into credits_history(id_player, type, amount, date)
select  player_1 as id_player,
        case floor(random() * 2)
            when 0 then '+'
            when 1 then '-'
        end as type,
        case floor(random() * 4)
            when 0 then 100     when 2 then 400
            when 1 then 50      when 3 then 250
        end as amount,
        random_timestamp(i) as date
from generate_series(1, 10000) as seq(i),
lateral random_player(i) as player_1;

ALTER TABLE credits_history
        ADD CONSTRAINT credits_history_id_player_fkey FOREIGN KEY (id_player) references players on delete cascade;

CREATE INDEX credits_player_index ON credits_history(id_player);

-- konec mazanie
drop table first_names, last_names cascade;
drop function random_first_name();
drop function random_last_name();
drop function random_player(integer);
drop function random_timestamp(integer);
drop function random_character(integer, integer);
drop function random_product(integer);
drop function random_race(integer);