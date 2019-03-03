create database docker_manger_system;
use docker_manger_system;

//用户数据表
create table user(
  phonenumber varchar(25) primary key ,
  name varchar(25) not null ,
  passwd varchar(25) not null
);
//连接主机IP集合
create table (
  ip varchar(25) primary key
)
//初始化账户
insert into user(phonenumber, name, passwd) VALUES (
    "17637938901",
    "ljy",
    "passwd"
);

