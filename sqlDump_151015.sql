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
  PRIMARY KEY (`id`),
  KEY `FK_ax5wcm3fcn1xss5lkmlbt68pu` (`pessoa_id`),
  CONSTRAINT `FK_ax5wcm3fcn1xss5lkmlbt68pu` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `lancamento` */

insert  into `lancamento`(`id`,`data_pagamento`,`data_vencimento`,`descricao`,`tipo`,`valor`,`pessoa_id`) values (25,NULL,'2015-11-05','empréstimo de $400 (parcela 2) Jhon Jhon','DESPESA','140.00',14),(26,NULL,'2015-11-08','despesas cartão','DESPESA','700.00',14),(28,NULL,'2015-11-10','taxa do condomínio','DESPESA','172.00',14),(30,NULL,'2015-11-10','Internet','DESPESA','90.00',14);

/*Table structure for table `pessoa` */

DROP TABLE IF EXISTS `pessoa`;

CREATE TABLE `pessoa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `pessoa` */

insert  into `pessoa`(`id`,`nome`) values (1,'WWW Indústria de Alimentos'),(2,'SoftBRAX Treinamentos'),(3,'Bv Financeira _ Carro'),(7,'Empréstimo Jonh John'),(8,'Cartão Hiper'),(9,'Cartão BB'),(10,'Zilda'),(11,'testes_'),(12,'Condomínio'),(13,'Luz'),(14,'IGOR CS');

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `username` varchar(255) NOT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usuario` */

insert  into `usuario`(`username`,`enable`,`password`) values ('admin',1,'admin'),('igor',1,'igor');

/*Table structure for table `usuario_autorizacao` */

DROP TABLE IF EXISTS `usuario_autorizacao`;

CREATE TABLE `usuario_autorizacao` (
  `Usuario_username` varchar(255) NOT NULL,
  `autorizacoes_nome` varchar(255) NOT NULL,
  UNIQUE KEY `autorizacoes_nome` (`autorizacoes_nome`),
  UNIQUE KEY `UK_cymsxar9x2quhdvudex2mwfen` (`autorizacoes_nome`),
  KEY `FKD04AA31379DDB250` (`autorizacoes_nome`),
  KEY `FKD04AA3139FEF07F0` (`Usuario_username`),
  CONSTRAINT `FKD04AA31379DDB250` FOREIGN KEY (`autorizacoes_nome`) REFERENCES `autorizacao` (`nome`),
  CONSTRAINT `FKD04AA3139FEF07F0` FOREIGN KEY (`Usuario_username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usuario_autorizacao` */

insert  into `usuario_autorizacao`(`Usuario_username`,`autorizacoes_nome`) values ('admin','ROLE_ADMIN'),('igor','ROLE_USER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
