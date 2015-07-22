drop database if exists lighter;
create database lighter;
alter database lighter default character set utf8 collate utf8_general_ci;
use lighter;
source E:\workspace\myserver\lighter\doc\lighter.sql

alter table ms_li_employee_role convert to character set utf8 collate utf8_general_ci;
source d:\lighter.sql
mysqldump -uroot -proot lighter > e:\lighter.sql
