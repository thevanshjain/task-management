DROP SCHEMA IF EXISTS `spring-demo`;

CREATE SCHEMA `spring-demo`;

use `spring-demo`;


DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) not null,
  `status` varchar(20) ,
  `startdate` date ,
  `enddate` date ,
   PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `notes`;

CREATE TABLE `notes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(256) not NULL,
  `createdAt` date ,
  `modifiedAt` date,
  `task_id` int DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `FK_task_idx` (`task_id`),

  CONSTRAINT `FK_task` 
  FOREIGN KEY (`task_id`) 
  REFERENCES `task` (`id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION
);
insert into task values(1,"Java","Completed","2021-08-02","2021-08-04");
insert into task values(2,"Collections","Not Started","2021-08-15","2021-08-24");
insert into task values(3,"OOPS","Completed","2021-08-25","2021-08-30");

insert into notes values(10,"Conditional and Iteration","2021-08-02","2021-08-03",1);
insert into notes values(11,"Arrays and String","2021-08-04","2021-08-05",1);
insert into notes values(12,"List","2021-08-05","2021-08-06",2);
insert into notes values(13,"Set","2021-08-04","2021-08-06",2);
insert into notes values(14,"Map","2021-08-06","2021-08-07",2);
insert into notes values(15,"Inheritance","2021-08-07","2021-08-08",3);
insert into notes values(16,"Encapsulation","2021-08-10","2021-08-12",3);
insert into notes values(17,"Polymorphism","2021-08-07","2021-08-10",3);
insert into notes values(18,"Abstraction","2021-08-09","2021-08-12",3);

