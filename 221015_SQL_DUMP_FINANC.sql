/*
SQLyog Ultimate v8.55 
MySQL - 5.5.45 : Database - financeiro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`financeiro` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `financeiro`;

/*Table structure for table `autorizacao` */

DROP TABLE IF EXISTS `autorizacao`;

CREATE TABLE `autorizacao` (
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `autorizacao` */

insert  into `autorizacao`(`nome`) values ('ROLE_ADMIN'),('ROLE_USER');

/*Table structure for table `lancamento` */

DROP TABLE IF EXISTS `lancamento`;

CREATE TABLE `lancamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_pagamento` date DEFAULT NULL,
  `data_vencimento` date NOT NULL,
  `descricao` varchar(80) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `pessoa_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ax5wcm3fcn1xss5lkmlbt68pu` (`pessoa_id`),
  KEY `FK_leujf0uilgwr9k59thfsqs7fq` (`usuario_id`),
  CONSTRAINT `FK_leujf0uilgwr9k59thfsqs7fq` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_ax5wcm3fcn1xss5lkmlbt68pu` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `lancamento` */

insert  into `lancamento`(`id`,`data_pagamento`,`data_vencimento`,`descricao`,`tipo`,`valor`,`pessoa_id`,`usuario_id`) values (4,NULL,'2015-10-21','testes','RECEITA','1000.00',2,2),(5,NULL,'2015-10-21','testes()','RECEITA','1290.00',2,1),(6,NULL,'2015-10-21','testes','RECEITA','1.00',1,3),(7,'2015-10-22','2015-10-22','testes','RECEITA','10.00',2,0),(8,NULL,'2015-10-22','eita ','RECEITA','9990.00',4,2),(9,NULL,'2015-10-22','testes automatizados','RECEITA','1789.00',5,2);

/*Table structure for table `pessoa` */

DROP TABLE IF EXISTS `pessoa`;

CREATE TABLE `pessoa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `pessoa` */

insert  into `pessoa`(`id`,`nome`) values (1,'WWW Indústria de Alimentos'),(2,'SoftBRAX Treinamentos'),(3,'Mingau  de Milho'),(4,'BV Financeira'),(5,'katarina nogueira'),(6,'igorcs');

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `username` varchar(255) NOT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`username`,`id`),
  UNIQUE KEY `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario`(`username`,`enable`,`password`,`id`) values ('admin',1,'admin',0),('carol',1,'carol',1),('igor',1,'igor',2),('katarina',1,'katarina',3);

/*Table structure for table `usuario_autorizacao` */

DROP TABLE IF EXISTS `usuario_autorizacao`;

CREATE TABLE `usuario_autorizacao` (
  `Usuario_username` varchar(255) NOT NULL,
  `autorizacoes_nome` varchar(255) NOT NULL,
  KEY `FKD04AA31379DDB250` (`autorizacoes_nome`),
  KEY `FKD04AA3139FEF07F0` (`Usuario_username`),
  KEY `UK_cymsxar9x2quhdvudex2mwfen` (`autorizacoes_nome`,`Usuario_username`),
  KEY `autorizacoes_nome` (`autorizacoes_nome`,`Usuario_username`),
  CONSTRAINT `FKD04AA31379DDB250` FOREIGN KEY (`autorizacoes_nome`) REFERENCES `autorizacao` (`nome`),
  CONSTRAINT `FKD04AA3139FEF07F0` FOREIGN KEY (`Usuario_username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usuario_autorizacao` */

insert  into `usuario_autorizacao`(`Usuario_username`,`autorizacoes_nome`) values ('admin','ROLE_ADMIN'),('igor','ROLE_USER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
