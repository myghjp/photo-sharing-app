LOCK TABLES `accounts` WRITE;
INSERT INTO `accounts` VALUES 
(1,'ユーザ１','$2a$10$IE8In.2EYq/ZPDFaPPUqceLow5bAFomcEbkhP52h9XihJQis.TByO','user1@example.com','2026-02-19 11:15:25','2026-02-19 11:15:25')
,(2,'ユーザ２','$2a$10$ABQEFYo1kF.3iHNG1OqIIuj5zRI7akKNXM0VjkW4PP.2aWkzg6SZS','user2@example.com','2026-02-19 11:15:34','2026-02-19 11:15:34')
,(4,'ユーザ４','$2a$10$FG2ygAOFOTc8Cb7CuGrWAOxWkGnNOpG8hQiFuRHCoZw.iEzvwoJVO','user4@example.com','2026-02-19 11:15:54','2026-02-19 11:15:54')
,(5,'ユーザ５','$2a$10$fW0aV1vpB7jGoAvgbcYt9usundlQeIEu6Y1zD0jYJenli3XqGJqga','user5@example.com','2026-02-19 11:16:05','2026-02-19 11:16:05');
UNLOCK TABLES;

LOCK TABLES `groups` WRITE;
INSERT INTO `groups` VALUES 
(1,'ユーザ１が作成したグループ',1,'2026-02-19 11:16:36','2026-02-19 11:16:36')
,(2,'ユーザ４が作成したグループ',4,'2026-02-19 11:18:16','2026-02-19 11:18:16');
UNLOCK TABLES;

LOCK TABLES `groups_members` WRITE;
INSERT INTO `groups_members` VALUES 
(1,1,2,'2026-02-19 11:16:52','2026-02-19 11:16:52')
,(2,1,NULL,'2026-02-19 11:16:56','2026-02-19 11:16:56')
,(3,2,5,'2026-02-19 11:18:49','2026-02-19 11:18:49')
,(4,2,1,'2026-02-19 11:18:52','2026-02-19 11:18:52');
UNLOCK TABLES;

LOCK TABLES `comments` WRITE;
INSERT INTO `comments` VALUES 
(1,1,'ここに投稿したコメント内容が表示されます。',1,'2026-02-19 11:17:36','2026-02-19 11:17:36')
,(2,1,'自身が投稿したコメントであれば削除できます。',2,'2026-02-19 11:22:28','2026-02-19 11:22:28')
,(3,1,'メンバーがアカウントを削除するとアカウント名がunknownで表示されます。',NULL,'2026-02-19 11:26:34','2026-02-19 11:26:34')
,(4,2,'ここに投稿したコメント内容が表示されます。',4,'2026-02-19 11:44:36','2026-02-19 11:44:36')
,(5,2,'自身が投稿したコメントであれば削除できます。',5,'2026-02-19 11:45:47','2026-02-19 11:45:47');
UNLOCK TABLES;

LOCK TABLES `albums` WRITE;
INSERT INTO `albums` VALUES 
(1,1,'フリー素材なアルバム','2026-02-19 11:17:16','2026-02-19 11:17:16')
,(2,2,'別グループのアルバム','2026-02-19 11:20:20','2026-02-19 11:20:20');
UNLOCK TABLES;