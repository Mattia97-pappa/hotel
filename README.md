Hotel🏨


👀 Il codice sorgente completo si trova in src, suddiviso in package chiari:

controller → gestione delle rotte

entity → entità del database

repository → interfaccia con il DB

security & config → login, autenticazione e accessi

Accedi all'app dal browser vai su http://localhost:8080

▶️ Per eseguire il progetto Clona la repository con il seguente comando su gitbash


```bash

git clone https://github.com/Mattia97-pappa/hotel.git
cd hotel

```

Per far funzionare il tutto copia le tabelle. Crea un record in AdmUser di un amministratore per accedere.  roles "admin"



```tabella adm
CREATE TABLE `admuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `roles` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AdmUser_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
```
```tabella booking

CREATE TABLE `bookings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `total_amount` decimal(38,2) DEFAULT NULL,
  `Guests_id` int(11) NOT NULL,
  `Rooms_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Bookings_Guests_idx` (`Guests_id`),
  KEY `fk_Bookings_Rooms1_idx` (`Rooms_id`),
  CONSTRAINT `fk_Bookings_Guests` FOREIGN KEY (`Guests_id`) REFERENCES `guests` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bookings_Rooms1` FOREIGN KEY (`Rooms_id`) REFERENCES `rooms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
```
```tabella guests
CREATE TABLE `guests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `cap` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthDay` date DEFAULT NULL,
  `identityCard` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
```
```tabella pay
CREATE TABLE `payments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount_paid` decimal(38,2) DEFAULT NULL,
  `payment_date` datetime DEFAULT curdate(),
  `status` enum('Paid','Unpaid','Rejected') DEFAULT NULL,
  `Bookings_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Payments_Bookings1_idx` (`Bookings_id`),
  CONSTRAINT `fk_Payments_Bookings1` FOREIGN KEY (`Bookings_id`) REFERENCES `bookings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
```
```tabella rooms
CREATE TABLE `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomNumber` varchar(255) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `is_available` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roomNumber_UNIQUE` (`roomNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;


```







🎥 Demo Videos

-
  [Watch on Vimeo](https://vimeo.com/1076051601)


  


