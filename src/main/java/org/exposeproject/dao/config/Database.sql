create database InjectionsProject;
use InjectionsProject;
create table uzer (
    id_user int not null auto_increment,
    username varchar(250) ,
    password varchar(250),
    primary key(id_user)
);
create table message (
 id_message INT NOT NULL AUTO_INCREMENT,
 message VARCHAR(500),
 fid_user int ,
 primary key(id_message),
 constraint fiduser foreign key(fid_user)
 references uzer(id_user)
 )
 
 select * from uzer u where u.id_user=1;
