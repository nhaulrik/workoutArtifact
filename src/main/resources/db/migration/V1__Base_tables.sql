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
-- Table structure for table `muscle_exercise`
--

DROP TABLE IF EXISTS `muscle_exercise`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `muscle_exercise` (
  `exercise_id` bigint(20) NOT NULL,
  `muscle_id` bigint(20) NOT NULL,
  PRIMARY KEY (`exercise_id`,`muscle_id`),
  KEY `FKmrwy4hl3oah17q7tkrft87mq5` (`muscle_id`),
  CONSTRAINT `FKlhsxiqybbk4syf9j6rm6wt9yw` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`),
  CONSTRAINT `FKmrwy4hl3oah17q7tkrft87mq5` FOREIGN KEY (`muscle_id`) REFERENCES `muscle` (`id`)
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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