--
-- Drop database and user if exists.
--
DROP DATABASE IF EXISTS "web-blog";
DROP USER IF EXISTS admin;

--
-- Create database and setup user.
--
CREATE DATABASE "web-blog";
CREATE USER admin WITH PASSWORD 'admin';
grant all privileges on database "web-blog" to admin;

--
-- Select database
--
\connect web-blog;