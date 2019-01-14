INSERT INTO d_category(d_category_id, name, value) VALUES (1000, 'Легковые', 1);
INSERT INTO d_category(	d_category_id, name, value)	VALUES (2000, 'Мото', 2);

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

COMMIT;
