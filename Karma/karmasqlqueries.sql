INSERT INTO `approval_status` VALUES (1,'pending'),(2,'approved'),(3,'declined'),(4,'completed'),(5,'incompleted'),(6,'cancelled');

INSERT INTO `category` VALUES (1,'finance','financial'),(2,'education','education'),(3,'health','health'),(4,'nec euismod','habitasse platea'),(5,'mauris laoreet','eu interdum'),(6,'magna vulputate','fusce posuere'),(7,'tortor sollicitudin','nisi nam'),(8,'vestibulum quam','curabitur gravida'),(9,'pede malesuada','accumsan felis'),(10,'posuere cubilia','nibh fusce');

INSERT INTO `help` VALUES ((1,'2018-09-28 21:45:44','Helped',1,NULL,NULL),(2,'2018-09-28 22:01:39','hg',2,NULL,1),(3,'2018-09-29 15:39:39','Helped new',4,NULL,1),(4,'2018-10-01 16:20:58','Help for need 1',5,NULL,1),(5,'2018-10-03 16:44:10','new help-helped',6,NULL,41),(16,'2018-10-03 16:45:47','help',2,NULL,5));


insert into user_check (vote_type, category,checked_need_id) values ('postive','genuiness',1);

insert into user_check (vote_type, category,checked_need_id) values ('negative','genuiness',2);
