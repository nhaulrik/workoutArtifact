--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `exercise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `body_part` varchar(255) DEFAULT NULL,
  `is_compound` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `muscle`
--

DROP TABLE IF EXISTS `muscle`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `muscle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `body_part` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `exercise_muscle_relation`
--

DROP TABLE IF EXISTS `exercise_muscle_relation`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `exercise_muscle_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ratio` int(11) DEFAULT NULL,
  `exercise_entity_id` bigint(20) DEFAULT NULL,
  `muscle_entity_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkxfradqvr65o99d2jcm9kf7qu` (`exercise_entity_id`),
  KEY `FKehi12yt81x1omut7w2mwkw8aa` (`muscle_entity_id`),
  CONSTRAINT `FKehi12yt81x1omut7w2mwkw8aa` FOREIGN KEY (`muscle_entity_id`) REFERENCES `muscle` (`id`),
  CONSTRAINT `FKkxfradqvr65o99d2jcm9kf7qu` FOREIGN KEY (`exercise_entity_id`) REFERENCES `exercise` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date_time` datetime(6) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `programme` varchar(255) DEFAULT NULL,
  `split_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `workoutset`
--

DROP TABLE IF EXISTS `workoutset`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `workoutset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `repetition_maximum` int(11) DEFAULT NULL,
  `repetitions` int(11) DEFAULT NULL,
  `set_number` int(11) DEFAULT NULL,
  `single` bit(1) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `exercise_entity_id` bigint(20) DEFAULT NULL,
  `session_entity_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhte4id6wwyx3y6nhojt9aq9rt` (`exercise_entity_id`),
  KEY `FK7j0d2f3j3y94bnpv3po9rtmff` (`session_entity_id`),
  CONSTRAINT `FK7j0d2f3j3y94bnpv3po9rtmff` FOREIGN KEY (`session_entity_id`) REFERENCES `session` (`id`),
  CONSTRAINT `FKhte4id6wwyx3y6nhojt9aq9rt` FOREIGN KEY (`exercise_entity_id`) REFERENCES `exercise` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;