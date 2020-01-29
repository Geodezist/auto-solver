INSERT INTO d_category(d_category_id, name, value) VALUES (1000, 'Легковые', 1);
INSERT INTO d_category(d_category_id, name, value) VALUES (2000, 'Мото', 2);

INSERT INTO d_mark(d_mark_id, d_category_id, name, value)	VALUES (1000, 1000, 'Acura', 98);
INSERT INTO d_mark(d_mark_id, d_category_id, name, value)	VALUES (2000, 1000, 'Toyota', 79);
INSERT INTO d_mark(d_mark_id, d_category_id, name, value)	VALUES (3000, 2000, 'Ducati', 1060);

INSERT INTO d_model(d_model_id, d_mark_id, name, value)	VALUES (1000, 2000, 'Camry', 698);
INSERT INTO d_model(d_model_id, d_mark_id, name, value)	VALUES (2000, 2000, 'Corolla', 702);
INSERT INTO d_model(d_model_id, d_mark_id, name, value)	VALUES (3000, 3000, 'Monster', 25656);

INSERT INTO d_bodystyle(d_bodystyle_id, d_category_id, name, value)	VALUES (1000, 1000, 'Седан', 3);
INSERT INTO d_bodystyle(d_bodystyle_id, d_category_id, name, value)	VALUES (2000, 1000, 'Внедорожник / Кроссовер', 5);
INSERT INTO d_bodystyle(d_bodystyle_id, d_category_id, name, value)	VALUES (3000, 2000, 'Мопеды', 58);

INSERT INTO d_drive_type(d_drive_type_id, d_category_id, name, value)	VALUES (1000, 1000, 'Полный', 1);
INSERT INTO d_drive_type(d_drive_type_id, d_category_id, name, value)	VALUES (2000, 1000, 'Передний', 2);
INSERT INTO d_drive_type(d_drive_type_id, d_category_id, name, value)	VALUES (3000, 2000, 'Цепь', 6);

INSERT INTO d_gearbox(d_gearbox_id, d_category_id, name, value)	VALUES (1000, 1000, 'Ручная / Механика', 1);
INSERT INTO d_gearbox(d_gearbox_id, d_category_id, name, value)	VALUES (2000, 1000, 'Автомат', 2);
INSERT INTO d_gearbox(d_gearbox_id, d_category_id, name, value)	VALUES (3000, 2000, 'Вариатор', 5);

INSERT INTO d_option(d_option_id, d_category_id, name, value)	VALUES (1000, 1000, 'ABD', 354);
INSERT INTO d_option(d_option_id, d_category_id, name, value)	VALUES (2000, 1000, 'ABS', 217);
INSERT INTO d_option(d_option_id, d_category_id, name, value)	VALUES (3000, 2000, 'Ветровое стекло', 402);

INSERT INTO d_country(d_country_id, name, value) VALUES (1000, 'Австрия', 40);
INSERT INTO d_country(d_country_id, name, value) VALUES (2000, 'Англия', 826);
INSERT INTO d_country(d_country_id, name, value) VALUES (3000, 'Япония', 392);

INSERT INTO d_fuel_type(d_fuel_type_id, name, value) VALUES (1000, 'Бензин', 1);
INSERT INTO d_fuel_type(d_fuel_type_id, name, value) VALUES (2000, 'Дизель', 2);
INSERT INTO d_fuel_type(d_fuel_type_id, name, value) VALUES (3000, 'Газ', 3);

INSERT INTO d_color(d_color_id, name, value) VALUES (1000, 'Бежевый', 1);
INSERT INTO d_color(d_color_id, name, value) VALUES (2000, 'Черный', 2);
INSERT INTO d_color(d_color_id, name, value) VALUES (3000, 'Синий', 3);

INSERT INTO d_state(d_state_id, name, value) VALUES (1000, 'Киевская', 10);
INSERT INTO d_state(d_state_id, name, value) VALUES (2000, 'Винницкая', 1);
INSERT INTO d_state(d_state_id, name, value) VALUES (3000, 'Волынская', 18);

INSERT INTO d_city(d_city_id, d_state_id, name, value) VALUES (1000, 1000, 'Киев', 10);
INSERT INTO d_city(d_city_id, d_state_id, name, value) VALUES (2000, 1000, 'Барышевка', 209);
INSERT INTO d_city(d_city_id, d_state_id, name, value) VALUES (3000, 2000, 'Винница', 1);

COMMIT;
