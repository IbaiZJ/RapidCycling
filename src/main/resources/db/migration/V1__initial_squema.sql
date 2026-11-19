SET FOREIGN_KEY_CHECKS=0;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bike` (
  `bike_id` int NOT NULL AUTO_INCREMENT,
  `maintenance_flag` enum('FALSE','INSPECTION_REQUIRED','NONE','OUT_OF_SERVICE','REPAIR_REQUIRED','TRUE') NOT NULL,
  `status` enum('LOCK','UNLOCK') NOT NULL,
  `version` bigint NOT NULL,
  PRIMARY KEY (`bike_id`),
  CONSTRAINT `bike_chk_1` CHECK ((`maintenance_flag` between 0 and 1)),
  CONSTRAINT `bike_chk_2` CHECK ((`status` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dock` (
  `dock_id` int NOT NULL AUTO_INCREMENT,
  `dock_number` int NOT NULL,
  `bike_id` int DEFAULT NULL,
  `station_id` int NOT NULL,
  PRIMARY KEY (`dock_id`),
  UNIQUE KEY `UKjwvw4otw0it4t7n75s9weoow5` (`station_id`,`dock_number`),
  UNIQUE KEY `UK64qdmjp7ju5qlleycemihu41k` (`bike_id`),
  CONSTRAINT `FK6x6umal573mlkck3ugcxvs0tr` FOREIGN KEY (`bike_id`) REFERENCES `bike` (`bike_id`),
  CONSTRAINT `FKq1a1liok2kru2imvkx0iybpew` FOREIGN KEY (`station_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `dock_chk_1` CHECK ((`dock_number` >= 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricing_policy` (
  `pricing_policy_id` int NOT NULL AUTO_INCREMENT,
  `billing_interval_minutes` int NOT NULL,
  `daily_cap` decimal(10,2) NOT NULL,
  `included_minutes` int NOT NULL,
  `price_per_interval` decimal(10,2) NOT NULL,
  PRIMARY KEY (`pricing_policy_id`),
  CONSTRAINT `pricing_policy_chk_1` CHECK ((`billing_interval_minutes` >= 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rebalancing_suggestion` (
  `suggestion_id` bigint NOT NULL AUTO_INCREMENT,
  `bike_count` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `driver_explanation` varchar(500) DEFAULT NULL,
  `source_capacity` int NOT NULL,
  `source_occupancy` int NOT NULL,
  `target_capacity` int NOT NULL,
  `target_occupancy` int NOT NULL,
  `source_station_id` int NOT NULL,
  `target_station_id` int NOT NULL,
  PRIMARY KEY (`suggestion_id`),
  KEY `idx_suggestion_created` (`created_at`),
  KEY `FK2vfakbg2kx6tn8cd8mwsvrfq` (`source_station_id`),
  KEY `FKsu656fk6envspyy49ylgnv2kh` (`target_station_id`),
  CONSTRAINT `FK2vfakbg2kx6tn8cd8mwsvrfq` FOREIGN KEY (`source_station_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `FKsu656fk6envspyy49ylgnv2kh` FOREIGN KEY (`target_station_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `rebalancing_suggestion_chk_1` CHECK ((`bike_count` >= 1)),
  CONSTRAINT `rebalancing_suggestion_chk_2` CHECK ((`source_capacity` >= 1)),
  CONSTRAINT `rebalancing_suggestion_chk_3` CHECK ((`source_occupancy` >= 0)),
  CONSTRAINT `rebalancing_suggestion_chk_4` CHECK ((`target_capacity` >= 1)),
  CONSTRAINT `rebalancing_suggestion_chk_5` CHECK ((`target_occupancy` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ride` (
  `ride_id` int NOT NULL AUTO_INCREMENT,
  `amount_charged` decimal(10,2) DEFAULT NULL,
  `ended_at` datetime(6) DEFAULT NULL,
  `started_at` datetime(6) NOT NULL,
  `status` enum('ACTIVE','CANCELLED','COMPLETED') NOT NULL,
  `version` bigint NOT NULL,
  `bike_id` int NOT NULL,
  `destination_station_id` int DEFAULT NULL,
  `origin_station_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`ride_id`),
  KEY `FKl8b82u26acvmft5a4g0hqfg52` (`bike_id`),
  KEY `FK6amsdc9puj0uecudlt3wwxs5y` (`destination_station_id`),
  KEY `FK9avpgkgi77tcg9wcjwy3vuls0` (`origin_station_id`),
  KEY `FKlxvu7hvl0q84p60yrrxwcytlx` (`user_id`),
  CONSTRAINT `FK6amsdc9puj0uecudlt3wwxs5y` FOREIGN KEY (`destination_station_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `FK9avpgkgi77tcg9wcjwy3vuls0` FOREIGN KEY (`origin_station_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `FKl8b82u26acvmft5a4g0hqfg52` FOREIGN KEY (`bike_id`) REFERENCES `bike` (`bike_id`),
  CONSTRAINT `FKlxvu7hvl0q84p60yrrxwcytlx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `station_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  `capacity` int NOT NULL,
  `occupied_docks` int NOT NULL,
  `version` bigint NOT NULL,
  PRIMARY KEY (`station_id`),
  CONSTRAINT `station_chk_1` CHECK ((`capacity` >= 1)),
  CONSTRAINT `station_chk_2` CHECK ((`occupied_docks` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station_occupancy_snapshot` (
  `snapshot_id` bigint NOT NULL AUTO_INCREMENT,
  `capacity` int NOT NULL,
  `captured_at` datetime(6) NOT NULL,
  `occupied_docks` int NOT NULL,
  `station_id` int NOT NULL,
  PRIMARY KEY (`snapshot_id`),
  KEY `idx_snapshot_station_time` (`station_id`,`captured_at`),
  CONSTRAINT `FKlcv80gbjjiy36oplepiwwypvg` FOREIGN KEY (`station_id`) REFERENCES `station` (`station_id`),
  CONSTRAINT `station_occupancy_snapshot_chk_1` CHECK ((`capacity` >= 1)),
  CONSTRAINT `station_occupancy_snapshot_chk_2` CHECK ((`occupied_docks` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(254) NOT NULL,
  `name` varchar(80) NOT NULL,
  `password` varchar(255) NOT NULL,
  `photo` varchar(2048) DEFAULT NULL,
  `surname` varchar(80) NOT NULL,
  `username` varchar(50) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_users_username` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
SET FOREIGN_KEY_CHECKS=1;
