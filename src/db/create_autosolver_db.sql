CREATE USER autosolver WITH createdb password 'autosolver'
;
CREATE database autosolver WITH owner autosolver
;
\connect "dbname=autosolver user=autosolver password=autosolver"
;
CREATE SCHEMA IF NOT EXISTS autosolver AUTHORIZATION autosolver
;
