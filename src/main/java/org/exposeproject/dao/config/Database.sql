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
 ); 
 
 create table Authentication (
 ip VARCHAR(500),
 tries int ,
 primary key(ip)
 );
ALTER TABLE injectionsproject.uzer 
ADD COLUMN bin INT(14) NULL AFTER password,
ADD COLUMN cvv INT(3) NULL AFTER bin,
ADD COLUMN dateExp VARCHAR(5) NULL AFTER cvv;
ALTER TABLE injectionsproject.uzer 
ADD COLUMN nomBanque Varchar(45)  AFTER dateExp ;
