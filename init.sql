CREATE DATABASE granteedb;
CREATE USER postgres WITH PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE "granteedb" to postgres;