create database injectionsproject;
use injectionsproject;
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
 
 create table authentication (
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
ALTER TABLE injectionsproject.message 
ADD COLUMN SujetMessage VARCHAR(45) NULL AFTER fid_user;
ALTER TABLE injectionsproject.uzer 
ADD COLUMN photo LONGBLOB NULL AFTER nomBanque;
ALTER TABLE injectionsproject.uzer 
CHANGE COLUMN photo photo VARCHAR(255) NULL DEFAULT NULL ;
ALTER TABLE injectionsproject.authentication 
ADD COLUMN start BIGINT(250) NULL AFTER tries;
ALTER TABLE `injectionsproject`.`uzer` 
CHANGE COLUMN `bin` `bin` VARCHAR(14) NULL DEFAULT NULL ;


/*******USER ************/
INSERT INTO `injectionsproject`.`uzer` (`id_user`, `username`, `password`, `bin`, `cvv`, `dateExp`, `nomBanque`) VALUES ('1', 'ziad', 'ziad', '1548784916', '111', '11/25', 'bmce');
INSERT INTO `injectionsproject`.`uzer` (`id_user`, `username`, `password`, `bin`, `cvv`, `dateExp`, `nomBanque`) VALUES ('2', 'ayman', 'ayman', '1817191316', '222', '11/24', 'bmce');
INSERT INTO `injectionsproject`.`uzer` (`username`, `password`, `bin`, `cvv`, `dateExp`, `nomBanque`) VALUES ('amine', 'amine', '1122334455', '333', '11/23', 'cih');
INSERT INTO `injectionsproject`.`uzer` (`username`, `password`, `bin`, `cvv`, `dateExp`, `nomBanque`) VALUES ('anass', 'anass', '1817191613', '354', '11/22', 'cih');
INSERT INTO `injectionsproject`.`uzer` (`username`, `password`, `bin`, `cvv`, `dateExp`, `nomBanque`) VALUES ('hawat', 'hawat', '1155226655', '200', '22/25', 'bmce');
INSERT INTO `injectionsproject`.`uzer` (`username`, `password`, `bin`, `cvv`, `dateExp`, `nomBanque`) VALUES ('redmen', 'redmen', '2000000008', '669', '22/25', 'cih');
/************MESSAGE********/
INSERT INTO `injectionsproject`.`message` (`id_message`, `message`, `fid_user`, `SujetMessage`) VALUES
 ('1', 'Tous les exemples fourniront un code volontairement vuln�rable, l�exploitation de ce dernier', '1', 'AIDE ?');
 
 INSERT INTO `injectionsproject`.`message` (`message`, `fid_user`, `SujetMessage`) VALUES
 ('Pr�f�riez vous l\'alternative ?', '2', 'REP MESS 1');

  INSERT INTO `injectionsproject`.`message` (`message`, `fid_user`, `SujetMessage`) VALUES
 ('est-il possible de co-emprunter avec une autre personne (son enfant, un ami, un coll�gue...) 
 pour un achat immobilier o� cette personne sera la seule propri�taire du bien achet�?', '13', 'est ce que c est  Possible');
 
 INSERT INTO `injectionsproject`.`message` (`message`, `fid_user`, `SujetMessage`) VALUES ('Locataires nous souhaitons faire construire et avons sign� une promesse de vente pour terrain + construction dessus. C\'est un lotissement donc il y a d\'abord la cr�ation du lotissement avant que l\'on 
 puisse d�poser le permis de construire...ce qui devrait se faire en mars 2022. ', '15', 'forum');
 -- drop database injectionsproject;